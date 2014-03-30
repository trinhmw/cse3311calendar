package com.example.cse3311_calendar;

import java.util.Date;

import android.util.Log;

/**
 * Controller to handle the Edit Event Use Case, called statically.
 */
public class EditEventController {

	private static Event toEditEvent;
	private static RepeatedEvent toEditRepeatedEvent;
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
	 * @return new Id if successful, 0 if edit fails.
	 */
	public static int editEvent( String name, String location, Date startDate, Date endDate, int startTime, 
			int endTime, String description, int category, boolean allDayOption, int id, String key )
	{

		boolean result;
		//Pull event to edit from EventList Manager
		EventListManager eventListMgr = EventListManager.getInstance();
		if (id < 0){
			toEditRepeatedEvent = eventListMgr.getRepeatedEventById(key, id);
			if(toEditRepeatedEvent == null){
				return 0;
			}
			//Set name, can fail
			result = toEditRepeatedEvent.setName(name);
			if(result == true){
				//Log.v("Failed to fill in.", "Name!");
				//Set other attributes, not required
				toEditRepeatedEvent.setLocation(location);
				toEditRepeatedEvent.setDescription(description);
				toEditRepeatedEvent.setCategory(category);
				toEditRepeatedEvent.setAllDayOption(allDayOption);
				//If all day is true handle start Date specially
				if(allDayOption == true){
					toEditRepeatedEvent.setStartDate(startDate);
					toEditRepeatedEvent.setEndDate(startDate);
				}
				//If all day is false go through and add start date and time, then
				//confirm that end date and time are valid considering start day/time
				else{				
					toEditRepeatedEvent.setStartDateTime(startDate, startTime);
					result = toEditRepeatedEvent.setendDateTime(endDate, endTime);
				}

			}

			//If at any point the filling in of event failed return the false.
			if(result == false){
				return 0;
			}


			result =  eventListMgr.editRepeatedEvent(toEditRepeatedEvent, key, id);

			if(result == false){
				return 0;	
			}
			return toEditRepeatedEvent.getId();
		}
		else{
			toEditEvent = eventListMgr.getEventById(key, id);
			
			if(toEditEvent == null){
				return 0;
			}
			//Set name, can fail
			result = toEditEvent.setName(name);
			if(result == true){
				//Log.v("Failed to fill in.", "Name!");
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
				return 0;
			}


			result =  eventListMgr.editEvent(toEditEvent, key, id);

			if(result == false){
				return 0;	
			}
			return toEditEvent.getId();
		}

	}
}

