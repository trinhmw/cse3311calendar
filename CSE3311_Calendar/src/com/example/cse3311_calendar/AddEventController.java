package com.example.cse3311_calendar;

import java.util.ArrayList;
import java.util.Date;

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
			int endTime, String description, int category, boolean allDayOption, boolean isRepeat, int repeatedDays, Date lastDay )
	{

		if(isRepeat == true)
		{
			return addRepeatedEvent(  name,  location,  startDate,  endDate,  startTime, 
					endTime,  description,  category,  allDayOption, repeatedDays, lastDay );
		}
		else
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
	
	/**
	 * Adds information to Repeated event, checks validity, then adds the event to the manager
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
	private static boolean addRepeatedEvent( String name, String location, Date startDate, Date endDate, int startTime, 
				int endTime, String description, int category, boolean allDayOption,int repeatedDays,Date lastDay )
	{
	
	    //create new Event
		RepeatedEvent newRepeatedEvent = new RepeatedEvent();
		
		boolean result = newRepeatedEvent.setName(name);
		if(result == true){
			newRepeatedEvent.setLocation(location);
			newRepeatedEvent.setDescription(description);
			newRepeatedEvent.setCategory(category);
			newRepeatedEvent.setAllDayOption(allDayOption);
			
			if(lastDay  != null)
			{
				newRepeatedEvent.setLastDay(lastDay);
			}
			if(allDayOption == true){
				newRepeatedEvent.setStartDate(startDate);
				newRepeatedEvent.setEndDate(startDate);
			}
			else{
				newRepeatedEvent.setStartDateTime(startDate, startTime);
				result = newRepeatedEvent.setendDateTime(endDate, endTime);
			}
			if (result == true){
				newRepeatedEvent.setTrueStartDate();
				result = newRepeatedEvent.setRepeatedDays(repeatedDays);
			}
						
		}
		
		if(result == false){
			return result;
		}
		
		
		EventListManager manager = EventListManager.getInstance();
		return manager.addRepeatedEvent(newRepeatedEvent);

		
	}

}
