package vc.process;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.*;
import javax.mail.search.FlagTerm;
import javax.swing.text.AbstractDocument.BranchElement;

import org.jsoup.Jsoup;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPMessage;


public class FolderFetchIMAP {


    public static void main(String[] args) throws MessagingException, IOException {
        IMAPFolder folder = null;
        Store store = null;
        String subject = null;
        Flag flag = null;
        try 
        {
        	System.setProperty("http.proxyHost", "10.1.7.55");
            System.setProperty("http.proxyPort", "8080");
            
          Properties props = System.getProperties();
          props.setProperty("mail.store.protocol", "imaps");

          Session session = Session.getDefaultInstance(props, null);

          store = session.getStore("imaps");		
          
//          store.connect("imap.googlemail.com","makeponoreply@gmail.com", "makepo123#@!");
          store.connect("imap.googlemail.com","cskh@fptshop.com.vn", "FPTshop@FPT$hop");

          folder = (IMAPFolder) store.getFolder("[Gmail]/Spam"); // This doesn't work for other email account
          //folder = (IMAPFolder) store.getFolder("inbox"); This works for both email account


          if(!folder.isOpen())
          folder.open(Folder.READ_WRITE);
          Flags seen = new Flags(Flags.Flag.USER);
          FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
          Message messages[] = folder.search(unseenFlagTerm);
          
         // Message[] messages = folder.getMessages();
          System.out.println("No of Messages : " + folder.getMessageCount());
          System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
          System.out.println(messages.length);
          for (int i=0; i < messages.length;i++) 
          {

	            System.out.println("*****************************************************************************");
	            System.out.println("MESSAGE " + (i + 1) + ":");
	            Message msg =  messages[i];
	            subject = msg.getSubject();	
	           
	            if(msg.getFrom()[0].toString().contains("MAILER-DAEMON@amazonses.com")&& !subject.contains("Re:")){
//	            if(msg.getFrom()[0].toString().contains("mailer-daemon@googlemail.com")){
	            	System.out.println("Email ko ton tai!");
	            	 System.out.println("Subject: " + subject);
		            System.out.println("From: " + msg.getFrom()[0]);
//		            System.out.println("To: "+msg.getAllRecipients()[0]);
//		            System.out.println("Date: "+msg.getReceivedDate());
//		            System.out.println("Size: "+msg.getSize());
//		            System.out.println(msg.getFlags());
		       //     System.out.println("Body: \n"+ msg.getContent());
		            try {
						System.out.println(getTextFromMessage(msg));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	            }else{
	            	System.out.println("ko dung!");
	            }	
	 

          }
        }
        finally 
        {
          if (folder != null && folder.isOpen()) { folder.close(true); }
          if (store != null) { store.close(); }
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