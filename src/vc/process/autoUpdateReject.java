
package vc.process;
import vc.VCSMS;
import vc.WSConfig;
import vc.util.logger.Logger;

public class autoUpdateReject extends Thread
{

    public autoUpdateReject()
    {

    }

 
    
    public void run()
    {
    	while(VCSMS.isRunning()&&WSConfig.enableUpdateReject==1){
                    Logger.getInstance().logInfo("autoUpdateReject runing...",null, "[autoUpdateReject]");
                    
                    loadEmailFromDB.insertEmailRejectToRedis();

           		 	try {
						sleep(5*60*60*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
