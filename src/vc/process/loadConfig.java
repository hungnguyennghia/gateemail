// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   loadConfig.java

package vc.process;

import vc.VCSMS;
import vc.WSConfig;
import vc.util.detextAcc;

public class loadConfig extends Thread
{

    public loadConfig()
    {
    }

    public void run()
    {
    	 while(VCSMS.isRunning()){
             try
             {
                 WSConfig.loadPropertiesConfig();
             }
             catch(Exception e)
             {
                 System.out.println("khong doc duoc file ./conf/loadPropertiesConfig.properties");
             }
             detextAcc.setAccountsFile();
             try {
				sleep(30000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 
    	 }
    }
}
