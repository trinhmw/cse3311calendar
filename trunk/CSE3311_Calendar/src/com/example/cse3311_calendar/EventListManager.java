package com.example.cse3311_calendar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import android.os.Environment;
import android.util.Log;

/**
 * Manager of lists of events
 */
public class EventListManager {

	private static EventListManager myself = null;
	private static Hashtable<String, ArrayList<Event>> eventTable;
	private static int nextID;
	private static final String saveLocation = "save.bin";
	private static File saveFile;

	/**
	 * Instantiates a new event list manager, protected to avoid use
	 */
	protected EventListManager (){

	}

	/**
	 * Gets the single instance of EventListManager.
	 *
	 * @return single instance of EventListManager
	 */
	public static EventListManager  getInstance(){

		if (myself == null){
			myself = new EventListManager();

			try{
				//String state = Environment.getExternalStorageState();
				//String file = Environment.getExternalStorageDirectory().toString();
				saveFile = new File(Environment.getExternalStorageDirectory(), "/data.dat");
				saveFile.createNewFile();
				ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(saveFile));
				eventTable = (Hashtable<String, ArrayList<Event>>) inStream.readObject();
				inStream.close();
			}
			catch(Exception e){
				e.printStackTrace();
				eventTable = new Hashtable<String, ArrayList<Event>> ();
				ArrayList<Event> nextIDList = new ArrayList<Event>();
				Event nextIDEvent = new Event();
				nextIDEvent.setId(0);
				nextIDList.add(nextIDEvent);
				eventTable.put("next ID", nextIDList);
				nextID = 0;
			}
			/*
			catch (Exception e){
				e.printStackTrace();
			}
			 */

		}

		return myself;
	}

	/**
	 * Adds a new event to the list.
	 *
	 * @param newEvent the new event to be added
	 * @return true, if successful
	 */
	public boolean addEvent(Event newEvent){

		boolean added = false;

		nextID = eventTable.get("next ID").get(0).getId();

		newEvent.setId(nextID);

		String key = newEvent.getStartDate().toString();

		ArrayList<Event> daysEvents  = eventTable.get(key);

		if (daysEvents == null){
			daysEvents = new ArrayList<Event>();
			daysEvents.add(newEvent);
			eventTable.put(key, daysEvents);
			nextID++;
			eventTable.get("next ID").get(0).setId(nextID);
			added = true;
		}
		else{
			//Log.v("Failed to fill in.", "Else!" + daysEvents.size());
			for(int i = 0; i < daysEvents.size(); i++){
				
				Event existingEvent = daysEvents.get(i);
				if((newEvent.getStartTime() >= existingEvent.getStartTime()) &&
						(newEvent.getStartTime() <= existingEvent.getEndTime())){
					//cannot add
					return added;
				}
				if((newEvent.getEndTime() <= existingEvent.getStartTime()) && 
						(newEvent.getEndTime() >= existingEvent.getEndTime())){
					//cannot add
					return added;
				}
			}

			//Log.v("Failed to fill in.", "Add!" + newEvent.getName());
			daysEvents.add(newEvent);
			Collections.sort(daysEvents);
			eventTable.put(key, daysEvents);
			//Log.v("Failed to fill in.", "Put!" + newEvent.getName());
			nextID++;
			eventTable.get("next ID").get(0).setId(nextID);
			added = true;
			//Log.v("Failed to fill in.", "added!" + added);
		}

		if(added == true){
			try{
				ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(saveFile));
				outStream.writeObject(eventTable);
				outStream.flush();
				outStream.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}

		//Log.v("Failed to fill in.", "Final!" + added);
		return added;
	}

	/**
	 * Gets the list of all events to be pulled
	 *
	 * @param key the key for the day to be pulled
	 * @return the list of that day's events
	 */
	public ArrayList<Event> getEvents(String key)
	{
		ArrayList<Event> toReturn;
		toReturn = eventTable.get(key);
		return toReturn;
	}

	/**
	 * Gets a single event using key and id
	 *
	 * @param key the key of the event's day
	 * @param id the id event to be pulled
	 * @return the event corresponding to that day, or null if no event found
	 */
	public Event getEventsById(String key, int id)
	{
		int i;
		ArrayList<Event> dayList;
		Event toReturn = null;
		dayList = eventTable.get(key);

		for (i = 0; i < dayList.size(); i++){
			if(dayList.get(i).getId() == id){
				toReturn = dayList.get(i);
			}
		}
		return toReturn;
	}

	/**
	 * Handles the deletion of an event
	 *
	 * @param key the key of the event to delete
	 * @param id the id of the event to delete
	 * @return true, if successful
	 */
	public boolean deleteEvent(String key, int id)
	{
		int i;
		boolean result = false;
		ArrayList<Event> dayList;

		dayList = eventTable.get(key);

		Log.v("Failed to fill in.", "size" + dayList.size());
		for (i = 0; (i < dayList.size())&&(!result); i++){
			Log.v("Failed to fill in.", "In loop" + id + "list ID" + dayList.get(i).getId() + "name: " + dayList.get(i).getName());
			if(dayList.get(i).getId() == id){
				dayList.remove(i);
				result = true;
			}
		}
		if (result == true){
			
			eventTable.put(key, dayList);
			try{
				ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(saveFile));
				outStream.writeObject(eventTable);
				outStream.flush();
				outStream.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		return result;

	}

	/**
	 * Handles editing of an event
	 *
	 *@param toEditEvent new event to replace old
	 * @param key the key to the old event
	 * @param id the id to the old event
	 * @return true, if successful
	 */
	public boolean editEvent(Event toEditEvent, String key, int id) {

		boolean result = this.deleteEvent(key, id);

		if (result == true){
			//Log.v("Failed to fill in.", "Delete!");
			result = this.addEvent(toEditEvent);
		}
		return result;
	}


}
