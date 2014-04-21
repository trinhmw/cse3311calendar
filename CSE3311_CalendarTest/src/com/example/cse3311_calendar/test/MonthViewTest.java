package com.example.cse3311_calendar.test;

import com.example.cse3311_calendar.MonthViewActivity;
import com.example.cse3311_calendar.R;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.TextView;

public class MonthViewTest extends ActivityInstrumentationTestCase2<MonthViewActivity> {

	private MonthViewActivity activity;
	private Button prevMonth;
	private Button nextMonth;
	private Button yearViewChanger, dayViewChanger;
	private TextView currentMonth;
	private int prevMonthInt;
	private int nextMonthInt;
	private int currMonthInt;
	
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
		currentMonth = (TextView) activity.findViewById(R.id.current_month);
		
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
		
		//If you change prevMonth below to nextMonth, test should fail.
		TouchUtils.clickView(this, prevMonth);
		
		assertEquals(activity.getPrevMonthInt(), prevMonthInt-1);
		
	}
	
	public void testClickYearViewChangerbutton(){
		
	}

}
