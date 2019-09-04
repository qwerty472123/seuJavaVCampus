package vCampus.server.dao.model;
import java.util.ArrayList;
import vCampus.server.dao.StrtoArr;
public class Lesson {
	
	private int lessonID;
	private String lessonName;
	private String location;
	private int teachID;
	private String timeTable;   
	private int maxNum;
	private String studentList;
	
	public String getStudentList() {
		return studentList;
	}
	public void setStudentList(String studentList) {
		this.studentList = studentList;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public String getTimeTable() {
		return timeTable;
	}
	public void setTimeTable(String timeTable) {
		this.timeTable = timeTable;
	}
	public int getLessonID() {
		return lessonID;
	}
	public void setLessonID(int lessonID) {
		this.lessonID = lessonID;
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
	public int getTeachID() {
		return teachID;
	}
	public void setTeachID(int teachID) {
		this.teachID = teachID;
	}
	
	
	public ArrayList<Integer> GetTimeTable(){
		ArrayList<Integer> gs = StrtoArr.strtoArr(timeTable);
 		return gs;     
	}
	
	public ArrayList<Integer> GetStudentList(){
		ArrayList<Integer> gs = StrtoArr.strtoArr(studentList);
		return gs;
		
		//返回学生学号组成的ArrayList数组
	}
	
	
	
	
	
}
