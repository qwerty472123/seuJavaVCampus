package vCampus.server.dao.model;

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
	
}
