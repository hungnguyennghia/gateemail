
package vc.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import vc.VCSMS;
import vc.database.DBTool;
import vc.util.BOrder;

public class ScanEmailVerifyFromDB 
{
	private static String fdate="20170302"; 
	private static String tdate="20170303";
	
    public ScanEmailVerifyFromDB()
    {
    }

    public static void checkEmailValid(BufferedReader reader)
    {
    	System.out.println("checkEmailValid..");

        fdate = getParam(reader, "From date: ", fdate);
        tdate   = getParam(reader, "To date: ", tdate);  	
    	
		DBTool db =new DBTool();

		ArrayList beans1= db.getAllEmailShopOrder(fdate, tdate);
		for (int i = 0; i < beans1.size(); i++) {
			BOrder bean=(BOrder)beans1.get(i);
			
			VCSMS.getRequestQueueOrder().enqueue(bean);
		}
	

    }
    
    private static String getParam(BufferedReader reader, String prompt, String defaultValue) {
        String value = "";
        String promptFull = prompt;
        promptFull += defaultValue == null ? "" : " ["+defaultValue+"] ";
        System.out.print(promptFull);
        try {
            value = reader.readLine();
        } catch (IOException e) { }
        if (value.compareTo("") == 0) {
            return defaultValue;
        } else {
            return value;
        }
    }
}
