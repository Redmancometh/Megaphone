package com.redmancometh.poller;

import java.lang.reflect.Field;
import com.example.poller.R;
import com.redmancometh.poller.events.ButtonClickedEvent;
import com.redmancometh.poller.listeners.Subscriber;
import com.redmancometh.poller.requests.RequestType;
import com.redmancometh.poller.listeners.EventCallback;
import com.redmancometh.poller.listeners.EventObserver;

import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PollerGUIConstructor implements Subscriber
{
	private PollerMain app;
	@SuppressWarnings("unused")
	private Button requestQuestionButton;

	public PollerGUIConstructor(PollerMain app)
	{
		this.app = app;
		EventObserver.subscribe(this);
	}

	public void init()
	{
		initButtons();
		zipperMethod();
	}

	public void initButtons()
	{
		requestQuestionButton = (Button) app.findViewById(R.id.requestQuestionsButton);
	}


	// No way this is a good idea, replace with something else later.
	public void zipperMethod()
	{
		for (Field f : this.getClass().getDeclaredFields())
		{
			try
			{
				final Object b = f.get(this);
				if (b instanceof Button)
				{
					((View) b).setOnClickListener(new View.OnClickListener()
					{
						public void onClick(View v)
						{
							new ButtonClickedEvent((Button) b).fire();
						}
					});
				}
			}
			catch (Throwable t)
			{
				t.printStackTrace();
			}
		}
	}

	@EventCallback
	public void eventCalled(ButtonClickedEvent e)
	{
		Log.d("Clicked", "ButtonClickedEvent");
		app.getWebClient().request("192.30.139.7", RequestType.CLIENT_REQUEST_GET_QUESTIONS.getRequest("pls"));
	}
}
