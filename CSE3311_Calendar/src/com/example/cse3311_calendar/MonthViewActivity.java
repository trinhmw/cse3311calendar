package com.example.cse3311_calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

// TODO: Auto-generated Javadoc

/**
 * Activity to show a whole month
 *
 */
public class MonthViewActivity extends Activity {

	private Button prevMonth;
	private Button nextMonth;
	private Button weekViewChanger, yearViewChanger, dayViewChanger;
	private GridView monthView;
	private TextView currentMonth;
	private MonthCellAdapter monthAdapter;
	private Calendar calendar;
	private int month, year;
	private int nextMonthInt, prevMonthInt;
	private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	
	private Button mAddEvent;
	private Button mAddGroupEvent;
	private Button mViewPending;
	
    /* Basic creation code
     * 
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Bundle extras = getIntent().getExtras();
        if (extras != null){
        	month = extras.getInt("month", -1);
        	year = extras.getInt("year", -1);
        }else{
        	calendar = Calendar.getInstance(Locale.getDefault());
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
        }
        if(month == -1){
        	calendar = Calendar.getInstance(Locale.getDefault());
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
        }
        
        
        currentMonth = (TextView) this.findViewById(R.id.current_month);
        currentMonth.setText(months[month] + " " + year);
        
        nextMonth = (Button) this.findViewById(R.id.next_month);
        setNextMonthInt(month);
        nextMonth.setText(months[nextMonthInt]);
        nextMonth.setOnClickListener(new OnClickListener(){
        	public void onClick(View v)
        	{
        		if(nextMonthInt == 0){
        			setActivityToDate(nextMonthInt, year + 1);	
        		} else {
        			setActivityToDate(nextMonthInt, year);
        		}
        	}
        });
        
        prevMonth = (Button) this.findViewById(R.id.prev_month);
        setPrevMonthInt(month);
        prevMonth.setText(months[prevMonthInt]);
        prevMonth.setOnClickListener(new OnClickListener(){
        	public void onClick(View v)
        	{
        		if(prevMonthInt == 11){
        			setActivityToDate(prevMonthInt, year - 1);
        		}else{
        			setActivityToDate(prevMonthInt, year);
        		}
        	}
        });
        
        monthView = (GridView) this.findViewById(R.id.month_view);
        
        monthAdapter = new MonthCellAdapter(getApplicationContext(), month, year);
        monthAdapter.notifyDataSetChanged();
        monthView.setAdapter(monthAdapter);
        
        yearViewChanger = (Button) this.findViewById(R.id.year);
        yearViewChanger.setOnClickListener(new OnClickListener(){
        	public void onClick(View v)
        	{
        		Bundle dataBundle = new Bundle();
        		dataBundle.putInt("year", year);
        		Intent changeIntent = new Intent(MonthViewActivity.this, YearViewActivity.class);
        		changeIntent.putExtras(dataBundle);
        		startActivity(changeIntent);
        	}
        });
        
        dayViewChanger = (Button) this.findViewById(R.id.day);
        dayViewChanger.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent changeIntent = new Intent(MonthViewActivity.this, DayViewActivity.class);
				startActivity(changeIntent);
			}
		});
        
        
        
        mAddEvent = (Button) this.findViewById(R.id.add_event);
        mAddEvent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				Toast.makeText(MonthViewActivity.this, R.string.add_event, Toast.LENGTH_LONG).show();
				Intent changeIntent = new Intent(MonthViewActivity.this, EventFormActivity.class);
				startActivity(changeIntent);
				
			}
		});
        
        mAddGroupEvent = (Button) this.findViewById(R.id.group_event);
        mAddGroupEvent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				Toast.makeText(MonthViewActivity.this, R.string.add_event, Toast.LENGTH_LONG).show();
				Intent changeIntent = new Intent(MonthViewActivity.this, GroupEventFormActivity.class);
				startActivity(changeIntent);
				
			}
		});
        
        mViewPending = (Button) this.findViewById(R.id.pending);
        mViewPending.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				Toast.makeText(MonthViewActivity.this, R.string.add_event, Toast.LENGTH_LONG).show();
				Intent changeIntent = new Intent(MonthViewActivity.this, PendingGroupEventActivity.class);
				startActivity(changeIntent);
				
			}
		});
        
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * Resets entire view including calendar for new month
     *
     * @param newMonth 0-11 value of new month to set
     * @param newYear 4 digit new year to set
     */
    public void setActivityToDate(int newMonth, int newYear){
    	month = newMonth;
    	year = newYear;
    	monthAdapter = new MonthCellAdapter(getApplicationContext(), month, year);
    	currentMonth.setText(months[month] + " " + year);
    	
    	setPrevMonthInt(month);
        prevMonth.setText(months[prevMonthInt]);
        
        setNextMonthInt(month);
        nextMonth.setText(months[nextMonthInt]);
        
        monthAdapter.notifyDataSetChanged();
        monthView.setAdapter(monthAdapter);
    }
    
