package com.redmancometh.poller.requests;
import java.util.Map.Entry;
public class ClientQuestionsRequest extends PollerRequest
{

	@Override
	public void processResponse(RequestType pollerRequest, String message)
	{
		
	}

	@Override
	public Entry<Boolean, Integer> receive(String channel, String message)
	{
		return null;
	}

}
