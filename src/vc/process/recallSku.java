
package vc.process;

import java.util.ArrayList;
import java.util.UUID;

import org.tempuri.TemplateMailItem;

import vc.VCSMS;
import vc.WSConfig;
import vc.database.DBTool;
import vc.util.BOrder;
import vc.util.BSku;
import vc.util.beanBR;

public class recallSku extends Thread
{

    public recallSku()
    {

    }



    
    public void run()
    {
    	while(VCSMS.isRunning()){
    		try {
    			DBTool db=new DBTool();
        		BSku beanSku = (BSku)VCSMS.getSkuQueue().dequeue();

    				TemplateMailItem teamplate=autoSend.getTeamplate(beanSku.getSku(),beanSku.getTypeTeamp());
					if(teamplate.getStatus()==1){
        				String tracking=WSConfig.htmlTracking;
        		        
        				ArrayList beansOrder=new ArrayList();
        				beansOrder=db.getAllOrderBySku(beanSku.getDateD(), beanSku.getSku());
        				for (int j = 0; j < beansOrder.size(); j++) {
        					BOrder beanOrder=new BOrder();
        					beanOrder=(BOrder)beansOrder.get(j);
                            beanBR beanS = new beanBR();
                            try {
                            	beanS.setMessage_ids(UUID.randomUUID().toString());
                            	beanS.setFrom(WSConfig.emailReport);
                            	beanS.setTo(beanOrder.getEmail());
                            	beanS.setCc("");
                            	beanS.setBcc("");
                            	beanS.setOrderid(beanOrder.getOrderNumber());
                            	beanS.setEmail_id(UUID.randomUUID().toString());

                            	String subjects=teamplate.getSubject().replace("%Order.OrderNumber%", beanOrder.getOrderNumber())
                            			.replace("%Order.CustomerFullName%", beanOrder.getCustomerFullName()).replace("%Order.Product(s)%", beanOrder.getProductName());
                            	beanS.setSubject(subjects);//co the phai replate
                            	String contents=teamplate.getBodyContent().replace("%Order.OrderNumber%", beanOrder.getOrderNumber())
                            			.replace("%Order.CustomerFullName%", beanOrder.getCustomerFullName()).replace("%Order.Product(s)%", beanOrder.getProductName());
                            	
                            	//gan code html tracking
                            	tracking=tracking.replace("#id", beanS.getEmail_id());
                            	tracking=tracking.replace("#email", beanS.getTo());
                            	tracking=tracking.replace("#orderid", beanS.getOrderid());
                            	tracking=tracking.replace("#teamplate", teamplate.getTeamplateName());
                            	beanS.setContent(contents+tracking);//gan tracking email
                            	beanS.setTeamplate(teamplate.getTeamplateName());
                            	beanS.setAccount("auto");
                            	beanS.setUrlpush("");
                                   VCSMS.getRequestBRQueue().enqueue(beanS);
                            } catch (Exception e) {
                   			// TODO: handle exception
                            }
                        	VCSMS.getLogFileQueue().enqueue(beanS);
                        	VCSMS.getLogDBQueue().enqueue(beanS);
						}
					}else{
						//queue de goi lai sau
						VCSMS.getSkuQueue().enqueue(beanSku);
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
