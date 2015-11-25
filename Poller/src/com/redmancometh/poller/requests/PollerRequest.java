package com.redmancometh.poller.requests;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;
public abstract class PollerRequest
{
	private String channel;
	private String message;

	public PollerRequest()
	{

	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public void executeRequest(OutputStream w)
	{
		try
		{
			String jsonString = getJSONObject(message).toString();
			w.write(jsonString.getBytes("utf-8"));
			w.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void respond(String channel, String message)
	{
		RequestType request = RequestType.getByChannel(channel);
		if (request != null)
		{
			processResponse(RequestType.getByChannel(channel), message);
		}
	}

	public abstract void processResponse(RequestType pollerRequest, String message);

	public abstract Entry<Boolean, Integer> receive(String channel, String message);

	public JSONObject getJSONObject(String message)
	{
		JSONObject json = new JSONObject();
		try
		{
			json.put(channel, message);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return json;
	}

	public void setChannel(String channel)
	{
		this.channel = channel;
	}

	public String getChannel()
	{
		return channel;
	}

}
