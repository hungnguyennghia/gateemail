// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PLoggerFile.java

package vc.util.logger;

import vc.VCSMS;
import vc.common.LoggerConfig;
import vc.util.beanBR;

// Referenced classes of package vc.util.logger:
//            FileLogger

public class PLoggerFile extends Thread
{

    public static int getMOCounter()
    {
        return moCounter;
    }

    public PLoggerFile()
    {
        fileLogger = null;
        fileLogger = new FileLogger();
    }

    public void run()
    {
        	while(VCSMS.isRunning()){
                try
                {
                    beanBR mo = (beanBR)VCSMS.getLogFileQueue().dequeue();
                    if(LoggerConfig.ENABLE_MO_MT_LOG_ALL == 1)
                    {
                        fileLogger.logMO(mo);
                    }
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

    private FileLogger fileLogger;
    private static int moCounter = 0;

}
