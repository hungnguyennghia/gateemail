// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PLoggerDB.java

package vc.util.logger;

import vc.VCSMS;
import vc.database.*;
import vc.util.beanBR;

// Referenced classes of package vc.util.logger:
//            FileLogger

public class PLoggerDB extends Thread
{

    public PLoggerDB()
    {
        dbTool = null;
        fileLogger = null;
        dbTool = new DBTool();
        fileLogger = new FileLogger();
    }

    public void run()
    {
        
        while(VCSMS.isRunning()){
        	beanBR mo=null;
            try
            {
                mo = (beanBR)VCSMS.getLogDBQueue().dequeue();
                boolean add2MOlogResult = false;
                if(DBPool.isConnecting()){
                	 if(mo.getContent()!=null){
                         add2MOlogResult = dbTool.insert2BR(mo);
     	                if(!add2MOlogResult)
     	                    fileLogger.logMO_ERR(mo);
                	 }
                	
                }else{
                	VCSMS.getLogDBQueue().enqueue(mo);
                }
                Logger.getInstance().logInfo((new StringBuilder("[PLoggerDB]")).append(mo.getMessage_ids()).append("|").append(mo.getTo()).toString(),null, "==>"+add2MOlogResult);
            }
            catch(DBException ex)
            {
                Logger.getInstance().logInfo((new StringBuilder("[PLoggerDB]")).append(mo.getResult()).toString(),ex, "[PLoggerDB]");

                System.out.println((new StringBuilder("[PLoggerDB]")).append(ex.getMessage()).toString());
                fileLogger.logMO_ERR(mo);
            }
            catch(Exception exception) { 
                Logger.getInstance().logInfo((new StringBuilder("[PLoggerDB]")).append(mo.getResult()).toString(),exception, "[PLoggerDB]");

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
    private FileLogger fileLogger;
}
