package com.gogate.apps.financial;

import java.time.LocalDate;

public class Leave {
	
	private String emp_id;
	private LocalDate leave_st;
	private LocalDate leave_end;
	
	//Constructor
	public Leave(String emp_id, LocalDate leave_st, LocalDate leave_end){
		this.emp_id = emp_id;
		this.leave_st = leave_st;
		this.leave_end = leave_end;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public LocalDate getLeave_st() {
		return leave_st;
	}

	public LocalDate getLeave_end() {
		return leave_end;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public void setLeave_st(LocalDate leave_st) {
		this.leave_st = leave_st;
	}

	public void setLeave_end(LocalDate leave_end) {
		this.leave_end = leave_end;
	}
	
	
}