    /**
     * Set value of nextMonthInt based on current month
     *
     * @param currentMonth 0-11 value of current month
     */
    public void setNextMonthInt(int currentMonth){
    	if(currentMonth == 11){
        	nextMonthInt = 0;
        }else{
        	nextMonthInt = currentMonth + 1;
        }
    }
    
    
    /**
     * Set value of prevMonthInt based on current month
     *
     * @param currentMonth 0-11 value of current month
     */
    public void setPrevMonthInt(int currentMonth){
    	if (currentMonth == 0){
        	prevMonthInt = 11;
        }else{
        	prevMonthInt = currentMonth - 1;
         }
    }
    
    
    private class MonthCellAdapter extends BaseAdapter{
    	
    	private List <String> toAddList;
    	private int currentMonth, currentYear;
    	private int daysInMonth;
    	private final Context context;


		public MonthCellAdapter(Context context, int month, int year) {
			
			this.toAddList = new ArrayList<String>();
			currentMonth = month;
			currentYear = year;
			this.context = context;
			
			createMonthArrayList();
		}

		private void createMonthArrayList() {
			
			int spaceAtBeginning;
			int count = 0;
			String currentMonthName = months[currentMonth];
			GregorianCalendar calendar = new GregorianCalendar(currentYear, currentMonth, 1);
			
			spaceAtBeginning = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			
			daysInMonth = daysOfMonth[currentMonth];
			if(calendar.isLeapYear(calendar.get(Calendar.YEAR)) && currentMonth == 1){
				daysInMonth++;
			}
			
			for (int i = 0; i < spaceAtBeginning; i++){
				toAddList.add("Null");
				count++;
			}
			for (int i = 1; i <= daysInMonth; i++){
				toAddList.add("" + currentMonth + "-" + i + "-" + currentYear);
				count++;
			}
			for (int i = count; i < (7 * 6); i++){
				toAddList.add("Null");
			}
			
			
		}

		@Override
		public int getCount() {
			return toAddList.size();
		}

		@Override
		public Object getItem(int position) {
			
			return toAddList.get(position);
		}

		@Override
		public long getItemId(int position) {
			
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			int days[] = {R.drawable.day1, R.drawable.day2,R.drawable.day3, R.drawable.day4, R.drawable.day5,
					R.drawable.day6, R.drawable.day7, R.drawable.day8, R.drawable.day9, R.drawable.day10, 
					R.drawable.day11, R.drawable.day12, R.drawable.day13, R.drawable.day14, R.drawable.day15,
					R.drawable.day16, R.drawable.day17, R.drawable.day18, R.drawable.day19, R.drawable.day20,
					R.drawable.day21, R.drawable.day22, R.drawable.day23, R.drawable.day24, R.drawable.day25,
					R.drawable.day26, R.drawable.day27, R.drawable.day28, R.drawable.day29, R.drawable.day30,
					R.drawable.day31};
			
			int colors[] = {R.drawable.red_event, R.drawable.blue_event, R.drawable.green_event};
			
			View row = convertView;
			if (row == null){
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.cal_day, parent, false);
			}
			
