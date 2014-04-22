package com.example.cse3311_calendar.test;


import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.ViewGroup;
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
	public void testClick1NextYearButton(){
		
		year = activity.getYear();
		
		TouchUtils.clickView(this, nextYear);
		
		assertEquals(activity.getYear(), year+1);		
	}
	
	@SmallTest
	public void testClick2PrevYearButton(){
		
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
	public void testClickMonthImage0() throws InterruptedException{
		month = (ImageView) activity.findViewById(R.id.january);
		
		Thread.sleep(3000);
		
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(1, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage1() throws InterruptedException{
		month = (ImageView) activity.findViewById(R.id.february);
		
		Thread.sleep(3000);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(2, mva.getNextMonthInt());//2
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage2() throws InterruptedException{
		month = (ImageView) activity.findViewById(R.id.march);
		Thread.sleep(3000);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(3, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage3() throws InterruptedException{
		month = (ImageView) activity.findViewById(R.id.april);
		Thread.sleep(3000);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(4, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage4() throws InterruptedException{
		month = (ImageView) activity.findViewById(R.id.may);
		Thread.sleep(3000);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(5, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage5() throws InterruptedException{
		month = (ImageView) activity.findViewById(R.id.june);
		Thread.sleep(3000);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(6, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage6() throws InterruptedException{
		month = (ImageView) activity.findViewById(R.id.july);
		Thread.sleep(3000);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
	
		
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		

		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(7, mva.getNextMonthInt()); //9
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage7() throws InterruptedException{
		month = (ImageView) activity.findViewById(R.id.august);
		Thread.sleep(3000);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		

		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(8, mva.getNextMonthInt()); //10?
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage8() throws InterruptedException{
		month = (ImageView) activity.findViewById(R.id.september);
		Thread.sleep(3000);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(9, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage9() throws InterruptedException{
		month = (ImageView) activity.findViewById(R.id.october);
		Thread.sleep(3000);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(10, mva.getNextMonthInt());
		mva.finish();
	}
	
	
	@SmallTest
	public void testClickMonthImage10() throws InterruptedException{
		month = (ImageView) activity.findViewById(R.id.november);
		Thread.sleep(3000);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(11, mva.getNextMonthInt());
		mva.finish();
	}
	
	@SmallTest
	public void testClickMonthImage11() throws InterruptedException{
		month = (ImageView) activity.findViewById(R.id.december);
		Thread.sleep(3000);
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.clickView(this, month);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		assertEquals(0, mva.getNextMonthInt());
		mva.finish();
	}
	
	



}
