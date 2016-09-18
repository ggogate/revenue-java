package com.gogate.apps.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gogate.apps.financial.*;

public class Application {

	public static void main(String[] args) {
		
		//Populate allocations
		List<Allocation> allocations = new ArrayList<Allocation>();
		allocations.add(new Allocation("123","Gaurav","offshore","Mumbai","Developer",(double) 50.5,LocalDate.parse("2016-01-01"),LocalDate.parse("2016-06-30"),false));
		allocations.add(new Allocation("456","Gaurav","onsite","San Fransisco","Tester",(double) 100.5,LocalDate.parse("2016-07-01"),LocalDate.parse("2016-12-31"),false));
		
		//Populate Holidays
		List<Holiday> holidays = new ArrayList<Holiday>();
		holidays.add(new Holiday("Mumbai",LocalDate.parse("2016-02-07")));
		holidays.add(new Holiday("San Fransisco",LocalDate.parse("2016-02-07")));
		holidays.add(new Holiday("San Fransisco",LocalDate.parse("2016-02-08")));
		
		//Populate Leaves
		List<Leave> leaves = new ArrayList<Leave>();
		leaves.add(new Leave("123", LocalDate.parse("2016-01-08"), LocalDate.parse("2016-01-09")));
		
		//Set calculation duration
		LocalDate start = LocalDate.parse("2016-01-01");
		LocalDate finish = LocalDate.parse("2016-01-10");
			
		//Calculate
		for (Allocation a:allocations){
			try{
				a.calculateBilling(start, finish, holidays, leaves);
			}catch (Exception e){
				e.printStackTrace();
			}
			System.out.println("City: " + a.getCity() + " Hours: " + a.getHours() + " Amount: " + a.getAmount());
		}
	}
}
