package vCampus.server.dao.model;

public class Student extends Person{
	private int grade;      //年级 1 2 3 4
	private int stuclass;   //班级 1 2 3 4 ...
	private String faculty; //院系
	private float GPA;      //绩点
	private String timeTable;     //存取自己课程ID的字符串  
	
	public String getTimeTable() {
		return timeTable;
	}
	public void setTimeTable(String timeTable) {
		this.timeTable = timeTable;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getStuclass() {
		return stuclass;
	}
	public void setStuclass(int stuclass) {
		this.stuclass = stuclass;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public float getGPA() {
		return GPA;
	}
	public void setGPA(float gPA) {
		GPA = gPA;
	}
	
}
