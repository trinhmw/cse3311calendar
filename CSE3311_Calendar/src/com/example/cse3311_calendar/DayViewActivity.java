package com.example.cse3311_calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DayViewActivity extends Activity {

		String myDate;
		Button mAddEvent;
		Button mMonth;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_day_view);
			// Get the message from the intent
	        Intent intent = getIntent();
	        
	        Bundle inputBundle = intent.getExtras();
	        int day = inputBundle.getInt("day");
	        int month = inputBundle.getInt("month");
	        int year = inputBundle.getInt("year");
	        String myDate = "" + (month + 1) + "/" + day + "/" + year;
	        //set extra message to to myDate
	        //@+id/dayTextView
	        
	        TextView dayText = (TextView)findViewById(R.id.dayTextView);
			dayText.setText(myDate);
			
			
			
			
			
	 
			TextView nTv = (TextView)findViewById(R.id.time00);
			nTv.setText("Get Doritos and cheese");
			nTv.setBackgroundColor(Color.RED);
			
			TextView nTv3 = (TextView)findViewById(R.id.time01);
			//nTv.setText("Get Doritos and cheese");
			nTv3.setBackgroundColor(Color.RED);
			
			TextView nTv2 = (TextView)findViewById(R.id.time04);
			nTv2.setText("Meditate");
			nTv2.setBackgroundColor(Color.BLUE);
			
			
			mAddEvent = (Button) findViewById(R.id.add_event);
			mAddEvent.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent changeIntent = new Intent(DayViewActivity.this, EventFormActivity.class);
					startActivity(changeIntent);
				}
			});
			
			mMonth = (Button) findViewById(R.id.month);
			mMonth.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent changeIntent = new Intent(DayViewActivity.this, MonthViewActivity.class);
					startActivity(changeIntent);
				}
			});
			
			
			
			}

	}


