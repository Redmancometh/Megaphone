package com.redmancometh.poller;
import com.example.poller.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
public class PollerMain extends Activity
{
	private  PollerGUIConstructor constructor;
	private PollerWebClient client;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_poller_main);
		constructor = new PollerGUIConstructor(this);
		constructor.init();
		Log.d("ThreadMain", Thread.currentThread().getName());
		client = new PollerWebClient("192.30.139.7");
		client.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.poller_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public PollerWebClient getWebClient()
	{
		return client;
	}
}
