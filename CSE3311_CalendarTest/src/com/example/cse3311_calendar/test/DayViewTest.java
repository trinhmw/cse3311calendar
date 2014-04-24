package com.example.cse3311_calendar.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.TextView;

import com.example.cse3311_calendar.DayViewActivity;
import com.example.cse3311_calendar.EventFormActivity;
import com.example.cse3311_calendar.MonthViewActivity;
import com.example.cse3311_calendar.R;

public class DayViewTest extends ActivityInstrumentationTestCase2<DayViewActivity> {
	private Button mAddEvent;
	private Button mMonth;
	private TextView time;
	private DayViewActivity activity;

	public DayViewTest() {
		super(DayViewActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(true);
		
		activity = getActivity();
		mAddEvent = (Button) activity.findViewById(R.id.add_event);
		mMonth = (Button) activity.findViewById(R.id.month);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SmallTest
	public void testClickMonthButton(){
		ActivityMonitor am = getInstrumentation().addMonitor(MonthViewActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, mMonth);
		
		MonthViewActivity mva = (MonthViewActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(mva);
		mva.finish();
	}
	
	@SmallTest
	public void testClickAddEventButton(){
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, mAddEvent);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		efa.finish();
	}
	
	@SmallTest
	public void testClick00Button(){
		time = (TextView) activity.findViewById(R.id.time00);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(0, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick01Button(){
		time = (TextView) activity.findViewById(R.id.time01);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(1, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick02Button(){
		time = (TextView) activity.findViewById(R.id.time02);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(2, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick03Button(){
		time = (TextView) activity.findViewById(R.id.time03);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(3, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick04Button(){
		time = (TextView) activity.findViewById(R.id.time04);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(4, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick05Button(){
		time = (TextView) activity.findViewById(R.id.time05);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(5, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick06Button(){
		time = (TextView) activity.findViewById(R.id.time06);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(6, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick07Button(){
		time = (TextView) activity.findViewById(R.id.time07);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(7, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick08Button(){
		time = (TextView) activity.findViewById(R.id.time08);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(8, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick09Button(){
		time = (TextView) activity.findViewById(R.id.time09);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(9, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick10Button() throws InterruptedException{
		time = (TextView) activity.findViewById(R.id.time010);
		Thread.sleep(3000);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,2000);
		assertNotNull(efa);
		
		assertEquals(10, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick11Button() throws InterruptedException{
		time = (TextView) activity.findViewById(R.id.time011);
		Thread.sleep(3000);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.dragQuarterScreenUp(this, activity);
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(11, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick12Button(){
		time = (TextView) activity.findViewById(R.id.time012);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(12, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick13Button(){
		time = (TextView) activity.findViewById(R.id.time013);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(13, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick14Button(){
		time = (TextView) activity.findViewById(R.id.time014);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(14, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick15Button(){
		time = (TextView) activity.findViewById(R.id.time015);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(15, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick16Button(){
		time = (TextView) activity.findViewById(R.id.time016);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(16, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick17Button(){
		time = (TextView) activity.findViewById(R.id.time017);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(17, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick18Button(){
		time = (TextView) activity.findViewById(R.id.time018);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(18, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick19Button(){
		time = (TextView) activity.findViewById(R.id.time019);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(19, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick20Button(){
		time = (TextView) activity.findViewById(R.id.time020);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(20, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick21Button(){
		time = (TextView) activity.findViewById(R.id.time021);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(21, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick22Button(){
		time = (TextView) activity.findViewById(R.id.time022);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(22, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick23Button(){
		time = (TextView) activity.findViewById(R.id.time023);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(23, efa.getStartHour());
		efa.finish();
	}
	
	@SmallTest
	public void testClick24Button(){
		time = (TextView) activity.findViewById(R.id.time024);
		ActivityMonitor am = getInstrumentation().addMonitor(EventFormActivity.class.getName(),null,false);
		
		TouchUtils.clickView(this, time);
		
		EventFormActivity efa = (EventFormActivity) getInstrumentation().waitForMonitorWithTimeout(am,5);
		assertNotNull(efa);
		
		assertEquals(24, efa.getStartHour());
		efa.finish();
	}
}
