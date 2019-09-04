package vCampus.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.Doctor;

public class DoctorsDao {

	public static void update(Doctor doc) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "UPDATE DoctorSheet" +
	                " set pname=? ,introtxt = ?, timetable=?" +
	                " where ID = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, doc.getName());
	        ptmt.setString(2, doc.getIntrotxt());
	        ptmt.setString(3, doc.getTimetable());
	        ptmt.setInt(4, doc.getID());
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
	
	public void addDoc(Doctor doc) {
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "INSERT INTO DoctorSheet(ID,pname,introtxt,timetable"
	                    +"values("+"?,?,?,?)";
	        ptmt = conn.prepareStatement(sql);
	        
	        ptmt.setInt(1, doc.getID());
	        ptmt.setString(2, doc.getName());
	        ptmt.setString(3, doc.getIntrotxt());
	        ptmt.setString(4, doc.getTimetable());
	        
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
	
	public void delDoc(int ID) throws SQLException{
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "DELETE FROM DoctorSheet WHERE ID=?";
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
	
	public Doctor getRec(int ID){
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
		Doctor s = null;
	    try{
	    	conn = ConnectionManager.getConnection();

			String sql = "select * from DoctorSheet where ID=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, ID);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				s = new Doctor();
				s.setID(rs.getInt("ID"));
				s.setName(rs.getString("pname"));
				s.setIntrotxt(rs.getString("introtxt"));
				s.setTimetable(rs.getString("timetable"));
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
}
