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


/**
 * Controller to handle Add Event Use Case
 */
public class AddEventController 
{

	private static Event newEvent;
	private EventListManager listManager;

	/**
	 * Adds information to event, checks validity, then adds the event to the manager
	 *
	 * @param name Name of new Event
	 * @param location Location of new Event
	 * @param startDate Start date of new Event
	 * @param endDate End date of new Event
	 * @param startTime Start time of new Event (in minutes)
	 * @param endTime End time of new Event (in minutes)
	 * @param description Description of new Event
	 * @param category Category of new Event (integer referencing an array of categories)
	 * @param allDayOption Boolean indicating event is all day.
	 * @return true if successful, false if add fails.
	 */
	public static boolean addEvent( String name, String location, Date startDate, Date endDate, int startTime, 
				int endTime, String description, int category, boolean allDayOption )
	{

	     //create new Event
		newEvent = new Event();
		
		//Set name, check if valid
		boolean result = newEvent.setName(name);
		if(result == true){
			//If valid set various data that does not need to be checked
			newEvent.setLocation(location);
			newEvent.setDescription(description);
			newEvent.setCategory(category);
			newEvent.setAllDayOption(allDayOption);
			//If event is all day set date's specially
			if(allDayOption == true){
				newEvent.setStartDate(startDate);
				newEvent.setEndDate(startDate);
			}
			//If event is not all day set start date/time then end date/time
			//checking if the dates/times are valid (end after beginning)
			else{
				newEvent.setStartDateTime(startDate, startTime);
				result = newEvent.setendDateTime(endDate, endTime);
			}
			
		}
		
		//If any filling it failed return the false
		if(result == false){
			return result;
		}
		
		//Add the new event to the manager, return result of this.
		EventListManager manager = EventListManager.getInstance();
		return manager.addEvent(newEvent);
		
	}

}
