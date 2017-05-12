
package vc;

import java.io.*;
import java.util.*;

// Referenced classes of package vc:
//            APIBR

public class WSConfig
{

    public WSConfig()
    {

		
    }



    public static void loadPropertiesConfig()
        throws IOException
    {
        FileInputStream propsFile = new FileInputStream((new StringBuilder(String.valueOf(dirFileLog))).append("conf/wsconfig.properties").toString());
        properties.load(propsFile);
        propsFile.close();
        dirLog = properties.getProperty("dirLog", dirLog);
        dirFileLog = properties.getProperty("dirFileLog", dirFileLog);
        fileNameMT = properties.getProperty("fileNameMT", fileNameMT);
        urlSOS=properties.getProperty("urlSOS", urlSOS);
        keyID=properties.getProperty("keyID", keyID);
        secretKey=properties.getProperty("secretKey", secretKey);
        emailRecReport=properties.getProperty("emailRecReport", emailRecReport);
        emailRecReportByH=properties.getProperty("emailRecReportByH", emailRecReportByH);
        timeRecReport=properties.getProperty("timeRecReport", timeRecReport);
        enableReport=properties.getProperty("enableReport", enableReport);
        enableEmailVerify=getIntProperty("enableEmailVerify", enableEmailVerify);
        urlEmailVerify=properties.getProperty("urlEmailVerify", urlEmailVerify);
        redisPort=getIntProperty("redisPort", redisPort);
        redisHost=properties.getProperty("redisHost", redisHost);
        redisAuth=properties.getProperty("redisAuth", redisAuth);
        emailReport=properties.getProperty("emailReport", emailReport);
        emailCskh=properties.getProperty("emailCskh", emailCskh);
        enableResend=getIntProperty("enableResend", enableResend);
        enableRepushStatus=getIntProperty("enableRepushStatus", enableRepushStatus);
        enableUrlPush=getIntProperty("enableRepushStatus", enableRepushStatus);
        setProxy=getIntProperty("set_proxy", setProxy);
        apiTeamplateUrl=properties.getProperty("apiTeamplateUrl", apiTeamplateUrl);
        apiTeamplateUser=properties.getProperty("apiTeamplateUser", apiTeamplateUser);
        apiTeamplatePass=properties.getProperty("apiTeamplatePass", apiTeamplatePass);
        autoHour=getIntProperty("autoHour", autoHour);
        autoSendTypeTeamp=properties.getProperty("autoSendTypeTeamp", autoSendTypeTeamp);
        autoUpdateHour=getIntProperty("autoUpdateHour", autoUpdateHour);
        autoNumberDate=getIntProperty("autoNumberDate", autoNumberDate);
        urlReject=properties.getProperty("urlReject", urlReject);
        htmlTracking=properties.getProperty("htmlTracking", htmlTracking);
        tempNameT=properties.getProperty("tempName", tempNameT);
        tempName=tempNameT.split(",");
        campLimit=getIntProperty("campLimit", campLimit);
        urlSendMe=properties.getProperty("urlSendMe", urlSendMe);
        threadpoolsendemail=getIntProperty("threadpoolsendemail", threadpoolsendemail);
        enableUpdateReject=getIntProperty("enableUpdateReject", enableUpdateReject);
        
        googlemailEnable=getIntProperty("googlemailEnable", googlemailEnable);
        googlemailHourRun=getIntProperty("googlemailHourRun", googlemailHourRun);
        googlemailAcc=properties.getProperty("googlemailAcc", googlemailAcc);
        googlemailPass=properties.getProperty("googlemailPass", googlemailPass);
        googlemailFolder=properties.getProperty("googlemailFolder", googlemailFolder);
        googlemailEmailSend=properties.getProperty("googlemailEmailSend", googlemailEmailSend);
        googlemailSubject=properties.getProperty("googlemailSubject", googlemailSubject);
        
    }
	static int getIntProperty(String propName, int defaultValue)
	{
		return Integer.parseInt(properties.getProperty(propName, Integer.toString(defaultValue)).trim());
	}
    public static void initLoadProperties()
    {
        try
        {
            allowIPs = loadAllowIPProperties();
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder("Can not find config-file '")).append(allowIPsDir).append("'").toString());
            e.printStackTrace();
        }
        try
        {
            allowUsers = loadAllowUserProperties();
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder("Can not find config-file '")).append(allowUsersDir).append("'").toString());
            e.printStackTrace();
        }
        dir = new File(dirLog);
        if(!dir.exists())
            dir.mkdir();
    }

    private static Hashtable loadAllowIPProperties()
        throws Exception
    {
        Hashtable proHashTable = null;
        FileInputStream in = new FileInputStream((new StringBuilder(String.valueOf(dirFileLog))).append("conf/allowIPs.properties").toString());
        if(proHashTable == null)
            proHashTable = new Hashtable();
        else
            proHashTable.clear();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        for(String temp = reader.readLine(); temp != null; temp = reader.readLine())
            if(!temp.equals(""))
            {
                String tk;
                for(StringTokenizer token = new StringTokenizer(temp, ","); token.hasMoreTokens(); proHashTable.put(tk, "allow"))
                {
                    tk = token.nextToken();
                    tk = tk.trim();
                }

            }

        return proHashTable;
    }

    private static Hashtable loadAllowUserProperties()
        throws Exception
    {
        Hashtable proHashTable = null;
        FileInputStream in = new FileInputStream((new StringBuilder(String.valueOf(dirFileLog))).append("conf/allowUsers.properties").toString());
        if(proHashTable == null)
            proHashTable = new Hashtable();
        else
            proHashTable.clear();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        for(String temp = reader.readLine(); temp != null; temp = reader.readLine())
            if(!temp.equals(""))
            {
                String inLine[] = temp.split(",");
                System.out.println((new StringBuilder(String.valueOf(inLine[0]))).append(",").append(inLine[1]).toString());
                proHashTable.put(inLine[0], inLine[1]);
            }

        return proHashTable;
    }


    public static Hashtable allowIPs = new Hashtable();
    public static Hashtable allowUsers = new Hashtable();
    static String allowIPsDir = "./conf/allowIPs.properties";
    static String allowUsersDir = "./conf/allowUsers.properties";
    public static String dirLog = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("/LOG").toString();
    public static String dirFileLog = "";
    public static String fileNameMT = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("/LOG").toString();
    public static String urlSOS="";
    static Properties properties = new Properties();
    static File dir = null;
    public static String keyID = "AKIAIN4XNYFOT2Q3XSDQ";
    public static String secretKey ="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";
    public static String emailRecReport ="hungnn21@fpt.com.vn";
    public static String emailRecReportByH ="hungnn21@fpt.com.vn";
    public static String timeRecReport="8";
    public static String enableReport="0";
    public static int enableEmailVerify=1;
    public static String urlEmailVerify="";
    public static int redisPort=6379;
    public static String redisHost="localhost";
    public static String redisAuth=null;
    public static String emailReport="cskh@fptshop.com.vn";
    public static String emailCskh="cskh@fptshop.com.vn";
    public static int enableResend=0;
    public static int enableRepushStatus=0;
    public static int enableUrlPush=0;
    public static int setProxy=0;
    public static String apiTeamplateUrl="";
    public static String apiTeamplateUser="";
    public static String apiTeamplatePass="";
    public static int autoHour=3;
    public static String autoSendTypeTeamp="";
    public static int autoUpdateHour=5;
    public static int autoNumberDate=1;
    public static String urlReject="";
    public static String htmlTracking="";
    public static String tempNameT;
    public static String[] tempName;
    public static int campLimit=1000;
    public static int threadpoolsendemail=10;
    public static String urlSendMe="";
    public static int enableUpdateReject=0;
    public static int googlemailEnable=0;
    public static int googlemailHourRun=25;    
    public static String googlemailAcc="";
    public static String googlemailPass="";
    public static String googlemailFolder="";
    public static String googlemailEmailSend="";
    public static String googlemailSubject="";
    public static String googlemailSubject1="";
}
