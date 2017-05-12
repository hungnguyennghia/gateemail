package vc.util;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;


public class BOrder
{

    public BOrder()
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

        String line = (new StringBuilder(String.valueOf(getAccount())))
        		.append("|").append(getOrderNumber())
        		.append("|").append(getSku())
        		.append("|").append(getProductName())
        		.append("|").append(getCustomerFullName())
        		.append("|").append(getBillingPhoneNumber())
        		.append("|").append(getMobileOperator())
        		.append("|").append(getEmail())
        		.append("|").append(getLoaiSP())
        		.append("|").append(getGiaSP())
        		.append("|").append(getTgMuaHang())
        		.append("|").append(getResult())
        		.append("|").append(getScan()).toString();
        fout.println(line);
        fout.flush();
    }

    public static BOrder parseString(String line)
    {
        if(line == null)
            return null;
        Collection c = parseString(line, "|");
        if(c.size() < 12)
        {
            System.out.println((new StringBuilder("Invalid BOrder String: ")).append(line).toString());
            return null;
        } else
        {
            
            Iterator it = c.iterator();
            BOrder mo = new BOrder();
            mo.setAccount((String)it.next());
            mo.setOrderNumber((String)it.next());
            mo.setSku((String)it.next());
            mo.setProductName((String)it.next());
            mo.setCustomerFullName((String)it.next());
            mo.setBillingPhoneNumber((String)it.next());
            mo.setMobileOperator((String)it.next());
            mo.setEmail((String)it.next());
            mo.setLoaiSP((String)it.next());
            mo.setGiaSP(Long.parseLong((String)it.next()));
            mo.setTgMuaHang((String)it.next());
            mo.setResult(Integer.parseInt((String)it.next()));
            mo.setScan(Integer.parseInt((String)it.next()));
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





	public long getGiaSP() {
		return giaSP;
	}


	public void setGiaSP(long giaSP) {
		this.giaSP = giaSP;
	}





	public String getTgMuaHang() {
		return tgMuaHang;
	}


	public void setTgMuaHang(String tgMuaHang) {
		this.tgMuaHang = tgMuaHang;
	}





	public String getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}





	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}





	public String getCustomerFullName() {
		return customerFullName;
	}


	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}





	public String getBillingPhoneNumber() {
		return billingPhoneNumber;
	}


	public void setBillingPhoneNumber(String billingPhoneNumber) {
		this.billingPhoneNumber = billingPhoneNumber;
	}





	public String getSku() {
		return sku;
	}


	public void setSku(String sku) {
		this.sku = sku;
	}


    public void buildMobileOperator()
    {
        String temp = billingPhoneNumber;
        String result = null;

        result = SMSTool.buildMobileOperator(temp);
        setMobileOperator(result);
    }


	public String getMobileOperator() {
		return mobileOperator;
	}


	public void setMobileOperator(String mobileOperator) {
		this.mobileOperator = mobileOperator;
	}


	public int getScan() {
		return scan;
	}


	public void setScan(int scan) {
		this.scan = scan;
	}


	private String account="";

    private String orderNumber="";
    private String sku="";
    private String productName="";
    private String customerFullName="";
    private String billingPhoneNumber="";

    private String email="";
    private String nganhHang="";    
    private String loaiSP="";
    private long giaSP=0;
    private String tgMuaHang ="";
    private int result=0;
    private String mobileOperator="";
    private int scan=0;

}
