package com.example.cse3311_calendar;

import java.util.ArrayList;
import java.util.Date;

/*------------------------------------------------------------
*       AddEventController
*
*   Written by: Brandon Deen
*   Date: 1 March 2015
*   Purpose: Take data input from user via Event Form GUI
*	     create an event from that data
*	     add the event to the Event List
*	     call the Day View to show user the event
-------------------------------------------------------------*/


public class AddEventController 
{

	private static Event newEvent;
	private EventListManager listManager;

	public static boolean addEvent( String name, String location, Date startDate, Date endDate, int startTime, 
				int endTime, String description, int category, boolean allDayOption )
	{

	     //create new Event
		newEvent = new Event();
		
		boolean result = newEvent.setName(name);
		if(result == true){
			newEvent.setLocation(location);
			newEvent.setDescription(description);
			newEvent.setCategory(category);
			newEvent.setAllDayOption(allDayOption);
			if(allDayOption == true){
				newEvent.setStartDate(startDate);
				newEvent.setEndDate(startDate);
			}
			else{
				newEvent.setstartDateTime(startDate, startTime);
				result = newEvent.setendDateTime(endDate, endTime);
			}
			
		}
		if(result == false){
			return result;
		}
		
		
		EventListManager manager = EventListManager.getInstance();
		return manager.addEvent(newEvent);
		/*
	     //Get event list from eventTable				
		tempEventList = listManager.getEvents( startDate ); //may have to => toString(startDate)		

             //event already exists
		if( tempEventList.contains( newEvent ) )
		     return false;

	     //add event to event list
		else  
		     listManager.addEvent( newEvent ); //still not a 100% if this is the way we talked about adding it			
				
				*/
	     //Show new event in day View
		
		
	}

}
