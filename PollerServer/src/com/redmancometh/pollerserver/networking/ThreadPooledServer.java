package com.redmancometh.pollerserver.networking;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadPooledServer implements Runnable
{
	private int serverPort;
	private ServerSocket serverSocket = null;
	private ExecutorService threadPool = Executors.newFixedThreadPool(10);

	public ThreadPooledServer(int port)
	{
		this.serverPort = port;
	}

	public void run()
	{
		openServerSocket();
		while (true)
		{
			Socket clientSocket = null;
			try
			{
				clientSocket = this.serverSocket.accept();
			}
			catch (Throwable e)
			{
				break;
			}
			this.threadPool.execute(new WorkerRunnable(clientSocket));
		}
		this.threadPool.shutdown();
	}
	
	public void stop()
	{
		try
		{
			this.serverSocket.close();
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

	private void openServerSocket()
	{
		try
		{
			this.serverSocket = new ServerSocket(this.serverPort);
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}
}