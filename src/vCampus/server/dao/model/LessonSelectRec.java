package vCampus.server.dao.model;

import java.util.Date;

import vCampus.bean.LessonSelectRecBean;

public class LessonSelectRec {
	int ID;
	int studentId;
	int lessonId;
	Date selectTime;
	
	public LessonSelectRecBean toBean() {
		//TO DO
		LessonSelectRecBean b=new LessonSelectRecBean();
		b.setID(ID);
		b.setStudentId(studentId);
		b.setLessonId(lessonId);
		b.setSelectTime(selectTime);
		return b;
	}
	
	public LessonSelectRec toModel(LessonSelectRecBean b) {
		//TO DO
		LessonSelectRec x=new LessonSelectRec();
		x.setID(b.getID());
		x.setStudentId(b.getStudentId());
		x.setLessonId(b.getLessonId());
		x.setSelectTime(b.getSelectTime());
		return x;
	}
	
	public int getUserId() {
		return studentId;
	}
	public void setUserId(int userId) {
		this.studentId = userId;
	}
	public int getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	public Date getSelectTime() {
		return selectTime;
	}
	public void setSelectTime(Date selectTime) {
		this.selectTime = selectTime;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
}
