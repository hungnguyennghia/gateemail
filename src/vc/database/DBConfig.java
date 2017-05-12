package vc.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class DBConfig
{

    public DBConfig()
    {
    }

    public static boolean isDatabaseEnabled()
    {
        return databaseEnabled;
    }

    public static void loadProperties()
        throws IOException
    {
//    	InputStream propsFile = APISOS.class.getResourceAsStream("./conf/db.properties");
       FileInputStream propsFile = new FileInputStream("./conf/db.properties");
        properties.load(propsFile);
        propsFile.close();
        db_DriverClassName = properties.getProperty("db_driver", db_DriverClassName);
        db_URL = properties.getProperty("db_url", db_URL);
        db_user = properties.getProperty("db_user", db_user);
        db_password = properties.getProperty("db_password", db_password);
//        if(db_password != null)
//            try
//            {
//                db_password = Encrypter.decrypt(db_password);
//            }
//            catch(Exception ex)
//            {
//                System.out.println("loadProperties: " + ex.getMessage());
//            }
        db_MaxConnections = getIntProperty("db_max_connections", db_MaxConnections);
        int i = getIntProperty("database_enabled", 0);
        if(i == 1)
            databaseEnabled = true;
        else
            databaseEnabled = false;
        System.out.println("Database\t" + (isDatabaseEnabled() ? "enabled" : "disabled"));
    }

    static byte getByteProperty(String propName, byte defaultValue)
    {
        return Byte.parseByte(properties.getProperty(propName, Byte.toString(defaultValue)).trim());
    }

    static int getIntProperty(String propName, int defaultValue)
    {
        return Integer.parseInt(properties.getProperty(propName, Integer.toString(defaultValue)).trim());
    }

    private static final String fileName = "./conf/db.properties";
    public static String db_DriverClassName = "org.postgresql.Driver";
    public static String db_URL = "jdbc:postgresql://127.0.0.1:5432/logsms";
    public static String db_user = "postgres";
    public static String db_password = "123#@!";
    public static int db_MaxConnections = 3;
    public static boolean databaseEnabled = true;
    private static Properties properties = new Properties();

}
