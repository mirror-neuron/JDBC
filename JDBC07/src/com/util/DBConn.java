package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn
{
	private static Connection dbConn;
	public static Connection getConnection() 
	{
		if (dbConn==null)
		{
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String pwd = "tiger";
			
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
			
		}
		return dbConn;
	}
	
	public static Connection getConnection(String url, String user, String pwd)
	{
		if (dbConn==null)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
			try
			{
				dbConn = DriverManager.getConnection(url, user, pwd);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		}
		return dbConn;
	}
	
	public static void close()
	{
		if (dbConn!=null)
		{
			try
			{
				if (!dbConn.isClosed())
					dbConn.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		}
		
		dbConn = null;
	}
}
