package com.redmancometh.pollerserver.events;
import com.redmancometh.pollerserver.listeners.EventObserver;
public class Event
{
	public void fire()
	{
		EventObserver.fireEvent(this);
	}
}
