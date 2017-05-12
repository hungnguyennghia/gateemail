
package vc.process;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import vc.VCSMS;
import vc.WSConfig;
import vc.util.BOrder;

public class requestOrder extends Thread
{

    public requestOrder()
    {

    }



    
    public void run()
    {
    	ExecutorService executor = Executors.newFixedThreadPool(WSConfig.threadpoolsendemail);

    	while(VCSMS.isRunning()){
    		try {
        		BOrder bean = (BOrder)VCSMS.getRequestQueueOrder().dequeue();
    			Runnable worker = new RequestEmailValid(bean);
    			executor.execute(worker);
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
