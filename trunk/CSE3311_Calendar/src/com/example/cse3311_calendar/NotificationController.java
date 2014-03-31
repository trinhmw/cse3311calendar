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
		/* Creates an EventNotification
		 * (no longer necessary) Creates an EventListManager
		 * (no longer necessary) Adds a notification to EventListManager
		 * (no longer necessary) Updates next Notification
		 * 
		 * notificationTime is minutes (8AM = 480)
		 * notificationDate is day of event
		 * timeBefore is in minutes
		 */
		
		int hour = notificationTime / 60; 
		int minute = (notificationTime - timeBefore) % 60;
		int day = notificationDate.getDay(); 
		int month = notificationDate.getMonth();
		int year = notificationDate.getYear();
		
		Date alarmDate = new Date();
		alarmDate.setDate(day); 
		alarmDate.setMonth(month); 
		alarmDate.setYear(year); 
		alarmDate.setHours(hour); 
		alarmDate.setMinutes(minute);
		
		/* Same as above, but using Calendar Object
		Calendar alarmDate = Calendar.getInstance();   //alarmDate = today, no other way to initialize it
		alarmDate.setTime(notificationDate);           //alarmDate = notificationDate
		alarmDate.add(Calendar.MINUTE, -timeBefore);   //alarmDate = notificationDate @ user requested time
   		*/
		
		//make Notification object Notification n = new Notification()
		EventNotification en = new EventNotification(eventID, alarmDate);
		
          Context context = null; 
          Intent intent = new Intent(context, AlarmReceiverActivity.class);
          PendingIntent alarmIntent = PendingIntent.getBroadcast(context.getApplicationContext(), eventID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
          AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
          am.set(AlarmManager.RTC_WAKEUP, alarmDate.getTime(), alarmIntent);
          
         if(am == null) //alarm was not set
        	 return false;
         else
        	 return true;
	}//end of newNotification()
	
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

	public boolean deleteNotification(AlarmManager am, int eventID){
		Context context = null;
		Intent intent = new Intent(context, AlarmReceiverActivity.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(context, eventID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		am.cancel( alarmIntent );
		if(am == null)
			return true;
		else 
			return false;
	}//end of deleteNotification()
}
