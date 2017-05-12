
package vc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Properties;


import vc.common.LoggerConfig;
import vc.database.DBConfig;
import vc.database.DBPool;
import vc.process.ReCallRequestBR;
import vc.process.ReRequestUrlPushFromDB;
import vc.process.ReSendFromDB;
import vc.process.RequestBR;
import vc.process.RequestSendMe;
import vc.process.ScanEmailVerifyFromDB;
import vc.process.autoOpenGmail;
import vc.process.autoSend;
import vc.process.autoUpdateReject;
import vc.process.autoUpdateShopOrder;
import vc.process.loadEmailFromDB;
import vc.process.recallSku;
import vc.process.reportEmail;
import vc.process.reportEmailByH;
import vc.process.requestCamp;
import vc.process.requestOrder;
import vc.process.requestUrlPush;
import vc.process.updateStatus;

import vc.util.CampQueue;
import vc.util.OrderQueue;
import vc.util.Queue;
import vc.util.RejectQueue;
import vc.util.SMSQueue;
import vc.util.SkuQueue;
import vc.util.UrlPushQueue;
import vc.util.logger.Logger;
import vc.util.logger.PLoggerDB;
import vc.util.logger.PLoggerDBCamp;
import vc.util.logger.PLoggerDBOrder;
import vc.util.logger.PLoggerDBReject;
import vc.util.logger.PLoggerFile;
import vc.util.logger.PLoggerFileCamp;
import vc.util.logger.PLoggerFileOrder;
import vc.util.logger.PLoggerFileRuningCamp;
import ws.sendSMS.ASendSMS;

// Referenced classes of package vc:
//            WSConfig

public class VCSMS extends Thread
{
	static BufferedReader keyboard;

