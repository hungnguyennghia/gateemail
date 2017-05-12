// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   detextAcc.java

package vc.util;

import java.io.*;
import java.util.Hashtable;
import vc.WSConfig;

public class detextAcc
{

    public detextAcc()
    {
    }

    public static void main(String args[])
    {
        try
        {
            setAccountsFile();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println((new StringBuilder("getUserName:")).append(getUserName("SL")).toString());
        System.out.println((new StringBuilder("getPass:")).append(getPass("SL")).toString());
        System.out.println((new StringBuilder("getContent:")).append(getContent("SL")).toString());
    }

    public static void setAccountsFile()
    {
        BufferedReader fin = null;
        try
        {
            byte b[] = FileTool.readFile(accountsFile);
            if(b.length != 0)
                try
                {
                    fin = new BufferedReader(new FileReader(accountsFile));
                    String tokens[] = (String[])null;
                    String splitPattern = ":";
                    for(String line = null; (line = fin.readLine()) != null;)
                    {
                        tokens = line.split(splitPattern);
                        try
                        {
                            accounts.put(tokens[0].trim(), tokens[1].trim());
                        }
                        catch(Exception exception) { }
                    }

                    fin.close();
                }
                catch(FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
    }

    public static void getAcc(String command_code)
    {
        if(!accounts.equals(null) || !accounts.isEmpty() || accounts.size() != 0)
            System.out.println(accounts.get(command_code));
        else
            System.out.println("accounts not init");
    }

    public static String getUserName(String command_code)
    {
        String userName = "";
        if(!accounts.equals(null) || !accounts.isEmpty() || accounts.size() != 0)
        {
            String temp = accounts.get(command_code).toString();
            userName = temp.substring(0, temp.indexOf("_"));
        } else
        {
            System.out.println("accounts not init");
        }
        return userName;
    }

    public static String getPass(String command_code)
    {
        String pass = "";
        if(!accounts.equals(null) || !accounts.isEmpty() || accounts.size() != 0)
        {
            String temp = accounts.get(command_code).toString();
            pass = temp.substring(temp.indexOf("_") + 1, temp.indexOf("_("));
        } else
        {
            System.out.println("accounts not init");
        }
        return pass;
    }

    public static String getContent(String command_code)
    {
        String content = "";
        if(!accounts.equals(null) || !accounts.isEmpty() || accounts.size() != 0)
        {
            String temp = accounts.get(command_code).toString();
            content = temp.substring(temp.indexOf("_(") + 1);
            if(content.length() <= 2)
                content = " ";
        } else
        {
            System.out.println("accounts not init");
        }
        return content;
    }

    private static Hashtable accounts = new Hashtable();
    private static final String accountsFile;

    static 
    {
        accountsFile = (new StringBuilder(String.valueOf(WSConfig.dirFileLog))).append("conf/acc.conf").toString();
    }
}
