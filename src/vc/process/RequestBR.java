
package vc.process;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import vc.VCSMS;
import vc.WSConfig;
import vc.util.beanBR;

public class RequestBR extends Thread
{

    public RequestBR()
    {
    }

  

    public void run()
    {
    	ExecutorService executor = Executors.newFixedThreadPool(WSConfig.threadpoolsendemail);

    	while(VCSMS.isRunning()){
    		try {
        		beanBR bean = (beanBR)VCSMS.getRequestBRQueue().dequeue();
        		//if(bean.getMessage_ids().length()>2){
        		if(bean.getMessage_ids()!=null && !bean.getMessage_ids().isEmpty()){
        			System.out.println("(RequestBR) Send email to AMZ...");
        			Runnable worker = new RequestCallSend(bean);
        			executor.execute(worker);
                   // processRequest(bean);  
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
