package vc.util.logger;

import vc.VCSMS;
import vc.database.*;
import vc.util.BOrder;

public class PLoggerDBOrder extends Thread
{

    public PLoggerDBOrder()
    {
        dbTool = null;
        dbTool = new DBTool();
    }

    public void run()
    {
        
        while(VCSMS.isRunning()){
        	BOrder mo=null;
            try
            {
                mo = (BOrder)VCSMS.getLogDBQueueOrder().dequeue();
                System.out.println((new StringBuilder("[PLoggerDBOrder]")).append(mo.getResult()).toString());
                boolean add2MOlogResult = false;
                if(DBPool.isConnecting())//DB duoc ket noi va ban ghi thanh cong
                	if(mo.getScan()==0){
                		add2MOlogResult = dbTool.insert2Order(mo);
                	}else{
                		if(mo.getResult()==0){//==0 thi ko can update
                			add2MOlogResult=true;
                		}else{
                			add2MOlogResult = dbTool.updateShopOrderScan(mo);
                		}
                		
                	}
                    
                if(!add2MOlogResult){
                	VCSMS.getLogDBQueueOrder().enqueue(mo);
                }
            }
            catch(DBException ex)
            {
                System.out.println((new StringBuilder("[PLoggerDBOrder]")).append(ex.getMessage()).toString());
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
