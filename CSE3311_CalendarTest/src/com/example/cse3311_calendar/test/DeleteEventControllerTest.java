package com.example.cse3311_calendar.test;

import java.io.File;
import java.util.Date;

import junit.framework.TestCase;
import android.os.Environment;
import android.util.Log;

import com.example.cse3311_calendar.DeleteEventController;
import com.example.cse3311_calendar.Event;
import com.example.cse3311_calendar.EventListManager;

public class DeleteEventControllerTest extends TestCase {
	
	private EventListManager elm;
	private Date testDate;
	private int id;
	
	public void setUp(){
		
		new File(Environment.getExternalStorageDirectory(), "/data.dat").delete();
		new File(Environment.getExternalStorageDirectory(), "/data2.dat").delete();
		elm = EventListManager.getInstance();
		
		Event newEvent = new Event();
		newEvent.setName("DeleteEventControllerTest");
		testDate = new Date (2014, 2, 2);
		newEvent.setStartDate(testDate);
		newEvent.setStartTime(200);
		newEvent.setEndDate(testDate);
		newEvent.setEndTime(400);
		elm.addEvent(newEvent);
		id = newEvent.getId();
		Log.v("Failed to fill in.", "Id!" + newEvent.getId());
	}
	
	public void tearDown(){
		new File(Environment.getExternalStorageDirectory(), "/data.dat").delete();
		new File(Environment.getExternalStorageDirectory(), "/data2.dat").delete();
	}
	
	public void testDeleteEvent(){
		Log.v("Failed to fill in.", "Id!" + id);
		assertTrue(elm.deleteEvent(testDate.toString(), id));
	}
	
	public void testDeleteEventFail(){
		Log.v("Failed to fill in.", "Id!" + id);
		assertFalse(elm.deleteEvent(testDate.toString(), (id + 1)));
	}

}
