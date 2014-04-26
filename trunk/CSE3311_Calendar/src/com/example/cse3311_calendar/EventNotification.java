package com.example.cse3311_calendar;

import java.io.Serializable;


public class EventNotification implements Comparable<EventNotification>,  Serializable{
	Event event;
	int notificationTime;  //actual time of notification in minutes
	//(i.e. if event occurs at noon and notification is 15minutes before, then = 11:45 = 705 minutes)

	public EventNotification(){
		notificationTime = 0;
		event = new Event();
	}
	
	public EventNotification(Event e, int t){
		setEvent( e );
		setNotificationTime( t );
	}
	
	public void setNotificationTime(int value){ this.notificationTime = value; }
	public int getNotificationTime(){ return this.notificationTime; }
	
	public void setEvent(Event value){ this.event = value; }
	public Event getEvent(){ return this.event; }
	
	@Override
	public int compareTo(EventNotification another) {
		EventNotification toCompare = another;
		return this.getEvent().getStartDate().compareTo(toCompare.getEvent().getStartDate());
	}
}
