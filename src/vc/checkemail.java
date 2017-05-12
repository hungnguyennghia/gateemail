
package vc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import vc.common.LoggerConfig;
import vc.database.DBConfig;
import vc.database.DBPool;
import vc.database.DBTool;
import vc.process.ReCallRequestBR;
import vc.process.ReRequestUrlPushFromDB;
import vc.process.ReSendFromDB;
import vc.process.RequestBR;
import vc.process.loadConfig;
import vc.process.loadEmailFromDB;
import vc.process.reportEmailByH;
import vc.process.requestUrlPush;

import vc.util.Queue;
import vc.util.SMSQueue;
import vc.util.beanBR;
import vc.util.logger.PLoggerDB;
import vc.util.logger.PLoggerFile;
import ws.sendSMS.ASendSMS;

// Referenced classes of package vc:
//            WSConfig

public class checkemail extends Thread
{
	static BufferedReader keyboard;

    public checkemail()
    {
    	
        allowIPs = WSConfig.allowIPs;
        allowUsers = WSConfig.allowUsers;
        dirLog = WSConfig.dirLog;
    }

	static 
	{
		keyboard = new BufferedReader(new InputStreamReader(System.in));
	}


	public static void main(String[] args)
	{
			checkemail vcsms= new checkemail();
	        try
	        {
	        	WSConfig.loadPropertiesConfig();
	        }
	        catch(Exception e)
	        {
	            System.out.println("khong doc duoc file ./conf/loadPropertiesConfig.properties");
	        }
	        try
	        {
	            LoggerConfig.loadProperties();
	        }
	        catch(IOException ex)
	        {
	            ex.printStackTrace();
	        }
	        try
	        {
	            DBConfig.loadProperties();
	        }
	        catch(IOException e)
	        {
	            System.out.println((new StringBuilder("Khong tim thay file cau hinh: ")).append(e.getMessage()).toString());
	        }

	        DBPool.load();
	        WSConfig.initLoadProperties();
	        DBTool db=new DBTool();
	        emailId=db.getId();
	        System.out.println("emailId:"+emailId);
	        vcsms.start();
	}
	private void showMenu()
	{
		System.out.println("---------------------");
		System.out.println("R - reload config");
		System.out.println("N - set redis from DB");
		System.out.println("J - set email reject to redis from DB");
		System.out.println("Q - Quit");
		System.out.println("---------------------");
		String option = "";
		try
		{
			option = keyboard.readLine();
		}
		catch(Exception e)
		{
			System.out.println("showMenu:" + e.getMessage());
		}
		if("R".equals(option.toUpperCase())){
	        try
	        {
	        	WSConfig.loadPropertiesConfig();
	        }
	        catch(Exception e)
	        {
	            System.out.println("khong doc duoc file ./conf/loadPropertiesConfig.properties");
	        }
	        WSConfig.initLoadProperties();
		}else if("N".equals(option.toUpperCase())){
		       //load data from db insert redis
			loadEmailFromDB.insertEmailToRedis();
		}else if("J".equals(option.toUpperCase())){
		       //load data from db insert redis
			loadEmailFromDB.insertEmailRejectToRedis();
			
			
		} else if("Q".equals(option.toUpperCase())){
			exit();
		}
	}
	public static void exit()
	{
		running = false;
		Queue.storeAllQueueData();
		DBPool.release();
		System.exit(1);
	}
	
	public static boolean isRunning()
	{
		return running;
	}
	
	public static void setRunning(boolean vl){
		running = vl;
	}
	
    public static Queue getRequestBRQueue()
    {
        return requestBRQueue;
    }
    public static Queue getRecallRequestBRQueue()
    {
        return recallRequestBRQueue;
    }
    public static Queue getLogFileQueue()
    {
        return logFileQueue;
    }

    public static Queue getLogDBQueue()
    {
        return logDBQueue;
    }
	public void run()
	{

        (new RequestBR()).start();
        (new PLoggerFile()).start();
        (new PLoggerDB()).start();
        (new ASendSMS()).start();
        (new loadConfig()).start();
        (new ReCallRequestBR()).start();
        (new ReSendFromDB()).start();
        (new requestUrlPush()).start();
        (new ReRequestUrlPushFromDB()).start();
//        (new reportEmail()).start();
        (new reportEmailByH()).start();
		isrunning=true;
		
		for(; isRunning(); showMenu()) { }
	}
	public static long getEmailId() {
		if(emailId<=0){
			DBTool db=new DBTool();
			emailId=db.getId()+1;
		}else{
			emailId++;
			}
		return emailId;
	}
	public static void setEmailId(long emailId) {
		checkemail.emailId = emailId;
	}
	public static Queue getRequestUrlPushQueue() {
		return requestUrlPushQueue;
	}
	public static void setRequestUrlPushQueue(Queue requestUrlPushQueue) {
		checkemail.requestUrlPushQueue = requestUrlPushQueue;
	}
	private static boolean isrunning=false;
	private static boolean running = true;	
    static String remoteClient = null;
    static String state = null;
    static Hashtable allowIPs = new Hashtable();
    static Hashtable allowUsers = new Hashtable();
    static String receiver = null;
    static File dir = null;
    static final String _PREFIX = "\n";
    static String allowIPsDir = "./conf/allowIPs.properties";
    static String allowUsersDir = "./conf/allowUsers.properties";
    static String dirLog = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("/LOG").toString();
    static Properties properties = new Properties();
    static boolean wscpStatus = false;
    private static Queue recallRequestBRQueue= new SMSQueue("recallRequestBR");
    private static Queue requestBRQueue= new SMSQueue("requestBR");
    private static Queue logFileQueue= new SMSQueue("LogFile");
    private static Queue logDBQueue= new SMSQueue("LogDB");
    private static long emailId = 0;
    private static Queue requestUrlPushQueue= new SMSQueue("requestUrlPushQueue");
}
