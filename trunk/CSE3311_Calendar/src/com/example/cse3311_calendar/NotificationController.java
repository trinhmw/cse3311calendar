package com.example.cse3311_calendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class NotificationController extends BroadcastReceiver{
	static EventListManager elm;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//start activity
		Log.v("recieve", "Notification recieved");
		Bundle extras = intent.getExtras();
		
		Intent intent2 = new Intent(context, AlarmReceiverActivity.class); 
		intent2.putExtras(extras);
		intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent2);		
	}


	public static boolean createNotification(Event event, int notificationTime, Context thisContext){
		/* Creates an EventNotification
		 *  Creates an EventListManager
		 *  Adds a notification to EventListManager
		 *  Updates next Notification
		 * 
		 * notificationTime is minutes (8AM = 480)
		 * notificationDate is day of event
		 * timeBefore is in minutes
		 */
		boolean added = false;
		if(notificationTime >= 0){

			int hour = (event.getStartTime() / 60)-(notificationTime / 60); 
			int minute = (event.getStartTime() % 60) -(notificationTime % 60);
			int day = event.getStartDate().getDate(); 
			int month = event.getStartDate().getMonth();
			int year = event.getStartDate().getYear();

			Date alarmDate = new Date();
			alarmDate.setDate(day); 
			alarmDate.setMonth(month); 
			alarmDate.setYear(year); 
			alarmDate.setHours(hour); 
			alarmDate.setMinutes(minute);

			int timeInMinutes = (60*hour)+minute;

			//add EventNotification to 
			EventNotification en = new EventNotification(event, timeInMinutes);
			elm =EventListManager.getInstance();
			elm.addNotification(en);


			// Context context = null; 
			Intent intent = new Intent(thisContext, NotificationController.class);
			GregorianCalendar cal = new GregorianCalendar(year, month, day, hour, minute);
			long time = cal.getTimeInMillis();
			Bundle extras = new Bundle();
			extras.putInt("id", event.getId());
			extras.putInt("day", day);
			extras.putInt("month", month);
			extras.putInt("year", year);
			intent.putExtras(extras);
			PendingIntent alarmIntent = PendingIntent.getBroadcast(thisContext, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
			//Log.v("pending", "alarmIntent: " + alarmIntent.getCreatorPackage()	);
			AlarmManager am = (AlarmManager) thisContext.getSystemService(Context.ALARM_SERVICE);
			//Log.v("am", "am: " + am.toString());
			am.set(AlarmManager.RTC_WAKEUP,time, alarmIntent);
			String sMinute = "";
			if(minute <=9 ){
				sMinute = "0" + minute;
			}
			else{
				sMinute = ""+minute;
			}

			Toast.makeText(thisContext,("Notification set for " + month + "/" + day + " at " + hour + ":" + sMinute),Toast.LENGTH_LONG).show();
		}
		added = true; 

		return added;
	}//end of newNotification()

	public static boolean repeatingNotification(Event event, int notificationTime, int frequency){
		/* Creates an Event Notification
		 * 
		 * notificationTime is minutes (8AM = 480)
		 * notificationDate is day of event
		 * timeBefore is in minutes
		 * frequency is in minutes -> weekly = 7*24*60
		 */
		boolean added = false;

		int hour = (event.getStartTime()-notificationTime) / 60; 
		int minute = (event.getStartTime()-notificationTime) % 60;
		int day = event.getStartDate().getDay(); 
		int month = event.getStartDate().getMonth();
		int year = event.getStartDate().getYear();
		long milliFrequency = frequency *24*60*1000; //converts frequency from days to milliseconds

		Date alarmDate = new Date();
		alarmDate.setDate(day); 
		alarmDate.setMonth(month); 
		alarmDate.setYear(year); 
		alarmDate.setHours(hour); 
		alarmDate.setMinutes(minute);
		int timeInMinutes = (60*hour)+minute;

		//add EventNotification to 
		EventNotification en = new EventNotification(event, timeInMinutes);
		elm =EventListManager.getInstance();
		elm.addNotification(en);

		Context context = null; 
		Intent intent = new Intent(context, AlarmReceiverActivity.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(context.getApplicationContext(), event.getId(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		am.setRepeating(AlarmManager.RTC_WAKEUP, alarmDate.getTime(), milliFrequency,alarmIntent);

		//if(am != null){
		Toast.makeText(context,("Notification set for " + month + "/" + day + " at " + hour + ":" + minute),Toast.LENGTH_LONG).show();
		added = true; 

		return added;
	}

	public static boolean deleteNotification(EventNotification notification){
		boolean deleted = false;

		Context context = null;
		Intent intent = new Intent(context, AlarmReceiverActivity.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(context, notification.getEvent().getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		am.cancel( alarmIntent );
		//error checking
		deleted = true;

		return deleted;
	}//end of deleteNotification()

	public static boolean deleteNotificationbyId(int id, Context context){
		boolean deleted = false;

		//Context context = null;
		Intent intent = new Intent(context, AlarmReceiverActivity.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		am.cancel( alarmIntent );

		elm = EventListManager.getInstance();
		ArrayList<EventNotification> noteList = elm.getNotificationList();
		for(int i=0; i< noteList.size(); i++){
			if(noteList.get(i).getEvent().getId() == id){
				elm.removeNotification(noteList.get(i));
				Collections.sort(noteList);
			}
		}
		//error checking
		deleted = true;


		return deleted;
	}

	public EventNotification getNotificationByEventId(int id){
		EventListManager elm = EventListManager.getInstance();
		ArrayList<EventNotification> nList = elm.getNotificationList();
		for(int i=0; i<nList.size(); i++){
			if(id == nList.get(i).getEvent().getId() )
				return nList.get(i);
		}
		return null;
	}
}
