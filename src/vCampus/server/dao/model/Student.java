package vCampus.server.dao.model;

import java.sql.Date;

import vCampus.bean.StudentBean;

public class Student extends Person{
	private int grade;      //年级 1 2 3 4
	private int stuclass;   //班级 1 2 3 4 ...
	private String faculty; //院系
	private float GPA;      //绩点
	private String timeTable;     //存取自己课程ID的字符串  
	
	public StudentBean toBean() {
		StudentBean b = new StudentBean();
		b.setAge(this.getAge());
		b.setBalance(this.getBalance());
		b.setBirthday(this.getBirthday());
		b.setEmail(this.getEmail());
		b.setFaculty(this.getFaculty());
		b.setGPA(this.getGPA());
		b.setGrade(this.getGrade());
		b.setId(this.getId());
		b.setName(this.getName());
		b.setPhone(this.getPhone());
		b.setPswd(this.getPswd());
		b.setQq(this.getQq());
		b.setSex(this.getSex());
		b.setStuclass(this.getStuclass());
		b.setTimeTable(this.getTimeTable());
		return b;
	}
	
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
	
	@SuppressWarnings("deprecation")
	public void init(){
		grade = 1;
		stuclass = 1;
		faculty = "无";
		GPA = 0;
		timeTable = "";
		super.setAge(18);
		super.setBalance(0);
		super.setBirthday(new Date(99,1,1));
		super.setEmail("");
		super.setName("");
		super.setPhone("");
		super.setQq("");
		super.setSex(0);
	}
	
}
