package com.example.cse3311_calendar;

import java.util.Calendar;
import java.util.Date;

public class EventNotification {
	int eventID;
	Date notificationDate;//Calendar notificationDate;

	public EventNotification(){
		notificationDate = new Date();//Calendar.getInstance();
		eventID = 0;
	}
	
	public EventNotification(int id, Date a){
		setEventID(id);
		setNotificationDate(a);
	}
	
	public void setNotificationDate(Date value){ this.notificationDate = value; }
	public Date getNotificationDate(){ return this.notificationDate; }
	
	public void setEventID(int value){ this.eventID = value; }
	public int getEventID(){ return this.eventID; }
	
	
}
