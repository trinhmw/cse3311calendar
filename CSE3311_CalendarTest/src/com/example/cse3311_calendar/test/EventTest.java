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
	
	public void testEndDateTimeInvalid(){
		Event tester = new Event();
		Date startDate = new Date(2014, 2, 3);
		tester.setStartDateTime(startDate, 100);
		Date endDate = new Date(2014, 1, 3);
		assertFalse(tester.setendDateTime(endDate, 200));
		
		assertFalse(tester.setendDateTime(startDate, 50));
	}
	
	public void testNameValid(){
		Event tester = new Event();
		assertTrue(tester.setName("test"));
	}
	
	public void testNameInvalid(){
		Event tester = new Event();
		assertFalse(tester.setName(""));
	}
	
	public void testToCompare(){
		Event tester = new Event();
		Event toCompare = new Event();
		
		tester.setStartTime(50);
		toCompare.setStartTime(100);
		
		assertTrue(tester.compareTo(toCompare) < 0);
		
		tester.setStartTime(100);
		toCompare.setStartTime(50);
		
		assertTrue(tester.compareTo(toCompare) > 0);
		
		tester.setStartTime(100);
		toCompare.setStartTime(100);
		
		assertTrue(tester.compareTo(toCompare) == 0);
	}

}
