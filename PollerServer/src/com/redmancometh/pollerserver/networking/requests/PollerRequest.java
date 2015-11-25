package com.redmancometh.pollerserver.networking.requests;
import java.util.concurrent.CompletableFuture;
import com.google.gson.JsonObject;

import javafx.util.Pair;

public abstract class PollerRequest
{
	private RequestType type;
	public PollerRequest(RequestType type)
	{
		this.type=type;
	}
	public abstract void executeRequest(RequestType type);

	public String getChannel()
	{
		return type.getChannel();
	}
	public void respond(String channel, JsonObject message)
	{
		RequestType request = RequestType.getByChannel(channel);
		if (request != null)
		{
			processResponse(type, message);
		}
	}
	public abstract void processResponse(RequestType pollerRequest, JsonObject message);
	public abstract CompletableFuture<Pair<Boolean, Integer>> receive(String channel, String message);
}
