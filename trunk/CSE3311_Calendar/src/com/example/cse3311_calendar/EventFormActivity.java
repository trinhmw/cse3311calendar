package com.example.cse3311_calendar;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Activity for seeing add event form 
 */
public class EventFormActivity extends Activity {

	Button mConfirm;
	Spinner mCategorySpinner;
	Spinner mAllDaySpinner;
	EditText et;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_form);
		
		final Spinner mCategorySpinner = (Spinner) findViewById(R.id.fCategory);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mCategorySpinner.setAdapter(adapter);
		
		final Spinner mAllDaySpinner = (Spinner) findViewById(R.id.fAll_day);
		adapter = ArrayAdapter.createFromResource(this, R.array.all_day_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mAllDaySpinner.setAdapter(adapter);
		
		final DatePicker mStartDate = (DatePicker) findViewById(R.id.fStart_date);
		final DatePicker mEndDate = (DatePicker) findViewById(R.id.fEnd_date);
		
		final TimePicker mStartTime = (TimePicker) findViewById(R.id.fStart_time);
		final TimePicker mEndTime = (TimePicker) findViewById(R.id.fEnd_time);
		
		mConfirm = (Button) this.findViewById(R.id.confirm);
		mConfirm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				String tag = (String) v.getTag();
				//Bundle bundle = new Bundle();
				
				et = (EditText) findViewById(R.id.fName);
				//String text = et.getText().toString();
				String name = et.getText().toString();
				//Toast.makeText(EventFormActivity.this, text, Toast.LENGTH_LONG).show();
				
				//bundle.putString("name", text);
				
				et = (EditText) findViewById(R.id.fLocation);
				//text = et.getText().toString();
				String location = et.getText().toString();
				//bundle.putString("location", text);
				
				Date startDate = new Date(mStartDate.getYear(), mStartDate.getMonth(), mStartDate.getDayOfMonth());
				Date endDate = new Date(mEndDate.getYear(), mEndDate.getMonth(), mEndDate.getDayOfMonth());
				int startTime = mStartTime.getCurrentHour().intValue();
				startTime = startTime * 60;
				startTime = startTime + mStartTime.getCurrentMinute().intValue();
				int endTime = mEndTime.getCurrentHour().intValue();
				endTime = endTime * 60;
				endTime = endTime + mEndTime.getCurrentMinute().intValue();
				/*
				bundle.putInt("start_date_month", mStartDate.getMonth());
				bundle.putInt("start_date_day", mStartDate.getDayOfMonth());
				bundle.putInt("start_date_year", mStartDate.getYear());
				
				bundle.putInt("end_date_month", mEndDate.getMonth());
				bundle.putInt("end_date_day", mEndDate.getDayOfMonth());
				bundle.putInt("end_date_year", mEndDate.getYear());
				
				bundle.putInt("start_time_hour", mStartTime.getCurrentHour().intValue());
				bundle.putInt("start_time_min", mStartTime.getCurrentMinute().intValue());
				
				bundle.putInt("end_time_hour", mEndTime.getCurrentHour().intValue());
				bundle.putInt("end_time_min", mEndTime.getCurrentMinute().intValue());
				*/
				
				et = (EditText) findViewById(R.id.fDescription);
				String description = et.getText().toString();
				//text = et.getText().toString();
				//bundle.putString("description", text);
				
				
				int category = mCategorySpinner.getSelectedItemPosition();
				//bundle.putString("category", text);
				
				boolean allDay;
				String allDayString = mAllDaySpinner.getSelectedItem().toString();
				if(allDayString.equals("Yes")){
					allDay = true;
				}else{
					allDay = false;
				}
				//bundle.putString("all_day", text);
				
				boolean result = AddEventController.addEvent(name, location, startDate, endDate, startTime, endTime, 
						description, category, allDay);
				
				if(result == false){
					Toast.makeText(EventFormActivity.this, "There was an error adding the Event", Toast.LENGTH_LONG).show();
				}
				else{
					Intent changeIntent = new Intent(EventFormActivity.this, DayViewActivity.class);
					Bundle bundle = new Bundle();
					bundle.putInt("day", mStartDate.getDayOfMonth());
					bundle.putInt("month", mStartDate.getMonth());
					bundle.putInt("year", mStartDate.getYear());
					changeIntent.putExtras(bundle);
					startActivity(changeIntent);
				}
				
				
				//Toast.makeText(EventFormActivity.this, changeIntent.getExtras().getInt("start_time_hour"), Toast.LENGTH_LONG).show();
				//String tmp = "" + changeIntent.getExtras().getInt("start_time_hour");
				//Toast.makeText(EventFormActivity.this, tmp, Toast.LENGTH_LONG).show();
				
				
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_form, menu);
		return true;
	}
	
/**
 * Handles when confirm button is clicked
 *
 * @param view the view
 */
public void confirmClick(View view){
		
		String tag = (String) view.getTag();
		
		Bundle dataBundle = new Bundle();
		
		
		et = (EditText) findViewById(R.id.fName);
		String text = et.getText().toString();
		
		dataBundle.putString("name", text);	
		
		Intent changeIntent = new Intent(EventFormActivity.this, MonthViewActivity.class);
		changeIntent.putExtras(dataBundle);
		startActivity(changeIntent);
		
	}

}
