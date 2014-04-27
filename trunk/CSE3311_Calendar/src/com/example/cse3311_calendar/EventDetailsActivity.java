package com.example.cse3311_calendar;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity that displays details of an event
 */
public class EventDetailsActivity extends Activity {

	EventListManager elm;

	private int day;
	private int month;
	private int year;
	private int id;
	private int index;
	private final String categories[] = {"Work", "School" , "Personal"};

	private TextView nameText;
	private Date currentDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_details);

		nameText = (TextView) findViewById(R.id.name);
		
		Button dayView = (Button) findViewById(R.id.day_view_button);
		dayView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent changeIntent = new Intent(EventDetailsActivity.this, DayViewActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("day", day);
				bundle.putInt("month", month);
				bundle.putInt("year", year);
				changeIntent.putExtras(bundle);
				startActivity(changeIntent);

			}
		});
		

		Intent intent = getIntent();
		Bundle inputBundle = intent.getExtras();

		if(inputBundle != null){
			day = inputBundle.getInt("day");
			month = inputBundle.getInt("month");
			year = inputBundle.getInt("year");
			id = inputBundle.getInt("id");
		}
		else
		{
			nameText.setText("Event could not be found.");
		}

		//Get key
		currentDate = new Date(year,month,day);
		elm = EventListManager.getInstance();

		Event currentEvent;
		//get event
		if(id < 0){
			currentEvent= elm.getRepeatedEventById(currentDate.toString(), id);
		}
		else{
			currentEvent= elm.getEventById(currentDate.toString(), id);
		}

		if(currentEvent == null){
			nameText.setText("Event could not be found.");
		}else{
			nameText.setText(currentEvent.getName());
			TextView locationText = (TextView) findViewById(R.id.location);
			locationText.setText(currentEvent.getLocation());

			//Toast.makeText(EventDetailsActivity.this, "" + currentEvent.getStartDate().getYear(), Toast.LENGTH_LONG).show();
			TextView startDateText = (TextView) findViewById(R.id.start_date);
			String startDateString = "" + (currentEvent.getStartDate().getMonth() + 1) + "/"
					+ currentEvent.getStartDate().getDate() + "/" + currentEvent.getStartDate().getYear();
			//SimpleDateFormat dateFormatter = new SimpleDateFormat("mm-dd-yyyy");
			startDateText.setText(startDateString);

			TextView startTimeText = (TextView) findViewById(R.id.start_time);
			int startTime = currentEvent.getStartTime();
			int hours = startTime/60;
			int minutes = startTime%60;
			String AmPm = "";
			if(hours == 0){
				hours = 12;
				AmPm = "AM";

			}
			else if (hours < 12){
				AmPm = "AM";
			}else{
				hours = hours - 12;
				AmPm = "PM";
			}
			String minutesString = "";
			if(minutes < 10){
				minutesString = "0" + minutes;
			}else{
				minutesString = "" + minutes;
			}
			startTimeText.setText("" + hours + ":" + minutesString + " " + AmPm);

			TextView endDateText = (TextView) findViewById(R.id.end_date);
			String endDateString = "" + (currentEvent.getEndDate().getMonth() + 1) + "/"
					+ currentEvent.getEndDate().getDate() + "/" + currentEvent.getEndDate().getYear();
			//SimpleDateFormat dateFormatter = new SimpleDateFormat("mm-dd-yyyy");
			endDateText.setText(endDateString);
			//endDateText.setText(dateFormatter.format(currentEvent.getEndDate()));

			TextView endTimeText = (TextView) findViewById(R.id.end_time);
			int endTime = currentEvent.getEndTime();
			hours = endTime/60;
			minutes = endTime%60;
			AmPm = "";
			if(hours == 0){
				hours = 12;
				AmPm = "AM";

			}
			else if (hours < 12){
				AmPm = "AM";
			}else{
				hours = hours - 12;
				AmPm = "PM";
			}
			minutesString = "";
			if(minutes < 10){
				minutesString = "0" + minutes;
			}else{
				minutesString = "" + minutes;
			}
			endTimeText.setText("" + hours + ":" + minutesString + " " + AmPm);

			TextView categoryText = (TextView) findViewById(R.id.category);
			categoryText.setText(categories[currentEvent.getCategory()]);

			TextView descriptionText = (TextView) findViewById(R.id.description);
			descriptionText.setText(currentEvent.getDescription());

			CheckBox allDayCheck = (CheckBox) findViewById(R.id.all_day);
			allDayCheck.setClickable(false);
			allDayCheck.setChecked(currentEvent.isAllDayOption());
			
			TextView notificationText = (TextView) findViewById(R.id.notificationDetail);
			notificationText.setText(getNotificationDetails(currentEvent.getName()));

			Button editButton = (Button) findViewById(R.id.edit_event);
			editButton.setBackgroundColor(Color.GREEN);
			editButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					Intent changeIntent = new Intent(EventDetailsActivity.this, EditEventActivity.class);
					Bundle bundle = new Bundle();
					bundle.putInt("day", day);
					bundle.putInt("month", month);
					bundle.putInt("year", year);
					bundle.putInt("id", id);
					changeIntent.putExtras(bundle);
					startActivity(changeIntent);

				}
			});


			Button deleteButton = (Button) findViewById(R.id.delete_event);
			deleteButton.setBackgroundColor(Color.RED);
			deleteButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.v("onClick", "OnClick");
					DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							switch (which){
							case DialogInterface.BUTTON_POSITIVE:
								boolean result = DeleteEventController.deleteEvent(currentDate.toString(), id, EventDetailsActivity.this);

								if (result == false){
									Toast.makeText(EventDetailsActivity.this, "Could not delete event."
											, Toast.LENGTH_LONG).show();
								}else{

									Intent changeIntent = new Intent(EventDetailsActivity.this, DayViewActivity.class);
									Bundle bundle = new Bundle();
									bundle.putInt("day", day);
									bundle.putInt("month", month);
									bundle.putInt("year", year);

									changeIntent.putExtras(bundle);
									startActivity(changeIntent);
								}
								break;

							case DialogInterface.BUTTON_NEGATIVE:
								//No button clicked
								break;
							}
						}
					};

					AlertDialog.Builder builder = new AlertDialog.Builder(EventDetailsActivity.this);
					builder.setMessage("Are you sure you want to delete?").setPositiveButton("Yes", dialogClickListener)
					.setNegativeButton("No", dialogClickListener).show();


				}
			});

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_details, menu);
		return true;
	}
	
	private String getNotificationDetails(String eventName){
		String notificationDetails = "None";
		int notificationValue = 0;
		elm = EventListManager.getInstance();
		ArrayList<EventNotification> nList = elm.getNotificationList();
		for(int i=0; i<nList.size(); i++){
			if(nList.get(i).getEvent().getName().equals(eventName)){
				notificationValue = nList.get(i).getNotificationTime();
				switch(notificationValue){
				case 0:    notificationDetails = ": At time of event"; 		break;
				case 15:   notificationDetails = ": 15 minutes before event"; break;
				case 30:   notificationDetails = ": 30 minutes before event"; break;
				case 60:   notificationDetails = ": 1 hour before event"; 	break;
				case 1440: notificationDetails = ": 1 day before event"; 		break;
				}
				break;
			}
		}
		
		return notificationDetails;
	}

}
