package vc.util;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class BSku
{

    public BSku()
    {

    }


    public void toFile(PrintWriter fout)
        throws IOException
    {

        String line = (new StringBuilder(String.valueOf(getSku())))
        		.append("|").append(getDateD())
        		.append("|").append(getTypeTeamp()).toString();
        fout.println(line);
        fout.flush();
    }

    public static BSku parseString(String line)
    {
        if(line == null)
            return null;
        Collection c = parseString(line, "|");
        if(c.size() < 2)
        {
            System.out.println((new StringBuilder("Invalid BSku String: ")).append(line).toString());
            return null;
        } else
        {
            
            Iterator it = c.iterator();
            BSku mo = new BSku();
            mo.setSku((String)it.next());
            mo.setDateD(Integer.parseInt((String)it.next()));
            mo.setTypeTeamp(Integer.parseInt((String)it.next()));
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






	public String getSku() {
		return sku;
	}


	public void setSku(String sku) {
		this.sku = sku;
	}



    public int getDateD() {
		return dateD;
	}


	public void setDateD(int dateD) {
		this.dateD = dateD;
	}



	public int getTypeTeamp() {
		return typeTeamp;
	}


	public void setTypeTeamp(int typeTeamp) {
		this.typeTeamp = typeTeamp;
	}



	private String sku ="";
    private int dateD=0;
    private int typeTeamp=0;

}
