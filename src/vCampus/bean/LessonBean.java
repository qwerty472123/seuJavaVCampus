package vCampus.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class LessonBean implements Serializable{
	int ID;
	String lessonName;
	String location;
	int teacherId;
	int maxNum;
	int curNum;
	String status;
	ArrayList<LessonTime> timeTable;
	int startWeek;
	int endWeek;

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public ArrayList<LessonTime> getTimeTable() {
		return timeTable;
	}
	public void setTimeTable(ArrayList<LessonTime> timeTable) {
		this.timeTable = timeTable;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public int getCurNum() {
		return curNum;
	}

	public void setCurNum(int curNum) {
		this.curNum = curNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStartWeek() {
		return startWeek;
	}

	public void setStartWeek(int startWeek) {
		this.startWeek = startWeek;
	}

	public int getEndWeek() {
		return endWeek;
	}

	public void setEndWeek(int endWeek) {
		this.endWeek = endWeek;
	}
}

