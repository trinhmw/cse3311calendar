package com.example.cse3311_calendar.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cse3311_calendar.DayViewActivity;
import com.example.cse3311_calendar.EventFormActivity;
import com.example.cse3311_calendar.MonthViewActivity;
import com.example.cse3311_calendar.R;
import com.example.cse3311_calendar.YearViewActivity;

public class MonthViewTest extends ActivityInstrumentationTestCase2<MonthViewActivity> {

	private MonthViewActivity activity;
	private Button prevMonth;
	private Button nextMonth;
	private Button yearViewChanger, dayViewChanger;
	private Button mAddEvent;
	private int prevMonthInt;
	private int nextMonthInt;
	private int currMonthInt;
	private ImageView dayImage;
	
	public MonthViewTest() {
		super(MonthViewActivity.class);
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		setActivityInitialTouchMode(true);
		activity = getActivity();
		
		nextMonth = (Button) activity.findViewById(R.id.next_month);
		prevMonth = (Button) activity.findViewById(R.id.prev_month);
		yearViewChanger = (Button) activity.findViewById(R.id.year);
		dayViewChanger = (Button) activity.findViewById(R.id.day);
		mAddEvent = (Button) activity.findViewById(R.id.add_event);
		dayImage = (ImageView) activity.findViewById(R.id.imageView1);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@SmallTest
	public void testClickNextMonthButton(){
		//fail("Not implemented yet");
		nextMonthInt = activity.getNextMonthInt();
		prevMonthInt = activity.getPrevMonthInt();
		
		if(prevMonthInt==12)
		{
			assertEquals(1, nextMonthInt-1);
			currMonthInt = 1;
		}
		else if(nextMonthInt==1)
		{
			assertEquals(12, prevMonthInt+1);
			currMonthInt = 12;
		}
		else
		{
			assertEquals(nextMonthInt-1, prevMonthInt+1);
			currMonthInt = nextMonthInt-1;
		}
		
		//If you change nextMonth below to prevMonth, test should fail.
		TouchUtils.clickView(this, nextMonth);
		
		assertEquals(activity.getNextMonthInt(), nextMonthInt+1);
		
	}
	
	@SmallTest
	public void testClickPrevMonthButton(){
		nextMonthInt = activity.getNextMonthInt();
		prevMonthInt = activity.getPrevMonthInt();
		
		if(prevMonthInt==11)
		{
			assertEquals(0, nextMonthInt-1);
			currMonthInt = 0;
		}
		else if(nextMonthInt==0)
		{
			assertEquals(11, prevMonthInt+1);
			currMonthInt = 11;
		}
		else
		{
			assertEquals(nextMonthInt-1, prevMonthInt+1);
			currMonthInt = nextMonthInt-1;
		}
		
		//If you change prevMonth below to nextMonth, test should fail.
		TouchUtils.clickView(this, prevMonth);
		
		assertEquals(activity.getPrevMonthInt(), prevMonthInt-1);
		
	}
	
	@SmallTest
	public void testClickYearViewChangerbutton(){
		ActivityMonitor am = getInstrumentation().addMonitor(YearViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, yearViewChanger);
		
		YearViewActivity yva = (YearViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(yva);
		yva.finish();
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
	public void testClickAddEventbutton(){
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, mAddEvent);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		efa.finish();
	}
	
	@SmallTest
	public void testClickSingleDaybutton(){
		ActivityMonitor am = getInstrumentation().addMonitor(DayViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, dayImage);
		
		DayViewActivity dva = (DayViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(dva);
		dva.finish();
	}
	

}
