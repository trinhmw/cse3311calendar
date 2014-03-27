package com.example.cse3311_calendar.test;

import java.util.ArrayList;
import java.util.Date;

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
		mActivity = this.getActivity();
		
	}
	
	public void testPreconditions(){
	}
	
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
		
		name = "EventName";
		location = "EventLocation";
		startDate = new Date(2014, 5, 20);
		endDate = new Date(2014, 5, 20);
		startTime = 1;
		endTime = 100;
		description = "Event Description";
		category = 1;
		allDay = false;
		
		boolean result = AddEventController.addEvent(name, location, startDate , endDate, startTime, endTime, description, category, allDay);		
		
		assertTrue(result);
	}
	
	
	public void testEventData(){
		Date startDate = new Date(2014, 5, 20);
		Date endDate = new Date(2014, 5, 20);
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
		
	}
	

}
