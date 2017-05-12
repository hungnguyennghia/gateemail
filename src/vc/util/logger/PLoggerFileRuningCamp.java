// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PLoggerFile.java

package vc.util.logger;

import vc.VCSMS;
import vc.util.BCamp;

// Referenced classes of package vc.util.logger:
//            FileLogger

public class PLoggerFileRuningCamp extends Thread
{

    public static int getMOCounter()
    {
        return moCounter;
    }

    public PLoggerFileRuningCamp()
    {
        fileLogger = null;
        fileLogger = new FileLogger();
    }

    public void run()
    {
        	while(VCSMS.isRunning()){
                try
                {
                    BCamp mo = (BCamp)VCSMS.getLogRuningQueueCamp().dequeue();
                        System.out.println("\t>>PLoggerFileRuningCamp to file!!!");
                        fileLogger.logRuningCamp(mo);
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
