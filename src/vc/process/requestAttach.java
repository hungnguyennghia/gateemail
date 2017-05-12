// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestBR.java

package vc.process;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;

// These are from the JavaMail API, which you can download at https://java.net/projects/javamail/pages/Home. 
// Be sure to include the mail.jar library in your project. In the build order, mail.jar should precede the AWS SDK for Java library.
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import vc.VCSMS;
import vc.WSConfig;
import vc.util.BUrlPush;
import vc.util.beanBR;

// These are from the AWS SDK for Java, which you can download at https://aws.amazon.com/sdk-for-java.
// Be sure to include the AWS SDK for Java library in your project.
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;


public class requestAttach {
  // IMPORTANT: Ensure that the region selected below is the one in which your identities are verified.  
  private static Regions AWS_REGION = Regions.US_EAST_1;           // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your sandbox 
                                                                   // status, sending limits, and Amazon SES identity-related settings are specific to a given AWS 
                                                                   // region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using 
                                                                   // the US West (Oregon) region. Examples of other regions that Amazon SES supports are US_EAST_1 
                                                                   // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html 
  
  

  public static String sendEmailAttach(beanBR bean) throws MessagingException {
	  String result = "-1";
	  BUrlPush beanPush=new BUrlPush();
	  beanPush.setMessage_ids(bean.getMessage_ids());
        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage message = new MimeMessage(session);
        message.setSubject(bean.getSubject(), "UTF-8");

        message.setFrom(new InternetAddress(bean.getFrom()));
        message.setReplyTo(new Address[]{new InternetAddress(bean.getFrom())});
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(bean.getTo()));
        if(bean.getCc().contains("@"))message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(bean.getCc()));
        if(bean.getBcc().contains("@"))message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bean.getBcc()));
       
        // Cover wrap
        MimeBodyPart wrap = new MimeBodyPart();

        // Alternative TEXT/HTML content
        MimeMultipart cover = new MimeMultipart("alternative");
        MimeBodyPart html = new MimeBodyPart();
        cover.addBodyPart(html);

        wrap.setContent(cover);

        MimeMultipart content = new MimeMultipart("related");
        message.setContent(content);
        content.addBodyPart(wrap);

     
         
        // This is just for testing HTML embedding of different type of attachments.
        StringBuilder sb = new StringBuilder();

        for (String attachmentFileName : bean.getAttach()) {
            String id = UUID.randomUUID().toString();
            sb.append("<img src=\"cid:");
            sb.append(id);
            sb.append("\" alt=\"ATTACHMENT\"/>\n");

            MimeBodyPart attachment = new MimeBodyPart();

            DataSource fds = new FileDataSource(attachmentFileName);
            attachment.setDataHandler(new DataHandler(fds));
            attachment.setHeader("Content-ID", "<" + id + ">");
            attachment.setFileName(fds.getName());

            content.addBodyPart(attachment);
        }

        html.setContent(bean.getContent(), "text/html; charset=utf-8");

        try {
            System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");
  		     String keyID = WSConfig.keyID;//<your key id>
		     String secretKey = WSConfig.secretKey;//<your secret key>
		     
    		 ClientConfiguration clientConfig = new ClientConfiguration();
    		 clientConfig.setProtocol(Protocol.HTTPS);
    		 clientConfig.setProxyHost("10.1.7.55");
    		 clientConfig.setProxyPort(8080);
		 BasicAWSCredentials credentials = new BasicAWSCredentials(keyID,secretKey);
            // Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
		 AmazonSimpleEmailServiceClient client=null;
		 if(WSConfig.setProxy==1){
			   client = new AmazonSimpleEmailServiceClient(credentials,clientConfig);
		 }else{
			   client = new AmazonSimpleEmailServiceClient(credentials);
		 }
            Region REGION = Region.getRegion(AWS_REGION);
            client.setRegion(REGION);

            // Print the raw email content on the console
//            PrintStream out = System.out;
//            message.writeTo(out);

            // Send the email.
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            message.writeTo(outputStream);
            RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

            SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);
            // SendRawEmailResult resultS = client.sendRawEmail(rawEmailRequest);
             client.sendRawEmail(rawEmailRequest);
//            System.out.println("messageId:"+resultS.getMessageId());
            System.out.println("Email sent!");
            
            result="0";
            beanPush.setRequestId("1");
//            beanPush.setRequestId(resultS.getMessageId());
            beanPush.setResult(1);
            Calendar cal  = Calendar.getInstance();                    
            beanPush.setTimeSend(cal.getTimeInMillis());
            //log gui email thanh cong
//            if(statusSend){//true sendmail ; false resend
//            	VCSMS.getLogFileQueue().enqueue(bean);
//            	VCSMS.getLogDBQueue().enqueue(bean);
//            }
        	VCSMS.getRequestUrlPushQueue().enqueue(beanPush);
        } catch (AmazonServiceException ase) {
//            System.out.println("Caught an AmazonServiceException, which means your request made it "
//                    + "to AWS CloudFormation, but was rejected with an error response for some reason.");
//            System.out.println("Error Message:    " + ase.getMessage());
//            System.out.println("HTTP Status Code: " + ase.getStatusCode());
//            System.out.println("AWS Error Code:   " + ase.getErrorCode());
//            System.out.println("Error Type:       " + ase.getErrorType());
//            System.out.println("Request ID:       " + ase.getRequestId());
          //log gui email loi AMZ tra ve
            beanPush.setResult(ase.getStatusCode());
            beanPush.setRequestId(ase.getRequestId());
            beanPush.setErrorMessage(ase.getMessage());
            beanPush.setErrorCode(ase.getErrorCode());
            Calendar cal  = Calendar.getInstance();                    
            beanPush.setTimeSend(cal.getTimeInMillis());
//        	VCSMS.getLogFileQueue().enqueue(bean);
//        	VCSMS.getLogDBQueue().enqueue(bean);
        	VCSMS.getRequestUrlPushQueue().enqueue(beanPush);
        	result="0";
        } catch (Exception ex) {
          System.out.println("Email Failed");
            System.err.println("Error message: " + ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }
  
}
