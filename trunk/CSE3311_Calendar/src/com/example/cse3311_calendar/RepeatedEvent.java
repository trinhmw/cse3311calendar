package com.example.cse3311_calendar;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;
//import org.joda.time.DateTime;
 //import org.joda.time.Days;
 //import org.joda.time.LocalDate;
 //import org.joda.time.format.DateTimeFormat;
 //import org.joda.time.format.DateTimeFormatter;

public class RepeatedEvent extends Event
{

	//The event will reapeat every (numberOfRepeatedDays) days
	private int numberOfRepeatedDays;
	//After this date the event should not repeat
	private Date lastDay = null;
	private Date trueStartDate = null;

	//create setters and getters methods
	public RepeatedEvent()
	{
	}
	
	/*
	public RepeatedEvent(int id, String name, String location, Date startDate, Date endDate, int startTime, int endTime, String description, int category, boolean allDayOption, int numberOfDaysRepeat, Date lastDay)
	{
		
		super(id,  name,  location,  startDate, 
		 endDate,  startTime,  endTime,  description, 
		 category,  allDayOption);
		 
		

		numberOfRepeatedDays = numberOfDaysRepeat;
		this.lastDay = lastDay;
		
	}
	*/
	
	public void setTrueStartDate(){
		trueStartDate = this.getStartDate();
	}
	
	public Date getTrueStartDate(){
		return trueStartDate;
	}

	//set the lastDay when to stop repeating, otherwise pass it null and will repeat forever
	public void setLastDay(Date lastDay) {

    	this.lastDay = lastDay;
		
	}
	public Date getLastDay()
	{
		return lastDay;
	}
	//set the number of days between repeats
	public boolean setRepeatedDays(int days)
	{
		if(days < 1){
			return false;
		}
		numberOfRepeatedDays = days;
		return true;
	}
	//repeats the number of day interval for repeats
	public int getRepeatedDays()
	{
		return numberOfRepeatedDays;
	}

	//returns true if date given is a date that should be a repeated event
	public boolean isRepeated(Date todaysDate)
	{
		if(todaysDate == null)
		{
			return false;
		}
		long dayDifference = 0;
		Date startDate = new Date();
		startDate = getTrueStartDate();
	
		
		Calendar startC = Calendar.getInstance();
		startC.setTime(startDate);
		Calendar todayC = Calendar.getInstance();
		todayC.setTime(todaysDate);
		todayC.set(Calendar.YEAR, startC.get(Calendar.YEAR));
		long startDay = startC.get(Calendar.DAY_OF_YEAR);
		long toDAY = todayC.get(Calendar.DAY_OF_YEAR);

		dayDifference =  toDAY  - startDay;
		
		
		
		//if last day is before todays date then it shouldnt be checked as repeating anymore
		//might swtitch to superclass object or have a method checking "valid" repeatable date
		if(startDate.compareTo(todaysDate) == 0)
		{
			return true;
		}
		
		if(lastDay != null)
		{
			if(todaysDate.after(lastDay))
				return false;
		}

		if(dayDifference <= 0)
		{
			return false;
		}
		else
		{
					
			if(dayDifference % numberOfRepeatedDays == 0 && numberOfRepeatedDays != 0)
			{
				return true;
			}
		}
		
		return false;
	}

	
}

