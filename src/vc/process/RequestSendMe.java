
package vc.process;


import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import fpt.sendemail.SendEmail;
import vc.VCSMS;
import vc.WSConfig;
import vc.util.beanBR;

public class RequestSendMe extends Thread
{

    public RequestSendMe()
    {

    }

    private void processRequest(beanBR beanS)
    {
       	URL url;
		try {
			url = new URL(WSConfig.urlSendMe);
	        QName qname = new QName("http://sendEmail.fpt/", "RSendSMSService");
	        Service service = Service.create(url, qname);
	        SendEmail hello = service.getPort(SendEmail.class);
	   		hello.sendEmailFull(beanS.getFrom(), beanS.getTo(), "", "",beanS.getEmail_id(), beanS.getSubject(), beanS.getContent(), beanS.getOrderid(),beanS.getTeamplate(),"", beanS.getCamp_id(),"autocamp", "123Hungnn321");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[RequestSendMe]"+ e.getMessage());
			VCSMS.getRequestSendMeQueue().enqueue(beanS);
		} catch (Exception e) {
			System.out.println("[RequestSendMe]"+ e.getMessage());
			VCSMS.getRequestSendMeQueue().enqueue(beanS);
			
		}


    }

    public void run()
    {
    	while(VCSMS.isRunning()){
    		try {
        		beanBR bean = (beanBR)VCSMS.getRequestSendMeQueue().dequeue();
        		//if(bean.getMessage_ids().length()>2){
        		if(bean.getMessage_ids()!=null && !bean.getMessage_ids().isEmpty()){
        			System.out.println("(RequestSendMe) Send email to Me...");
                    processRequest(bean);  
        		}else{
        			sleep(100);
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
    	}

    }



 }
