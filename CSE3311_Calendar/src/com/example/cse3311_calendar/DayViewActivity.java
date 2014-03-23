package com.example.cse3311_calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity for viewing events on a particular day
 */
public class DayViewActivity extends Activity {

	String myDate;
	Button mAddEvent;
	Button mMonth;
	EventListManager elm;
	String key;

	int size;
	int day;
	int month;
	int year;
	int id;
	int index;

	int colors[] = { Color.RED, Color.BLUE, Color.GREEN };
	private final String TAG = "DayViewActivity";
	
	int textViewResources[] = {R.id.time00, R.id.time01,R.id.time02,R.id.time03,R.id.time04,R.id.time05,
			R.id.time06,R.id.time07,R.id.time08,R.id.time09,R.id.time10,R.id.time11,R.id.time12,R.id.time13,
			R.id.time14,R.id.time15,R.id.time16,R.id.time17,R.id.time18,R.id.time19,R.id.time20,R.id.time21,
			R.id.time22,R.id.time23};

	//Function for creating the day view
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day_view);
		
		// Get the message from the intent
		Intent intent = getIntent();

		Bundle inputBundle = intent.getExtras();

		if (inputBundle != null) {
			day = inputBundle.getInt("day");
			month = inputBundle.getInt("month");
			year = inputBundle.getInt("year");
		} 
		//If intent is empty set current day
		else {
			Calendar cal = Calendar.getInstance();
			day = cal.get(Calendar.DAY_OF_MONTH);
			month = cal.get(Calendar.MONTH);
			year = cal.get(Calendar.YEAR);
		}
		String myDate = "" + (month + 1) + "/" + day + "/" + year;

		TextView dayText = (TextView) findViewById(R.id.dayTextView);
		dayText.setText(myDate);

		// Identify key and get the events
		Date currentDate = new Date(year, month, day);
		elm = EventListManager.getInstance();

		ArrayList<Event> events = elm.getEvents(currentDate.toString());
		if (events == null) {
			Log.d(TAG, "null");
		} else {
			if (events.isEmpty()) {

			} else
			{
				//Handle filling in the time chart
				index = 0;
				timeAssignment(events);
			}

		}
		
		//Set up add event button
		mAddEvent = (Button) findViewById(R.id.add_event);
		mAddEvent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent changeIntent = new Intent(DayViewActivity.this,
						EventFormActivity.class);
				startActivity(changeIntent);
			}
		});

		//Set up month view button
		mMonth = (Button) findViewById(R.id.month);
		mMonth.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				

				Bundle dataBundle = new Bundle();
        		dataBundle.putInt("month", month);
        		dataBundle.putInt("year", year);
        		Intent changeIntent = new Intent(DayViewActivity.this, MonthViewActivity.class);
        		changeIntent.putExtras(dataBundle);
        		startActivity(changeIntent);
			}
		});

	}

	/**
	 * Takes an int representing the hour and returns a string for that hour
	 *
	 * @param startTime the integer value of the time
	 * @return the String value of the time
	 */
	public String time(int startTime) {
		String time;
		// determine timeslot
		if (startTime == 0)
			time = "time00";
		else if (startTime == 1)
			time = "time01";
		else if (startTime == 2)
			time = "time02";
		else if (startTime == 3)
			time = "time03";
		else if (startTime == 4)
			time = "time04";
		else if (startTime == 5)
			time = "time05";
		else if (startTime == 6)
			time = "time06";
		else if (startTime == 7)
			time = "time07";
		else if (startTime == 8)
			time = "time08";
		else if (startTime == 9)
			time = "time09";
		else if (startTime == 10)
			time = "time010";
		else if (startTime == 11)
			time = "time011";
		else if (startTime == 12)
			time = "time012";
		else if (startTime == 13)
			time = "time013";
		else if (startTime == 14)
			time = "time014";
		else if (startTime == 15)
			time = "time015";
		else if (startTime == 16)
			time = "time016";
		else if (startTime == 17)
			time = "time017";
		else if (startTime == 18)
			time = "time018";
		else if (startTime == 19)
			time = "time019";
		else if (startTime == 20)
			time = "time020";
		else if (startTime == 21)
			time = "time021";
		else if (startTime == 22)
			time = "time022";
		else if (startTime == 23)
			time = "time023";
		else if (startTime == 24)
			time = "time024";
		else
			time = "none";
		return time;
	}

	/**
	 * timeAssignment - Determines the time slot of each event on a day and
	 * creates a text view for each event recursively.
	 *
	 * @param events array list of the events for the day
	 * @return true if successful
	 */
	public Boolean timeAssignment(ArrayList<Event> events)

	{
		int cIndex;
		int sTime;
		int eTime;
		String time;

		for (int i = 0; i < events.size(); i++) {

			index = i;
			sTime = events.get(index).getStartTime() / 60;
			eTime = events.get(index).getEndTime() / 60;

			while (sTime != eTime) // while this event has not filled all their
									// time slots
			{
				// determine time slot
				time = time(sTime);
				if (time.equals("none")) {
					// if there's no matching time slot, don't make textview
				} else {// otherwise set the text view
					int resID = getResources().getIdentifier(time, "id",
							"com.example.cse3311_calendar");
					TextView nTv = (TextView) findViewById(resID);
					nTv.setText(events.get(index).getName());
					cIndex = events.get(index).getCategory();
					nTv.setBackgroundColor(colors[cIndex]);

					// ResourceID identified in R file
					// Not sure if really needed?
					id = events.get(index).getId();

					nTv.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							Bundle info = new Bundle();
							info.putInt("id", id);//?
							//info.putInt("index", index);
							info.putInt("day", day);
							info.putInt("month", month);
							info.putInt("year", year);
							
							Intent eventDetailsIntent = new Intent(
									DayViewActivity.this,
									EventDetailsActivity.class);
							eventDetailsIntent.putExtras(info);
							startActivity(eventDetailsIntent);
						}
					});

				}
				sTime = sTime + 1; // add an hour to the sTime
			}
		}

		return true;

	}

}
