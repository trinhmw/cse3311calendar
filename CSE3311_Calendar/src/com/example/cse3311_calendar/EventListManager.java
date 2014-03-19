package com.example.cse3311_calendar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import android.os.Environment;

public class EventListManager {
	
	private static EventListManager myself = null;
	private static Hashtable<String, ArrayList<Event>> eventTable;
	private static int nextID;
	private static final String saveLocation = "save.bin";
	private static File saveFile;
	
	protected EventListManager (){
		
	}
	
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
			
			daysEvents.add(newEvent);
			Collections.sort(daysEvents);
			eventTable.put(key, daysEvents);
			nextID++;
			eventTable.get("next ID").get(0).setId(nextID);
			added = true;	
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
		
		return added;
	}
	
	public ArrayList<Event> getEvents(String key)
	{
		ArrayList<Event> toReturn;
		toReturn = eventTable.get(key);
		return toReturn;
	}
	
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
	
	public boolean deleteEvent(String key, int id)
	{
		int i;
		boolean result = false;
		ArrayList<Event> dayList;
		
		dayList = eventTable.get(key);
		
		for (i = 0; (i < dayList.size())&&(!result); i++){
			if(dayList.get(i).getId() == id){
				dayList.remove(i);
				result = true;
			}
		}
		return result;
		
	}
	

}