    public VCSMS()
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
		Logger.getInstance().logInfo("VCSMS Starting....", null, null);
			VCSMS vcsms= new VCSMS();
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
	        vcsms.start();
	}
	private void showMenu()
	{
		System.out.println("---------------------");
		System.out.println("R - reload config");
		System.out.println("N - set redis from DB");
		System.out.println("J - set email reject to redis from DB");
		System.out.println("V - check email valid from DB");
		System.out.println("S - Scan email verify from DB shoporder");
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
		}else if("V".equals(option.toUpperCase())){
		       //load data from db insert redis
			loadEmailFromDB.checkEmailValid();
		}else if("S".equals(option.toUpperCase())){
		       //load data from db insert redis
			ScanEmailVerifyFromDB.checkEmailValid(keyboard);			
			
		} else if("Q".equals(option.toUpperCase())){
			exit();
		}
	}
	public static void exit()
	{
		running = false;
		Queue.storeAllQueueData();
		DBPool.release();
		Logger.getInstance().logInfo("VCSMS Exit....", null, null);
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
      //  (new loadConfig()).start();
        (new ReCallRequestBR()).start();
        (new ReSendFromDB()).start();
        (new requestUrlPush()).start();
        (new ReRequestUrlPushFromDB()).start();
        (new reportEmail()).start();
        (new reportEmailByH()).start();
        (new updateStatus()).start();
        
        (new requestOrder()).start();
        (new PLoggerFileOrder()).start();
        (new PLoggerDBOrder()).start();
        (new PLoggerFileCamp()).start();
        (new PLoggerDBCamp()).start();
        (new requestCamp()).start();
        (new PLoggerFileRuningCamp()).start();
        (new autoSend()).start();
        (new recallSku()).start();
        (new RequestSendMe()).start();
     //   (new autoUpdateShopOrder()).start();
        if(WSConfig.enableUpdateReject==1){
        	(new autoUpdateReject()).start();
        } 
        if(WSConfig.googlemailEnable==1){
        	(new autoOpenGmail()).start();
        	(new PLoggerDBReject()).start();
        }
        running=true;
		
		for(; isRunning(); showMenu()) { }
	}
	public static long getEmailId() {
		return emailId;
	}
	public static void setEmailId(long emailId) {
		VCSMS.emailId = emailId;
	}
	public static Queue getRequestUrlPushQueue() {
		return requestUrlPushQueue;
	}
	public static void setRequestUrlPushQueue(Queue requestUrlPushQueue) {
		VCSMS.requestUrlPushQueue = requestUrlPushQueue;
	}
	public static Queue getUpdateStatusQueue() {
		return updateStatusQueue;
	}
	public static void setUpdateStatusQueue(Queue updateStatusQueue) {
		VCSMS.updateStatusQueue = updateStatusQueue;
	}
	public static Queue getLogFileQueueOrder() {
		return logFileQueueOrder;
	}
	public static void setLogFileQueueOrder(Queue logFileQueueOrder) {
		VCSMS.logFileQueueOrder = logFileQueueOrder;
	}
	public static Queue getLogDBQueueOrder() {
		return logDBQueueOrder;
	}
	public static void setLogDBQueueOrder(Queue logDBQueueOrder) {
		VCSMS.logDBQueueOrder = logDBQueueOrder;
	}
	public static Queue getLogFileQueueCamp() {
		return logFileQueueCamp;
	}
	public static void setLogFileQueueCamp(Queue logFileQueueCamp) {
		VCSMS.logFileQueueCamp = logFileQueueCamp;
	}
	public static Queue getLogDBQueueCamp() {
		return logDBQueueCamp;
	}
	public static void setLogDBQueueCamp(Queue logDBQueueCamp) {
		VCSMS.logDBQueueCamp = logDBQueueCamp;
	}
	public static Queue getRequestQueueCamp() {
		return requestQueueCamp;
	}
	public static void setRequestQueueCamp(Queue requestQueueCamp) {
		VCSMS.requestQueueCamp = requestQueueCamp;
	}
	public static Queue getSkuQueue() {
		return skuQueue;
	}
	public static void setSkuQueue(Queue skuQueue) {
		VCSMS.skuQueue = skuQueue;
	}
	public static Queue getRequestSendMeQueue() {
		return requestSendMeQueue;
	}
	public static void setRequestSendMeQueue(Queue requestSendMeQueue) {
		VCSMS.requestSendMeQueue = requestSendMeQueue;
	}
	public static Queue getRequestQueueOrder() {
		return requestQueueOrder;
	}
	public static void setRequestQueueOrder(Queue requestQueueOrder) {
		VCSMS.requestQueueOrder = requestQueueOrder;
	}
	public static Queue getLogRuningQueueCamp() {
		return logRuningQueueCamp;
	}
	public static void setLogRuningQueueCamp(Queue logRuningQueueCamp) {
		VCSMS.logRuningQueueCamp = logRuningQueueCamp;
	}

	public static Queue getLogDBQueueReject() {
		return logDBQueueReject;
	}
	public static void setLogDBQueueReject(Queue logDBQueueReject) {
		VCSMS.logDBQueueReject = logDBQueueReject;
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
    private static Queue requestSendMeQueue= new SMSQueue("requestSendMe");
    private static Queue logFileQueue= new SMSQueue("LogFile");
    private static Queue logDBQueue= new SMSQueue("LogDB");
    private static long emailId = 0;
    private static Queue requestUrlPushQueue= new UrlPushQueue("requestUrlPushQueue");
    
    private static Queue updateStatusQueue= new UrlPushQueue("updateStatus");
    private static Queue requestQueueOrder= new OrderQueue("requestOrder");
    private static Queue logFileQueueOrder= new OrderQueue("LogFileOrder");
    private static Queue logDBQueueOrder= new OrderQueue("LogDBOrder");
    private static Queue logFileQueueCamp= new CampQueue("LogFileCamp");
    private static Queue logDBQueueCamp= new CampQueue("LogDBCamp");
    private static Queue requestQueueCamp= new CampQueue("requestCamp");//queue cac camp de cho den time send roi thuc hien gui
    private static Queue logRuningQueueCamp= new CampQueue("LogRungingCamp");
    private static Queue skuQueue= new SkuQueue("skuTeamp");
    
    private static Queue logDBQueueReject= new RejectQueue("logDBReject");
   
}
