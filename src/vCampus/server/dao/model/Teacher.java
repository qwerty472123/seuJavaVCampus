package vCampus.server.dao.model;

import java.sql.Date;

public class Teacher extends Person{
	private String faculty;
	private String classTable;
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getClassTable() {
		return classTable;
	}
	public void setClassTable(String classTable) {
		this.classTable = classTable;
	}
	@SuppressWarnings("deprecation")
	public void init(){
		faculty = "无";
		classTable = "";
		super.setAge(18);
		super.setBalance(0);
		super.setBirthday(new Date(1999,1,1));
		super.setEmail("");
		super.setName("");
		super.setPhone("");
		super.setQq("");
		super.setSex(0);
	}
}
