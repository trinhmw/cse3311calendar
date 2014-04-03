package com.example.cse3311_calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class AlarmReceiver extends BroadcastReceiver{
	
	@Override
    public void onReceive(Context context, Intent intent){  
			
			/*
			//get key and id
			Calendar c = Calendar.getInstance();
			Date d = c.getTime();
			
			String key = "" + d.getDay() + d.getMonth() + d.getYear();
			int id = 0; //need to find a way to get the eventID
			//long milliTime = d.getTime();
			
			//get event via event list manager
			EventListManager elm = EventListManager.getInstance();
			Event e = elm.getEventById(key, id);
			
			/*
			ArrayList<Event> ale = elm.getEvents(key);
			for(Event a : ale){
				if( (a.getStartTime()*60*1000) == milliTime ){
					e = a; break;
				}
			}
			
			
			//extract event info from event object
			String eventName = e.getName();
			String eventLocation = e.getLocation();
			int eventTime = e.getStartTime();
			int hour = eventTime / 60;
			int minute = eventTime % 60;
			String time = hour + ":" + minute;
			
			//format message to send to user
			String message = eventName + "\nLocation: " + eventLocation + "\nTime: " + time;
			*/
		
			//get users phone number
			String phoneNumberReciver = "5554";
			String message = "This is an Event Reminder!";
			//send SMS with Event information to user
            SmsManager sms = SmsManager.getDefault(); 
            sms.sendTextMessage(phoneNumberReciver, null, message, null, null);
            
            
     }
}
