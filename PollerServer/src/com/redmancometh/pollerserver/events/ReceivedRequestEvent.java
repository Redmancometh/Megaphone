package com.redmancometh.pollerserver.events;
import com.google.gson.JsonObject;

public class ReceivedRequestEvent extends Event
{
	private JsonObject request;
	
	public ReceivedRequestEvent(JsonObject request)
	{
		this.request=request;
	}
	
	public JsonObject getRequest()
	{
		return request;
	}
}
