package com.redmancometh.pollerserver;

import com.redmancometh.pollerserver.networking.ThreadPooledServer;
import com.redmancometh.pollerserver.persistence.DatabaseManager;

public class PollerServer
{
	private static DatabaseManager db = new DatabaseManager();
	public static void main(String[] args)
	{
		ThreadPooledServer server = new ThreadPooledServer(9000);
		new Thread(server).start();
	}
	
	public static DatabaseManager getDBManager()
	{
		return db;
	}
}
