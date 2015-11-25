package com.redmancometh.poller.requests;
public enum RequestType
{
	CLIENT_REQUEST_GET_QUESTIONS("Client_Questions", ClientQuestionsRequest.class), SERVER_SEND_QUESTION_RESPONSE(
			"Server_Questions", ServerSendQuestionsRequest.class);
	private String channel;
	private Class<? extends PollerRequest> requestClass;

	RequestType(String channel, Class<? extends PollerRequest> request)
	{
		this.channel = channel;
		this.requestClass = request;
	}

	public String getChannel()
	{
		return channel;
	}

	public static RequestType getByChannel(String channel)
	{
		for (RequestType type : RequestType.values())
		{
			if (type.getChannel().equals(channel))
			{
				return type;
			}
		}
		return null;
	}

	public PollerRequest getRequest(String message)
	{
		PollerRequest request = null;
		try
		{
			request = requestClass.newInstance();
			request.setChannel(channel);
			request.setMessage(message);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
		return request;
	}
}
