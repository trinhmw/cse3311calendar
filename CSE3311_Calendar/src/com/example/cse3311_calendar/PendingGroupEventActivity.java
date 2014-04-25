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
	private Button mAddGroupEvent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pending_group_event);

		nameText = (TextView) findViewById(R.id.name);
		
		Button monthView = (Button) findViewById(R.id.month_view);
		monthView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent changeIntent = new Intent(PendingGroupEventActivity.this, MonthViewActivity.class);
				startActivity(changeIntent);
			}
		});
	
		 mAddGroupEvent = (Button) this.findViewById(R.id.group_event);
	        mAddGroupEvent.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
//					Toast.makeText(MonthViewActivity.this, R.string.add_event, Toast.LENGTH_LONG).show();
					Intent changeIntent = new Intent(PendingGroupEventActivity.this, GroupEventFormActivity.class);
					startActivity(changeIntent);
					
				}
			});
	        
		String pendingList= "";
		pendingGroupEvent = mgr.getGroupEvents();
		
		for(int i = 0; i < pendingGroupEvent.size(); i++){
			
			pendingList = pendingList.concat("     ");
			pendingList = pendingList.concat(pendingGroupEvent.get(i).getName());
			//pendingList = pendingList.concat("    ");
			//pendingList = pendingList.concat(pendingGroupEvent.get(i).getStartDate());
			pendingList = pendingList.concat("\n");
			
		} // end of for
		
		nameText.setText(pendingList);
	}
	
	
}
