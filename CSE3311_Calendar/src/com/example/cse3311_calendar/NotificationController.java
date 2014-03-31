package com.example.cse3311_calendar;

import java.util.Date;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotificationController {
	

	public static boolean newNotification(int eventID, Date notificationDate, int notificationTime, int timeBefore){
		
		 //* Creates an EventNotification
		 //* Creates an EventListManager
		 //* Adds a notification to EventListManager
		 //* Updates next Notification
		 //* 
		 //* notificationTime is minutes (8AM = 480)
		 //* notificationDate is day of event
		 //* timeBefore is in minutes

		int hour = notificationTime / 60; 
		int minute = (notificationTime - timeBefore) % 60;
		
		@SuppressWarnings("deprecation")
		int day = notificationDate.getDay(); 
		@SuppressWarnings("deprecation")
		int month = notificationDate.getMonth();
		@SuppressWarnings("deprecation")
		int year = notificationDate.getYear();
		
		Date alarmDate = new Date();
		alarmDate.setDate(day); 
		alarmDate.setMonth(month); 
		alarmDate.setYear(year); 
		alarmDate.setHours(hour); 
		alarmDate.setMinutes(minute);
		
		/*
		Calendar alarmDate = Calendar.getInstance();   //alarmDate = today, no other way to initialize it
		alarmDate.setTime(notificationDate);           //alarmDate = notificationDate
		alarmDate.add(Calendar.MINUTE, -timeBefore);   //alarmDate = notificationDate @ user requested time
   		*/
		
		//make Notification object Notification n = new Notification()
		EventNotification en = new EventNotification(eventID, alarmDate);
        
		//get eventListManager
        EventListManager em = EventListManager.getInstance();
        
        //write notification to eventListManager
        //em.addNotification( en );
        
        //make alarm of newest notification on list
        //scheduleNotification( em );
          Context context = null;
          Intent intent = new Intent(context, AlarmReceiverActivity.class);
          PendingIntent pendingIntent = PendingIntent.getActivity(context, 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
          AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
          am.set(AlarmManager.RTC_WAKEUP, alarmDate.getTime(), pendingIntent);
          //deleteNotification( em, en.getEventID() );
        
        return false;
	}
	
	public static void scheduleNotification( EventListManager em ){
		/*
		 * gets the next notification on list
		 * sets the corresponding alarm
		 * deletes notification (from list via EventListManager) once alarm goes off
		 */
		
		//need to write getNextNotification(), 
		//get newest notification from EventListManager
		EventNotification en = new EventNotification();//EventNotification en = em.getNextNotification();
		
		//Create a new PendingIntent and add it to the AlarmManager
		Context context = null;
		Date alarmDate = en.getNotificationDate();
		
        Intent intent = new Intent(context, AlarmReceiverActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager)context.getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, alarmDate.getTime(), pendingIntent);
        deleteNotification( em, en.getEventID() );
	}
	
	public static void deleteNotification(EventListManager em, int eventID){
		//EventNotification en = em.getNotification(eventID);
		//em.deleteNotification( en );
		//cancel( someIntent );
	}
}
