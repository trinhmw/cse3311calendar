package com.example.cse3311_calendar.test;


import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;

import com.example.cse3311_calendar.DayViewActivity;
import com.example.cse3311_calendar.MonthViewActivity;
import com.example.cse3311_calendar.R;
import com.example.cse3311_calendar.YearViewActivity;

public class YearViewTest extends
		ActivityInstrumentationTestCase2<YearViewActivity> {
	
	private Button prevYear;
	private Button nextYear;
	private Button monthViewChanger, dayViewChanger;
	private YearViewActivity activity;
	private int year;

	public YearViewTest() {
		super(YearViewActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		setActivityInitialTouchMode(true);
		activity = getActivity();
		
		prevYear = (Button) activity.findViewById(R.id.prev_year);
		nextYear = (Button) activity.findViewById(R.id.next_year);
		monthViewChanger = (Button) activity.findViewById(R.id.month);
		dayViewChanger = (Button) activity.findViewById(R.id.day);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@SmallTest
	public void testClickNextYearButton(){
		
		year = activity.getYear();
		
		TouchUtils.clickView(this, nextYear);
		
		assertEquals(activity.getYear(), year+1);		
	}
	
	@SmallTest
	public void testClickPrevYearButton(){
		
		year = activity.getYear();
		
		TouchUtils.clickView(this, prevYear);
		
		assertEquals(activity.getYear(), year-1);		
	}
	
	@SmallTest
	public void testClickDayViewChangerbutton(){
		ActivityMonitor am = getInstrumentation().addMonitor(DayViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, dayViewChanger);
		
		DayViewActivity dva = (DayViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(dva);
		dva.finish();
	}
	
	@SmallTest
	public void testClickMonthViewChangerbutton(){
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, monthViewChanger);
		
		MonthViewActivity yva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(yva);
		yva.finish();
	}

}
