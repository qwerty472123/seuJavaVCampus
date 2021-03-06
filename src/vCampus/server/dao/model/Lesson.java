package vCampus.server.dao.model;
import java.io.Serializable;
import java.util.ArrayList;

import vCampus.bean.LessonBean;
import vCampus.bean.LessonTime;
import vCampus.server.dao.StrtoArr;

public class Lesson implements Serializable{
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
	
	public LessonBean toBean() {
		//TO DO
		LessonBean b=new LessonBean();
		b.setID(ID);
		b.setLessonName(lessonName);
		b.setLocation(location);
		b.setTeacherId(teacherId);
		b.setMaxNum(maxNum);
		b.setCurNum(curNum);
		b.setStatus(status);
		b.setTimeTable(timeTable);
		b.setStartWeek(startWeek);
		b.setEndWeek(endWeek);
		return b;
	}
	
	public static Lesson toModel(LessonBean b) {
		//TO DO
		Lesson x=new Lesson();
		x.setID(b.getID());
		x.setLessonName(b.getLessonName());
		x.setLocation(b.getLocation());
		x.setTeacherId(b.getTeacherId());
		x.setMaxNum(b.getMaxNum());
		x.setCurNum(b.getCurNum());
		x.setStatus(b.getStatus());
		x.setTimeTable(b.getTimeTable());
		x.setStartWeek(b.getStartWeek());
		x.setEndWeek(b.getEndWeek());
		return x;
	}
	
	public static ArrayList<LessonTime> toTimeTable(Lesson c,String table) {
		ArrayList<LessonTime> ans=new ArrayList<LessonTime>();
		ArrayList<Integer> tb = StrtoArr.strtoArr(table);
		for(int i=2;i<tb.size();i+=2) {
			int day=tb.get(i)/100,
				S=tb.get(i)%100,
				T=tb.get(i+1)%100;
			LessonTime t=new LessonTime();
			t.setDay(day);
			t.setStart(S);
			t.setLessonName(c.getLessonName());
			t.setLocation(c.getLocation());
			t.setEnd(T);
			ans.add(t);
		}
		return ans;
	}
	
	
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
