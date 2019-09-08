package vCampus.server.dao.model;

import vCampus.bean.DoctorBean;

public class Doctor {

	private int id;
	private String name;
	private String introtxt;
	private String availableTime;
	
	public DoctorBean toBean() {
		DoctorBean d = new DoctorBean();
		d.setId(id);
		d.setName(name);
		d.setIntrotxt(introtxt);
		return d;
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
	public void setName(String name) {
		this.name = name;
	}
	public String getIntrotxt() {
		return introtxt;
	}
	public void setIntrotxt(String introtxt) {
		this.introtxt = introtxt;
	}
	public String getAvailableTime() {
		return availableTime;
	}
	public void setAvailableTime(String availableTime) {
		this.availableTime = availableTime;
	}
	
}
