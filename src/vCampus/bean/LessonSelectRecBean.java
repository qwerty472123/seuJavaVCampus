package vCampus.bean;

import java.util.Date;

import vCampus.server.dao.model.LessonSelectRec;

public class LessonSelectRecBean {
	int ID;
	int studentId;
	int lessonId;
	Date selectTime;
	
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
