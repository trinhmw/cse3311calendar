package com.example.cse3311_calendar;

import java.util.Calendar;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity for viewing whole year
 */
public class YearViewActivity extends Activity {

	
	private int year;
	private Calendar calendar;
	private Button prevYear;
	private Button nextYear;
	private TextView currentYear;
	private Button weekViewChanger, monthViewChanger, dayViewChanger;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_year);
		
		Bundle extras = getIntent().getExtras();
        if (extras != null){
        	year = extras.getInt("year", -1);
        }else{
        	calendar = Calendar.getInstance(Locale.getDefault());
            year = calendar.get(Calendar.YEAR);
        }
        
        currentYear = (TextView) this.findViewById(R.id.current_year);
        currentYear.setText("" +year);
        
        prevYear = (Button) this.findViewById(R.id.prev_year);
        prevYear.setOnClickListener(new OnClickListener(){
        	public void onClick(View v)
        	{
        		year--;
        		currentYear.setText("" +year);
        	}
        });
        
        nextYear = (Button) this.findViewById(R.id.next_year);
        nextYear.setOnClickListener(new OnClickListener(){
        	public void onClick(View v)
        	{
        		year++;
        		currentYear.setText("" +year);
        	}
        });
        
        
        monthViewChanger = (Button) this.findViewById(R.id.month);
        monthViewChanger.setOnClickListener(new OnClickListener(){
        	public void onClick(View v)
        	{
        		Bundle dataBundle = new Bundle();
        		dataBundle.putInt("month", 0);
        		dataBundle.putInt("year", year);
        		Intent changeIntent = new Intent(YearViewActivity.this, MonthViewActivity.class);
        		changeIntent.putExtras(dataBundle);
        		startActivity(changeIntent);
        	}
        });
        
        dayViewChanger = (Button) this.findViewById(R.id.day);
        dayViewChanger.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent changeIntent = new Intent(YearViewActivity.this, DayViewActivity.class);
				startActivity(changeIntent);
			}
		});
        
        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.year, menu);
		return true;
	}
	
	/**
	 * Handles if the month button is pressed
	 *
	 * @param view the view
	 */
	public void monthClick(View view){
		
		String tag = (String) view.getTag();
		int month = Integer.parseInt(tag);
		
		Bundle dataBundle = new Bundle();
		dataBundle.putInt("month", month);
		dataBundle.putInt("year", year);
		Intent changeIntent = new Intent(YearViewActivity.this, MonthViewActivity.class);
		changeIntent.putExtras(dataBundle);
		startActivity(changeIntent);
		
	}

	public int getYear() {
		return year;
	}

}
