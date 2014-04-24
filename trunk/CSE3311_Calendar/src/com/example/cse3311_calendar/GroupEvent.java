package com.example.cse3311_calendar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class GroupEvent extends Event {
	
	private ArrayList<String> contactName;
	private ArrayList<String> contactsPhoneNumber;
	
	private ArrayList<Integer> contactReply;
	private ArrayList<String> dayBinaries;
	
	private int minAmountAccepting;
	EventListManager elm;
	
	//create setters and getters methods
	public GroupEvent()
	{
		contactName = new ArrayList<String>();
		contactsPhoneNumber = new ArrayList<String>();
		
		contactReply = new ArrayList<Integer>();
		contactReply.add(0);
		contactReply.add(0);
		
		dayBinaries = new ArrayList<String>();
		dayBinaries.add("");
		dayBinaries.add("");
		minAmountAccepting = 1;
	}
	public void setMinAmount(int x)
	{
		minAmountAccepting = x;
	}
	public void addContactName(String name)
	{
		contactName.add(name);
	}
	public void addContactPhoneNumber(String phoneNumber)
	{
		contactsPhoneNumber.add(phoneNumber);
	}
	
	public void addReply(String phoneNumber)
	{
		if(phoneNumber.compareTo(contactsPhoneNumber.get(0)) == 0)
		{
			contactReply.set(0,1);
		}
		else if(phoneNumber.compareTo(contactsPhoneNumber.get(1)) == 0)
		{
			contactReply.set(1, 1);
		}
	}
	
	public void addDayBinary(String phoneNumber,String dayBinary)
	{
		if(phoneNumber.compareTo(contactsPhoneNumber.get(0)) == 0)
		{
			dayBinaries.set(0,dayBinary);
		}
		else if(phoneNumber.compareTo(contactsPhoneNumber.get(1)) == 0)
		{
			dayBinaries.set(1, dayBinary);
		}
		
	}
	public ArrayList<String> getContacts()
	{
		return contactName;
	}
	public ArrayList<Integer> getReply()
	{
		return contactReply;
	}
	public ArrayList<String> getContactsPhoneNumber()
	{
		return contactsPhoneNumber;
	}
	public ArrayList<String> getDayBinaries()
	{
		return dayBinaries;
	}
	private String getTimeBinary()
	{
		String availDays = "111111111111111111111111";
		char[] availDaysArray = availDays.toCharArray();
		int sTime;
		int eTime;
		sTime = this.getStartTime()/60;
		eTime = this.getEndTime() / 60;
		
		while(sTime != eTime)
		{
			availDaysArray[sTime] = '0';
			sTime++;
		
		}
		return String.valueOf(availDaysArray);
		
	}
	public String getAvailableDays()
	{
		elm = EventListManager.getInstance();
		String availDays = "000000000000000000000000";
		char[] availDaysArray = availDays.toCharArray();
		
		String dayBinary = elm.getDayBinary(this.getStartDate().toString());
		String timeBinary = getTimeBinary();
		
		if(contactReply.get(0) == 1)
		{
			for(int i = 0;i<24;i++)
			{
				if(dayBinaries.get(0).substring(i, i+1).compareTo("1") == 0|| dayBinary.substring(i, i+1).compareTo("1") == 0 || timeBinary.substring(i, i+1).compareTo("1") == 0)
				{
					availDaysArray[i] = '1';
				}
				
				
			}
		}
		
		return String.valueOf(availDaysArray);
	}
	
	
	
	

}

