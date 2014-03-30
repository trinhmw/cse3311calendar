package com.example.cse3311_calendar;

import java.util.Date;

public class NotificationController {
	/*

	public static boolean newNotification(int eventID, Date notificationDate, int notificationTime, int timeBefore){
		
		 //* Creates an EventNotification
		 //* Creates an EventListManager
		 //* Adds a notification to EventListManager
		 //* Updates next Notification
		 //* 
		 //* notificationTime is minutes
		 //* notificationDate is day of event
		 //* timeBefore is in minutes
		 
		int hourOfDay = notificationTime / 60;
		int minute = notificationTime % 60;
        Calendar cal = Calendar.getInstance(); //today
        //Calendar future = Calendar.();
        cal.add(Calendar.MINUTE, timeBefore);
        
        EventNotification en = new EventNotification(eventID, cal);
		
		//make Notification object Notification n = new Notification()
		
		//get eventlist manager ( em = EventListManager.getInstance() )
        
        //write notification to eventlist manager ( em.addNotification(Notification n) )

        
        //make alarm of newest notification on list
        nextNotification( em );
        
        return false;
	}
	
	public void nextNotification( EventListManager em ){
		//*
		//* gets the next notification on list
		//*
		
		//get newest notification from EventListManager
	
		//set(notificationDate.getYear(), notificationDate.getMonth(), notificationDate.getDay(), hourOfDay, minute)
		//Create a new PendingIntent and add it to the AlarmManager
        Intent intent = new Intent(this, AlarmReceiverActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
	}
	
	public void deleteNotification(){
		
	}
	*/
}
