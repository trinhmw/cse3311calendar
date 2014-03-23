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

/**
 * Handles showing a from for editing an event
 */
public class EditEventActivity extends Activity {
	private Button mConfirm;
	private Spinner mCategorySpinner;
	private Spinner mAllDaySpinner;
	private EditText et;
	private EventListManager elm;
	private Date currentDate;
	private int day, month, year, id;
	private final String TAG = "EditEventActivity";

	//Function for creation of activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_event);


		Intent intent = getIntent();

		Bundle inputBundle = intent.getExtras();

		et = (EditText) findViewById(R.id.fName);

		if(inputBundle == null){
			et.setText("Unable to load event.");

		}
		else
		{
			day = inputBundle.getInt("day");
			month = inputBundle.getInt("month");
			year = inputBundle.getInt("year");
			id = inputBundle.getInt("id");


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





			currentDate = new Date(year, month, day);
			
			elm  = EventListManager.getInstance();
			
			Event currentEvent = elm.getEventsById(currentDate.toString(), id);
			
			if(currentEvent == null)
			{
				Log.d(TAG, "null");
				et.setText("Unable to load event.");
			}
			else
			{

				et.setText(currentEvent.getName());

				et = (EditText) findViewById(R.id.fLocation);
				et.setText(currentEvent.getLocation());


				Date endDate = currentEvent.getEndDate();
				int startTime = currentEvent.getStartTime();
				int endTime = currentEvent.getEndTime();

				mEndDate.updateDate(endDate.getYear(), endDate.getMonth(), endDate.getDay());
				mStartDate.updateDate(year, month, day);
				mStartTime.setCurrentHour(startTime/60);
				mStartTime.setCurrentMinute(startTime%60);

				mEndTime.setCurrentHour(endTime/60);
				mEndTime.setCurrentMinute(endTime%60);


				et = (EditText) findViewById(R.id.fDescription);
				et.setText(currentEvent.getDescription());

				mCategorySpinner.setSelection(currentEvent.getCategory());


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
						
						boolean result = EditEventController.editEvent(name, location, startDate, endDate, startTime, endTime, 
								description, category, allDay, id, currentDate.toString());

						
						if(result == false){
							Toast.makeText(EditEventActivity.this, "There was an error editing the Event", Toast.LENGTH_LONG).show();
						}
						else{

							Intent changeIntent = new Intent(EditEventActivity.this, EventDetailsActivity.class);
							Bundle bundle = new Bundle();
							bundle.putInt("day", mStartDate.getDayOfMonth());
							bundle.putInt("month", mStartDate.getMonth());
							bundle.putInt("year", mStartDate.getYear());
							bundle.putInt("id", id);
							changeIntent.putExtras(bundle);
							startActivity(changeIntent);
						}




					}
				});
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_event, menu);
		return true;
	}

}
