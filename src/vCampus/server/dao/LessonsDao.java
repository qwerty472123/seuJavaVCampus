package vCampus.server.dao;
import java.sql.*;
import java.util.ArrayList;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.Lesson;
import vCampus.server.dao.model.LessonSelectRec;
import vCampus.utility.Config;

public class LessonsDao {
	public static void addLesson(Lesson c) {
		
	}
	public static void removeLesson(Lesson c) {
		
	}
	public static void updateLesson(Lesson c) {
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
			String sql = "UPDATE Lesson SET lessonName = ?,location = ?"
					+ ",teacherId=?,maxNum=?,curNum=? WHERE ID = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, c.getLessonName());
			ptmt.setString(2, c.getLocation());
			ptmt.setInt(3, c.getTeacherId());
			ptmt.setInt(4, c.getMaxNum());
			ptmt.setInt(5, c.getCurNum());
			ptmt.setInt(6, c.getID());
			ptmt.execute();
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }finally {
	    	try{
	    		if (ptmt!=null) ptmt.close();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	if (conn!=null) ConnectionManager.close(conn);
	    }
	}
	public static ArrayList<Lesson> queryAllLessons(){
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    ArrayList<Lesson> res=new ArrayList<Lesson>();
		Lesson s = null;
	    try{
	    	conn = ConnectionManager.getConnection();
			String sql = "select * from Lesson";
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				s = new Lesson();
				s.setID(rs.getInt("ID"));
				s.setLessonName(rs.getString("lessonName"));
				s.setLocation(rs.getString("location"));
				s.setTeacherId(rs.getInt("teacherId"));
				String tb=rs.getString("timeTable");
				s.setTimeTable(Lesson.toTimeTable(s, rs.getString("timeTable")));
				ArrayList<Integer> arr=StrtoArr.strtoArr(rs.getString("timeTable"));
				s.setStartWeek(arr.get(0));
				//Config.log("xxxxxxxxxxxxxxxxxx");
				//Config.log(arr.get(0)+"~"+arr.get(1));
				s.setEndWeek(arr.get(1));
				s.setMaxNum(rs.getInt("maxNum"));
				s.setCurNum(rs.getInt("curNum"));
				res.add(s);
			}
			return res;
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    	return res;
	    }finally {
	    	try{
	    		if (ptmt!=null) ptmt.close();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	try{
	    		if (rs!=null) rs.close();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	if (conn!=null) ConnectionManager.close(conn);
	    }
	}
	
	
	public static ArrayList<LessonSelectRec> querySelectRec4Stu(int studentId){
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    ArrayList<LessonSelectRec> res=new ArrayList<LessonSelectRec>();
	    LessonSelectRec s = null;
	    try{
	    	conn = ConnectionManager.getConnection();
			String sql = "select * from LessonSelection where userId=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, studentId);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				s = new LessonSelectRec();
				s.setID(rs.getInt("ID"));
				s.setUserId(rs.getInt("userId"));
				s.setLessonId(rs.getInt("lessonId"));
				s.setSelectTime(rs.getTimestamp("selectTime"));
				res.add(s);
			}
			return res;
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    	return res;
	    }finally {
	    	try{
	    		if (ptmt!=null) ptmt.close();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	try{
	    		if (rs!=null) rs.close();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	if (conn!=null) ConnectionManager.close(conn);
	    }
	}
	
	public static Lesson queryLesson(int ID) {
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
		Lesson s = null;
	    try{
	    	conn = ConnectionManager.getConnection();
			String sql = "select * from Lesson where ID=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, ID);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				s = new Lesson();
				s.setID(rs.getInt("ID"));
				s.setLessonName(rs.getString("lessonName"));
				s.setLocation(rs.getString("location"));
				s.setTeacherId(rs.getInt("teacherId"));
				String tb=rs.getString("timeTable");
				s.setTimeTable(Lesson.toTimeTable(s, rs.getString("timeTable")));
				s.setMaxNum(rs.getInt("maxNum"));
				s.setCurNum(rs.getInt("curNum"));
				s.setStartWeek(StrtoArr.strtoArr(rs.getString("timeTable")).get(0));
				s.setEndWeek(StrtoArr.strtoArr(rs.getString("timeTable")).get(1));
			}
			return s;
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    	return s;
	    }finally {
	    	try{
	    		if (ptmt!=null) ptmt.close();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	try{
	    		if (rs!=null) rs.close();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	if (conn!=null) ConnectionManager.close(conn);
	    }
	}
	
	public static ArrayList<Lesson> queryLesson4Stu(int studentId){
		ArrayList<Lesson> ans=new ArrayList<Lesson>();
		for(LessonSelectRec rc:querySelectRec4Stu(studentId)) {
			ans.add(queryLesson(rc.getLessonId()));
			//Config.log("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			//Config.log(""+rc.getID());
		}
		return ans;
	}
	public static ArrayList<Lesson> queryLesson4Tea(int teacherId){
		return null;
	}
	
	public static ArrayList<Integer> queryStudentIds4Lesson(Lesson c){
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    ArrayList<Integer> res=new ArrayList<Integer>();
	    try{
	    	conn = ConnectionManager.getConnection();
			String sql = "select * from LessonSelection where lessonId=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, c.getID());
			rs = ptmt.executeQuery();
			while(rs.next()){
				res.add(rs.getInt("userId"));
			}
			return res;
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    	return res;
	    }finally {
	    	try{
	    		if (ptmt!=null) ptmt.close();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	try{
	    		if (rs!=null) rs.close();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	if (conn!=null) ConnectionManager.close(conn);
	    }
	}
	
	
	
	/**
	 * 注意 不加ID字段
	 * @param c
	 * @return
	 */
	public static boolean addLessonSelectRec(LessonSelectRec rc) {
		synchronized(LessonsDao.class){
			Lesson c=queryLesson(rc.getLessonId());
			if(c.getCurNum()>=c.getMaxNum())return false;
			Connection conn = null;
		    PreparedStatement ptmt = null;
		    try{
		    	conn = ConnectionManager.getConnection();
		        String sql = "INSERT INTO LessonSelection(userId,lessonId,selectTime) "
		                    +" values(?,?,?)";
		        ptmt = conn.prepareStatement(sql);
		        ptmt.setInt(1, rc.getUserId());
		        ptmt.setInt(2, rc.getLessonId());
		        ptmt.setTimestamp(3, new Timestamp(rc.getSelectTime().getTime()));
		        ptmt.execute();
		        c.setCurNum(c.getCurNum()+1);
		        updateLesson(c);
		        return true;
		    }catch(SQLException e) {
		    	e.printStackTrace();
		    }finally {
		    	try{
		    		if (ptmt!=null) ptmt.close();
		    	}catch(SQLException e) {
		    		e.printStackTrace();
		    	}
		    	if (conn!=null) ConnectionManager.close(conn);
		    }
		    return false;
		}
	}
	public static void removeLessonSelectRec(int userId,int lessonId) {
		Lesson c=queryLesson(lessonId);
		
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "delete from LessonSelection where userId=? AND lessonId=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, userId);
	        ptmt.setInt(2, lessonId);
	        ptmt.execute();
	        c.setCurNum(c.getCurNum()-1);
	        updateLesson(c);
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }finally {
	    	try{
	    		if (ptmt!=null) ptmt.close();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	if (conn!=null) ConnectionManager.close(conn);
	    }
	}
	public static boolean hasLessonSelectRec(int userId,int lessonId) {
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "select * from LessonSelection where userId=? AND lessonId=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, userId);
	        ptmt.setInt(2, lessonId);
	        rs = ptmt.executeQuery();
	        boolean empty=true;
	        while(rs.next())empty=false;
	        return !empty;
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }finally {
	    	try{
	    		if (ptmt!=null) ptmt.close();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	if (conn!=null) ConnectionManager.close(conn);
	    }
		return false;
	}
	
}
