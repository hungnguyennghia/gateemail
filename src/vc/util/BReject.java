package vc.util;

import java.io.*;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class BReject
{

    public BReject()
    {

    }


  

    public void toFile(PrintWriter fout)
        throws IOException
    {
        String line = (new StringBuilder(String.valueOf(getEmail())))
        		.append("|").append(getGendate())
         		.append("|").append(getStatus())
        		.toString();
        fout.println(line);
        fout.flush();
    }

    public static BReject parseString(String line)
    {
        if(line == null)
            return null;
        Collection c = parseString(line, "|");
        if(c.size() < 16)
        {
            System.out.println((new StringBuilder("Invalid BCamp String: ")).append(line).toString());
            return null;
        } else
        {
            Base64.Decoder dec= Base64.getDecoder();

            Iterator it = c.iterator();
            BReject mo = new BReject();
            mo.setEmail((String)it.next());
            mo.setGendate((String)it.next());
            mo.setStatus(Integer.parseInt((String)it.next()));
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


	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}


	public String getGendate() {
		return gendate;
	}




	public void setGendate(String gendate) {
		this.gendate = gendate;
	}


	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}


	private String email="";
    private String gendate="";
    private int status=0;
}
