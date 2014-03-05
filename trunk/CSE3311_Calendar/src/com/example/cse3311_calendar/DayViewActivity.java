package com.example.cse3311_calendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.view.ViewGroup;


public class DayViewActivity extends Activity {

		String myDate;
			 
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_day_view);
			// Get the message from the intent
	        Intent intent = getIntent();
	        
	        Bundle inputBundle = intent.getExtras();
	        int day = inputBundle.getInt("day");
	        int month = inputBundle.getInt("month");
	        int year = inputBundle.getInt("year");
	        String myDate = "" + (month + 1) + "/" + day + "/" + year;
	        //set extra message to to myDate
	        //@+id/dayTextView
	        
	        TextView dayText = (TextView)findViewById(R.id.dayTextView);
			dayText.setText(myDate);
			
			
			
			
			
	 
			TextView nTv = (TextView)findViewById(R.id.time00);
			nTv.setText("Get Doritos and cheese");
			nTv.setBackgroundColor(Color.RED);
			
			TextView nTv3 = (TextView)findViewById(R.id.time01);
			//nTv.setText("Get Doritos and cheese");
			nTv3.setBackgroundColor(Color.RED);
			
			TextView nTv2 = (TextView)findViewById(R.id.time04);
			nTv2.setText("Meditate");
			nTv2.setBackgroundColor(Color.BLUE);
			
			
			
			
			
			
			}

	}