			ImageView dayImage = (ImageView) row.findViewById(R.id.day_image);
			ImageView event1 = (ImageView) row.findViewById(R.id.event1);
			ImageView event2 = (ImageView) row.findViewById(R.id.event2);
			ImageView event3 = (ImageView) row.findViewById(R.id.event3);
			ImageView event4 = (ImageView) row.findViewById(R.id.event4);
			
			String[] date = toAddList.get(position).split("-");
			
			if(date[0] != "Null"){
				String thisMonth = date[0];
				String thisDay = date[1];
				int thisDayInt = Integer.parseInt(thisDay);
				String thisYear = date[2];
				
				dayImage.setImageResource(days[thisDayInt-1]);
				dayImage.setAlpha(160);
				dayImage.setTag(thisMonth + "-" + thisDay + "-" + thisYear);
				dayImage.setOnClickListener(new OnClickListener(){
		        	public void onClick(View v)
		        	{
		        		String monthDayYear = (String) v.getTag();
		        		String[] date = monthDayYear.split("-");
		        		String thisMonth = date[0];
		        		int thisMonthInt = Integer.parseInt(thisMonth);
						String thisDay = date[1];
						int thisDayInt = Integer.parseInt(thisDay);
						String thisYear = date[2];
						int thisYearInt = Integer.parseInt(thisYear);
						
						//Here handle going to day view
						
						//Toast.makeText(getApplicationContext(), "Month: " + (thisMonthInt+1) + " Day: " +
						//thisDayInt + " Year: " + thisYearInt,Toast.LENGTH_LONG).show();	
						Intent dayIntent = new Intent(MonthViewActivity.this, DayViewActivity.class);
						Bundle bundle = new Bundle();
						bundle.putInt("day", thisDayInt);
						bundle.putInt("month", thisMonthInt);
						bundle.putInt("year", thisYearInt);
						dayIntent.putExtras(bundle);
						startActivity(dayIntent);
		        	}
		        });
				
				
        		int thisMonthInt = Integer.parseInt(thisMonth);
				int thisYearInt = Integer.parseInt(thisYear);
				EventListManager manager = EventListManager.getInstance();
				Date thisDate = new Date (thisYearInt, thisMonthInt, thisDayInt);
				ArrayList <Event> eventList = manager.getEvents(thisDate.toString());
				
				if(eventList != null){
					int length = eventList.size();
					if(length == 0){
						
					}
					else if(length == 1){
						event4.setImageResource(colors[eventList.get(0).getCategory()]);
					}
					else if (length == 2){
						event3.setImageResource(colors[eventList.get(0).getCategory()]);
						event4.setImageResource(colors[eventList.get(1).getCategory()]);
					}
					else if (length == 3){
						event2.setImageResource(colors[eventList.get(0).getCategory()]);
						event3.setImageResource(colors[eventList.get(1).getCategory()]);
						event4.setImageResource(colors[eventList.get(2).getCategory()]);
					}
					else{
						event1.setImageResource(colors[eventList.get(0).getCategory()]);
						event2.setImageResource(colors[eventList.get(1).getCategory()]);
						event3.setImageResource(colors[eventList.get(2).getCategory()]);
						event4.setImageResource(colors[eventList.get(3).getCategory()]);
					}
				}
				
			}
			return row;
		}
    
    }


    /**
     * For testing purposes
     * @return
     */
	public int getNextMonthInt() {
		return nextMonthInt;
	}


	/**
	 * For testing purposes
	 * @return
	 */
	public int getPrevMonthInt() {
		return prevMonthInt;
	}
    
    
}
