package com.example.cse3311_calendar.test;

import java.io.File;
import java.util.Date;

import junit.framework.TestCase;
import android.os.Environment;
import android.util.Log;

import com.example.cse3311_calendar.AddEventController;
import com.example.cse3311_calendar.EditEventController;
import com.example.cse3311_calendar.Event;
import com.example.cse3311_calendar.EventListManager;
import com.example.cse3311_calendar.RepeatedEvent;

public class EditEventTest extends TestCase {
	int id;
	Date key;
	Date repeatKey;
	int repeatId;
	

	protected void setUp() throws Exception {
		super.setUp();
		new File(Environment.getExternalStorageDirectory(), "/data.dat").delete();
		new File(Environment.getExternalStorageDirectory(), "/data2.dat").delete();
		EventListManager.killInstnace();
		EventListManager elm = EventListManager.getInstance();

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
		
		name = "EventName2";
		location = "EventLocation2";
		startDate = new Date(2014, 7, 21);
		repeatKey = startDate;
		endDate = new Date(2014, 7, 21);
		startTime = 3;
		endTime = 800;
		description = "Event Description 2";
		category = 3;
		allDay = false; // temporary until implemented
		isRepeat = true; //
		repeatedDays = 3; // ^
		lastDay = new Date(2014, 8, 21); // ^

		
		RepeatedEvent newRepeatedEvent = new RepeatedEvent();
		newRepeatedEvent.setName("EventName2");
		newRepeatedEvent.setLocation(location);
		newRepeatedEvent.setStartDate(startDate);
		newRepeatedEvent.setStartTime(startTime);
		newRepeatedEvent.setEndDate(endDate);
		newRepeatedEvent.setEndTime(endTime);
		newRepeatedEvent.setDescription(description);
		newRepeatedEvent.setCategory(category);
		newRepeatedEvent.setAllDayOption(allDay);
		newRepeatedEvent.setRepeatedDays(repeatedDays);
		newRepeatedEvent.setLastDay(lastDay);
		
		//AddEventController.addEvent(name, location, startDate, endDate, startTime, endTime, 
		//		description, category, allDay, isRepeat, repeatedDays, lastDay);
		// repeats...
		elm.addRepeatedEvent(newRepeatedEvent);
		repeatId = newRepeatedEvent.getId();
		Log.v("DEBUGTEST", "DEBUG EDITEVENTTEST id: " + id);
		//System.out.println("id: "+ id);

	}

	protected void tearDown() throws Exception {
		super.tearDown();
		new File(Environment.getExternalStorageDirectory(), "/data.dat").delete();
		new File(Environment.getExternalStorageDirectory(), "/data2.dat").delete();
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

		EventListManager elm = EventListManager.getInstance();
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
		

		name = "EditEventName";
		location = "EditEventLocation";
		startDate = new Date(2015, 6, 11);
		endDate = new Date(2015, 6, 11);
		startTime = 4;
		endTime = 200;
		description = "Edit Event Description";
		category = 2;
		allDay = false; // temporary until implemented
		
		Log.v("DEBUGTEST", "testEditEvent  name: " + name);

		int newid = EditEventController.editEvent(name, location, startDate, endDate,
				startTime, endTime, description, category, allDay, id,
				key.toString());
		
		assertFalse(0 == newid);

		Log.v("DEBUGTEST", "DEBUG id: " + id + "  newid:  " + newid + "  name:" + name);

		//Date startDate = new Date(2015, 6, 11);
		//Date endDate = new Date(2015, 6, 11);
		EventListManager elm = EventListManager.getInstance();
		Event currentEvent = elm.getEventById(startDate.toString(), newid);
		if (currentEvent != null){
			assertEquals(currentEvent.getName(), "EditEventName");
			assertEquals(currentEvent.getLocation(), "EditEventLocation");
			assertEquals(currentEvent.getStartDate().toString(),
					startDate.toString());
			assertEquals(currentEvent.getEndDate().toString(), endDate.toString());
			assertEquals(currentEvent.getStartTime(), 4);
			assertEquals(currentEvent.getEndTime(), 200);
			assertEquals(currentEvent.getDescription(), "Edit Event Description");
			assertEquals(currentEvent.getCategory(), 2);
		}else{
			//Should never reach here
			fail();
		}

	}
	
