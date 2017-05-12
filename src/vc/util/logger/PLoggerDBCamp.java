package vc.util.logger;

import vc.VCSMS;
import vc.database.*;
import vc.util.BCamp;

public class PLoggerDBCamp extends Thread
{

    public PLoggerDBCamp()
    {
        dbTool = null;
        dbTool = new DBTool();
    }

    public void run()
    {
        
        while(VCSMS.isRunning()){
        	BCamp mo=null;
            try
            {
                mo = (BCamp)VCSMS.getLogDBQueueCamp().dequeue();
                Logger.getInstance().logInfo((new StringBuilder("[PLoggerDBCamp]")).append(mo.getRequestID()).toString(),null, "[PLoggerDBCamp]");

                System.out.println((new StringBuilder("[PLoggerDBCamp]")).append(mo.getRequestID()));
                boolean add2MOlogResult = false;
                if(DBPool.isConnecting())//DB duoc ket noi va ban ghi thanh cong
                	if(mo.getSended()==2){
                        add2MOlogResult = dbTool.update2Camp(mo);
                	}else if(mo.getSended()==-1){
                		return;
                	}else{
                        add2MOlogResult = dbTool.insert2Camp(mo);
                	}
                if(!add2MOlogResult){
                	VCSMS.getLogDBQueueCamp().enqueue(mo);
                }
            }
            catch(DBException ex)
            {
                Logger.getInstance().logError((new StringBuilder("[PLoggerDBCamp]")).append(ex.getMessage()).toString(),ex, "[PLoggerDBCamp]");

                System.out.println((new StringBuilder("[PLoggerDBCamp]")).append(ex.getMessage()).toString());
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
