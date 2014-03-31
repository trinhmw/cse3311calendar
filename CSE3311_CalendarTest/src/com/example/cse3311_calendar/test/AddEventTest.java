package com.example.cse3311_calendar.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;

import com.example.cse3311_calendar.AddEventController;
import com.example.cse3311_calendar.Event;
import com.example.cse3311_calendar.EventFormActivity;
import com.example.cse3311_calendar.EventListManager;

public class AddEventTest extends
ActivityInstrumentationTestCase2<EventFormActivity> {

	private EventFormActivity mActivity;
	public AddEventTest(Class<EventFormActivity> activityClass) {
		super("com.example.cse3311_calendar", EventFormActivity.class);

	}

	public AddEventTest()
	{
		super(EventFormActivity.class);
	}

	protected void setUp() throws Exception{
		super.setUp();
		//mActivity = this.getActivity();
		EventListManager.killInstnace();
		new File(Environment.getExternalStorageDirectory(), "/data.dat").delete();
		new File(Environment.getExternalStorageDirectory(), "/data2.dat").delete();

	}

	/*
	public void testPreconditions(){
	}
	 */

	public void tearDown(){
		new File(Environment.getExternalStorageDirectory(), "/data.dat").delete();
		new File(Environment.getExternalStorageDirectory(), "/data2.dat").delete();
	}

	/*
	public void testAddEvent(){


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

		name = "EventName";
		location = "EventLocation";
		startDate = new Date(2014, 5, 20);
		endDate = new Date(2014, 5, 20);
		startTime = 1;
		endTime = 100;
		description = "Event Description";
		category = 1;
		allDay = false;   //temporary until implemented
		isRepeat = false; //
		repeatedDays = 0; //^
		lastDay = null; //^

		boolean result = AddEventController.addEvent(name, location, startDate , endDate, startTime, endTime, description, category, allDay, isRepeat, repeatedDays, lastDay);		

		assertTrue(result);
	}
	 */



	public void testEventData(){
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

		name = "EventName";
		location = "EventLocation";
		startDate = new Date(2014, 5, 20);
		endDate = new Date(2014, 5, 20);
		startTime = 1;
		endTime = 100;
		description = "Event Description";
		category = 1;
		allDay = false;   //temporary until implemented
		isRepeat = false; //
		repeatedDays = 0; //^
		lastDay = null; //^

		boolean result = AddEventController.addEvent(name, location, startDate , endDate, startTime, endTime, description, category, allDay, isRepeat, repeatedDays, lastDay);		

		assertTrue(result);
		//Date startDate = new Date(2014, 5, 20);
		//Date endDate = new Date(2014, 5, 20);
		EventListManager elm = EventListManager.getInstance();

		ArrayList<Event> events = elm.getEvents(startDate.toString());
		Event newEvent = events.get(0);
		assertEquals(newEvent.getName(), "EventName");
		assertEquals(newEvent.getLocation(), "EventLocation");		
		assertEquals(newEvent.getStartDate().toString(), startDate.toString());
		assertEquals(newEvent.getEndDate().toString(), endDate.toString());		
		assertEquals(newEvent.getStartTime(), 1);
		assertEquals(newEvent.getEndTime(), 100);
		assertEquals(newEvent.getDescription(), "Event Description");
		assertEquals(newEvent.getCategory(), 1);
		//add all day and repeats later on
	}
	public void testRepeat(){

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

		name = "EventName";
		location = "EventLocation";
		startDate = new Date(2014, 5, 20);
		endDate = new Date(2014, 5, 20);
		startTime = 1;
		endTime = 100;
		description = "Event Description";
		category = 1;
		allDay = false; 
		isRepeat = true;
		repeatedDays = 7;
		lastDay = new Date(2014, 6, 20);

		boolean result = AddEventController.addEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, isRepeat, repeatedDays, lastDay);

		assertTrue(result);
	}

	public void testEventFail(){

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
		
		//assertTrue(true);

		
		name = "EventName";
		location = "EventLocation";
		startDate = new Date(2014, 5, 20);
		endDate = new Date(2014, 5, 15);
		startTime = 1;
		endTime = 100;
		description = "Event Description";
		category = 1;
		allDay = false;   //temporary until implemented
		isRepeat = false; //
		repeatedDays = 0; //^
		lastDay = null; //^

		boolean result = AddEventController.addEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, isRepeat, repeatedDays, lastDay);

		assertFalse(result);

		
		
		name = "EventName";
		location = "EventLocation";
		startDate = new Date(2014, 5, 20);
		endDate = new Date(2014, 5, 20);
		startTime = 200;
		endTime = 100;
		description = "Event Description";
		category = 1;
		allDay = false;   //temporary until implemented
		isRepeat = false; //
		repeatedDays = 0; //^
		lastDay = null; //^

		result = AddEventController.addEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, isRepeat, repeatedDays, lastDay);

		assertFalse(result);

		name = "";
		location = "EventLocation";
		startDate = new Date(2014, 5, 20);
		endDate = new Date(2014, 5, 20);
		startTime = 5;
		endTime = 100;
		description = "Event Description";
		category = 1;
		allDay = false;   //temporary until implemented
		isRepeat = false; //
		repeatedDays = 0; //^
		lastDay = null; //^

		result = AddEventController.addEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, isRepeat, repeatedDays, lastDay);

		assertFalse(result);



	}

	public void testRepeatedEventFail(){

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

		name = "EventName";
		location = "EventLocation";
		startDate = new Date(2014, 5, 20);
		endDate = new Date(2014, 5, 15);
		startTime = 1;
		endTime = 100;
		description = "Event Description";
		category = 1;
		allDay = false;   
		isRepeat = true; 
		repeatedDays = 7; 
		lastDay = new Date(2014, 6, 15); 

		boolean result = AddEventController.addEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, isRepeat, repeatedDays, lastDay);

		assertFalse(result);

		name = "EventName";
		location = "EventLocation";
		startDate = new Date(2014, 5, 20);
		endDate = new Date(2014, 5, 20);
		startTime = 200;
		endTime = 100;
		description = "Event Description";
		category = 1;
		allDay = false;   //temporary until implemented
		isRepeat = true; 
		repeatedDays = 7; 
		lastDay = new Date(2014, 6, 15); 

		result = AddEventController.addEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, isRepeat, repeatedDays, lastDay);

		assertFalse(result);

		name = "";
		location = "EventLocation";
		startDate = new Date(2014, 5, 20);
		endDate = new Date(2014, 5, 20);
		startTime = 5;
		endTime = 100;
		description = "Event Description";
		category = 1;
		allDay = false;   //temporary until implemented
		isRepeat = true; 
		repeatedDays = 7; 
		lastDay = new Date(2014, 6, 15); 

		result = AddEventController.addEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, isRepeat, repeatedDays, lastDay);

		assertFalse(result);

		name = "Event Name";
		location = "EventLocation";
		startDate = new Date(2014, 5, 20);
		endDate = new Date(2014, 5, 20);
		startTime = 200;
		endTime = 100;
		description = "Event Description";
		category = 1;
		allDay = false;   //temporary until implemented
		isRepeat = true; 
		repeatedDays = -1; 
		lastDay = new Date(2014, 6, 15); 

		result = AddEventController.addEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, isRepeat, repeatedDays, lastDay);

		assertFalse(result);

		name = "Event Name";
		location = "EventLocation";
		startDate = new Date(2014, 5, 20);
		endDate = new Date(2014, 5, 20);
		startTime = 200;
		endTime = 100;
		description = "Event Description";
		category = 1;
		allDay = false;   //temporary until implemented
		isRepeat = true; 
		repeatedDays = 7; 
		lastDay = new Date(2013, 6, 15); 

		result = AddEventController.addEvent(name, location, startDate, endDate, startTime, endTime, 
				description, category, allDay, isRepeat, repeatedDays, lastDay);

		assertFalse(result);


	}


}

