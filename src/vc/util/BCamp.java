package vc.util;

import java.io.*;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class BCamp
{

    public BCamp()
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

  

    public void toFile(PrintWriter fout)
        throws IOException
    {
        Base64.Encoder enc= Base64.getEncoder();
        
        //encoding  byte array into base 64
            
         byte[] subjectenc =enc.encode(getSubject().getBytes("UTF-8"));
         byte[] contentenc =enc.encode(getContent().getBytes("UTF-8"));
        String line = (new StringBuilder(String.valueOf(getAccount())))
        		.append("|").append(getNameCamp())
        		.append("|").append(getNganhHang().equals("")?"null":getNganhHang())
        		.append("|").append(getLoaiSP().equals("")?"null":getLoaiSP())
        		.append("|").append(getGiaTu())
        		.append("|").append(getGiaDen())
        		.append("|").append(getTgMuaTu().equals("")?"null":getTgMuaTu())
        		.append("|").append(getTgMuaDen().equals("")?"null":getTgMuaDen())
        		.append("|").append(getTgGui())	
        		.append("|").append(getTeamplate())
        		.append("|").append(new String(subjectenc,"UTF-8"))
        		.append("|").append(new String(contentenc,"UTF-8"))
        		.append("|").append(getRequestID())
        		.append("|").append(getMobileoperator().equals("")?"null":getMobileoperator())
        		.append("|").append(getCountRows())
        		.append("|").append(getOffset())
        		.append("|").append(getUnique())
        		.append("|").append(getSendTest())
        		.toString();
        fout.println(line);
        fout.flush();
    }

    public static BCamp parseString(String line)
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
            BCamp mo = new BCamp();
            mo.setAccount((String)it.next());
            mo.setNameCamp((String)it.next());
            String nganhHang=(String)it.next();
            mo.setNganhHang(nganhHang.equals("null")?"":nganhHang);
            String loaiSP=(String)it.next();
            mo.setLoaiSP(loaiSP.equals("null")?"":loaiSP);           
            mo.setGiaTu(Long.parseLong((String)it.next()));
            mo.setGiaDen(Long.parseLong((String)it.next()));
            String tgMuatu=(String)it.next();
            mo.setTgMuaTu(tgMuatu.equals("null")?"":tgMuatu);
            String tgMuaden=(String)it.next();
            mo.setTgMuaDen(tgMuaden.equals("null")?"":tgMuaden);
            mo.setTgGui((String)it.next());
            mo.setTeamplate((String)it.next());
            byte[] subjectdec=dec.decode((String)it.next());
            byte[] contentdec=dec.decode((String)it.next());
            try {
				mo.setSubject(new String(subjectdec,"UTF-8"));
				mo.setContent(new String(contentdec,"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            mo.setRequestID((String)it.next());
            String mobileoperator=(String)it.next();
            mo.setMobileoperator(mobileoperator.equals("null")?"":mobileoperator);
            mo.setCountRows(Long.parseLong((String)it.next()));
            mo.setOffset(Integer.parseInt((String)it.next()));
            mo.setUnique(Integer.parseInt((String)it.next()));
            mo.setSendTest(Integer.parseInt((String)it.next()));
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

	public String getNganhHang() {
		return nganhHang;
	}


	public void setNganhHang(String nganhHang) {
		this.nganhHang = nganhHang;
	}

	public String getLoaiSP() {
		return loaiSP;
	}


	public void setLoaiSP(String loaiSP) {
		this.loaiSP = loaiSP;
		
	}


	public String getNameCamp() {
		return nameCamp;
	}


	public void setNameCamp(String nameCamp) {
		this.nameCamp = nameCamp;
	}


	public long getGiaTu() {
		return giaTu;
	}


	public void setGiaTu(long giaTu) {
		this.giaTu = giaTu;
	}

	public long getGiaDen() {
		return giaDen;
	}


	public void setGiaDen(long giaDen) {
		this.giaDen = giaDen;
	}


	public String getTgGui() {
		return tgGui;
	}


	public void setTgGui(String tgGui) {
		this.tgGui = tgGui;
	}



	public String getRequestID() {
		return requestID;
	}


	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}



	public int getSended() {
		return sended;
	}


	public void setSended(int sended) {
		this.sended = sended;
	}



	public String getTgMuaTu() {
		return tgMuaTu;
	}


	public void setTgMuaTu(String tgMuaTu) {
		this.tgMuaTu = tgMuaTu;
	}


	public String getTgMuaDen() {
		return tgMuaDen;
	}


	public void setTgMuaDen(String tgMuaDen) {
		this.tgMuaDen = tgMuaDen;
	}

	public String getTeamplate() {
		return teamplate;
	}


	public void setTeamplate(String teamplate) {
		this.teamplate = teamplate;
	}












	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}












	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}












	public String getMobileoperator() {
		return mobileoperator;
	}


	public void setMobileoperator(String mobileoperator) {
		this.mobileoperator = mobileoperator;
	}



	public int getOffset() {
		return offset;
	}


	public void setOffset(int offset) {
		this.offset = offset;
	}












	public long getCountRows() {
		return countRows;
	}


	public void setCountRows(long countRows) {
		this.countRows = countRows;
	}



	public int getUnique() {
		return unique;
	}


	public void setUnique(int unique) {
		this.unique = unique;
	}



	public int getSendTest() {
		return sendTest;
	}


	public void setSendTest(int sendTest) {
		this.sendTest = sendTest;
	}



	private String account="";
    private String nameCamp="";
    private String nganhHang="";
    private String loaiSP="";
    private long giaTu=0;
    private long giaDen=0;
    private String tgMuaTu ="";
    private String tgMuaDen ="";
    private String tgGui ="";
    private String teamplate="";
    private String content="";
    private String mobileoperator="";
    private String subject="";
    private String requestID="";
    private int sended=0;
    private long countRows=0;
    private int offset=0;
    private int unique=0;
    private int sendTest=0;
}
