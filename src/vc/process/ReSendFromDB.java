package vc.process;

import java.util.ArrayList;

import javax.mail.MessagingException;

import vc.VCSMS;
import vc.WSConfig;
import vc.database.DBTool;
import vc.util.beanBR;

public class ReSendFromDB extends Thread
{

    public ReSendFromDB()
    {

    }

    private void processGetEmailErrFromDB()
    {

    	DBTool db=new DBTool();
    	ArrayList beans=new ArrayList();
    	beans= db.getAllRecord();
    	for (int i = 0; i < beans.size(); i++) {
    		beanBR bean=(beanBR)beans.get(i);
            String result = "-1";
        	if(bean.getAttach()!=null){//gui file dinh kem
        		try {
					result = requestAttach.sendEmailAttach(bean);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}else{
        		 RequestCallSend.sendEmail(bean);
        	}
        	
//            if(Integer.parseInt(result)<0)
//            {
//            	bean.setResult(0);
//            	
//            }else{
//            	bean.setResult(1);
//            }
    		
//            bean.setResend(bean.getResend()+1);
//            VCSMS.getUpdateStatusQueue().enqueue(bean);
		}

    }
    
    public void run()
    {
    	while(VCSMS.isRunning()){
    		if(WSConfig.enableResend==1){
        		System.out.println("ReSendFromDB...");
        		try {
            			processGetEmailErrFromDB();
    				
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
