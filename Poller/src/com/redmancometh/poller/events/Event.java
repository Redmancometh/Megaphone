package com.redmancometh.poller.events;
import com.redmancometh.poller.listeners.EventObserver;
public class Event
{
	public void fire()
	{
		EventObserver.fireEvent(this);
	}
}
