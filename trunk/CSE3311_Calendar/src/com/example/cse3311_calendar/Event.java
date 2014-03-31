package com.example.cse3311_calendar;

import java.io.Serializable;
import java.util.Date;


/**
 * Event class, holds all information relating to an event
 */
public class Event implements Comparable<Event>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	// initialize the variables
	private int id;
	private String name;
	private String location;
	private Date startDate;
    private Date endDate;
	private int startTime;
    private int endTime;
    private String description;
    private int category;
    private boolean allDayOption;

	// create setters and getters methods
    /**
	 * Instantiates a new event, blank.
	 */
	public Event() {
		startDate = new Date();
	    endDate = new Date();
	    allDayOption = false;
    }
    
	
	//Constructor commented out, due to fact that it contains no erro checking
    /*
    
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
    */
    
    /**
     * Sets both the start date and time
     *
     * @param startDate the start date
     * @param startTime the start time
     */
    public void setStartDateTime(Date startDate, int startTime) {

    	this.startDate = startDate;
		this.startTime = startTime;
		
	}
	
	/**
	 * Sets both the end date and time
	 *
	 * @param endDate the end date
	 * @param endTime the end time
	 * @return true, if successful, false if end comes before beginning
	 */
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
	
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the name
	 * @return true, if successful, false if blank
	 */
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
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public int getStartTime() {
		return startTime;
	}
	
	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public int getEndTime() {
		return endTime;
	}
	
	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public int getCategory() {
		return category;
	}
	
	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(int category) {
		this.category = category;
	}
	
	/**
	 * Checks if is all day option.
	 *
	 * @return true, if is all day option
	 */
	public boolean isAllDayOption() {
		return allDayOption;
	}
	
	/**
	 * Sets the all day option.
	 *
	 * @param allDayOption the new all day option
	 */
	public void setAllDayOption(boolean allDayOption) {
		this.allDayOption = allDayOption;
		
	}
	
	@Override
	public int compareTo(Event another) {
		Event toCompare = another;
		int compare = 0;
		//compare = this.getStartDate().compareTo(toCompare.getStartDate());
		if (compare == 0){
			return   this.getStartTime() - toCompare.getStartTime() ;
		}
		return compare;
	}

    

}// end of public class Event()
