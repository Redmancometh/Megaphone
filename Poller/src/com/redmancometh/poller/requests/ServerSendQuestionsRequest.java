package com.redmancometh.poller.requests;
import java.util.Map.Entry;
public class ServerSendQuestionsRequest extends PollerRequest
{

	@Override
	public Entry<Boolean, Integer> receive(String channel, String message)
	{
		return null;
	}

	@Override
	public void processResponse(RequestType pollerRequest, String message)
	{
		
	}
}
