
package vc.process;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.net.ssl.HttpsURLConnection;

import redis.clients.jedis.Jedis;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;



import vc.VCSMS;
import vc.WSConfig;
import vc.util.BUrlPush;
import vc.util.beanBR;

public class RequestCallSend implements Runnable
{

  

    private void processRequest(beanBR bean)
    {
    	String result = "-1";
    	BUrlPush beanPush=new BUrlPush();
    	beanPush.setMessage_ids(bean.getMessage_ids());
        beanPush.setRequestId("1");
        beanPush.setErrorMessage("");
        beanPush.setErrorCode("");
    	try {
    		if(bean.getEmailValid()==1){
    			sendEmail(bean);
    			return;
    		}

    		if(bean.getSubject().contains("[]")){
    			//chan email truyen thieu tham so
    			beanPush.setResult(-5);//email thieu tham so
//update status
             	VCSMS.getRequestUrlPushQueue().enqueue(beanPush);
            	return;
    		}
            if(isValidEmailAddress(bean.getTo())){//kiem tra email valid
            	

    	        	boolean checkVerify=false;
    	        	Jedis jedis = new Jedis(WSConfig.redisHost, WSConfig.redisPort);
    	        	
    	        	if(!jedis.exists(bean.getTo().toLowerCase())){
    	        		//check email verlify
    	        		//https://app.emaillistverify.com/api/verifEmail?secret=".$key."&email=".$email
    	        		if(WSConfig.enableEmailVerify==1){
    	        			String status=sendGet(WSConfig.urlEmailVerify+bean.getTo().toLowerCase());
    		        		//Status="ok"
    		        		
    		        		if(status.equals("ok")){
    		        			checkVerify=true;
    		        			jedis.set(bean.getTo().toLowerCase(), "1");
    		        		}else if(status.equals("unknown")){
    		        			checkVerify=true;
    		        			jedis.set(bean.getTo().toLowerCase(), "2");
    		        		}else{
    		        			jedis.set(bean.getTo().toLowerCase(), "0");//email invalid
    		        		}
    	        		}else{
    	        			checkVerify=true;
    	        		}
    	        	
    	        	}else{
    	        		String valueJedis=jedis.get(bean.getTo().toLowerCase());
    	        		if(valueJedis.equals("1")||valueJedis.equals("2")){
    	        		//Ecom check valid
          		
    	        			checkVerify=true;
    	        		}else if(valueJedis.equals("-1")){
    	        			beanPush.setResult(-1);
       	    	            beanPush.setErrorMessage("gateway email reject");
        	    	        	VCSMS.getRequestUrlPushQueue().enqueue(beanPush);
//update status        	    	        
//        	    	        	VCSMS.getUpdateStatusQueue().enqueue(bean);
        	    	        	return;
    	        		}

    	        	}
    	        	
    	        	jedis.close();

    	        	//send email
    	        	if(checkVerify){
//    		        	if(!bean.getTeamplate().equals("Email.EBilling")){//chan EBilling
    			        	if(bean.getAttach()!=null){//gui file dinh kem
    			        		try {
    								result = requestAttach.sendEmailAttach(bean);
    							} catch (MessagingException e) {
    								// TODO Auto-generated catch block
    								e.printStackTrace();
    							}
    			        	}else{
    			        		 sendEmail(bean);
    			        		 return;
    			        	}

    	        	}else{
    	        		beanPush.setResult(-2);
//update status
    	        		VCSMS.getRequestUrlPushQueue().enqueue(beanPush);
    	        		//VCSMS.getUpdateStatusQueue().enqueue(bean);
    		        	return;
    	        	}
            }else{
            	beanPush.setResult(-2);//email invalid
//update status
            	VCSMS.getRequestUrlPushQueue().enqueue(beanPush);
            	return;
            }

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("hungnn error RequestCallSend");
			beanPush.setResult(-3);//loi khong xac dinh
//update status   
        	VCSMS.getRequestUrlPushQueue().enqueue(beanPush);
		}
        

    }

