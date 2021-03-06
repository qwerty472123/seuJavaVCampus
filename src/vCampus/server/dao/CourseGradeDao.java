package vCampus.server.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.AptRec;
import vCampus.server.dao.model.CourseGrade;
import vCampus.utility.Config;

public class CourseGradeDao {

	public static CourseGrade queryAccount(int stuId, int courseId) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT * FROM CourseGrade WHERE stuId=? and courseId=?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, stuId);
	        ptmt.setInt(2, courseId);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	CourseGrade a = new CourseGrade();
	        	a.setCourseId(rs.getInt("courseId"));
	        	a.setStuId(rs.getInt("stuId"));
	        	a.setGrade(rs.getInt("grade"));
	        	return a;
	        }else {
	        	throw new SQLException();
	        }
	    }catch(SQLException e) {
	    	Config.log(e);
	    	return null;
	    }finally {
	    	try{
	    		if (ptmt!=null) ptmt.close();
	    	}catch(SQLException e) {
	    		Config.log(e);
	    	}
	    	try{
	    		if (rs!=null) rs.close();
	    	}catch(SQLException e) {
	    		Config.log(e);
	    	}
	    	if (conn!=null) ConnectionManager.close(conn);
	    }	
	}
	

	public static int queryGrade(int stuId, int courseId) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT grade FROM CourseGrade WHERE stuId=? and courseId=?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, stuId);
	        ptmt.setInt(2, courseId);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	return rs.getInt("grade");
	        }else {
	        	throw new SQLException();
	        }
	    }catch(SQLException e) {
	    	Config.log(e);
	    	return 0;
	    }finally {
	    	try{
	    		if (ptmt!=null) ptmt.close();
	    	}catch(SQLException e) {
	    		Config.log(e);
	    	}
	    	try{
	    		if (rs!=null) rs.close();
	    	}catch(SQLException e) {
	    		Config.log(e);
	    	}
	    	if (conn!=null) ConnectionManager.close(conn);
	    }	
        
	}
		
	public static int updateGrade(int stuId, int courseId, int num) {		
		Connection conn = null;
		PreparedStatement ptmt = null;
	    int lns = 0;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "UPDATE CourseGrade set grade = ? where stuId = ? AND courseId = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, num);
	        ptmt.setInt(2, stuId);
	        ptmt.setInt(3, courseId);
	        ptmt.execute();
	        lns = ptmt.getUpdateCount();
	    }catch(SQLException e) {
	    	Config.log(e);
	    }finally {
	    	try{
	    		if (ptmt!=null) ptmt.close();
	    	}catch(SQLException e) {
	    		Config.log(e);
	    	}
	    	if (conn!=null) ConnectionManager.close(conn);
	    }
	    return lns;
	}
	
	public static void addCourse(int stuId, int courseId) {
	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	
	    	String sql = "INSERT INTO CourseGrade (stuId, courseId, grade) "
	    			+"VALUES(?, ?, 0)";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, stuId);
	        ptmt.setInt(2, courseId);
	        ptmt.execute();
	    }catch(SQLException e) {
	    	Config.log(e);
	    }finally {
	    	try{
	    		if (ptmt!=null) ptmt.close();
	    	}catch(SQLException e) {
	    		Config.log(e);
	    	}
	    	if (conn!=null) ConnectionManager.close(conn);
	    }        
	}
	
	public static void deleCourse(int stuId, int courseId) throws SQLException{

		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "delete from CourseGrade where stuId=? and courseId=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, stuId);
	        ptmt.setInt(2, courseId);
	        ptmt.execute();	    	
	    }catch(SQLException e) {
	    	Config.log(e);
	    }finally {
	    	try{
	    		if (ptmt!=null) ptmt.close();
	    	}catch(SQLException e) {
	    		Config.log(e);
	    	}
	    	if (conn!=null) ConnectionManager.close(conn);
	    }
			
	}
	
	
}
