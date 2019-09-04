package vCampus.server.dao.model;

import java.util.ArrayList;

import vCampus.server.dao.StrtoArr;

public class Doctor {
	private int ID;
	private String name;
	private String introtxt;
	private String timetable;
	
	public Doctor(int iD, String name, String introtxt, String timetable) {
		super();
		ID = iD;
		this.name = name;
		this.introtxt = introtxt;
		this.timetable = timetable;
	}
	
	public Doctor() {
		// TODO �Զ����ɵĹ��캯�����
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	public String getTimetable() {
		return timetable;
	}
	public void setTimetable(String timetable) {
		this.timetable = timetable;
	}
	
	public ArrayList<Integer> GetTimetable() {
		ArrayList<Integer> gs = StrtoArr.strtoArr(timetable);
		return gs;
	}
}
