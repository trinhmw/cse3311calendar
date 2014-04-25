package com.example.cse3311_calendar;

import java.util.Calendar;
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

public class GroupEventFormActivity extends Activity{

	private Button mConfirm;
	private Spinner mCategorySpinner;
	private Spinner mMinimumSpinner;
	private EditText et;
	private int startHour, endHour;
	private int startMinute, endMinute;
	private int day, month, year; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_event_form);
		
		final Spinner mMinimumSpinner = (Spinner) findViewById(R.id.fMinimum);
		ArrayAdapter<CharSequence> minAdapter = ArrayAdapter.createFromResource(this, R.array.minimum_array, android.R.layout.simple_spinner_item);
		minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mMinimumSpinner.setAdapter(minAdapter);
		
		
		
		final Spinner mCategorySpinner = (Spinner) findViewById(R.id.fCategory);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mCategorySpinner.setAdapter(adapter);
		
		final DatePicker mStartDate = (DatePicker) findViewById(R.id.fStart_date);
		//final DatePicker mEndDate = (DatePicker) findViewById(R.id.fEnd_date);
		
		final TimePicker mStartTime = (TimePicker) findViewById(R.id.fStart_time);
		final TimePicker mEndTime = (TimePicker) findViewById(R.id.fEnd_time);
		
		
		// Get the message from the intent
		Intent intent = getIntent();

		Bundle inputBundle = intent.getExtras();

		if (inputBundle != null) {
			day = inputBundle.getInt("day");
			month = inputBundle.getInt("month");
			year = inputBundle.getInt("year");
			startHour = inputBundle.getInt("hour");
			startMinute = 0;
			endMinute = 0;
			
		} 
		//If intent is empty set current day
		else {
			Calendar cal = Calendar.getInstance();
			day = cal.get(Calendar.DAY_OF_MONTH);
			month = cal.get(Calendar.MONTH);
			year = cal.get(Calendar.YEAR);
			startHour = cal.get(Calendar.HOUR_OF_DAY);
			startMinute = cal.get(Calendar.MINUTE);
			endMinute  = cal.get(Calendar.MINUTE);
		}
		
		
		
		endHour = startHour + 1;

		mStartDate.updateDate(year, month, day);
		mStartTime.setCurrentHour(startHour);
		mStartTime.setCurrentMinute(startMinute);

		//mEndDate.updateDate(year, month, day);
		mEndTime.setCurrentHour(endHour);
		mEndTime.setCurrentMinute(endMinute);
		
	
		

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
				
				et = (EditText) findViewById(R.id.fContact1name);
				//text = et.getText().toString();
				String contact1name = et.getText().toString();
				//bundle.putString("contact1", text);
				

				et = (EditText) findViewById(R.id.fContact1num);
				//text = et.getText().toString();
				String contact1num = et.getText().toString();
				//bundle.putString("contact1", text);
				
				
				et = (EditText) findViewById(R.id.fContact2name);
				String contact2name = et.getText().toString();
				//bundle.putString("contact2", text);
				

				et = (EditText) findViewById(R.id.fContact1num);
				//text = et.getText().toString();
				String contact2num = et.getText().toString();
				//bundle.putString("contact1", text);
				
				

				//Toast.makeText(EventFormActivity.this, "" + gStartDate.getYear(), Toast.LENGTH_LONG).show();

				Date startDate = new Date(mStartDate.getYear(), mStartDate.getMonth(), mStartDate.getDayOfMonth());
				Date endDate = startDate;
				
				int startTime = mStartTime.getCurrentHour().intValue();
				startTime = startTime * 60;
				startTime = startTime + mStartTime.getCurrentMinute().intValue();
				int endTime = mEndTime.getCurrentHour().intValue();
				endTime = endTime * 60;
				endTime = endTime + mEndTime.getCurrentMinute().intValue();
				
				et = (EditText) findViewById(R.id.fDescription);
				String description = et.getText().toString();
				//text = et.getText().toString();
				//bundle.putString("description", text);
				
				
				int category = mCategorySpinner.getSelectedItemPosition();
				//bundle.putString("category", text);
				
				int minimum = mMinimumSpinner.getSelectedItemPosition();
				
				
			 GroupEventController.addGroupEvent(name, location, startDate, endDate, startTime, endTime, description, category, contact1name, contact2name, contact1num, contact2num , minimum);
boolean result = true;
				if(result == false){
					Toast.makeText(GroupEventFormActivity.this, "There was an error scheduling the Group Event", Toast.LENGTH_LONG).show();
				}
				else{
					Intent changeIntent = new Intent(GroupEventFormActivity.this, PendingGroupEventActivity.class);
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
		getMenuInflater().inflate(R.menu.group_event_form, menu);
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

		Intent changeIntent = new Intent(GroupEventFormActivity.this, MonthViewActivity.class);
		changeIntent.putExtras(dataBundle);
		startActivity(changeIntent);

	}

}	