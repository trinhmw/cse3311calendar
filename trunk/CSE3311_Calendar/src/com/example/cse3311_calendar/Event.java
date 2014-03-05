package com.example.cse3311_calendar;

import java.io.Serializable;
import java.util.Date;

public class Event implements Comparable<Event>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	// initialize the variables
	private int id;
	private String name;
	private String location;
	private Date startDate = new Date();
    private Date endDate = new Date();
	private int startTime;
    private int endTime;
    private String description;
    private int category;
    private boolean allDayOption = true;

	// create setters and getters methods
    public Event() {
    	
    }
    public Event(int id, String name, String location, Date startDate, Date endDate, int startTime, int endTime, String description, int category, boolean allDayOption ) {
    	
    	this.id = id;
    	this.name = name;
    	this.location = location;
    	this.startDate = startDate;
    	this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.category = category;
        this.allDayOption = allDayOption;
        
    }
    public void setstartDateTime(Date startDate, int startTime) {

    	this.startDate = startDate;
		this.startTime = startTime;
		
	}
	
	public boolean setendDateTime(Date endDate, int endTime) {
		boolean bool = true;
		
		//if((endDate.after(getStartDate())&& endDate.equals(getStartDate()))) {
		if((endDate.equals(getStartDate()))&&(endTime > getStartTime())){
			this.endDate = endDate;
			this.endTime = endTime; 
			
		}
		else {
			bool = false;
		}
		
		return bool;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public boolean setName(String name) {
		boolean result;
		if (name.equals("")){
			result = false;
		}else{
			this.name = name;
			result = true;
		}
		return result;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public boolean isAllDayOption() {
		return allDayOption;
	}
	public void setAllDayOption(boolean allDayOption) {
		this.allDayOption = allDayOption;
		
	}
	
	@Override
	public int compareTo(Event another) {
		Event toCompare = another;
		int compare = this.getStartDate().compareTo(toCompare.getStartDate());
		if (compare == 0){
			return   this.getStartTime() - toCompare.getStartTime() ;
		}
		return compare;
	}

    

}// end of public class Event()
