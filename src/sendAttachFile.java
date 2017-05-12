import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Base64;
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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

// These are from the AWS SDK for Java, which you can download at https://aws.amazon.com/sdk-for-java.
// Be sure to include the AWS SDK for Java library in your project.
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;

public class sendAttachFile {

  // IMPORTANT: To successfully send an email, you must replace the values of the strings below with your own values.   
  private static String EMAIL_FROM      = "cskh@fptshop.com.vn";    // Replace with the sender's address. This address must be verified with Amazon SES.
  private static String EMAIL_REPLY_TO  = "cskh@fptshop.com.vn";  // Replace with the address replies should go to. This address must be verified with Amazon SES. 
  private static String EMAIL_RECIPIENT = "hungnn21@fpt.com.vn"; // Replace with a recipient address. If your account is still in the sandbox,
                                                                   // this address must be verified with Amazon SES.  
  private static String EMAIL_ATTACHMENTS = "d:/contacts.txt,d:/commands.cfg"; // Replace with the path of an attachment. Must be a valid path or this project will not build.
                                                                              // Remember to use two slashes in place of each slash.
  
  // IMPORTANT: Ensure that the region selected below is the one in which your identities are verified.  
  private static Regions AWS_REGION = Regions.US_EAST_1;           // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your sandbox 
                                                                   // status, sending limits, and Amazon SES identity-related settings are specific to a given AWS 
                                                                   // region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using 
                                                                   // the US West (Oregon) region. Examples of other regions that Amazon SES supports are US_EAST_1 
                                                                   // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html 
  
  private static String EMAIL_SUBJECT   = "Amazon SES email test";
  private static String EMAIL_BODY_TEXT = "This MIME email was sent through Amazon SES using SendRawEmail.";
  
  public static void string_base64_decode_java_8()
	        throws UnsupportedEncodingException {

	    String encodedPhrase = "TGVhcm4uIEVhdC4gQ29kZS4=";

	    byte[] decodedPhraseAsBytes = java.util.Base64.getDecoder().decode(
	            encodedPhrase);

	    String phraseDecodedToString = new String(decodedPhraseAsBytes, "utf-8");
System.out.println(phraseDecodedToString);
	  //  assertEquals("Learn. Eat. Code.", phraseDecodedToString);
	}
  

    public static void main(String[] args) throws AddressException, MessagingException, IOException {
    	
        try{

            String str = "nguyễn nghĩa hưng";
            Base64.Encoder enc= Base64.getEncoder();
            
            //encoding  byte array into base 64
                
             byte[] strenc =enc.encode(str.getBytes("UTF-8"));
            
             System.out.println("Base64 Encoded String : " + new String(strenc,"UTF-8"));
        
            //decoding byte array into base64

            Base64.Decoder dec= Base64.getDecoder();

            byte[] strdec=dec.decode(strenc);
            

            System.out.println("Base64 Decoded String : " + new String(strdec,"UTF-8"));
        }

        catch(Exception e){
            System.out.println("Invalid URL Exception");
        }
//        Session session = Session.getDefaultInstance(new Properties());
//        MimeMessage message = new MimeMessage(session);
//        message.setSubject(EMAIL_SUBJECT, "UTF-8");
//
//        message.setFrom(new InternetAddress(EMAIL_FROM));
//        message.setReplyTo(new Address[]{new InternetAddress(EMAIL_REPLY_TO)});
//        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_RECIPIENT));
////        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("hungnn2504@gmail.com,hungnguyennghia@vccorp.vn"));
//        message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("hungnn2504@gmail.com,hungnguyennghia@vccorp.vn"));
//        // Cover wrap
//        MimeBodyPart wrap = new MimeBodyPart();
//
//        // Alternative TEXT/HTML content
//        MimeMultipart cover = new MimeMultipart("alternative");
//        MimeBodyPart html = new MimeBodyPart();
//        cover.addBodyPart(html);
//
//        wrap.setContent(cover);
//
//        MimeMultipart content = new MimeMultipart("related");
//        message.setContent(content);
//        content.addBodyPart(wrap);
//
//        String[] attachmentsFiles = new String[]{
//        		"d:/contacts.txt","d:/commands.cfg"
//        };
//
//        // This is just for testing HTML embedding of different type of attachments.
//        StringBuilder sb = new StringBuilder();
//
//        for (String attachmentFileName : attachmentsFiles) {
//            String id = UUID.randomUUID().toString();
//            sb.append("<img src=\"cid:");
//            sb.append(id);
//            sb.append("\" alt=\"ATTACHMENT\"/>\n");
//
//            MimeBodyPart attachment = new MimeBodyPart();
//
//            DataSource fds = new FileDataSource(attachmentFileName);
//            attachment.setDataHandler(new DataHandler(fds));
//            attachment.setHeader("Content-ID", "<" + id + ">");
//            attachment.setFileName(fds.getName());
//
//            content.addBodyPart(attachment);
//        }
//
//        html.setContent("<html><body><h1>HTML</h1>\n" + EMAIL_BODY_TEXT + "</body></html>", "text/html");
//
//        try {
//            System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");
//
//            /*
//             * The ProfileCredentialsProvider will return your [default]
//             * credential profile by reading from the credentials file located at
//             * (~/.aws/credentials).
//             *
//             * TransferManager manages a pool of threads, so we create a
//             * single instance and share it throughout our application.
//             */
////            AWSCredentials credentials = null;
////            try {
////                credentials = new ProfileCredentialsProvider().getCredentials();
////            } catch (Exception e) {
////                throw new AmazonClientException(
////                        "Cannot load the credentials from the credential profiles file. " +
////                        "Please make sure that your credentials file is at the correct " +
////                        "location (~/.aws/credentials), and is in valid format.",
////                        e);
////            }
//		     String keyID = "2222";//<your key id>
//		     String secretKey = "2222";//<your secret key>
//		     
//		 BasicAWSCredentials credentials = new BasicAWSCredentials(keyID,secretKey);
//            // Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
//            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);
//            Region REGION = Region.getRegion(AWS_REGION);
//            client.setRegion(REGION);
//
//            // Print the raw email content on the console
//            PrintStream out = System.out;
//            message.writeTo(out);
//
//            // Send the email.
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            message.writeTo(outputStream);
//            RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));
//
//            SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);
//            client.sendRawEmail(rawEmailRequest);
//            System.out.println("Email sent!");
//
//        } catch (Exception ex) {
//          System.out.println("Email Failed");
//            System.err.println("Error message: " + ex.getMessage());
//            ex.printStackTrace();
//        }
    }
}
