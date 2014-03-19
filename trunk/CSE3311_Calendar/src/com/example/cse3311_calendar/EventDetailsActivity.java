package com.example.cse3311_calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class EventDetailsActivity extends Activity {
	
	EventListManager elm;

	int day;
	int month;
	int year;
	int id;
	int index;
	
	TextView mTemp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_details);
		
		mTemp = (TextView) findViewById(R.id.temp);
		
		Intent intent = getIntent();
		if(intent != null)
		{
        mTemp.setText("Not empty");
		}
		else
		{
			mTemp.setText("Empty");
		}
        
        //mTemp =(TextView)findViewById(R.id.temp);
        //mTemp.setText(id);
        
        /*
        if(inputBundle != null){
	        day = inputBundle.getInt("day");
	        month = inputBundle.getInt("month");
	        year = inputBundle.getInt("year");
	        id = inputBundle.getInt("id");
        }
        else
        {
        	Calendar cal = Calendar.getInstance();
        	day = cal.get(Calendar.DAY_OF_MONTH);
        	month = cal.get(Calendar.MONTH);
        	year = cal.get(Calendar.YEAR);	        	
        }
		*/
        //Get key
        //Date startDate = new Date(year,month,day);
		//elm = EventListManager.getInstance();
		
		//get event
		//ArrayList<Event> events= elm.getEvents(startDate.toString());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_details, menu);
		return true;
	}

}
