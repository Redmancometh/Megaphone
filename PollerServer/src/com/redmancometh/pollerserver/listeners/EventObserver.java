package com.redmancometh.pollerserver.listeners;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.redmancometh.pollerserver.events.Event;
public class EventObserver
{
	private static List<Subscriber> subscribers = new ArrayList<Subscriber>();

	public static void subscribe(Subscriber subscriber)
	{
		subscribers.add(subscriber);
	}

	public static void unSubscribe(Subscriber subscriber)
	{
		subscribers.remove(subscriber);
	}

	@SuppressWarnings("rawtypes")
	public static void notifySubscribers(Event e)
	{
		for (Subscriber subscriber : subscribers)
		{
			for (Method m : subscriber.getClass().getDeclaredMethods())
			{
				if(m.isAnnotationPresent(EventCallback.class))
				{
					Class[] c = m.getParameterTypes();
					if(c.length==1&&c[0]==e.getClass())
					{
						try
						{
							m.invoke(subscriber, e);
						}
						catch (Throwable t)
						{
							t.printStackTrace();
						}
					}
				}
			}
		}
	}

	public static void fireEvent(Event e)
	{
		notifySubscribers(e);
	}
}
