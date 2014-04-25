package com.example.cse3311_calendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	      //get key and id
			Calendar c = Calendar.getInstance();
			Date d = c.getTime();
			
			String key = "" + d.getDay() + d.getMonth() + d.getYear();
			int id = 0; //need to find a way to get the eventID
			//long milliTime = d.getTime();
			
			//get event via event list manager
			EventListManager elm = EventListManager.getInstance();
			ArrayList<EventNotification> notificationList = elm.getNotificationList();
			EventNotification next = notificationList.get(0);
			
			//check if the Event e actually has a notification at this time
			//    i.e. an EventNotification was removed from list, but the notification itself wasn't
			if(next.getEvent().getStartDate() == d ){ //if next EventNotification is scheduled for now
				//extract event info from event object via Event List Manager
				// the next notification will be listed 
				String eventName = next.getEvent().getName();
				String eventLocation = next.getEvent().getLocation();
				int eventTime = next.getEvent().getStartTime();
				int hour = eventTime / 60;
				int minute = eventTime % 60;
				String time = hour + ":" + minute;
			
				//format message to send to user
				String message = eventName + "\nLocation: " + eventLocation + "\nTime: " + time;
				//play alarm sound
				playSound(this, getAlarmUri()); 
				//callStop();
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

	    private void callStop(){
	    	//get information about event
	    	EventListManager elm = EventListManager.getInstance();
	    	Event e = elm.getNotificationList().get(0).getEvent();
	    	
	    	String title = e.getName();//get event title
	    	String location = e.getLocation();//get event location
	    	String date = e.getStartDate().toString();
	    	int intTime = e.getStartTime();
	    	String time = (intTime / 60) + ":" + (intTime%60); //get event time
	    	String alertMSG = title + ": " + location + " at " + date + " - " + time;
	    	//build alert dialog
	        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	        alertDialogBuilder.setTitle(alertMSG);
	        alertDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {                   
	        	public void onClick(DialogInterface dialog, int which) {
	        		mMediaPlayer.stop();
	        		//delete notification from list of notifications
	        		finish();
	        	} //end onClick.
	    }); // end alertDialog.setButton.
	    alertDialogBuilder.show();  
	    }
	}

