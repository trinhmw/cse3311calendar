package com.example.cse3311_calendar.test;

import java.util.Date;

import junit.framework.TestCase;
import android.util.Log;

import com.example.cse3311_calendar.EditEventController;
import com.example.cse3311_calendar.Event;
import com.example.cse3311_calendar.EventListManager;

public class EditEventTest extends TestCase {
	int id;
	Date key;
	EventListManager elm;

	protected void setUp() throws Exception {
		super.setUp();
		elm = EventListManager.getInstance();
		String name;
		String location;
		Date startDate;
		Date endDate;
		int startTime;
		int endTime;
		String description;
		int category;
		boolean allDay;
		boolean isRepeat;
		int repeatedDays;
		Date lastDay;

		name = "EventName2";
		location = "EventLocation2";
		startDate = new Date(2014, 6, 21);
		key = startDate;
		endDate = new Date(2014, 6, 21);
		startTime = 3;
		endTime = 800;
		description = "Event Description 2";
		category = 3;
		allDay = false; // temporary until implemented
		isRepeat = false; //
		repeatedDays = 0; // ^
		lastDay = null; // ^

		Event newEvent = new Event();
		newEvent.setName("EventName2");
		newEvent.setLocation(location);
		newEvent.setStartDate(startDate);
		newEvent.setStartTime(startTime);
		newEvent.setEndDate(endDate);
		newEvent.setEndTime(endTime);
		newEvent.setDescription(description);
		newEvent.setCategory(category);
		newEvent.setAllDayOption(allDay);
		// repeats...
		elm.addEvent(newEvent);
		id = newEvent.getId();
		Log.v("DEBUGTEST", "SETUP id: " + id);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testAddedEvent(){
            String name;
            String location;
		Date startDate;
		Date endDate;
		int startTime;
		int endTime;
		String description;
		int category;
		boolean allDay;
		boolean isRepeat;
		int repeatedDays;
		Date lastDay;

		name = "EventName2";
		location = "EventLocation2";
		startDate = new Date(2014, 6, 21);
		endDate = new Date(2014, 6, 21);
		startTime = 3;
		endTime = 800;
		description = "Event Description 2";
		category = 3;
		allDay = false; // temporary until implemented
		isRepeat = false; //
		repeatedDays = 0; // ^
		lastDay = null; // ^
                
		Event currentEvent = elm.getEventById(key.toString(), id);
		assertEquals(currentEvent.getName(), name);
		assertEquals(currentEvent.getLocation(), location);
		assertEquals(currentEvent.getStartDate().toString(),
				startDate.toString());
		assertEquals(currentEvent.getEndDate().toString(), endDate.toString());
		assertEquals(currentEvent.getStartTime(), startTime);
		assertEquals(currentEvent.getEndTime(), endTime);
		assertEquals(currentEvent.getDescription(), description);
		assertEquals(currentEvent.getCategory(), 3);
		Log.v("DEBUGTEST", "testAddedEvent id: " + id);
	}
	

	public void testEditEvent() {
		String name;
		String location;
		Date startDate;
		Date endDate;
		int startTime;
		int endTime;
		String description;
		int category;
		boolean allDay;
		boolean isRepeat;
		int repeatedDays;
		Date lastDay;

		name = "EditEventName";
		location = "EditEventLocation";
		startDate = new Date(2015, 6, 11);
		endDate = new Date(2015, 6, 11);
		startTime = 4;
		endTime = 200;
		description = "Edit Event Description";
		category = 2;
		allDay = false; // temporary until implemented
		isRepeat = false; //
		repeatedDays = 0; // ^
		lastDay = null; // ^
		Log.v("DEBUGTEST", "testEditEvent  name: " + name);

		int newid = EditEventController.editEvent(name, location, startDate, endDate,
				startTime, endTime, description, category, allDay, id,
				key.toString());
		
		Log.v("DEBUGTEST", "id: " + id + "  newid:  " + newid + "  name:" + name);
		
	}

	public void testEditEventData() {
		Date startDate = new Date(2015, 6, 11);
		Date endDate = new Date(2015, 6, 11);
		Event currentEvent = elm.getEventById(key.toString(), id);
		assertEquals(currentEvent.getName(), "EditEventName");
		assertEquals(currentEvent.getLocation(), "EditEventLocation");
		assertEquals(currentEvent.getStartDate().toString(),
				startDate.toString());
		assertEquals(currentEvent.getEndDate().toString(), endDate.toString());
		assertEquals(currentEvent.getStartTime(), 4);
		assertEquals(currentEvent.getEndTime(), 200);
		assertEquals(currentEvent.getDescription(), "Edit Event Description");
		assertEquals(currentEvent.getCategory(), 2);
	}

}
