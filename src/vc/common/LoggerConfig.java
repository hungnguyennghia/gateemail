// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoggerConfig.java

package vc.common;

import java.io.*;
import java.util.*;
import vc.WSConfig;

public class LoggerConfig
{

    public LoggerConfig()
    {
    }

    public static void loadProperties()
        throws IOException
    {
        System.out.println("Loading ...");
        FileInputStream propsFile = new FileInputStream((new StringBuilder(String.valueOf(WSConfig.dirFileLog))).append("conf/logger.conf").toString());
        getProperties().load(propsFile);
        propsFile.close();
        LOG_TO_FILE = getIntProperty("log_to_file", LOG_TO_FILE);
        LOG_TO_CONSOLE = getIntProperty("log_to_console", LOG_TO_CONSOLE);
        LOG_TO_FOLDER = getProperties().getProperty("log_to_folder", LOG_TO_FOLDER);
        LOG_FULL_MESSAGE = getIntProperty("log_full_message", LOG_FULL_MESSAGE);
        MO_LOG_FOLDER = getProperties().getProperty("mo_log_folder", MO_LOG_FOLDER);
        MT_LOG_FOLDER = getProperties().getProperty("mt_log_folder", MT_LOG_FOLDER);
        MO_ERR_LOG_FOLDER = getProperties().getProperty("mo_err_log_folder", MO_ERR_LOG_FOLDER);
        MT_ERR_LOG_FOLDER = getProperties().getProperty("mt_err_log_folder", MT_ERR_LOG_FOLDER);
        ENABLE_MO_MT_LOG_ALL = getIntProperty("enable_mo_mt_log_all", ENABLE_MO_MT_LOG_ALL);
        WAIT_LOG_FOLDER = getProperties().getProperty("wait_log_folder", WAIT_LOG_FOLDER);
        QUEUE_FOLDER = getProperties().getProperty("queue_folder", QUEUE_FOLDER);
        
        LOG_UPLOAD_FOLDER= getProperties().getProperty("log_upload_folder", LOG_UPLOAD_FOLDER);
        
        LOG_ORDER_FOLDER= getProperties().getProperty("log_order_folder", LOG_ORDER_FOLDER);
        LOG_CAMP_FOLDER= getProperties().getProperty("log_camp_folder", LOG_CAMP_FOLDER);
        LOG_RUN_CAMP_FOLDER= getProperties().getProperty("log_run_camp_folder", LOG_RUN_CAMP_FOLDER);
        LOG_PATH= getProperties().getProperty("log_path", LOG_PATH);
    }

    public static void main(String args[])
    {
        try
        {
            loadProperties();
            System.out.println(debugString());
        }
        catch(IOException ioexception) { }
    }

    public static String debugString(String param)
    {
        if(param != null)
            param = param.toLowerCase();
        String s = "LoggerConfig: logger.conf\r\n";
        List keyList = Collections.list(properties.keys());
        Collections.sort(keyList);
        for(Enumeration sortedEnum = Collections.enumeration(keyList); sortedEnum.hasMoreElements();)
        {
            String key = (String)sortedEnum.nextElement();
            if(key != null)
            {
                key = key.toLowerCase();
                if(param == null || key.startsWith(param))
                {
                    String val = (String)properties.get(key);
                    s = (new StringBuilder(String.valueOf(s))).append(key).append(" = ").append(val).append("\r\n").toString();
                }
            }
        }

        return s;
    }

    public static Properties getProperties()
    {
        return properties;
    }

    public static String debugString()
    {
        String s = "LoggerConfig: logger.conf";
        for(Enumeration enum1 = properties.keys(); enum1.hasMoreElements();)
        {
            String key = (String)enum1.nextElement();
            if(key != null)
            {
                String val = (String)properties.get(key);
                s = (new StringBuilder(String.valueOf(s))).append(key).append(" = ").append(val).append("\r\n").toString();
            }
        }

        return s;
    }

    static byte getByteProperty(String propName, byte defaultValue)
    {
        return Byte.parseByte(properties.getProperty(propName, Byte.toString(defaultValue)).trim());
    }

    static int getIntProperty(String propName, int defaultValue)
    {
        return Integer.parseInt(properties.getProperty(propName, Integer.toString(defaultValue)).trim());
    }

    private static final String fileName = "";
    public static int LOG_TO_FILE = 0;
    public static int LOG_TO_CONSOLE = 0;
    public static String LOG_TO_FOLDER = "./LOG-G";
    public static int LOG_FULL_MESSAGE = 0;
    public static String MO_LOG_FOLDER = "./LOG-MO";
    public static String MT_LOG_FOLDER = "./LOG-MT";
    public static String WAIT_LOG_FOLDER = "./LOG-W";
    public static String QUEUE_FOLDER = "./QUEUE";
    public static int ENABLE_MO_MT_LOG_ALL = 0;
    public static String MO_ERR_LOG_FOLDER = "./LOG-MO-ERR";
    public static String MT_ERR_LOG_FOLDER = "./LOG-MT-ERR";
    private static Properties properties = new Properties();
    public static String LOG_CAMP_FOLDER = "./LOG-CAMP";
    public static String LOG_RUN_CAMP_FOLDER = "./LOG-RUN-CAMP";
    public static String LOG_ORDER_FOLDER = "./LOG-ORDER";
    public static String LOG_UPLOAD_FOLDER = "./UPLOAD";
    public static String LOG_PATH = "/";
}
