package com.example.cse3311_calendar;

import android.content.Context;
import android.util.Log;

public class DeleteEventController {
	
	private static EventListManager mgr;
	//private static NotificationController nc;
	
	public static boolean deleteEvent (String key, int id, Context thisContext){
		
		mgr = EventListManager.getInstance();
		NotificationController.deleteNotificationbyId(id, thisContext);
		//Log.v("got Instance", "got Instantc");
		return mgr.deleteEvent(key, id);
		
	}

}
