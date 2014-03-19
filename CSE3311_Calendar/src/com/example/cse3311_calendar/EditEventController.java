package com.example.cse3311_calendar;

import java.util.ArrayList;
import java.util.Date;

public class EditEventController {

	private static Event toEditEvent;
	private EventListManager listManager;

	public static boolean editEvent( String name, String location, Date startDate, Date endDate, int startTime, 
			int endTime, String description, int category, boolean allDayOption, int id, String key )
	{

		//create new Event
		EventListManager eventListMgr = EventListManager.getInstance();
		toEditEvent = eventListMgr.getEventsById(key, id);

		boolean result = toEditEvent.setName(name);
		if(result == true){
			toEditEvent.setLocation(location);
			toEditEvent.setDescription(description);
			toEditEvent.setCategory(category);
			toEditEvent.setAllDayOption(allDayOption);
			if(allDayOption == true){
				toEditEvent.setStartDate(startDate);
				toEditEvent.setEndDate(startDate);
			}
			else{
				toEditEvent.setstartDateTime(startDate, startTime);
				result = toEditEvent.setendDateTime(endDate, endTime);
			}

		}
		if(result == false){
			return result;
		}

		result = eventListMgr.addEvent(toEditEvent);

		if(result == true){
			result = eventListMgr.deleteEvent(key, id);
		}
		return result;
	}
}

