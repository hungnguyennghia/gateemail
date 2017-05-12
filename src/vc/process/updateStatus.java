package vc.process;

import java.util.ArrayList;

import vc.VCSMS;
import vc.database.DBException;
import vc.database.DBTool;
import vc.util.BUrlPush;
import vc.util.logger.Logger;


public class updateStatus extends Thread
{

    public updateStatus()
    {

    }

    private void processUpdate(BUrlPush bean)
    {

    	DBTool db=new DBTool();

    		try {
    			if(bean.getResend()<2){
    				boolean update=db.updateUrlpushStatus(bean);
    				Logger.getInstance().logInfo(bean.getMessage_ids() +"==>"+update,null, "[updateStatus]");
    				if(!update){
    					bean.setResend(bean.getResend()+1);
    					VCSMS.getUpdateStatusQueue().enqueue(bean);
    				}
    			}

			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

    }
    private void processUpdates(ArrayList beans)
    {

    	DBTool db=new DBTool();

    		try {
    				boolean update=db.updateUrlpushStatusMulti(beans);
    				Logger.getInstance().logInfo(" multi==>"+update,null, "[updateStatus]");

			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

    }   
    public void run()
    {
    	while(VCSMS.isRunning()){
    		
    		try {
    			if(VCSMS.getUpdateStatusQueue().size()>100){
        			ArrayList beans=new ArrayList();  
					for (int i = 0; i < 100; i++) {
						BUrlPush bean = (BUrlPush)VCSMS.getUpdateStatusQueue().dequeue();
						beans.add(bean);
					}
					processUpdates(beans);
    			}else{
        			BUrlPush bean = (BUrlPush)VCSMS.getUpdateStatusQueue().dequeue();

        			if(bean.getMessage_ids()!=null && !bean.getMessage_ids().isEmpty()){
        				 
                        processUpdate(bean);
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
    	}

    }



}
