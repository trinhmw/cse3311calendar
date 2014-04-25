package com.example.cse3311_calendar;

import android.util.Log;

public class DeleteEventController {
	
	private static EventListManager mgr;
	private static NotificationController nc;
	
	public static boolean deleteEvent (String key, int id){
		
		mgr = EventListManager.getInstance();
		nc.deleteNotificationbyId(id);
		//Log.v("got Instance", "got Instantc");
		return mgr.deleteEvent(key, id);
		
	}

}
