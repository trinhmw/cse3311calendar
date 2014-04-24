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
	/** How it Works: Receives broadcast from alarm manager at time of notification
	 *	 	finds the next EventNotification from the EventListManager
	 *		Gets the event info from the EventNotification
	 *		sends event info to user via SMS message
	 *		deletes the EventNotification from EventListManager
	 **/
			/*
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
		
				//get users phone number
				String phoneNumberReciver = "5554";
			
				//send SMS with Event information to user
				SmsManager sms = SmsManager.getDefault(); 
				sms.sendTextMessage(phoneNumberReciver, null, message, null, null);
			}
            */
     }
}
