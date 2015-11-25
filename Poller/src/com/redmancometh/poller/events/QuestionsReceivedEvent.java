package com.redmancometh.poller.events;

public class QuestionsReceivedEvent extends Event
{
	private String responseBody;
	public QuestionsReceivedEvent(String responseBody)
	{
		this.responseBody=responseBody;
	}
	
	public String getResponseBody()
	{
		return responseBody;
	}
}
