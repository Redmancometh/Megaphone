package com.redmancometh.pollerserver.networking;

import java.io.UnsupportedEncodingException;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import com.google.gson.JsonParser;

public class WorkerRunnable implements Runnable
{
	private Socket clientSocket = null;
	private static JsonParser parser = new JsonParser();

	public WorkerRunnable(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}

	public void run()
	{
		System.out.println("Connection");
		try
		{
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			byte[] data = new byte[1024];
			int n = 0,b =0;
			while ((b = input.read()) != -1)
			{
				data[n++] = (byte) b;
				System.out.println(b+" "+n);
			}
			receiveJson(data);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void receiveJson(byte[] data)
	{
		try
		{
			String rawJson = new String(data, "utf-8");
			System.out.println(rawJson);
			parser.parse(rawJson);
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
}