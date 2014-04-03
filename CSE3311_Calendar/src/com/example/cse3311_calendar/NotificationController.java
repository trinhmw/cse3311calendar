package com.example.cse3311_calendar;

import java.util.Date;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationController {
	

	public static boolean createNotification(Event event, int notificationTime){
		/* Creates an EventNotification
		 * (no longer necessary) Creates an EventListManager
		 * (no longer necessary) Adds a notification to EventListManager
		 * (no longer necessary) Updates next Notification
		 * 
		 * notificationTime is minutes (8AM = 480)
		 * notificationDate is day of event
		 * timeBefore is in minutes
		 */
		boolean added = false;
		
		int hour = notificationTime / 60; 
		int minute = notificationTime % 60;
		int day = event.getStartDate().getDay(); 
		int month = event.getStartDate().getMonth();
		int year = event.getStartDate().getYear();
		
		Date alarmDate = new Date();
		alarmDate.setDate(day); 
		alarmDate.setMonth(month); 
		alarmDate.setYear(year); 
		alarmDate.setHours(hour); 
		alarmDate.setMinutes(minute);
		/*
		//CharSequence message = "EVENT INFO";
        //Notification n = new Notification(0, message,alarmDate.getTime());
		
		//make Notification object Notification n = new Notification()
		//EventNotification en = new EventNotification(eventID, alarmDate);
		*/
        Context context = null; 
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context.getApplicationContext(), event.getId(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, alarmDate.getTime(), alarmIntent);
        //do some error checking
        	 Toast.makeText(context,("Notification set for " + month + "/" + day + " at " + hour + ":" + minute),Toast.LENGTH_LONG).show();
        	 added = true; 
        return added;
	}//end of newNotification()
	
	public static boolean repeatingNotification(Event event, int notificationTime, int frequency){
		/* Creates an Event Notification
		 * 
		 * notificationTime is minutes (8AM = 480)
		 * notificationDate is day of event
		 * timeBefore is in minutes
		 * frequency is in minutes -> weekly = 7*24*60
		 */
		boolean added = false;
		
		int hour = notificationTime / 60; 
		int minute = notificationTime % 60;
		int day = event.getStartDate().getDay(); 
		int month = event.getStartDate().getMonth();
		int year = event.getStartDate().getYear();
		long milliFrequency = frequency *60*1000; //converts frequency from minutes to milliseconds
		
		Date alarmDate = new Date();
		alarmDate.setDate(day); 
		alarmDate.setMonth(month); 
		alarmDate.setYear(year); 
		alarmDate.setHours(hour); 
		alarmDate.setMinutes(minute);
		
		//CharSequence message = "EVENT INFO";
        //Notification n = new Notification(0, message,alarmDate.getTime());
		
		Context context = null; 
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context.getApplicationContext(), event.getId(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, alarmDate.getTime(), milliFrequency,alarmIntent);
		
        //if(am != null){
       	 	Toast.makeText(context,("Notification set for " + month + "/" + day + " at " + hour + ":" + minute),Toast.LENGTH_LONG).show();
       	 	added = true; 
       	 	
        return added;
	}
	
    /* UPDATE!!!
     * After some research I found that AlarmManager takes care of multiple alarms
     * which eliminates the need to store them in a list, so to delete/edit
     * all you need to do is use the eventID which connects the event to the notification
     * 
     * SO...scheduleNotification shouldn't be needed
     * 
	public static void scheduleNotification( EventListManager em ){
		// gets the next notification on list
		// sets the corresponding alarm
		// deletes notification (from list via EventListManager) once alarm goes off
		 
		
		//need to write getNextNotification(), 
		//get newest notification from EventListManager
		EventNotification en = new EventNotification();//EventNotification en = em.getNextNotification();
		
		//Create a new PendingIntent and add it to the AlarmManager
		Context context = null;
		Date alarmDate = en.getNotificationDate();
		
        Intent intent = new Intent(context, AlarmReceiverActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, alarmDate.getTime(), pendingIntent);
        //deleteNotification( em, en.getEventID() );
	}
	*/

	public static boolean deleteNotification(EventNotification notification){
		boolean deleted = false;
		
		Context context = null;
		Intent intent = new Intent(context, AlarmReceiver.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(context, notification.getEvent().getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		am.cancel( alarmIntent );
		//error checking
		deleted = true;
		
		return deleted;
	}//end of deleteNotification()
}
