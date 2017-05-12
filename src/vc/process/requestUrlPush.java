// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestBR.java

package vc.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;






import vc.VCSMS;
import vc.WSConfig;
import vc.util.BUrlPush;

public class requestUrlPush extends Thread
{

    public requestUrlPush()
    {

    }



    
    public void run()
    {
    	while(VCSMS.isRunning()){
    		try {
    			BUrlPush bean = (BUrlPush)VCSMS.getRequestUrlPushQueue().dequeue();
    			if(bean.getMessage_ids()!=null && !bean.getMessage_ids().isEmpty()){
        			System.out.println("(requestUrlPush) Update status to API app...");
        			sendPost(bean);

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
				sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}

    }

    private final static String USER_AGENT = "Mozilla/5.0";

	public static void sendPost(BUrlPush bean) throws Exception {
//		DBTool db=new DBTool();
		try {
			String url = bean.getUrlpush();
			 if(WSConfig.enableUrlPush==1 && bean.getUrlpush()!=null && url.contains("http")){
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(url);
				
					// add header
					post.setHeader("User-Agent", USER_AGENT);
				
					List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
					urlParameters.add(new BasicNameValuePair("message_ids", bean.getMessage_ids()+""));
					urlParameters.add(new BasicNameValuePair("requestId", bean.getRequestId()));
					urlParameters.add(new BasicNameValuePair("statusCode", bean.getResult()+""));
					urlParameters.add(new BasicNameValuePair("errorCode", bean.getErrorCode()));
					urlParameters.add(new BasicNameValuePair("message", bean.getErrorMessage()));
				System.out.println("message_ids:"+ bean.getMessage_ids());
				System.out.println("requestId:"+ bean.getRequestId());
					post.setEntity(new UrlEncodedFormEntity(urlParameters));
				
					HttpResponse response = client.execute(post);
					System.out.println("\nSending 'POST' request to URL : " + url);
					System.out.println("Post parameters : " + post.getEntity());
					System.out.println("Response Code : " + 
				                                response.getStatusLine().getStatusCode());
				
					BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				
					StringBuffer result = new StringBuffer();
					String line = "";
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}
				//{"Result":1}
					System.out.println(result.toString());
					
					//update da chuyen trang thai cho EcomApp
		    	
		    			if(result.toString().contains("1")){
		    				bean.setStatusPush(1);
		    			}else{
		    				bean.setStatusPush(0);
		    			}
		    			
//						db.updateUrlpushStatus(bean);
						VCSMS.getUpdateStatusQueue().enqueue(bean);

			 }else{
			//	 bean.setResult(-1);
				 System.err.println("hungnn--"+bean.getRequestId()+"--"+bean.getMessage_ids());
				 VCSMS.getUpdateStatusQueue().enqueue(bean);
//		    		try {
//		    			//bean.setResult(-1);
//						db.updateUrlpushStatus(bean);
//					} catch (DBException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//						VCSMS.getRequestUrlPushQueue().enqueue(bean);
//					}
			 }

		} catch (Exception e) {
			// TODO: handle exception
			//loi update 
			System.out.println(e.getMessage());
			//VCSMS.getRequestUrlPushQueue().enqueue(bean);
			bean.setStatusPush(0);
			VCSMS.getUpdateStatusQueue().enqueue(bean);
		}



	}

}
