package com.example.cse3311_calendar.test;

import java.util.Date;

import junit.framework.TestCase;

import com.example.cse3311_calendar.Event;

public class EventTest extends TestCase {
	
	public void testEndDateTimeValid(){
		Event tester = new Event();
		Date startDate = new Date(2014, 2, 3);
		tester.setStartDateTime(startDate, 100);
		assertTrue(tester.setendDateTime(startDate, 200));
	}

}
