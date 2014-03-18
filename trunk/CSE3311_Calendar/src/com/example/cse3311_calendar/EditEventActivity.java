package com.example.cse3311_calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class EditEventActivity extends Activity {
	Button mConfirm;
	Spinner mCategorySpinner;
	Spinner mAllDaySpinner;
	EditText et;
	EventListManager elm;
	int day, month, year, index;
	private final String TAG = "EditEventActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_event);
		

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
		
		
		Intent intent = getIntent();
		
		Bundle inputBundle = intent.getExtras();
		
		if(inputBundle != null){
			day = inputBundle.getInt("day");
			month = inputBundle.getInt("month");
			year = inputBundle.getInt("year");
		}
		else
		{
			Calendar cal = Calendar.getInstance();
			day = cal.get(Calendar.DAY_OF_MONTH);
			month = cal.get(Calendar.MONTH);
			year = cal.get(Calendar.YEAR);
		}
		
		
		Date startDate = new Date(year, month, day);
		elm  = EventListManager.getInstance();
		ArrayList<Event> events = elm.getEvents(startDate.toString());
		if(events == null)
		{
			Log.d(TAG, "null");
		}
		else
		{
			if(events.isEmpty()){
				
			}
			else{
				
			}
		}
		
		et = (EditText) findViewById(R.id.fName);
		et.setText(events.get(index).getName());
		
		et = (EditText) findViewById(R.id.fLocation);
		et.setText(events.get(index).getLocation());
		
		
		Date endDate = events.get(index).getEndDate();
		int startTime = events.get(index).getStartTime();
		int endTime = events.get(index).getEndTime();
		
		mEndDate.updateDate(endDate.getYear(), endDate.getMonth(), endDate.getDay());
		mStartDate.updateDate(year, month, day);
		mStartTime.setCurrentHour(startTime/60);
		mStartTime.setCurrentMinute(startTime%60);
		
		mEndTime.setCurrentHour(endTime/60);
		mEndTime.setCurrentMinute(endTime%60);
		
		
		et = (EditText) findViewById(R.id.fDescription);
		et.setText(events.get(index).getDescription());
		
		mCategorySpinner.setSelection(events.get(index).getCategory());
		
		
		mConfirm = (Button) this.findViewById(R.id.confirm);
		mConfirm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String tag = (String) v.getTag();
				
				et = (EditText) findViewById(R.id.fName);
				String name = et.getText().toString();
				
				
				et = (EditText) findViewById(R.id.fLocation);

				String location = et.getText().toString();
				
				Date startDate = new Date(mStartDate.getYear(), mStartDate.getMonth(), mStartDate.getDayOfMonth());
				Date endDate = new Date(mEndDate.getYear(), mEndDate.getMonth(), mEndDate.getDayOfMonth());
				int startTime = mStartTime.getCurrentHour().intValue();
				startTime = startTime * 60;
				startTime = startTime + mStartTime.getCurrentMinute().intValue();
				int endTime = mEndTime.getCurrentHour().intValue();
				endTime = endTime * 60;
				endTime = endTime + mEndTime.getCurrentMinute().intValue();
				
				et = (EditText) findViewById(R.id.fDescription);
				String description = et.getText().toString();
				
				
				int category = mCategorySpinner.getSelectedItemPosition();
				
				boolean allDay;
				String allDayString = mAllDaySpinner.getSelectedItem().toString();
				if(allDayString.equals("Yes")){
					allDay = true;
				}else{
					allDay = false;
				}
				
				elm.getEvents(startDate.toString()).get(index).setName(name);
				elm.getEvents(startDate.toString()).get(index).setLocation(location);
				elm.getEvents(startDate.toString()).get(index).setEndDate(endDate);
				elm.getEvents(startDate.toString()).get(index).setStartDate(startDate);
				elm.getEvents(startDate.toString()).get(index).setStartTime(startTime);
				elm.getEvents(startDate.toString()).get(index).setEndTime(endTime);
				elm.getEvents(startDate.toString()).get(index).setDescription(description);
				elm.getEvents(startDate.toString()).get(index).setCategory(category);
				elm.getEvents(startDate.toString()).get(index).setAllDayOption(allDay);
			

				Intent changeIntent = new Intent(EditEventActivity.this, EventDetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("day", mStartDate.getDayOfMonth());
				bundle.putInt("month", mStartDate.getMonth());
				bundle.putInt("year", mStartDate.getYear());
				bundle.putInt("index", index);
				changeIntent.putExtras(bundle);
				startActivity(changeIntent);
				
				
				
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_event, menu);
		return true;
	}

}
