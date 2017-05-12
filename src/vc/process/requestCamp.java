
package vc.process;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import redis.clients.jedis.Jedis;
import vc.VCSMS;
import vc.WSConfig;
import vc.database.DBTool;
import vc.util.BCamp;
import vc.util.BOrder;
import vc.util.beanBR;
import vc.util.logger.Logger;

public class requestCamp extends Thread
{

    public requestCamp()
    {

    }



    
    public void run()
    {
    	while(VCSMS.isRunning()){
    		try {
    			DBTool db=new DBTool();
        		BCamp bean = (BCamp)VCSMS.getRequestQueueCamp().dequeue();
        		
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                cal.setTime(sdf.parse(bean.getTgGui()));// all done                
                long timeCamp=cal.getTimeInMillis();

                Calendar c = Calendar.getInstance();
                long timeNow=c.getTimeInMillis();
                if(bean.getSended()==1){//chua update DB can phai update DB
                	//update trang thai camp
                	if(!db.updateCampStatus(bean.getRequestID())){
                		bean.setSended(1);//chua duoc update DB
                		VCSMS.getRequestQueueCamp().enqueue(bean);
                	}
                }else{
    	        	Jedis jedis = new Jedis(WSConfig.redisHost, WSConfig.redisPort);
    	        	if(!WSConfig.redisAuth.equals(""))jedis.auth(WSConfig.redisAuth);
    	        	boolean checkUpdate=false;
    	        	try {
        	        	if(jedis.get(bean.getRequestID()).equals("UPDATE")){
            	        	//neu update thi lay tu db
        	        		bean=db.getCamp_ByID(bean.getRequestID());
        	        		//jedis.del(bean.getRequestID());
        	        	}else if(jedis.get(bean.getRequestID()).equals("DELETE")){
        	        		boolean deleteCamp=db.deleteCamp_byID(bean.getRequestID());    
        	        		jedis.del(bean.getRequestID());
        	        		jedis.close();
        	        		return;
        	        	}
					} catch (Exception e) {
						// TODO: handle exception
					}

    	        	
                    if (timeCamp<=timeNow) {
            			Logger.getInstance().logInfo("requestCamp Den gio chay camp..."+timeNow+"---"+timeCamp,null, "[requestCamp]");

                    	System.out.println("(requestCamp) Den gio chay camp..."+timeNow+"---"+timeCamp);
                    	//get all data email by camp
                    	bean=db.getCountAllEmailByCamp(bean);

                    	int ii = 0;
                    	  while(ii < bean.getCountRows())
                    	     {
                    		  VCSMS.getLogRuningQueueCamp().enqueue(bean);//log camp runing...
                    	  //     System.out.println("ii is : " + ii);
		                           	ArrayList listEmail=db.getAllEmailByCamp(bean);
		                        	for (int i = 0; i < listEmail.size(); i++) {
		                        		BOrder beanOrder= (BOrder)listEmail.get(i);
		                                beanBR beanS = new beanBR();
		                                try {
		                                	String tracking=WSConfig.htmlTracking;
		                                	beanS.setMessage_ids(UUID.randomUUID().toString());
		                                	beanS.setFrom(WSConfig.emailCskh);
		                                	beanS.setTo(beanOrder.getEmail());
		                                	beanS.setCc("");
		                                	beanS.setBcc("");
		                                	beanS.setOrderid(beanOrder.getOrderNumber());
		                                	beanS.setEmail_id(UUID.randomUUID().toString());
		
		                                	String subjects=bean.getSubject().replace("%Order.OrderNumber%", beanOrder.getOrderNumber())
		                                			.replace("%Order.CustomerFullName%", beanOrder.getCustomerFullName().toUpperCase()).replace("%Order.Product(s)%", beanOrder.getProductName());
		                                	beanS.setSubject(subjects);//co the phai replate
		                                	String contents=bean.getContent().replace("%Order.OrderNumber%", beanOrder.getOrderNumber())
		                                			.replace("%Order.CustomerFullName%", beanOrder.getCustomerFullName().toUpperCase()).replace("%Order.Product(s)%", beanOrder.getProductName())
		                                			.replace("%Order.urlReject%",WSConfig.urlReject+beanOrder.getEmail())
		                                            .replace("%Url.email_id%", beanS.getEmail_id()).replace("%Url.camp_id%", bean.getRequestID())
		                                            .replace("%Url.utm_campaign%",autoSend.removeAccent(beanOrder.getProductName()).replace(" ", ""));
		                                			
		                                	
		                                	//gan code html tracking
		                                	tracking=tracking.replace("#id", beanS.getEmail_id());
		                                	tracking=tracking.replace("#email", beanS.getTo());
		                                	tracking=tracking.replace("#orderid", beanS.getOrderid());
		                                	tracking=tracking.replace("#teamplate", bean.getTeamplate());
		                                	tracking=tracking.replace("#camp_id", bean.getRequestID());
		                                	beanS.setContent(contents+tracking);//gan tracking email
		                                	beanS.setTeamplate(bean.getTeamplate());
		                                	beanS.setAccount("auto");
		                                	beanS.setUrlpush("");
		                                	beanS.setCamp_id(bean.getRequestID());
		                                	beanS.setEmailValid(1);
		                                	beanS.setResult(1);
		                      VCSMS.getRequestBRQueue().enqueue(beanS);
		                               //call send email       
		                           //     	VCSMS.getRequestSendMeQueue().enqueue(beanS);
		
		                                } catch (Exception e) {
		                       			// TODO: handle exception
		                                }
		                            	VCSMS.getLogFileQueue().enqueue(beanS);
		                            	VCSMS.getLogDBQueue().enqueue(beanS);
		    						}
		                        	bean.setOffset(bean.getOffset()+WSConfig.campLimit);
                    	       ii=ii+WSConfig.campLimit;
                    	     }

                    	//update trang thai camp
                    	if(!db.updateCampStatus(bean.getRequestID())){
                    		bean.setSended(1);//chua duoc update DB
                    		//VCSMS.getRequestQueueCamp().enqueue(bean);
                    	}
                    	
                    }else{
            			Logger.getInstance().logInfo("requestCamp Chua den gio chay camp..."+timeNow+"---"+timeCamp,null, "[requestCamp]");

                    	System.out.println("(requestCamp) Chua den gio chay camp..."+timeNow+"---"+timeCamp);
                    	VCSMS.getRequestQueueCamp().enqueue(bean);
                    }
                }

                sleep(100);
				
			} catch (Exception e) {
				// TODO: handle exception
				try {
					sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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


}
