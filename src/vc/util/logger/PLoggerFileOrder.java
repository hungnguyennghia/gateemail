// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PLoggerFile.java

package vc.util.logger;

import vc.VCSMS;
import vc.util.BOrder;

// Referenced classes of package vc.util.logger:
//            FileLogger

public class PLoggerFileOrder extends Thread
{

    public static int getMOCounter()
    {
        return moCounter;
    }

    public PLoggerFileOrder()
    {
        fileLogger = null;
        fileLogger = new FileLogger();
    }

    public void run()
    {
        	while(VCSMS.isRunning()){
                try
                {
                    BOrder mo = (BOrder)VCSMS.getLogFileQueueOrder().dequeue();
                        System.out.println("\t>>PLoggerFileOrder to file!!!");
                        fileLogger.logOrder(mo);
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
