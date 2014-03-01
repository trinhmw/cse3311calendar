package com.example.cse3311_calendar;

import com.example.cse3311_calendar.R.id;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class EventFormActivity extends Activity {

	Button mConfirm;
	Spinner mCategorySpinner;
	Spinner mAllDaySpinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_form);
		
		Spinner mCategorySpinner = (Spinner) findViewById(R.id.fCategory);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mCategorySpinner.setAdapter(adapter);
		
		Spinner mAllDaySpinner = (Spinner) findViewById(R.id.fAll_day);
		adapter = ArrayAdapter.createFromResource(this, R.array.all_day_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mAllDaySpinner.setAdapter(adapter);
		
		mConfirm = (Button) this.findViewById(R.id.confirm);
		mConfirm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(EventFormActivity.this, R.string.add_event, Toast.LENGTH_LONG).show();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_form, menu);
		return true;
	}

}
