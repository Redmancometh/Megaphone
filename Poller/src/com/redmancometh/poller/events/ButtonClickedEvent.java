package com.redmancometh.poller.events;

import android.widget.Button;

public class ButtonClickedEvent extends Event
{
	private Button button;
	public ButtonClickedEvent(Button b)
	{
		this.button=b;
	}
	
	public Button getButton()
	{
		return button;
	}
}
