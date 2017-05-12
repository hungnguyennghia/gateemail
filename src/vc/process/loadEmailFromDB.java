
package vc.process;

import java.util.ArrayList;

import redis.clients.jedis.Jedis;
import vc.WSConfig;
import vc.database.DBException;
import vc.database.DBTool;
import vc.util.BOrder;
import vc.util.beanBR;

public class loadEmailFromDB 
{

    public loadEmailFromDB()
    {
    }

    public static void insertEmailToRedis()
    {
    	Jedis jedis = new Jedis(WSConfig.redisHost, WSConfig.redisPort);
    	if(!WSConfig.redisAuth.equals(""))jedis.auth(WSConfig.redisAuth);
		DBTool db =new DBTool();
		ArrayList beans= db.getAllEmailOpenRate();
		for (int i = 0; i < beans.size(); i++) {
			beanBR bean=(beanBR)beans.get(i);
			jedis.set(bean.getTo().toLowerCase(),bean.getResult()+"");
		}
		ArrayList beans1= db.getAllEmailReject();
		for (int i = 0; i < beans1.size(); i++) {
			beanBR bean=(beanBR)beans1.get(i);
			jedis.set(bean.getTo().toLowerCase(),bean.getResult()+"");
		}
		jedis.close();
    	System.out.println("Da insert redis xong..");
    }
    
    public static void insertEmailRejectToRedis()
    {
    	
		DBTool db =new DBTool();
    	Jedis jedis = new Jedis(WSConfig.redisHost, WSConfig.redisPort);
    	if(!WSConfig.redisAuth.equals(""))jedis.auth(WSConfig.redisAuth);
		ArrayList beans1= db.getAllEmailReject();
		for (int i = 0; i < beans1.size(); i++) {
			beanBR bean=(beanBR)beans1.get(i);
			jedis.set(bean.getTo().toLowerCase(),bean.getResult()+"");
			BOrder beanOrder =new BOrder();
			beanOrder.setEmail(bean.getTo().toLowerCase());
			beanOrder.setResult(bean.getResult());
			try {
				db.updateShopOrderScan(beanOrder);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		jedis.close();
    	
    }
    
    public static void checkEmailValid()
    {
    	
		DBTool db =new DBTool();
    	Jedis jedis = new Jedis(WSConfig.redisHost, WSConfig.redisPort);
    	if(!WSConfig.redisAuth.equals(""))jedis.auth(WSConfig.redisAuth);

		ArrayList beans1= db.getAllEmailValid();
		for (int i = 0; i < beans1.size(); i++) {
			beanBR bean=(beanBR)beans1.get(i);
			
			if(jedis.get(bean.getTo().toLowerCase())!=null){
//    			String status=RequestBR.sendGet(WSConfig.urlEmailVerify+bean.getTo().toLowerCase());
//        		//Status="ok"
//        		
//        		if(status.equals("ok")){
//        			
//        			jedis.set(bean.getTo().toLowerCase(), "1");
//        			
//        		}else{
//        			jedis.set(bean.getTo().toLowerCase(), "0");//email invalid
//        			//update db
//        			bean.setResult(-1);
//        			try {
//						db.updateEmailValid(bean);
//					} catch (DBException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//        		}
//        	}else{
        		String valueJedis=jedis.get(bean.getTo().toLowerCase());
        		if(valueJedis.equals("0")){
        		//Ecom check invalid
  		//update 
        			bean.setResult(-1);
        			try {
						db.updateEmailValid(bean);
					} catch (DBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
			}else{
    			bean.setResult(-2);
    			try {
					db.updateEmailValid(bean);
				} catch (DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		jedis.close();
    	System.out.println("Da update DB xong..");
    }
}
