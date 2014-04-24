package com.example.cse3311_calendar;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PendingGroupEventActivity extends Activity {
	
	private ArrayList<GroupEvent> pendingGroupEvent;
	EventListManager mgr = EventListManager.getInstance();
	//pendingGroupEvent = mgr.getGroupEvents();
	
	private TextView nameText;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pending_group_event);

		nameText = (TextView) findViewById(R.id.name);
		
		Button dayView = (Button) findViewById(R.id.day_view_button);
		dayView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent changeIntent = new Intent(PendingGroupEventActivity.this, DayViewActivity.class);
				
			}
		});
	
		
	 
		pendingGroupEvent = mgr.getGroupEvents();
		
		
		for(int i = 0; i < pendingGroupEvent.size(); i++){
			nameText.setText(pendingGroupEvent.get(i).getName());
			
			
		}	
	}
	
	
}
