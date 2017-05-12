//Source File Name:   DBPool.java

package vc.database;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;

public class DBPool
{

	public DBPool()
	{
	}

	public static boolean isDBAvailable()
	{
		return dbAvailable;
	}

	public static boolean isConnecting()
	{
		return isConnecting;
	}

	public static void setDBAvailable(boolean value)
	{
		dbAvailable = value;
	}

	public static void build(int number)
	{
		System.out.println("build: Establishing " + number + " DB connections...");
		Connection conn = null;
		for(int i = 0; i < 2; i++)
		{
			try
			{
				conn = makeDBConnection();
			}
			catch(SQLException ex)
			{
				System.out.println("build: " + ex.getMessage());
				System.out.println("Khong noi dc voi database roi, xem lai di!");
				System.exit(1);
			}
			if(conn != null)
			{
				pool.addLast(conn);
				if(!isDBAvailable())
					setDBAvailable(true);
				isConnecting = true;
				//setDBAvailable(true);
			}
		}

		System.out.println("OK, pool size = " + size());
	}

	public static void rebuildDBPool()
	{
		release();
		Connection conn = null;
		for(boolean ok = false; !ok;)
			try
		{
				conn = makeDBConnection();
				ok = true;
		}
		catch(SQLException e)
		{
			System.out.println("Get DB Connection FAILT. Try later in 10 seconds");
			try
			{
				Thread.sleep(10000L);
			}
			catch(InterruptedException ie) { }
		}

		if(conn != null)
			putConnection(conn);
		if(!isDBAvailable())
			setDBAvailable(true);
		build(1);
	}

	public static Connection getConnection() throws DBException
	{
		Connection conn;
		if(!DBConfig.isDatabaseEnabled())
			throw new DBException("Database is disabled; could not be used.");
		try 
		{
			for(conn = null; conn == null;)
				try
			{
					synchronized(pool)
					{
						if(pool.size() > 0)
							conn = (Connection)pool.removeFirst();
					}
					if(conn == null)
					{
						conn = makeDBConnection();
						setDBAvailable(true);
					}
					if(conn != null){
						conn.setAutoCommit(true);
						isConnecting = true;
						setDBAvailable(true);
					}else
						Thread.sleep(1000L);
					
			}
			catch(Exception ex)
			{
				System.out.println("getConnection: " + ex.getMessage());
				isConnecting = false;
				//Send Alarm

			}
		}
		catch (Exception ex)
		{ 
			throw new DBException("DBPool.getConnection(): Database is available but still --> " + ex.toString());
		}
			//this.build(1);
		
		
		

		return conn;
	}

	public static void putConnection(Connection conn)
	{
		try
		{
			if(conn == null || conn.isClosed())
			{
				System.out.println("putConnection: conn is null or closed: " + conn);
				return;
			}

			if(pool.size() >= MAX_CONNECTIONS)
			{
				conn.close();
				return;
			}
		}
		catch(SQLException ex) { }
		synchronized(pool)
		{
			pool.addLast(conn);
			pool.notify();
		}
		return;
	}

	public static void release()
	{
		System.out.println("Closing connections in pool...");
		synchronized(pool)
		{
			for(Iterator it = pool.iterator(); it.hasNext();)
			{
				Connection conn = (Connection)it.next();
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					System.out.println("release: Cannot close connection! (maybe closed?)");
				}
			}

			pool.clear();
			setDBAvailable(false);
		}
		System.out.println("OK");
	}


	public static int size() {
		synchronized (pool) {
			return pool.size();
		}
	}

	public static boolean isEmpty() {
		synchronized (pool) {
			return pool.isEmpty();
		}
	}
	public void finalize()
	{
		release();
	}
//	public static Connection makeDBConnection() throws SQLException
//	{
//		Connection conn = null;
//		try
//		{
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.72:1521:SMS", "smsgate", "sms2006");
//		}
//		catch(Exception ex)
//		{
//			throw new SQLException(ex.getMessage());
//		}
//		return conn;
//	}
	public static Connection makeDBConnection() throws SQLException
	{
		Connection conn = null;
		try
		{
			Class.forName(DBConfig.db_DriverClassName);
			System.out.println(DBConfig.db_URL+" "+DBConfig.db_user+" "+ DBConfig.db_password);
			conn = DriverManager.getConnection(DBConfig.db_URL, DBConfig.db_user, DBConfig.db_password);
		}
		catch(ClassNotFoundException ex)
		{
			throw new SQLException(ex.getMessage());
		}
		return conn;
	}

	public static void main(String args[])
	{
		DBPool pool = new DBPool();
		try
		{
			System.out.println(pool.makeDBConnection());
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	public static void load()
	{
	}

	private static LinkedList pool = new LinkedList();
	public static final int MAX_CONNECTIONS = 20;
	public static final int INI_CONNECTIONS = 2;
	private static boolean dbAvailable = false;
	private static boolean isConnecting = true;

	static 
	{
		if(DBConfig.isDatabaseEnabled())
			build(2);
	}
}