	public void testEditRepeatedEvent() {
		String name;
		String location;
		Date startDate;
		Date endDate;
		int startTime;
		int endTime;
		String description;
		int category;
		boolean allDay;
		

		name = "EditEventName";
		location = "EditEventLocation";
		startDate = new Date(2015, 7, 11);
		endDate = new Date(2015, 7, 11);
		startTime = 4;
		endTime = 200;
		description = "Edit Event Description";
		category = 2;
		allDay = false; // temporary until implemented
		
		Log.v("DEBUGTEST", "TEST repeatId: " + repeatId);

		int newid = EditEventController.editEvent(name, location, startDate, endDate,
				startTime, endTime, description, category, allDay, repeatId,
				repeatKey.toString());
		
		assertFalse(0 == newid);

		Log.v("DEBUGTEST", "DEBUG id: " + id + "  newid:  " + newid + "  name:" + name);

		//Date startDate = new Date(2015, 6, 11);
		//Date endDate = new Date(2015, 6, 11);
		EventListManager elm = EventListManager.getInstance();
		Event currentEvent = elm.getRepeatedEventById(startDate.toString(), newid);
		if (currentEvent != null){
			assertEquals(currentEvent.getName(), "EditEventName");
			assertEquals(currentEvent.getLocation(), "EditEventLocation");
			assertEquals(currentEvent.getStartDate().toString(),
					startDate.toString());
			assertEquals(currentEvent.getEndDate().toString(), endDate.toString());
			assertEquals(currentEvent.getStartTime(), 4);
			assertEquals(currentEvent.getEndTime(), 200);
			assertEquals(currentEvent.getDescription(), "Edit Event Description");
			assertEquals(currentEvent.getCategory(), 2);
		}else{
			//Should never reach here
			fail();
		}

	}
	
	public void testEditEventInvalid(){
		String name;
		String location;
		Date startDate;
		Date endDate;
		int startTime;
		int endTime;
		String description;
		int category;
		boolean allDay;
		
		int result;

		name = "";
		location = "EditEventLocation";
		startDate = new Date(2015, 6, 11);
		endDate = new Date(2015, 6, 11);
		startTime = 4;
		endTime = 200;
		description = "Edit Event Description";
		category = 2;
		allDay = false; // temporary until implemented
		
		
		result = EditEventController.editEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, id, key.toString());
		
		assertTrue(result == 0);
		
		name = "EditEventName";
		location = "EditEventLocation";
		startDate = new Date(2015, 6, 11);
		endDate = new Date(2015, 5, 11);
		startTime = 4;
		endTime = 200;
		description = "Edit Event Description";
		category = 2;
		allDay = false; // temporary until implemented
		
		
		result = EditEventController.editEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, id, key.toString());
		
		assertTrue(result == 0);
		
		name = "EditEventName";
		location = "EditEventLocation";
		startDate = new Date(2015, 6, 11);
		endDate = new Date(2015, 6, 11);
		startTime = 300;
		endTime = 200;
		description = "Edit Event Description";
		category = 2;
		allDay = false; // temporary until implemented
		
		
		result = EditEventController.editEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, id, key.toString());
		
		assertTrue(result == 0);
	}
	
	public void testEditRepeatedEventInvalid(){
		String name;
		String location;
		Date startDate;
		Date endDate;
		int startTime;
		int endTime;
		String description;
		int category;
		boolean allDay;
		
		int result;

		name = "";
		location = "EditEventLocation";
		startDate = new Date(2015, 6, 11);
		endDate = new Date(2015, 6, 11);
		startTime = 4;
		endTime = 200;
		description = "Edit Event Description";
		category = 2;
		allDay = false; // temporary until implemented
		
		
		result = EditEventController.editEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, repeatId, repeatKey.toString());
		
		assertTrue(result == 0);
		
		name = "EditEventName";
		location = "EditEventLocation";
		startDate = new Date(2015, 6, 11);
		endDate = new Date(2015, 5, 11);
		startTime = 4;
		endTime = 200;
		description = "Edit Event Description";
		category = 2;
		allDay = false; // temporary until implemented
		
		
		result = EditEventController.editEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, repeatId, repeatKey.toString());
		
		assertTrue(result == 0);
		
		name = "EditEventName";
		location = "EditEventLocation";
		startDate = new Date(2015, 6, 11);
		endDate = new Date(2015, 6, 11);
		startTime = 300;
		endTime = 200;
		description = "Edit Event Description";
		category = 2;
		allDay = false; // temporary until implemented
		
		
		result = EditEventController.editEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, repeatId, repeatKey.toString());
		
		assertTrue(result == 0);
	}

	/*
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
	 */

}
