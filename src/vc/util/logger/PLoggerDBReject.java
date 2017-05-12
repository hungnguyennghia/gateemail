package vc.util.logger;

import vc.VCSMS;
import vc.database.*;
import vc.util.BReject;

public class PLoggerDBReject extends Thread
{

    public PLoggerDBReject()
    {
        dbTool = null;
        dbTool = new DBTool();
    }

    public void run()
    {
        
        while(VCSMS.isRunning()){
        	BReject mo=null;
            try
            {
                mo = (BReject)VCSMS.getLogDBQueueReject().dequeue();
                Logger.getInstance().logInfo((new StringBuilder("[PLoggerDBReject]")).append(mo.getEmail()).toString(),null, "[PLoggerDBReject]");

                System.out.println((new StringBuilder("[PLoggerDBReject]")).append(mo.getEmail()));
                boolean add2MOlogResult = false;
                if(DBPool.isConnecting())//DB duoc ket noi va ban ghi thanh cong

                        add2MOlogResult = dbTool.insert2Reject(mo);

                if(!add2MOlogResult){
                	VCSMS.getLogDBQueueReject().enqueue(mo);
                }
            }
            catch(DBException ex)
            {
                Logger.getInstance().logError((new StringBuilder("[PLoggerDBReject]")).append(ex.getMessage()).toString(),ex, "[PLoggerDBReject]");

                System.out.println((new StringBuilder("[PLoggerDBReject]")).append(ex.getMessage()).toString());
            }
            catch(Exception exception) { 
            	try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        } 
    }


    private DBTool dbTool;
}
