package com.gogate.apps.financial;

import java.time.LocalDate;;

public class Holiday {
	private String city;
	private LocalDate holiday_dt;
	
	public Holiday(String city, LocalDate holiday_dt){
		this.city = city;
		this.holiday_dt = holiday_dt;
	}

	public String getCity() {
		return city;
	}

	public LocalDate getHoliday_dt() {
		return holiday_dt;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setHoliday_dt(LocalDate holiday_dt) {
		this.holiday_dt = holiday_dt;
	}
	
}
