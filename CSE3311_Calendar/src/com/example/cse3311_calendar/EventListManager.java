package com.example.cse3311_calendar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
	private static int rNextID;
	private static final String saveLocation = "save.bin";
	private static File saveFile;
	private static File saveFileRepeats;
	private static File saveGroupEvents;
	private static File saveNotifications;
	private static ArrayList<RepeatedEvent> repeatList;
	private static ArrayList<GroupEvent> pendingGroupEvents;
	private static ArrayList<EventNotification> notificationList;

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
				Log.v("DEBUG", "DEBUG Recreating Hashtable");
				//e.printStackTrace();
				eventTable = new Hashtable<String, ArrayList<Event>> ();
				ArrayList<Event> nextIDList = new ArrayList<Event>();
				Event nextIDEvent = new Event();
				nextIDEvent.setId(1);
				nextIDList.add(nextIDEvent);
				eventTable.put("next ID", nextIDList);
				nextID = 1;
			}
			/*
			catch (Exception e){
				e.printStackTrace();
			}
			 */
			try{

				saveFileRepeats = new File(Environment.getExternalStorageDirectory(), "/data2.dat");
				saveFileRepeats.createNewFile();
				ObjectInputStream inStream2 = new ObjectInputStream(new FileInputStream(saveFileRepeats));
				repeatList = (ArrayList<RepeatedEvent>) inStream2.readObject();
				inStream2.close();

			}
			catch(Exception e)
			{

				repeatList = new ArrayList<RepeatedEvent>();

				RepeatedEvent nextIDRepeatEvent = new RepeatedEvent();
				nextIDRepeatEvent.setId(-1);
				repeatList.add(nextIDRepeatEvent);
				rNextID = 0;


			}
			try{

				saveGroupEvents = new File(Environment.getExternalStorageDirectory(), "/data3.dat");
				saveGroupEvents.createNewFile();
				ObjectInputStream inStream3 = new ObjectInputStream(new FileInputStream(saveGroupEvents));
				pendingGroupEvents = (ArrayList<GroupEvent>) inStream3.readObject();
				inStream3.close();
			}
			catch(Exception e)
			{
				pendingGroupEvents = new ArrayList<GroupEvent>();
			}
			try{			
				saveNotifications = new File(Environment.getExternalStorageDirectory(), "/data4.dat");
				saveNotifications.createNewFile();
				ObjectInputStream inStream3 = new ObjectInputStream(new FileInputStream(saveNotifications));
				notificationList = (ArrayList<EventNotification>) inStream3.readObject();
				inStream3.close();
			}
			catch(Exception e)
			{
				notificationList = new ArrayList<EventNotification>();
			}

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

	public boolean addGroupEvent(GroupEvent myGEvent)
	{
		boolean added = false;
		pendingGroupEvents.add(myGEvent);
		added = true;
		if(added == true){
			try{
				ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(saveGroupEvents));
				outStream.writeObject(pendingGroupEvents);
				outStream.flush();
				outStream.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		return added;
	}
	/**
	 * Adds a new repeated event to the list.
	 *
	 * @param rEvent the new repeated event to be added
	 * @return true, if successful
	 */
	public boolean addRepeatedEvent(RepeatedEvent rEvent)
	{

		boolean added = false;
		//get the next id
		rNextID = repeatList.get(0).getId();
		//set this event with that id	
		rEvent.setId(rNextID);
		//add event to list
		repeatList.add(rEvent);
		//move the id	
		rNextID--;
		//update new id to be filled
		repeatList.get(0).setId(rNextID);
		//set added flag to true
		added = true;
		//sort to the list
		Collections.sort(repeatList);

		if(added == true){
			try{
				ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(saveFileRepeats));
				outStream.writeObject(repeatList);
				outStream.flush();
				outStream.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}

		return added;
	}
	public String getDayBinary(String key)
	{
		ArrayList<Event> DayList = getEvents(key);

		String availDays = "000000000000000000000000";
		char[] availDaysArray = availDays.toCharArray();
		int sTime;
		int eTime;
		if(DayList != null)
		{
			for(int i = 0; i < DayList.size(); i++)
			{
				sTime = DayList.get(i).getStartTime()/60;
				eTime = DayList.get(i).getEndTime() / 60;

				while(sTime != eTime)
				{
					availDaysArray[sTime] = '1';
					sTime++;

				}

			}
		}


		return String.valueOf(availDaysArray);
		//return availDays;
	}
	public GroupEvent getGroupEvent(String key, String eventName)
	{
		GroupEvent toReturn = new GroupEvent();
		//get the repeated events that repeat on key date and add to list
		if(pendingGroupEvents.size() != 0)
		{
			for(int i = 0;i<pendingGroupEvents.size();i++)
			{
				@SuppressWarnings("deprecation")
				Date testDate = new Date(key);
				if(pendingGroupEvents.get(i).getStartDate().compareTo(testDate) == 0 && pendingGroupEvents.get(i).getName().compareTo(eventName) == 0)
				{

					toReturn = pendingGroupEvents.get(i);
				}
			}
		}

		return toReturn;
	}
	public ArrayList<GroupEvent> getGroupEvents()
	{
		return pendingGroupEvents;
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
		/*
		if (toReturn.isEmpty()){
			toReturn = null;
		}
		 */
		//get the repeated events that repeat on key date and add to list
		for(int i = 1;i<repeatList.size();i++)
		{
			@SuppressWarnings("deprecation")
			Date testDate = new Date(key);
			if(repeatList.get(i).isRepeated(testDate))
			{
				if(toReturn == null)
				{
					toReturn = new ArrayList<Event>();
				}
				toReturn.add(repeatList.get(i));
			}
		}

		//sort the list 
		if(toReturn != null)
			Collections.sort(toReturn);
		return toReturn;
	}

	/**
	 * Gets a single event using key and id
	 *
	 * @param key the key of the event's day
	 * @param id the id event to be pulled
	 * @return the event corresponding to that day, or null if no event found
	 */
	public Event getEventById(String key, int id)
	{
		int i;
		ArrayList<Event> dayList;
		Event toReturn = null;

		dayList = eventTable.get(key);
		Log.v("Tag", "DEBUG size:" + dayList.size());

		for (i = 0; i < dayList.size(); i++){
			if(dayList.get(i).getId() == id){
				toReturn = dayList.get(i);
			}
		}

		return toReturn;
	}
	public boolean editGroupEvent(GroupEvent event,String dateKey, String eventName)
	{
		boolean result = this.deleteGroupEvent(dateKey, eventName);

		if (result == true){
			//Log.v("Failed to fill in.", "Delete!");
			result = this.addGroupEvent(event);
		}
		return result;
	}
	public boolean deleteGroupEvent(String dateKey, String eventName)
	{
		if(pendingGroupEvents.size() != 0)
		{
			for(int i = 0;i<pendingGroupEvents.size();i++)
			{
				@SuppressWarnings("deprecation")
				Date testDate = new Date(dateKey);
				if(pendingGroupEvents.get(i).getStartDate().compareTo(testDate) == 0 && pendingGroupEvents.get(i).getName().compareTo(eventName) == 0)
				{

					pendingGroupEvents.remove(i);
					return true;
				}
			}
		}

		return false;
	}
	/**
	 * Gets a single repeated event using key and id
	 *
	 * @param key the key of the event's day
	 * @param id the id event to be pulled
	 * @return the event corresponding to that day, or null if no event found
	 */
	public RepeatedEvent getRepeatedEventById(String key, int id)
	{
		int i;
		RepeatedEvent toReturn = null;

		for (i = 0; i < repeatList.size(); i++){
			if(repeatList.get(i).getId() == id){
				toReturn = repeatList.get(i);
			}
		}
		Date currentDate = new Date(key);
		toReturn.setStartDate(currentDate);
		toReturn.setEndDate(currentDate);


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

		Log.v("test", "value of id" + id);
		dayList = eventTable.get(key);
		if(id > 0){


			for (i = 0; (i < dayList.size())&&(!result); i++){
				if(dayList.get(i).getId() == id){
					dayList.remove(i);
					result = true;
				}
			}
		}
		else{
			for (i = 0; (i < repeatList.size())&&(!result); i++){
				if(repeatList.get(i).getId() == id){
					repeatList.remove(i);
					result = true;
				}
			}
		}
		if (result == true){

			if(id > 0){
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
			else{
				try{
					ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(saveFileRepeats));
					outStream.writeObject(repeatList);
					outStream.flush();
					outStream.close();
				}
				catch (Exception e){
					e.printStackTrace();
				}
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

	/**
	 * Handles editing of a repeated event
	 *
	 *@param toEditEvent new event to replace old
	 * @param key the key to the old event
	 * @param id the id to the old event
	 * @return true, if successful
	 */
	public boolean editRepeatedEvent(RepeatedEvent toEditEvent, String key, int id) {

		boolean result = this.deleteEvent(key, id);

		if (result == true){
			//Log.v("Failed to fill in.", "Delete!");
			result = this.addRepeatedEvent(toEditEvent);
		}
		return result;
	}

	public boolean addNotification(EventNotification newNotification){

		boolean added = false;

		if( !notificationList.contains(newNotification) ){//newNotification is unique
			//add to array list
			notificationList.add(newNotification);
			//sort array list
			Collections.sort(notificationList);
			added = true;
		}
		
		//added = true;
		if(added == true){
			try{
				ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(saveNotifications));
				outStream.writeObject(notificationList);
				outStream.flush();
				outStream.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}

		//Log.v("Failed to fill in.", "Final!" + added);
		return added;
	}//end of addNotification

	public ArrayList<EventNotification> getNotificationList(){
		ArrayList<EventNotification> toReturn;
		toReturn = notificationList;

		//sort the list 
		if(toReturn != null)
			Collections.sort(toReturn);
		return toReturn;
	}//end of getNotificationList

	public boolean removeNotification(EventNotification notification){
		boolean removed = false;
		//remove from notificationList

		ArrayList<EventNotification> nList = getNotificationList();
		int index = nList.indexOf(notification);
		Log.v("Failed to fill in.", "INDEX: " + index);
		nList.remove(index);
		removed = true;

		return removed;
	}//end of removeNotification

	public static void killInstnace(){
		myself = null;
	}


}

