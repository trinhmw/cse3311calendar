package com.example.cse3311_calendar;


public class EventNotification implements Comparable<EventNotification>{
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
		//compare date of this to date of another
		  //compare the years
		int yearCompare = this.getEvent().getStartDate().getYear() - toCompare.getEvent().getStartDate().getYear();
		if(yearCompare == 0){
		    //compare the months
			int monthCompare = this.getEvent().getStartDate().getMonth() - toCompare.getEvent().getStartDate().getMonth();
			if(monthCompare == 0){
		        //compare the days
				int dayCompare = this.getEvent().getStartDate().getDay() - toCompare.getEvent().getStartDate().getDay();
				if(dayCompare == 0){
					//compare the notificationTime
					return this.getNotificationTime() - toCompare.getNotificationTime();
				}
				else
					return dayCompare;
			}
			else
				return monthCompare;
		}
		else  //not on the same day
			return yearCompare;
	}
}
