// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestBR.java

package vc.process;

import vc.VCSMS;
import vc.util.beanBR;

public class ReCallRequestBR extends Thread
{

    public ReCallRequestBR()
    {

    }

    private void processRequest(beanBR bean)
    {
    //    String result = "-1";
         RequestCallSend.sendEmail(bean);
//        if(Integer.parseInt(result)<0)
//        {
//
//
//        }
    }

    
    public void run()
    {
    	while(VCSMS.isRunning()){
    		try {
        		beanBR bean = (beanBR)VCSMS.getRecallRequestBRQueue().dequeue();
        		System.out.println("(ReCallRequestBR) re...");
        		if(bean.getMessage_ids()!=null && !bean.getMessage_ids().isEmpty()){
                    processRequest(bean);
        		}else{
        			sleep(100);
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