	private final beanBR bean;
	 
	RequestCallSend(beanBR bean) {
		this.bean = bean;
	}

	@Override
	public void run() {
		processRequest(bean);
	}


    public static boolean isValidEmailAddress(String email) {
    	   boolean result = true;
    	   try {
    	      InternetAddress emailAddr = new InternetAddress(email);
    	      emailAddr.validate();
    	   } catch (AddressException ex) {
    	      result = false;
    	   }
    	   return result;
    	}


    public static void sendEmail(beanBR bean)
    {
     //   String result = "-1";
        BUrlPush beanPush= new BUrlPush();
        beanPush.setMessage_ids(bean.getMessage_ids());
        beanPush.setRequestId("1");
        beanPush.setErrorMessage("");
        beanPush.setErrorCode("");
        try {
            // Construct an object to contain the recipient address.
            Destination destination = new Destination();
            if(bean.getTo().contains("@"))destination.withToAddresses(bean.getTo().split(","));
            try {
                if(bean.getCc().contains("@"))destination.withCcAddresses(bean.getCc().split(","));
                if(bean.getBcc().contains("@"))destination.withBccAddresses(bean.getBcc().split(","));

			} catch (Exception e) {
				// TODO: handle exception
			}
             // Create the subject and body of the message.
            Content subject = new Content().withData(bean.getSubject());
             
            Content textBody = new Content().withData(bean.getContent());
            Body body = new Body().withHtml(textBody);
            
            // Create a message with the specified subject and body.
            Message message = new Message().withSubject(subject).withBody(body);

            // Assemble the email.
            SendEmailRequest request = new SendEmailRequest().withSource(bean.getFrom()).withDestination(destination).withMessage(message);

            try {
                System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

                /*
                 * The ProfileCredentialsProvider will return your [default]
                 * credential profile by reading from the credentials file located at
                 * (C:\\Users\\PC0353\\.aws\\credentials).
                 *
                 * TransferManager manages a pool of threads, so we create a
                 * single instance and share it throughout our application.
                 */
                
    		     String keyID = WSConfig.keyID;//<your key id>
    		     String secretKey = WSConfig.secretKey;//<your secret key>
    		     
    		 BasicAWSCredentials credentials = new BasicAWSCredentials(keyID,secretKey);
//                AWSCredentials credentials = null;
//                try {
//                    credentials = new ProfileCredentialsProvider("default").getCredentials();
//                } catch (Exception e) {
//                    throw new AmazonClientException(
//                            "Cannot load the credentials from the credential profiles file. " +
//                            "Please make sure that your credentials file is at the correct " +
//                            "location (C:\\Users\\PC0353\\.aws\\credentials), and is in valid format.",
//                            e);
//                }

    		 ClientConfiguration clientConfig = new ClientConfiguration();
    		 clientConfig.setProtocol(Protocol.HTTPS);
    		 clientConfig.setProxyHost("10.1.7.55");
    		 clientConfig.setProxyPort(8080);
    		 AmazonSimpleEmailServiceClient client=null;
                // Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
    		 if(WSConfig.setProxy==1){
    			   client = new AmazonSimpleEmailServiceClient(credentials,clientConfig);
    		 }else{
    			   client = new AmazonSimpleEmailServiceClient(credentials);
    		 }
              
                // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your production
                // access status, sending limits, and Amazon SES identity-related settings are specific to a given
                // AWS region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using
                // the US East (N. Virginia) region. Examples of other regions that Amazon SES supports are US_WEST_2
                // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html
                Region REGION = Region.getRegion(Regions.US_EAST_1);
                client.setRegion(REGION);

                // Send the email.
                client.sendEmail(request);
//                SendEmailResult resultS = client.sendEmail(request);
                
//                System.out.println("messageId:"+resultS.getMessageId());
                System.out.println("Email sent!");
          //      result="0";
//                beanPush.setRequestId(resultS.getMessageId());

                beanPush.setResult(1);
                Calendar cal  = Calendar.getInstance();                    
                beanPush.setTimeSend(cal.getTimeInMillis());

                //log gui email thanh cong
//                if(statusSend){
//                	VCSMS.getLogFileQueue().enqueue(bean);
//                	VCSMS.getLogDBQueue().enqueue(bean);
//                }
                if(WSConfig.enableUrlPush==1 && bean.getUrlpush()!=null && bean.getUrlpush().contains("http")){
                	VCSMS.getRequestUrlPushQueue().enqueue(beanPush);              	
                }else{
                	VCSMS.getUpdateStatusQueue().enqueue(beanPush);
                }
            	
            } catch (AmazonServiceException ase) {
                System.out.println("Caught an AmazonServiceException, which means your request made it "
                        + "to AWS CloudFormation, but was rejected with an error response for some reason.");
                System.out.println("Error Message:    " + ase.getMessage());
                System.out.println("HTTP Status Code: " + ase.getStatusCode());
                System.out.println("AWS Error Code:   " + ase.getErrorCode());
                System.out.println("Error Type:       " + ase.getErrorType());
                System.out.println("Request ID:       " + ase.getRequestId());
              //log gui email loi AMZ tra ve
                beanPush.setResult(ase.getStatusCode());
                beanPush.setRequestId(ase.getRequestId());
                beanPush.setErrorMessage(ase.getMessage());
                beanPush.setErrorCode(ase.getErrorCode());
//                if(statusSend){
//                	VCSMS.getLogFileQueue().enqueue(bean);
//                	VCSMS.getLogDBQueue().enqueue(bean);
//                }
                Calendar cal  = Calendar.getInstance();                    
                beanPush.setTimeSend(cal.getTimeInMillis());

                if(WSConfig.enableUrlPush==1 && bean.getUrlpush()!=null && bean.getUrlpush().contains("http")){
                	VCSMS.getRequestUrlPushQueue().enqueue(beanPush);              	
                }else{
                	VCSMS.getUpdateStatusQueue().enqueue(beanPush);
                }
           // 	result="0";
            } catch (Exception ex) {
            	System.err.println("The email was not sent. Error message: " + ex.getMessage());
            		bean.setResend(bean.getResend()+1);
                	VCSMS.getRecallRequestBRQueue().enqueue(bean);        		
            }
             
  	    } catch (Exception e) {
  	      e.printStackTrace();
  	    }
        return ;
    }
    
    public static String sendGet(String url) {
    	String ketqua="";
    
    	try  {
                System.out.println(url);
            String jsonData = "";
//                URL obj = new URL(url);
//                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
                
                InetSocketAddress proxyInet = new InetSocketAddress("10.1.7.55",8080);
                Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyInet);
                URL httpsUrl = new URL(url);
  
                if(WSConfig.setProxy==1){
                	HttpsURLConnection con = (HttpsURLConnection) httpsUrl.openConnection(proxy);
                    // optional default is GET
                    con.setRequestMethod("GET");
            
                    int responseCode = con.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + url);
                    System.out.println("Response Code : " + responseCode);
            
                    BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
            
                    while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                            ketqua= inputLine;
                    }
                    in.close();
                }else{
                	HttpURLConnection con = (HttpURLConnection) httpsUrl.openConnection();
                    // optional default is GET
                    con.setRequestMethod("GET");
            
                    int responseCode = con.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + url);
                    System.out.println("Response Code : " + responseCode);
            
                    BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
            
                    while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                            ketqua= inputLine;
                    }
                    in.close();
                }
                
        
            

	    } catch (Exception ex)  {
	        ex.printStackTrace();
	    } finally  {
	    }
    return ketqua;
    }
 }
