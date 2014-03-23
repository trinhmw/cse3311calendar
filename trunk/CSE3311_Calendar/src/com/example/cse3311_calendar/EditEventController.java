package com.example.cse3311_calendar;

import java.util.ArrayList;
import java.util.Date;

/**
 * Controller to handle the Edit Event Use Case, called statically.
 */
public class EditEventController {

	private static Event toEditEvent;
	private EventListManager listManager;

	/**
	 * Complete process of editing an event, returning false if for any reason it fails.
	 *
	 * @param name Name of edited Event
	 * @param location Location of edited Event
	 * @param startDate Start date of edited Event
	 * @param endDate End date of edited Event
	 * @param startTime Start time of edited Event (in minutes)
	 * @param endTime End time of edited Event (in minutes)
	 * @param description Description of edited Event
	 * @param category Category of edited Event (integer referencing an array of categories)
	 * @param allDayOption Boolean indicating event is all day.
	 * @param id Id of event that is being edited
	 * @param key String key for event being edited (toString of a Date object)
	 * @return true if successful, false if edit fails.
	 */
	public static boolean editEvent( String name, String location, Date startDate, Date endDate, int startTime, 
			int endTime, String description, int category, boolean allDayOption, int id, String key )
	{

		//Pull event to edit from EventList Manager
		EventListManager eventListMgr = EventListManager.getInstance();
		toEditEvent = eventListMgr.getEventsById(key, id);

		//Set name, can fail
		boolean result = toEditEvent.setName(name);
		if(result == true){
			//Set other attributes, not required
			toEditEvent.setLocation(location);
			toEditEvent.setDescription(description);
			toEditEvent.setCategory(category);
			toEditEvent.setAllDayOption(allDayOption);
			//If all day is true handle start Date specially
			if(allDayOption == true){
				toEditEvent.setStartDate(startDate);
				toEditEvent.setEndDate(startDate);
			}
			//If all day is false go through and add start date and time, then
			//confirm that end date and time are valid considering start day/time
			else{
				toEditEvent.setStartDateTime(startDate, startTime);
				result = toEditEvent.setendDateTime(endDate, endTime);
			}

		}
		//If at any point the filling in of event failed return the false.
		if(result == false){
			return result;
		}

		return eventListMgr.editEvent(toEditEvent, key, id);

		
	}
}

