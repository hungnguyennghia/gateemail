// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   beanBR.java

package vc.util;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
public class BUrlPush
{

    public BUrlPush()
    {

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
            
        String line = (new StringBuilder(String.valueOf(getMessage_ids())))
        		.append("|").append(getUrlpush().equals("")?"null":getUrlpush())
        		.append("|").append(getRequestId())
        		.append("|").append(getErrorCode().equals("")?"null":getErrorCode())
        		.append("|").append(getErrorMessage().equals("")?"null":getErrorMessage())
        		.append("|").append(getResend())
        		.append("|").append(getResult())
        		.append("|").append(getTimeSend()).toString();
        
        
        fout.println(line);
        fout.flush();
    }

    public static BUrlPush parseString(String line)
    {
        if(line == null)
            return null;
        Collection c = parseString(line, "|");
        if(c.size() < 7)
        {
            System.out.println((new StringBuilder("Invalid BUrlPush String: ")).append(line).toString());
            return null;
        } else
        {
    
            Iterator it = c.iterator();
            BUrlPush mo = new BUrlPush();
            mo.setMessage_ids((String)it.next());    
            String urlP=(String)it.next();
            mo.setUrlpush(urlP.equals("null")?"":urlP);
            String requestId=(String)it.next();
            mo.setRequestId(requestId.equals("null")?"":requestId);
            String errorCode=(String)it.next();
            mo.setErrorCode(errorCode.equals("null")?"":errorCode);            
            String errorMessage=(String)it.next();
            mo.setErrorMessage(errorMessage.equals("null")?"":errorMessage);
            mo.setResend(Integer.parseInt((String)it.next()));
            mo.setResult(Integer.parseInt((String)it.next()));
            mo.setTimeSend(Long.parseLong((String)it.next()));
//            System.out.println(mo.getMessage_ids()+"|"+mo.getUrlpush()+"|"+mo.getRequestId()+"|"+mo.getErrorCode()
//            		+"|"+mo.getErrorMessage()+"|"+mo.getResend()+"|"+mo.getResult()+"|"+mo.getTimeSend());
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



	public int getResend() {
		return resend;
	}


	public void setResend(int resend) {
		this.resend = resend;
	}




	public String getRequestId() {
		return requestId;
	}


	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}


	public String getUrlpush() {
		if(urlpush != null && !urlpush.isEmpty()){
			return urlpush;
		}else{
			return "null";
		}
		 
	}


	public void setUrlpush(String urlpush) {
		if(urlpush.equals("null")){
			this.urlpush= "";
		}else{
			this.urlpush= urlpush;
		}
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


    public int getStatusPush() {
		return statusPush;
	}



	public void setStatusPush(int statusPush) {
		this.statusPush = statusPush;
	}


	public String getMessage_ids() {
		return message_ids;
	}



	public void setMessage_ids(String message_ids) {
		this.message_ids = message_ids;
	}


	public long getTimeSend() {
		return timeSend;
	}



	public void setTimeSend(long timeSend) {
		this.timeSend = timeSend;
	}


	private int resend=0;
    private int result=0;
    private String message_ids ="";
    private String requestId=null;
    private String errorCode=null;
    private String errorMessage=null;
    private int statusPush=0;
    private String urlpush=null;
    private long timeSend=0;
}
