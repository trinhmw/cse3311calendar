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
	private int nextID;
	private static final String saveLocation = "save.bin";
	private static File saveFile;
	
	protected EventListManager (){
		
	}
	
	public static EventListManager  getInstance(){
		
		if (myself == null){
			myself = new EventListManager();
			
			try{
				String state = Environment.getExternalStorageState();
				String file = Environment.getExternalStorageDirectory().toString();
				File newFile = new File(Environment.getExternalStorageDirectory(), "/data.dat");
				newFile.createNewFile();
				ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(newFile));
				eventTable = (Hashtable<String, ArrayList<Event>>) inStream.readObject();
				inStream.close();
			}
			catch(Exception e){
				e.printStackTrace();
				eventTable = new Hashtable<String, ArrayList<Event>> ();
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
		
		newEvent.setId(nextID);
		
		String key = newEvent.getStartDate().toString();
		
		ArrayList<Event> daysEvents  = eventTable.get(key);
		
		if (daysEvents == null){
			daysEvents = new ArrayList<Event>();
			daysEvents.add(newEvent);
			eventTable.put(key, daysEvents);
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
	

}
