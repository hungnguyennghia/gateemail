
package vc.process;

import redis.clients.jedis.Jedis;

import vc.VCSMS;
import vc.WSConfig;
import vc.util.BOrder;


public class RequestEmailValid implements Runnable
{

  

    private void processRequest(BOrder bean)
    {
    	try {
    		bean.setResult(0);
    		bean.setEmail(bean.getEmail().toLowerCase());
            if(RequestCallSend.isValidEmailAddress(bean.getEmail())){//kiem tra email valid
            	Jedis jedis = new Jedis(WSConfig.redisHost, WSConfig.redisPort);

			try {
				
				if(!jedis.exists(bean.getEmail())){
					//check email verlify
					//https://app.emaillistverify.com/api/verifEmail?secret=".$key."&email=".$email
					String status="0";
					if(WSConfig.enableEmailVerify==1){
						 status=RequestCallSend.sendGet(WSConfig.urlEmailVerify+bean.getEmail());
			    		//Status="ok"
					}
			    		if(status.equals("ok")){
			    			bean.setResult(1);
			    			jedis.set(bean.getEmail(), "1");
			    		}else if(status.equals("unknown")){
			    			bean.setResult(1);
			    			jedis.set(bean.getEmail(), "2");
			    		}else{
			    			jedis.set(bean.getEmail(), "0");//email invalid
			    		}
				}else{
					String valueJedis=jedis.get(bean.getEmail());
					if(valueJedis.equals("1")||valueJedis.equals("2")){
					//Ecom check valid
						bean.setResult(1);
					}
			
				}
				jedis.close();
			} catch (Exception e) {
				// TODO: handle exception
				jedis.close();
			}

        }

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			
		}
    	VCSMS.getLogDBQueueOrder().enqueue(bean);
    	if(bean.getScan()==0){
    	    	VCSMS.getLogFileQueueOrder().enqueue(bean);	
    	}


    }

	private final BOrder bean;
	 
	RequestEmailValid(BOrder bean) {
		this.bean = bean;
	}

	@Override
	public void run() {
		processRequest(bean);
	}


 }
