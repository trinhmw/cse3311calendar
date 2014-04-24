package com.example.cse3311_calendar;

import android.telephony.SmsManager;

import java.text.SimpleDateFormat;

import java.util.Date;

import android.content.Context;

import android.widget.Toast;


public class GroupEventController {
	final SmsManager sms = SmsManager.getDefault();
	private static EventListManager elm;
	
	
	public static void replyToRequest(Context context,String eventDate,String date, String eventName, String pNumber)
	{
			elm = EventListManager.getInstance();
			
			String dString =  elm.getDayBinary(date);
	        
			String reply = "1"+eventDate+dString+eventName;
			SmsManager.getDefault().sendTextMessage(pNumber, null,reply , null, null);
			Toast toast = Toast.makeText(context,"Available days for " +eventDate+" has been sent" ,Toast.LENGTH_LONG);
			toast.show();
			
	}
	//look up event and fill in reply number and reply day binary
	public static void repliedToRequest(Context context,String eventDate,String date, String eventName, String pNumber, String dayBinary)
	{
		elm = EventListManager.getInstance();
		GroupEvent tempGroupEvent;
		tempGroupEvent = elm.getGroupEvent(date, eventName);
		tempGroupEvent.addReply(pNumber);//pnumber might be different
		tempGroupEvent.addDayBinary(pNumber, dayBinary);//pnumber might be different
		
		boolean edited = elm.editGroupEvent(tempGroupEvent, date, eventName);
		if(edited)
		{
			Toast toast = Toast.makeText(context,pNumber +" has responded to Group Event named " + eventName ,Toast.LENGTH_LONG);
			toast.show();
		}
		
	}
	//send request
	public static boolean addGroupEvent(String name, String location, Date startDate, Date endDate, int startTime, 
			int endTime, String description, int category, String contact1, String contact2, String contact1phone, String contact2phone,int minAmountAccepting)
	{
		
		GroupEvent newEvent = new GroupEvent();
		//Set name, check if valid
		boolean result = newEvent.setName(name);
		
		
		if(result == true){
			//If valid set various data that does not need to be checked
			newEvent.setLocation(location);
			newEvent.setDescription(description);
			newEvent.setCategory(category);
			newEvent.setStartDateTime(startDate, startTime);
			newEvent.setendDateTime(endDate, endTime);
			
			newEvent.addContactName(contact1);
			newEvent.addContactPhoneNumber(contact1phone);
			if(contact2 != null)
			{
				newEvent.addContactName(contact2);
				newEvent.addContactPhoneNumber(contact2phone);
			}
			
			newEvent.setMinAmount(minAmountAccepting);
			
			//Add the new event to the manager, return result of this.
			EventListManager manager = EventListManager.getInstance();
			manager.addGroupEvent(newEvent);
			
			String date = new SimpleDateFormat("MM/dd/yyyy").format(startDate);
			String message = "0"+date+name;
			sendMessage(message,contact1phone);
			if(contact2 != null)
			{
				sendMessage(message,contact2phone);
			}
			
			
		
		}
		return true;

	}
	private static boolean sendMessage(String message, String Contact)
	{
		SmsManager smsManager = SmsManager.getDefault(); 
    	//smsManager.sendDataMessage("5556", null, SMS_PORT, messageText.getBytes(), null, null); 
		smsManager.sendTextMessage(Contact, null,message, null, null);
		return true;
	
	}
	
}

