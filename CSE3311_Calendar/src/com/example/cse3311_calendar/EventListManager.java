package com.example.cse3311_calendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class EventListManager implements Serializable {
	
	private static EventListManager myself = null;
	private Hashtable<String, ArrayList<Event>> eventTable;
	private int nextID;
	
	protected EventListManager (){
		
	}
	
	public static EventListManager  getInstance(){
		
		if (myself == null){
			myself = new EventListManager();
			
		}
		
		return myself;
	}
	
	public boolean addEvent(Event newEvent){
		
		boolean added = false;
		
		newEvent.setId(nextID);
		
		String key = newEvent.getStartDate().toString();
		
		ArrayList<Event> daysEvents  = eventTable.get(key);
		
		if (daysEvents == null){
			daysEvents = new ArrayList<Event>();
			daysEvents.add(newEvent);
			eventTable.put(key, daysEvents);
			added = true;
		}
		else{
			for(int i = 0; i < daysEvents.size(); i++){
				Event existingEvent = daysEvents.get(i);
				if((newEvent.getStartTime() >= existingEvent.getStartTime()) &&
						(newEvent.getStartTime() <= existingEvent.getEndTime())){
					//cannot add
					return added;
				}
				if((newEvent.getEndTime() <= existingEvent.getStartTime()) && 
						(newEvent.getEndTime() >= existingEvent.getEndTime())){
					//cannot add
					return added;
				}
			}
			
			daysEvents.add(newEvent);
			Collections.sort(daysEvents);
			added = true;	
		}
		return added;
	}
	

}
