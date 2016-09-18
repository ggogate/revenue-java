package com.gogate.apps.financial;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Allocation {

	private String emp_id;
	private String name;
	private String location;
	private String city;
	private String role;
	private double rate;
	private LocalDate start_dt;
	private LocalDate end_dt;
	private boolean buffer_flag;
	
	private double hours;
	private double amount;
	
	public Allocation(String emp_id, String name, String location, String city, String role, double rate,
			LocalDate start_dt, LocalDate end_dt, boolean buffer_flag) {
		super();
		this.emp_id = emp_id;
		this.name = name;
		this.location = location;
		this.city = city;
		this.role = role;
		this.rate = rate;
		this.start_dt = start_dt;
		this.end_dt = end_dt;
		this.buffer_flag = buffer_flag;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getCity() {
		return city;
	}

	public String getRole() {
		return role;
	}

	public double getRate() {
		return rate;
	}

	public LocalDate getStart_dt() {
		return start_dt;
	}

	public LocalDate getEnd_dt() {
		return end_dt;
	}

	public boolean isBuffer_flag() {
		return buffer_flag;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public void setStart_dt(LocalDate start_dt) {
		this.start_dt = start_dt;
	}

	public void setEnd_dt(LocalDate end_dt) {
		this.end_dt = end_dt;
	}

	public void setBuffer_flag(boolean buffer_flag) {
		this.buffer_flag = buffer_flag;
	}

	public double getHours() {
		return hours;
	}

	public double getAmount() {
		return amount;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void calculateBilling(LocalDate start, LocalDate finish, List<Holiday> holidays, List<Leave> leaves) throws Exception{
		//Validate dates
		if(start.isAfter(finish) || start.isEqual(finish)){
			throw new Exception();
		}

		//Calculate billing for this allocation based on the period provided
		LocalDate current = start;
		boolean workDay = true;
		do{
			//Check if current date is not Saturday or Sunday. For weekend, ignore remaining checks
			if(current.getDayOfWeek() == DayOfWeek.SATURDAY || current.getDayOfWeek() == DayOfWeek.SUNDAY ){
				System.out.println(current.toString() + " is a " + current.getDayOfWeek().name()); 
				workDay = false;				
			}
			else{				
				//Check if current date is not public holiday
				for (Holiday h:holidays){
					if(h.getCity().equalsIgnoreCase(this.city)){
						if(h.getHoliday_dt().equals(current)){
							//Holiday matched. Don't count this day 
							workDay = false;
							break;
						}
						else{
							workDay = true;
						}
					}	
				}
				
				if(workDay){
					System.out.println(current.toString() + " is neither weekend, nor holiday. Checking if any leaves");
					//Check if the employee has any holiday for current date
					for (Leave l:leaves){
						if(l.getEmp_id().equalsIgnoreCase(this.emp_id)){
							if( (current.isAfter(l.getLeave_st()) && current.isBefore(l.getLeave_end())) || 
								 current.equals(l.getLeave_st()) || current.equals(l.getLeave_end()) )
								{
								System.out.println(current.toString() + " is a employee leave");
									workDay = false;
									break;
								}
							}
					}
				}
			}
			
			//if no to all of above, add 8 hours to hours and calculate hours into rate as amount
			if(workDay){
				hours = hours + 8;
				amount = hours * rate;	
			}

			//Increment date
			current = current.plusDays(1);
			workDay = true;
		}while(!current.isAfter(finish));		
	}
}
