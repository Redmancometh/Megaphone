package com.redmancometh.pollerserver.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager
{
	private static final String DB_URL = "jdbc:mysql://localhost/test";
	private static final String USER = "root";
	private static final String PASS = "enter11284";

	public Connection openConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}

	public void printShit()
	{
		Connection conn = openConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement("SELECT * FROM Questions");
			rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString("question"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				ps.close();
				conn.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
