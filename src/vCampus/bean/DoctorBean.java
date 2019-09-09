package vCampus.bean;

import java.io.Serializable;

public class DoctorBean implements Serializable{

	private int id;
	private String name;
	private String introtxt;
	private boolean gender;
	private int age;
	
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	
}
