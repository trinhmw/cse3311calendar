package com.example.cse3311_calendar.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.example.cse3311_calendar.*;
public class suiteTest extends TestSuite {
	
    public static Test suite() {
        final TestSuite s = new TestSuite(AddEventTest.class, DeleteEventControllerTest.class, EditEventTest.class, EventTest.class);
        
        return s;
    }
}
