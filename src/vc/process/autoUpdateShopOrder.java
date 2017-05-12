
package vc.process;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.tempuri.BrandIdAndProductTypeId;
import org.tempuri.GetTemplateForSkuSoap;


import vc.VCSMS;
import vc.WSConfig;
import vc.database.DBTool;
import vc.util.BSku;

public class autoUpdateShopOrder extends Thread
{

    public autoUpdateShopOrder()
    {

    }

	public static void main(String[] args)
	{
//		try {
//			BrandIdAndProductTypeId teamplate=new GetTemplateForSkuSoapProxy().getBrandIdAndProductTypeIdBySku("00226282");
//			System.out.println(teamplate.getBrandId());
//			System.out.println(teamplate.getProductTypeID());
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		BrandIdAndProductTypeId teamplate=getBrandIdAndProductTypeIdBySku("00226282");
		System.out.println(teamplate.getBrandId());
		System.out.println(teamplate.getProductTypeID());	
		
	}
    
    public void run()
    {
    	while(VCSMS.isRunning()){
    		System.out.println("runing... autoUpdateShopOrder");
    			Calendar now = Calendar.getInstance();
                int hour=now.get(Calendar.HOUR_OF_DAY);
                if(hour==WSConfig.autoUpdateHour){
                	
            		DBTool db=new DBTool();
            		try {            			
            			ArrayList beans=new ArrayList();
            			int time=WSConfig.autoNumberDate;
            			beans=db.getAllSku(time);

            			for (int i = 0; i < beans.size(); i++) {
            				BSku beanSku=(BSku)beans.get(i);
            				BrandIdAndProductTypeId teamplate=getBrandIdAndProductTypeIdBySku(beanSku.getSku());
//            				BrandIdAndProductTypeId teamplate=new GetTemplateForSkuSoapProxy().getBrandIdAndProductTypeIdBySku("00226282");

            				System.out.println(teamplate.getBrandId());
            				System.out.println(teamplate.getProductTypeID());
            				//sp co huong dan thi moi gui email                          	
            					db.updateShopOrderNganhHang(teamplate.getBrandId()+"",teamplate.getProductTypeID()+"",beanSku.getSku(),time);        					
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
				sleep(10*60*1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}

    }

	public static BrandIdAndProductTypeId getBrandIdAndProductTypeIdBySku(String sku){
		BrandIdAndProductTypeId bean=new BrandIdAndProductTypeId();
	
	URL url;
	try {
		url = new URL(WSConfig.apiTeamplateUrl);
       QName qname = new QName("http://tempuri.org/", "GetTemplateForSku");

        Service service = Service.create(url, qname);

        GetTemplateForSkuSoap hello = service.getPort(GetTemplateForSkuSoap.class);
        bean=hello.getBrandIdAndProductTypeIdBySku(sku);
        

	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(e.getMessage());
	}


	return bean;
	}


}
