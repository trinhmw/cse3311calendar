package com.example.cse3311_calendar.test;


import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cse3311_calendar.DayViewActivity;
import com.example.cse3311_calendar.MonthViewActivity;
import com.example.cse3311_calendar.R;
import com.example.cse3311_calendar.YearViewActivity;

public class YearViewTest extends
		ActivityInstrumentationTestCase2<YearViewActivity> {
	
	private Button prevYear;
	private Button nextYear;
	private Button monthViewChanger, dayViewChanger;
	private ImageView month;
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
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage0(){
		month = (ImageView) activity.findViewById(R.id.january);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(1, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage1(){
		month = (ImageView) activity.findViewById(R.id.february);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(2, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage2(){
		month = (ImageView) activity.findViewById(R.id.march);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(3, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage3(){
		month = (ImageView) activity.findViewById(R.id.april);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(4, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage4(){
		month = (ImageView) activity.findViewById(R.id.may);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(5, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage5(){
		month = (ImageView) activity.findViewById(R.id.june);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(6, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage6(){
		month = (ImageView) activity.findViewById(R.id.july);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(7, mva.getNextMonthInt()); //9
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage7(){
		month = (ImageView) activity.findViewById(R.id.august);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(8, mva.getNextMonthInt()); //10?
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage8(){
		month = (ImageView) activity.findViewById(R.id.september);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(9, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage9(){
		month = (ImageView) activity.findViewById(R.id.october);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(10, mva.getNextMonthInt());
		mva.finish();
	}
	
	/*
	@SmallTest
	public void testClickMonthImage10(){
		month = (ImageView) activity.findViewById(R.id.november);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(11, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage11(){
		month = (ImageView) activity.findViewById(R.id.december);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(0, mva.getNextMonthInt());
		mva.finish();
	}
	*/
	



}
