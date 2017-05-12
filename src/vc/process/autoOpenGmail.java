
package vc.process;

import java.util.Calendar;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags.Flag;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import org.jsoup.Jsoup;

import com.sun.mail.imap.IMAPFolder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import vc.VCSMS;
import vc.WSConfig;
import vc.util.BReject;
import vc.util.logger.Logger;


public class autoOpenGmail extends Thread
{

    public autoOpenGmail()
    {

    }

 
    
    public void run()
    {
    	while(VCSMS.isRunning()){
    			Calendar now = Calendar.getInstance();
                int hour=now.get(Calendar.HOUR_OF_DAY);
                if(hour==WSConfig.googlemailHourRun){
                	Logger.getInstance().logInfo("autoOpenGmail runing...",null, "[autoOpenGmail]");
                    IMAPFolder folder = null;
                    Store store = null;
                    String subject = null;
                    Flag flag = null;
                    try 
                    {
                      Properties props = System.getProperties();
                      if(WSConfig.setProxy==1){
                          props.setProperty("proxySet","true");
                          props.setProperty("http.proxyHost","10.1.7.55");
                          props.setProperty("http.proxyPort","8080");  
                      }

                      
                      props.setProperty("mail.store.protocol", "imaps");

                      Session session = Session.getDefaultInstance(props, null);

                      try {
						store = session.getStore("imaps");
					} catch (NoSuchProviderException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}		
                       try {
						store.connect("imap.googlemail.com",WSConfig.googlemailAcc, WSConfig.googlemailPass);
						folder = (IMAPFolder) store.getFolder(WSConfig.googlemailFolder);
	                     if(!folder.isOpen())
	                         folder.open(Folder.READ_WRITE);
	                         Flags seen = new Flags(Flags.Flag.SEEN);
	                         FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
	                         Message messages[] = folder.search(unseenFlagTerm);
	                         
	                        // Message[] messages = folder.getMessages();
	                         System.out.println("No of Messages : " + folder.getMessageCount());
	                         System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
	                         System.out.println(messages.length);
	                         Jedis jedis = new Jedis(WSConfig.redisHost, WSConfig.redisPort);
	                         for (int i=0; i < messages.length;i++) 
	                         {

	               	            System.out.println("*****************************************************************************");
	               	            System.out.println("MESSAGE " + (i + 1) + ":");
	               	            Message msg =  messages[i];
	               	            subject = msg.getSubject();	
	               	           
	               	            if(msg.getFrom()[0].toString().contains(WSConfig.googlemailEmailSend)&&subject.contains(WSConfig.googlemailSubject)){
	               	         //   if(msg.getFrom()[0].toString().contains("mailer-daemon@googlemail.com")){
	               	            	BReject bean=new BReject();
	               	            	
	               	            	
	               	            	System.out.println("Email ko ton tai!");
	               	            	 System.out.println("Subject: " + subject);
	               		            System.out.println("From: " + msg.getFrom()[0]);
//	               		            System.out.println("To: "+msg.getAllRecipients()[0]);
//	               		            System.out.println("Date: "+msg.getReceivedDate());
//	               		            System.out.println("Size: "+msg.getSize());
//	               		            System.out.println(msg.getFlags());
	               		       //     System.out.println("Body: \n"+ msg.getContent());
	               		            try {	         
	               		            	String email=getTextFromMessage(msg).toString().toLowerCase();
	               		            	if(email.contains("@")){
	               		            		bean.setEmail(email);
		               						System.out.println(bean.getEmail());
		               						if(jedis.exists(bean.getEmail())){
		               							String valueJedis=jedis.get(bean.getEmail());
		               							if(!valueJedis.equals("-1")){
		    	               						jedis.set(bean.getEmail(),"-1");
		    		   	               		         VCSMS.getLogDBQueueReject().enqueue(bean);	
		               							}
		               						}else{
		               							jedis.set(bean.getEmail(),"-1");
		               							VCSMS.getLogDBQueueReject().enqueue(bean);	
		               						}
		               						jedis.close();
	               		            	}
	               						

	               					} catch (Exception e) {
	               						// TODO Auto-generated catch block
	               						e.printStackTrace();
	               						jedis.close();
	               					}
	               	            }else{
	               		            try {	               						
	               						System.out.println(getTextFromMessage(msg));
	               					} catch (Exception e) {
	               						// TODO Auto-generated catch block
	               						e.printStackTrace();
	               					}
	               	            	
	               	            }	
	               	 

	                         }
                      }
                      finally 
                      {
                        if (folder != null && folder.isOpen()) { folder.close(true); }
                        if (store != null) { store.close(); }
                      }
                      } catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

           		 	try {
						sleep(60*60*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		

            		//end check time
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

    private static String getTextFromMessage(Message message) throws Exception {
        String result = "";
        if (message.isMimeType("text/plain")) {
            System.out.println("text/plain");
            result=getEmail(message.getContent().toString());
        } else if (message.isMimeType("text/html")) {
            String html = (String) message.getContent();
            result = getEmail(Jsoup.parse(html).text()) ;
        } else if (message.isMimeType("multipart/*")) {
        	System.out.println("multipart");
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result=getEmail(getTextFromMimeMultipart(mimeMultipart));
        }
        return result;
    }
    private static String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart) throws Exception{
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());               
            }
        }
        return result;
    }
    private static String getEmail(String s){
    	String result="";
  //  String s = "*** test@gmail.com&&^ test2@gmail.com((& ";
    Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(s);
    while (m.find()) {
    	result=m.group();
        return result;
    }
    return result;
}

}
