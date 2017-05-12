
package vc.process;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.tempuri.GetTemplateForSkuSoap;
import org.tempuri.TemplateMailItem;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import vc.VCSMS;
import vc.WSConfig;
import vc.database.DBException;
import vc.database.DBTool;
import vc.database.JedisConnectionPool;
import vc.util.BOrder;
import vc.util.BSku;
import vc.util.BTheadAuto;
import vc.util.DateProc;
import vc.util.beanBR;
import vc.util.logger.Logger;

public class autoSend extends Thread
{

    public autoSend()
    {

    }

 
    
    public void run()
    {
    	while(VCSMS.isRunning()){
    			Calendar now = Calendar.getInstance();
                int hour=now.get(Calendar.HOUR_OF_DAY);
                if(hour==WSConfig.autoHour){
                    Logger.getInstance().logInfo("autoSend runing...",null, "[autoSend]");
                	
            		DBTool db=new DBTool();
            		ArrayList beansAuto=new ArrayList();
            		beansAuto=db.getAllTheadAuto(WSConfig.autoSendTypeTeamp);
            		for (int ia = 0; ia < beansAuto.size(); ia++) {
            			BTheadAuto beanT=(BTheadAuto)beansAuto.get(ia);
            			Logger.getInstance().logInfo("autoSend start...D-"+beanT.getNumber_date(),null, "[autoSend]");
            			System.out.println("autoSend start...D-"+beanT.getNumber_date());
            			//tao report auto
            			String id=UUID.randomUUID().toString();
            			try {
							db.insert2ReportAuto(id,beanT.getType_Teamp());
						} catch (DBException e2) {
							// TODO Auto-generated catch block
	            			Logger.getInstance().logError("autoSend start...D-"+beanT.getNumber_date(),e2, "[autoSend]");
						}
                		try {            			
                			ArrayList beans=new ArrayList();
                			beans=db.getAllSku(beanT.getNumber_date());
                 			Logger.getInstance().logInfo("count All Sku"+beans.size(),null, "[autoSend]");

                			for (int i = 0; i < beans.size(); i++) {
                				BSku beanSku=(BSku)beans.get(i);
                				TemplateMailItem teamplate=getTeamplate(beanSku.getSku(),beanT.getType_Teamp());
//sp co huong dan thi moi gui email
                				if(teamplate.getStatus()==1){
                                   	

                    				ArrayList beansOrder=new ArrayList();
                    				beansOrder=db.getAllOrderBySku(beanT.getNumber_date(), beanSku.getSku());
                    				for (int j = 0; j < beansOrder.size(); j++) {
                    					BOrder beanOrder=new BOrder();
                    					beanOrder=(BOrder)beansOrder.get(j);
                                        beanBR beanS = new beanBR();
                                        try {
                                        	String tracking=WSConfig.htmlTracking;
                                        	beanS.setCamp_id(id);
                                        	beanS.setMessage_ids(UUID.randomUUID().toString());
                                        	beanS.setFrom(WSConfig.emailCskh);
                                        	beanS.setTo(beanOrder.getEmail());
                                        	beanS.setCc("");
                                        	beanS.setBcc("");
                                        	beanS.setOrderid(beanOrder.getOrderNumber());
                                        	beanS.setEmail_id(UUID.randomUUID().toString());
                                        	
                                        	String subjects=teamplate.getSubject().replace("%Order.OrderNumber%", beanOrder.getOrderNumber())
                                        			.replace("%Order.CustomerFullName%", beanOrder.getCustomerFullName().toUpperCase()).replace("%Order.Product(s)%", beanOrder.getProductName())
                                        			.replace("%Order.BuyDate%", beanOrder.getTgMuaHang());
                                        	beanS.setSubject(subjects);//co the phai replate
                                        	String contents=teamplate.getBodyContent().replace("%Order.OrderNumber%", beanOrder.getOrderNumber())
                                        			.replace("%Order.CustomerFullName%", beanOrder.getCustomerFullName().toUpperCase()).replace("%Order.Product(s)%", beanOrder.getProductName())
                                        			.replace("%Order.BuyDate%", beanOrder.getTgMuaHang()).replace("%Order.urlReject%",WSConfig.urlReject+beanOrder.getEmail())
                                        			.replace("%Url.email_id%", beanS.getEmail_id()).replace("%Url.utm_content%",removeAccent(beanOrder.getProductName()).replace(" ", ""))
                                        			.replace("%Url.utm_campaign%", WSConfig.tempName[beanT.getType_Teamp()-1]);
                                        	//gan code html tracking
                                        	tracking=tracking.replace("#id", beanS.getEmail_id());
                                        	tracking=tracking.replace("#email", beanS.getTo());
                                        	tracking=tracking.replace("#orderid", beanS.getOrderid());
                                        	tracking=tracking.replace("#teamplate", teamplate.getTeamplateName());
                                        	tracking=tracking.replace("#camp_id", beanS.getCamp_id());
                                        	beanS.setContent(contents+tracking);//gan tracking email
                                        	beanS.setTeamplate(teamplate.getTeamplateName());
                                        	beanS.setAccount("auto");
                                        	beanS.setUrlpush("");
                                        	beanS.setEmailValid(1);
                                 			Logger.getInstance().logInfo("cgetRequestBRQueue "+beanS.getEmail_id(),null, "[autoSend]");

                                               VCSMS.getRequestBRQueue().enqueue(beanS);
 //                                              VCSMS.getRequestSendMeQueue().enqueue(beanS);
                                        } catch (Exception e) {
                               			// TODO: handle exception
                                        }
                                    	VCSMS.getLogFileQueue().enqueue(beanS);
                                    	VCSMS.getLogDBQueue().enqueue(beanS);
            						}
                				}
 
            					
            				}
                			

                           
            				
            			} catch (Exception e) {
            				// TODO: handle exception
            				try {
            					sleep(100);
            				} catch (InterruptedException e1) {
            					// TODO Auto-generated catch block
            					e1.printStackTrace();
            				}
            			}
                		//ket thuc report auto
            			try {
							db.updateReportAutoStatus(id);
						} catch (DBException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
        			}
           		 	try {
						sleep(60*60*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		

            		//end check time
                }



            	

    		//sleep recall
			try {
				sleep(60*1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}

    }

	public static void main(String[] args)
	{
		System.err.println(DateProc.getYYYYMMDDHHMMSSString(DateProc.createTimestamp()) );
	  //	Jedis jedis = new Jedis("localhost", 6379);
	  	String key="hungnguyennghia";
	  	try {
			WSConfig.loadPropertiesConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	
	  	System.out.println(WSConfig.googlemailSubject);
	//  	if(msg.getFrom()[0].toString().contains("mailer-daemon@googlemail.com")){
     //(msg.getFrom()[0].toString().contains(WSConfig.googlemailEmailSend)&&subject.contains(WSConfig.googlemailSubject)){

        	   
        	   
	  	try {
	  		Jedis jedis = new JedisConnectionPool().getJedisConnection();
	  		
	  		
//
//            JedisPoolConfig config = new JedisPoolConfig(); 
//            config.setMaxTotal(10); 
//		    config.setMaxIdle(5); 
//		    config.setMinIdle(1); 
//		    config.setMaxWaitMillis(3000); 		     
//		    JedisPool jedisPool = new JedisPool(config,"localhost", 6379); 
	  		
		  	for (int i = 0; i < 1000000; i++) {
//			  	Jedis jedis = null;//new Jedis(WSConfig.redisHost, WSConfig.redisPort); 
//				   try { 
//				  		 jedis= jedisPool.getResource(); 
					  		key="hungnguyennghia"+i;
						  	if(!jedis.exists(key)){
							  	jedis.set(key, "1");
							  	jedis.expire(key, 30);
//							  	System.out.println("set key...");
						  	}else{
//						  		jedis.get(key);
						  		System.out.println(jedis.get(key));
//						  		System.out.println("exists key...");
						  	}
					  		new JedisConnectionPool().close(jedis);
//					   } catch (JedisConnectionException e) { 
//						   jedisPool.returnBrokenResource(jedis); 
//						   jedisPool = null; 
//					   } finally { 
//					    if (jedis != null)  
//					     jedisPool.returnResource(jedis); 
//					    jedis = null; 
//					   } 
				   

//			  	jedis.close();

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	  	System.err.println(DateProc.getYYYYMMDDHHMMSSString(DateProc.createTimestamp()) );

	}
	
	public static TemplateMailItem getTeamplate(String sku,int typeTeamp){
	TemplateMailItem bean=new TemplateMailItem();
	
	URL url;
	try {
		url = new URL(WSConfig.apiTeamplateUrl);
       QName qname = new QName("http://tempuri.org/", "GetTemplateForSku");

        Service service = Service.create(url, qname);

        GetTemplateForSkuSoap hello = service.getPort(GetTemplateForSkuSoap.class);
        bean=hello.getTemplateEmail(WSConfig.apiTeamplateUser, WSConfig.apiTeamplatePass, sku, typeTeamp);
        

	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	return bean;
	}
	 public static String removeAccent(String s) {
		  
		  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		  return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
		 }

}
