package com.example.cse3311_calendar;

import java.util.Date;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
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

		/* Commented out because Date class is deprecated and no longer used
		notificationTime = notificationTime - timeBefore; 
		int hour = notificationTime / 60; 
		int minute = notificationTime % 60;
		
		@SuppressWarnings("deprecation")
		int day = notificationDate.getDay(); 
		@SuppressWarnings("deprecation")
		int month = notificationDate.getMonth();
		@SuppressWarnings("deprecation")
		int year = notificationDate.getYear();
		
		Date alarmDay = new Date();
		alarmDay.setDate(day); 
		alarmDay.setMonth(month); 
		alarmDay.setYear(year); 
		alarmDay.setHours(hour); 
		alarmDay.setMinutes(minute);
		*/
		
		Calendar alarmDate = Calendar.getInstance();   //alarmDate = today, no other way to initialize it
		alarmDate.setTime(notificationDate);           //alarmDate = notificationDate
		alarmDate.add(Calendar.MINUTE, -timeBefore);   //alarmDate = notificationDate @ user requested time
   	
		//make Notification object Notification n = new Notification()
		EventNotification en = new EventNotification(eventID, alarmDate);
        
		//get eventlist manager ( em = EventListManager.getInstance() )
        EventListManager em = EventListManager.getInstance();
        /*
        //write notification to eventListManager ( em.addNotification(EventNotification en) )
        em.addNotification( en );
        
        //make alarm of newest notification on list
        setNextNotification( em );
        */
        return false;
	}
	
	public void setNextNotification( EventListManager em ){
		/*
		 * gets the next notification on list
		 * sets the corresponding alarm
		 * deletes notification (from list via EventListManager) once alarm goes off
		 */
		
		/* need to write getNextNotificaiton(), 
		//get newest notification from EventListManager
		EventNotification en = em.getNextNotification();
		
		//Create a new PendingIntent and add it to the AlarmManager
        Intent intent = new Intent(this, AlarmReceiverActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        notify();
        deleteNotification( em, en.getEventID() );
        */
	}
	
	public void deleteNotification(EventListManager em, int eventID){
		//EventNotification en = em.getNotification(eventID);
		//em.deleteNotification( en );
	}
	
	public boolean isSet(){
		
		return false;
	}
}
