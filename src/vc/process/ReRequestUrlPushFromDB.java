
package vc.process;

import java.util.ArrayList;

import vc.VCSMS;
import vc.WSConfig;
import vc.database.DBTool;
import vc.util.BUrlPush;
import vc.util.beanBR;

public class ReRequestUrlPushFromDB extends Thread
{

    public ReRequestUrlPushFromDB()
    {

    }

    private void processGetUrlPushErrFromDB() throws Exception
    {

    	DBTool db=new DBTool();
    	ArrayList beans=new ArrayList();
    	beans= db.getAllRecordUrlPush();
    	for (int i = 0; i < beans.size(); i++) {
    		BUrlPush bean=(BUrlPush)beans.get(i);
            requestUrlPush.sendPost(bean);
		}

    }
    
    public void run()
    {
    	while(VCSMS.isRunning()){
    		if(WSConfig.enableRepushStatus==1){
        		System.out.println("ReRequestUrlPushFromDB...");
        		try {
        			processGetUrlPushErrFromDB();
    				
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
    				sleep(5*60*1000);
    			} catch (InterruptedException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    		}else{
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
