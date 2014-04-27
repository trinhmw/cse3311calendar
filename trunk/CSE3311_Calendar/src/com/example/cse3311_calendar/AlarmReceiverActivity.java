package com.example.cse3311_calendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.cse3311_calendar.EventNotification;
import com.example.cse3311_calendar.Event;
import com.example.cse3311_calendar.EventListManager;


public class AlarmReceiverActivity extends Activity{
		
		private MediaPlayer mMediaPlayer; 
		private int day, month, year;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_notification);
	        //get key and id
			Calendar c = Calendar.getInstance();
			Date d = c.getTime();
			
			
			int id = 0; //need to find a way to get the eventID
			Bundle extras = getIntent().getExtras();
	        if (extras != null){
	        	id = extras.getInt("id", 0);
	        	month = extras.getInt("month");
	        	year = extras.getInt("year");
	        	day = extras.getInt("day");
	        	d = new Date (year, month, day);

	        }
	        String key = d.toString();
			
			//get event via event list manager
			EventListManager elm = EventListManager.getInstance();
			Event event = elm.getEventById(key, id);

				String eventName = event.getName();
				String eventLocation = event.getLocation();
				String eventDescription = event.getDescription();
				int eventTime = event.getStartTime();
				int hours = eventTime / 60;
				int minutes = eventTime % 60;
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
				String time = "" + hours + ":" + minutesString + " " + AmPm;
			
				//format message to send to user
				String message = eventName + "\nLocation: " + eventLocation + 
						"\nDate: " + month + "-" + day + "-" + year + "\nTime: " + time
						+ "\nDescription: " + eventDescription;
				//play alarm sound
				playSound(this, getAlarmUri()); 
				//createAlertDialoge( message );
				
				TextView tv = (TextView) findViewById(R.id.eventNotification);
		        tv.setText(message);
		        
		        Button take = (Button) findViewById(R.id.eventNotificationButton);
		        take.setOnClickListener( new OnClickListener() {
		        	public void onClick(View v) {
		        		mMediaPlayer.stop();
		        		EventListManager elm = EventListManager.getInstance();
		        		elm.removeNotification(elm.getNotificationList().get(0));
		        		finish();
		        	} //end onClick.
		        }); 
		}       
	    

	    private void playSound(Context context, Uri alert) {
	        mMediaPlayer = new MediaPlayer();
	        try {
	            mMediaPlayer.setDataSource(context, alert);
	            final AudioManager audioManager = (AudioManager) context
	                .getSystemService(Context.AUDIO_SERVICE);
	            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
	                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
	                mMediaPlayer.prepare();
	                mMediaPlayer.start();
	            }
	        } catch (IOException e) {
	             System.out.println("OOPS");
	        }
	    }

	    //Get an alarm sound. Try for an alarm. If none set, try notification, 
	    //Otherwise, ringtone.
	    private Uri getAlarmUri() {
	        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
	        if (alert == null) {
	            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	            if (alert == null) {
	            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
	            }
	        }
	        return alert;
	    }
	    /* Not Used
	    private void createAlertDialog(String alertMSG){
	    	//build alert dialog
	        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	        alertDialogBuilder.setTitle(alertMSG);
	        alertDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {                   
	        	public void onClick(DialogInterface dialog, int which) {
	        		mMediaPlayer.stop();
	        		//delete notification from list of notifications
	        		EventListManager elm = EventListManager.getInstance();
	        		elm.removeNotification(elm.getNotificationList().get(0));
	        		
	        		finish();
	        	} //end onClick.
	    }); // end alertDialog.setButton.
	    alertDialogBuilder.show();  
	    }
	    */
	}

