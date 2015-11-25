package com.redmancometh.poller;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import com.redmancometh.poller.events.QuestionsReceivedEvent;
import com.redmancometh.poller.listeners.EventCallback;
import com.redmancometh.poller.listeners.EventObserver;
import com.redmancometh.poller.listeners.Subscriber;
import com.redmancometh.poller.requests.PollerRequest;
import android.util.Log;

public class PollerWebClient extends Thread
{
	private String urlString;
	private OutputStream out;
	private Socket socket;
	PollerWebClient(String urlString)
	{
		this.urlString = urlString;
	}
	
	@Override
	public void run()
	{
		connect();
	}

	public void connect()
	{
		this.socket = new Socket();
		SocketAddress sockaddr = new InetSocketAddress(urlString, 9000);
		try
		{
			socket.connect(sockaddr);
			this.out=socket.getOutputStream();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			Log.d("can't even", "connect");
		}
	}
	
	public void request(String url, PollerRequest request)
	{
		new RequestTask(request).run();
	}

	class RequestTask implements Subscriber, Runnable
	{
		private PollerRequest request;
		public RequestTask(PollerRequest request)
		{
			this.request=request;
		}
		
		@Override
		public void run()
		{
			request.executeRequest(out);
			EventObserver.subscribe(this);
		}

		@EventCallback
		public void onQuestionsReceived(QuestionsReceivedEvent e)
		{
			
		}
	}
}
