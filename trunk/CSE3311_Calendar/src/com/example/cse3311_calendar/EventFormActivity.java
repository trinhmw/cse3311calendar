package com.example.cse3311_calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.cse3311_calendar.R;

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

	private Button mConfirm;
	private Spinner mCategorySpinner;
	private Spinner mAllDaySpinner;
	private Spinner mRepeatSpinner;
	private Spinner mNotificationSpinner;
	private EditText et;
	private int startHour, endHour;
	private int startMinute, endMinute;
	private int day, month, year; 


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
		final DatePicker mLastDate = (DatePicker) findViewById(R.id.fLast_date);

		final TimePicker mStartTime = (TimePicker) findViewById(R.id.fStart_time);
		final TimePicker mEndTime = (TimePicker) findViewById(R.id.fEnd_time);

		final Spinner mRepeatSpinner = (Spinner) findViewById(R.id.fIsRepeat);
		ArrayAdapter<CharSequence> adapterRepeat = ArrayAdapter.createFromResource(this, R.array.repeat_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mRepeatSpinner.setAdapter(adapterRepeat);

		final Spinner mNotificationSpinner = (Spinner) findViewById(R.id.notificationValue);
		ArrayAdapter<CharSequence> adapterNotfification = ArrayAdapter.createFromResource(this, R.array.notification_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		mNotificationSpinner.setAdapter(adapterNotfification);

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

		mEndDate.updateDate(year, month, day);
		mEndTime.setCurrentHour(endHour);
		mEndTime.setCurrentMinute(endMinute);

		mLastDate.updateDate(year, month, day);


		mConfirm = (Button) this.findViewById(R.id.confirm);
		mConfirm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				//Temp variables for repeat
				boolean isRepeat = false;
				Date lastDay = null;
				int repeatedDays = 3;


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

				//Toast.makeText(EventFormActivity.this, "" + mStartDate.getYear(), Toast.LENGTH_LONG).show();

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




				String repeatString = mRepeatSpinner.getSelectedItem().toString();

				if(repeatString.equals("Yes")){
					isRepeat = true;					
				}

				else{
					isRepeat = false;
					repeatedDays = 0;
				}

				lastDay = new Date(mLastDate.getYear(), mLastDate.getMonth(), mLastDate.getDayOfMonth());
				et = (EditText) findViewById(R.id.fRepeated_Days);

				try{
					repeatedDays = Integer.parseInt(et.getText().toString());
				}catch(Exception e){
					repeatedDays = -1;
				}

			
				//TODO modify mNotificationSpinner
				int notificationVal = mNotificationSpinner.getSelectedItemPosition();
				switch(notificationVal){
				case 0: //no notification
					notificationVal = -1; break;
				case 1: //at time of event
					notificationVal = 0; break;
				case 2: //15 minutes prior
					notificationVal = 15; break;
				case 3: //30 minutes prior
					notificationVal = 30; break;
				case 4: //1 hour prior
					notificationVal = 60; break;
				case 5: //1 day prior
					notificationVal = 24*60; break;
				default: //no notification
					notificationVal = -1; break;
				}


				boolean result = AddEventController.addEvent(name, location, startDate, endDate, startTime, endTime, 
						description, category, allDay,isRepeat, repeatedDays, lastDay);

				
				//EventListManager elm = EventListManager.getInstance();
				//ArrayList<Event> eList = elm.getEvents(startDate.toString());
				//Event event = new Event();
				//for(int i=0; i<eList.size(); i++){
				//	if(name.equals(eList.get(i).getName()))
				//		event = eList.get(i);
				//}


				if(result == true){
					EventListManager elm = EventListManager.getInstance();
					ArrayList<Event> eList = elm.getEvents(startDate.toString());
					Event event = new Event();
					for(int i=0; i<eList.size(); i++){
						if(name.equals(eList.get(i).getName()))
							event = eList.get(i);

					}

					boolean notificationResult = NotificationController.createNotification( event, notificationVal, EventFormActivity.this );
					if( notificationResult == false ){ 
						Toast.makeText(EventFormActivity.this, "There was an error making the notification.", Toast.LENGTH_LONG).show(); 
					}
				}

				
				//boolean notificationResult = NotificationController.createNotification( event, notificationVal );
				//if( notificationResult == false ){ 
				//	Toast.makeText(EventFormActivity.this, "There was an error making the notification.", Toast.LENGTH_LONG).show(); 
				//}

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

	public int getStartHour() {
		// TODO Auto-generated method stub
		return startHour;
	}

}
