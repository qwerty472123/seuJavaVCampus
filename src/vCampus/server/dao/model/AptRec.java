package vCampus.server.dao.model;

import java.sql.Date;

import vCampus.bean.AptRecBean;

public class AptRec {
	private int id;
	private int personID;
	private int doctorID;
	private int aptday;
	private String remark;
	private Date operTime;
	private boolean done;
	
	public AptRecBean toBean() {
		AptRecBean a = new AptRecBean();
		a.setId(id);
		a.setDoctorID(doctorID);
		a.setPersonID(personID);
		a.setAptday(aptday);
		a.setRemark(remark);
		a.setOperTime(operTime);
		a.setDone(done);
		return a;
	}
	
	public static AptRec toModel(AptRecBean a) {
		AptRec x = new AptRec();
		x.setId(a.getId());
		x.setPersonID(a.getPersonID());
		x.setDoctorID(a.getDoctorID());
		x.setAptday(a.getAptday());
		x.setRemark(a.getRemark());
		x.setOperTime(new Date(a.getOperTime().getTime()));
		x.setDone(a.isDone());
		return x;
	}
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
	public int getAptday() {
		return aptday;
	}
	public void setAptday(int aptday) {
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
