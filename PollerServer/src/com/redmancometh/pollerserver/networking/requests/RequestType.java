package com.redmancometh.pollerserver.networking.requests;

public enum RequestType
{
	CLIENT_REQUEST_GET_QUESTIONS("Client_Questions"), SERVER_SEND_QUESTION_RESPONSE("Server_Questions");
	private String channel;
	RequestType(String channel)
	{
		this.channel=channel;
	}
	public String getChannel()
	{
		return channel;
	}
	public static RequestType getByChannel(String channel)
	{
		for(RequestType type : RequestType.values())
		{
			if(type.getChannel().equals(channel))
			{
				return type;
			}
		}
		return null;
	}
}
