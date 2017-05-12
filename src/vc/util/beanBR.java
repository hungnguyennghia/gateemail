// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   beanBR.java

package vc.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import vc.util.logger.Logger;

public class beanBR
{

    public beanBR()
    {

    }


    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getAccount()
    {
        return account;
    }

  

    public void setResult(int result)
    {
        this.result = result;
    }

    public int getResult()
    {
        return result;
    }


    public void toFile(PrintWriter fout)
        throws IOException
    {
        Base64.Encoder enc= Base64.getEncoder();
        
        //encoding  byte array into base 64
            
         byte[] subjectenc =enc.encode(getSubject().getBytes("UTF-8"));
         byte[] contentenc =enc.encode(getContent().getBytes("UTF-8"));
        String line = (new StringBuilder(String.valueOf(getMessage_ids())))
        		.append("|").append(getAccount())
        		.append("|").append(getFrom())
        		.append("|").append(getTo())
        		.append("|").append(getCc().equals("")?"null":getCc())
        		.append("|").append(getBcc().equals("")?"null":getBcc())
        		.append("|").append(new String(subjectenc,"UTF-8"))
        		.append("|").append(new String(contentenc,"UTF-8"))
        		.append("|").append(getEmail_id())
        		.append("|").append(getOrderid().equals("")?"null":getOrderid())
        		.append("|").append(getTeamplate().equals("")?"null":getTeamplate())
        		.append("|").append(getFilePaths().equals("")?"null":getFilePaths().replace('|', '#'))
        		.append("|").append(getUrlpush().equals("")?"null":getUrlpush())
        		.append("|").append(getResult())
        		.append("|").append(getTime_send().equals("")?"null":getTime_send())
        		.append("|").append(getEmailValid())
        		.append("|").append(getCamp_id().equals("")?"null":getCamp_id()).toString();
        fout.println(line);
        fout.flush();
    }

    public static beanBR parseString(String line)
    {
    	beanBR mo =null;
        if(line == null)
            return null;
        Collection c = parseString(line, "|");
        if(c.size() < 16)
        {
    		Logger.getInstance().logInfo((new StringBuilder("Invalid beanBR String: ")).append(line).toString(), null, null);

            System.out.println((new StringBuilder("Invalid beanBR String: ")).append(line).toString());
            return null;
        } else
        {
        	try {
                Base64.Decoder dec= Base64.getDecoder();

                Iterator it = c.iterator();
                mo = new beanBR();
                mo.setMessage_ids((String)it.next());
                mo.setAccount((String)it.next());
                mo.setFrom((String)it.next());
                mo.setTo((String)it.next());
                String cc=(String)it.next();
                mo.setCc(cc.equals("null")?"":cc);
                String bcc=(String)it.next();
                mo.setBcc(bcc.equals("null")?"":bcc);
                byte[] subjectdec=dec.decode((String)it.next());
                byte[] contentdec=dec.decode((String)it.next());
                try {
    				mo.setSubject(new String(subjectdec,"UTF-8"));
    				mo.setContent(new String(contentdec,"UTF-8"));
    			} catch (UnsupportedEncodingException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
                mo.setEmail_id((String)it.next());
                String orderid=(String)it.next();
                mo.setOrderid(orderid.equals("null")?"":orderid);
                mo.setTeamplate((String)it.next());
                String filePath=(String)it.next();
                mo.setFilePaths(filePath.equals("null")?"":filePath.replace('#', '|'));//replate #==>|
                String urlP=(String)it.next();
                mo.setUrlpush(urlP.equals("null")?"":urlP);
                mo.setResult(Integer.parseInt((String)it.next()));
                String time_send=((String)it.next());
                mo.setTime_send(time_send.equals("null")?"":time_send);
                mo.setEmailValid(Integer.parseInt((String)it.next()));
                String campid=(String)it.next();
                mo.setCamp_id(campid.equals("null")?"":campid);
               
			} catch (Exception e) {
				// TODO: handle exception
	    		Logger.getInstance().logInfo((new StringBuilder("Valid beanBR String: ")).append(line).toString(), e, null);

			}
 
        	 return mo;
        }
    }

    public static Collection parseString(String text, String seperator) {
        Vector vResult = new Vector();
        if (text == null || "".equals(text))
            return vResult;

        String tempStr = text.trim();
        String currentLabel = null;

        int index = tempStr.indexOf(seperator);
        while (index != -1) {
            currentLabel = tempStr.substring(0, index).trim();
            //Only accept not null element
            if (!"".equals(currentLabel))
                vResult.addElement(currentLabel);
            tempStr = tempStr.substring(index + 1);
            index = tempStr.indexOf(seperator);
        }
        //Last label
        currentLabel = tempStr.trim();
        if (!"".equals(currentLabel))
            vResult.addElement(currentLabel);
        return vResult;
    }


    public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public String getCc() {
		return cc;
	}


	public void setCc(String cc) {
		this.cc = cc;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getTime_send() {
		return time_send;
	}


	public void setTime_send(String time_send) {
		this.time_send = time_send;
	}


	public String getBcc() {
		return bcc;
	}


	public void setBcc(String bcc) {
		this.bcc = bcc;
	}



	public int getResend() {
		return resend;
	}


	public void setResend(int resend) {
		this.resend = resend;
	}





	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getUrlpush() {
			return urlpush;
	}


	public void setUrlpush(String urlpush) {
			this.urlpush= urlpush;
	}



	public String getOrderid() {
		return orderid;
	}


	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


	public String getTeamplate() {
		return teamplate;
	}


	public void setTeamplate(String teamplate) {
		this.teamplate = teamplate;
	}


	public String[] getTeampParam() {
		return teampParam;
	}


	public void setTeampParam(String teampParam[]) {
		this.teampParam = teampParam;
	}


	public String[] getAttach() {
		return attach;
	}


	public void setAttach(String attach[]) {
		this.attach = attach;
	}




	public ArrayList<fileupload> getFiles() {
		return files;
	}


	public void setFiles(ArrayList<fileupload> files) {
		this.files = files;
	}




	public String getFilePaths() {
		return filePaths;
	}


	public void setFilePaths(String filePaths) {
		this.filePaths = filePaths;
	}




	public String getEmail_id() {
		return email_id;
	}


	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}




	public int getStatusPush() {
		return statusPush;
	}


	public void setStatusPush(int statusPush) {
		this.statusPush = statusPush;
	}




	public String getCamp_id() {
		return camp_id;
	}


	public void setCamp_id(String camp_id) {
		this.camp_id = camp_id;
	}




	public String getMessage_ids() {
		return message_ids;
	}


	public void setMessage_ids(String message_ids) {
		this.message_ids = message_ids;
	}




	public int getEmailValid() {
		return emailValid;
	}


	public void setEmailValid(int emailValid) {
		this.emailValid = emailValid;
	}




	private String account=null;
    private String from=null;
    private String to=null;
    private String cc=null;
    private String bcc=null;
    private String subject=null;
    private String content=null;
    private int result=0;
    private String time_send="";
    private int resend=0;
    private String message_ids ="";
    private String orderid=null;
    private String teamplate=null;
    private String urlpush=null;
    private String teampParam[];
    private String attach[];
    private ArrayList<fileupload>  files;
    private String filePaths="";
    private String email_id=null;
    private int statusPush=0;
    private String camp_id=null;
    private int emailValid=0;
}
