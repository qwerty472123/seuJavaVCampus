package vCampus.bean;

import java.io.Serializable;
import java.util.Date;

public class AptRecBean implements Serializable{

	private int id;
	private int personID;
	private int doctorID;
	private Date aptday;
	private String remark;
	private Date operTime;
	private boolean done;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public int getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	public Date getAptday() {
		return aptday;
	}
	public void setAptday(Date aptday) {
		this.aptday = aptday;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	
	
}
