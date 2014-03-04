package com.example.cse3311_calendar;

import java.util.ArrayList;

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

/*
public class AddEventController 
{

	private Event newEvent;
	private EventListManager listManager;

	public boolean addEvent( int id, String name, String location, Date startDate, Date endDate, int startTime, 
				int endTime, String description, int category, boolean allDayOption )
	{
		ArrayList<Event> tempEventList = new ArrayList<Event>();

	     //create new Event
		newEvent = new Event( id, name, location, startDate, endDate, startTime, endTime, 
					description, category, allDayOption );
		

	     //Get event list from eventTable				
		tempEventList = listManager.getEvents( startDate ); //may have to => toString(startDate)		

             //event already exists
		if( tempEventList.contains( newEvent ) )
		     return false;

	     //add event to event list
		else  
		     listManager.addEvent( newEvent ); //still not a 100% if this is the way we talked about adding it			
				
	     //Show new event in day View
		GUIManager.launchNewView(DayView); //or whatever the enum value for Day View is
		
		return true;
	}
}
*/