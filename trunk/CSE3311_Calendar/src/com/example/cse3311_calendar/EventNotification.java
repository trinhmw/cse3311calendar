package com.example.cse3311_calendar;

import java.util.Calendar;

public class EventNotification {
	int eventID;
	Calendar notificationDate;

	public EventNotification(){
		notificationDate = Calendar.getInstance();
		eventID = 0;
	}
	
	public EventNotification(int id, Calendar a){
		setEventID(id);
		setNotificationDate(a);
	}
	
	public void setNotificationDate(Calendar value){ this.notificationDate = value; }
	public Calendar getNotificationDate(){ return this.notificationDate; }
	
	public void setEventID(int value){ this.eventID = value; }
	public int getEventID(){ return this.eventID; }
	
	
}
