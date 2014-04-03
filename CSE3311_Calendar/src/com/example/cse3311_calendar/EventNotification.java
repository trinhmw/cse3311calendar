package com.example.cse3311_calendar;


public class EventNotification {
	Event event;
	int notificationTime;  //actual time of notification 
	//(i.e. if event occurs at noon and notification is 15minutes before, then = 11:45)

	public EventNotification(){
		notificationTime = 0;
		event = new Event();
	}
	
	public EventNotification(Event e, int a){
		setEvent(e);
		setNotificationTime(a);
	}
	
	public void setNotificationTime(int value){ this.notificationTime = value; }
	public int getNotificationTime(){ return this.notificationTime; }
	
	public void setEvent(Event value){ this.event = value; }
	public Event getEvent(){ return this.event; }
	
	
}
