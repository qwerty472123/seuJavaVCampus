package vCampus.bean;

import java.io.Serializable;

public class DoctorBean implements Serializable{

	private int id;
	private String name;
	private String introtxt;
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
	
}
