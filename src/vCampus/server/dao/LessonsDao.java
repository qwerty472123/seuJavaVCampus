package vCampus.server.dao;
import java.sql.*;
import java.util.ArrayList;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.Lesson;

public class LessonsDao {
	
	
	public static void addRec(Lesson s){   

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "INSERT INTO LessonSheet(lessonID,lessonName,location,"
	        		+ "teachID, timeTable, maxNum, studentList)"
	                    +"values("+"?,?,?,?,?,?,?)";
	        ptmt = conn.prepareStatement(sql);
	        
	        ptmt.setInt(1, s.getLessonID());
	        ptmt.setString(2, s.getLessonName());
	        ptmt.setString(3, s.getLocation());
	        ptmt.setInt(4, s.getTeachID());
	        ptmt.setString(5, s.getTimeTable());
	        ptmt.setInt(6, s.getMaxNum());
	        ptmt.setString(7, s.getStudentList());
	        
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
	
	public static void delRec(int ID) throws SQLException{
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "delete from LessonSheet where lessonID=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, ID);
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
	
	public static Lesson getRec(int lessonID){
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
		Lesson s = null;
	    try{
	    	conn = ConnectionManager.getConnection();

			String sql = "select * from LessonSheet where lessonID=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, lessonID);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				s = new Lesson();
				s.setLessonID(rs.getInt("lessonID"));
				s.setLessonName(rs.getString("lessonName"));
				s.setLocation(rs.getString("location"));
				s.setTeachID(rs.getInt("teachID"));
				s.setTimeTable(rs.getString("timeTable"));
				s.setMaxNum(rs.getInt("maxNum"));
				s.setStudentList(rs.getString("studentList"));
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
	
	
	public static void add_Stu(int personID, int lessonID){   

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "SELECT studentList FROM LessonSheet WHERE lessonID=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, lessonID);
	        ptmt.execute();
	        rs = ptmt.executeQuery();
	        ArrayList<Integer> tmp = StrtoArr.strtoArr(rs.getString("studentList"));
	        if(!tmp.contains(personID)){
	        	String ListUpdate = rs.getString("studentList") + "," + Integer.toString(personID);
	        	sql = "UPDATE LessonSheet SET studentList = ? WHERE lessonID = ?";
	        	PreparedStatement ptmt1 = null;
	        	ptmt1 = conn.prepareStatement(sql);
	        	ptmt1.setString(1, ListUpdate);
	        	ptmt1.setInt(2, lessonID);
	        	ptmt1.execute();	
	        	ptmt1.close();
	        }
	        
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
	

	public static void dele_Stu(int personID, int lessonID){   

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "SELECT studentList FROM LessonSheet WHERE lessonID=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, lessonID);
	        ptmt.execute();
	        rs = ptmt.executeQuery();
	        ArrayList<Integer> tmp = StrtoArr.strtoArr(rs.getString("studentList"));
	        
	        if(tmp.contains(personID)){
	        	tmp.remove(tmp.indexOf(personID));
	        	String ListUpdate = StrtoArr.arrtoStr(tmp);
	        	sql = "UPDATE LessonSheet SET studentList = ? WHERE lessonID = ?";
	        	PreparedStatement ptmt1 = null;
	        	ptmt1 = conn.prepareStatement(sql);
	        	ptmt1.setString(1, ListUpdate);
	        	ptmt1.setInt(2, lessonID);
	        	ptmt1.execute();	
	        	ptmt1.close();
	        }
	        
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
	
}
