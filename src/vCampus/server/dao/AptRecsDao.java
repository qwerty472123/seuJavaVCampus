package vCampus.server.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.AptRec;

//表：AptRec
//字段：id, personID, doctorID, aptday, remark（备注信息）, operTime, done
public class AptRecsDao {
	   
	public static void addApt(AptRec rec) {
	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	
	    	String sql = "INSERT INTO AptRec (personID, doctorID, aptday, remark, operTime, done) "
	    			+"VALUES(?, ?, ?, ?, ?, false)";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, rec.getPersonID());
	        ptmt.setInt(2, rec.getDoctorID());
	        ptmt.setDate(3, rec.getAptday());
	        ptmt.setString(4, rec.getRemark());
	        ptmt.setDate(5, new Date(rec.getOperTime().getTime()));
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
	
	public static List<AptRec> queryAptByPerson(int personid){
		List<AptRec> list = new ArrayList<AptRec>();
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "select * from AptRec where personID = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, personid);
			rs = ptmt.executeQuery();
			while(rs.next()){
				AptRec rc = new AptRec();
				rc.setId(rs.getInt("id"));
				rc.setPersonID(rs.getInt("personID"));
				rc.setDoctorID(rs.getInt("doctorID"));
				rc.setAptday(new Date(rs.getTimestamp("aptday").getTime()));
				rc.setRemark(rs.getString("remark"));
				rc.setOperTime(new Date(rs.getTimestamp("operTime").getTime()));
				rc.setDone(rs.getBoolean("done"));
				list.add(rc);
			}
			return list;
	    }catch(SQLException e) {
	    	e.printStackTrace();
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
	    return list;
	}
	
	public static List<AptRec> queryAptByDoctor(int doctorid){
		List<AptRec> list = new ArrayList<AptRec>();
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "select * from AptRec where doctorID = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, doctorid);
			rs = ptmt.executeQuery();
			while(rs.next()){
				AptRec rc = new AptRec();
				rc.setId(rs.getInt("id"));
				rc.setPersonID(rs.getInt("personID"));
				rc.setDoctorID(rs.getInt("doctorID"));
				rc.setAptday(new Date(rs.getTimestamp("aptday").getTime()));
				rc.setRemark(rs.getString("remark"));
				rc.setOperTime(new Date(rs.getTimestamp("operTime").getTime()));
				rc.setDone(rs.getBoolean("done"));
				list.add(rc);
			}
			return list;
	    }catch(SQLException e) {
	    	e.printStackTrace();
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
	    return list;
	}
	
	public static List<AptRec> queryAptByDoctorAndTime(int doctorid, Date aptday){
		List<AptRec> list = new ArrayList<AptRec>();
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "select * from AptRec where doctorID = ? and aptday = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, doctorid);
			ptmt.setDate(2, aptday);
			rs = ptmt.executeQuery();
			while(rs.next()){
				AptRec rc = new AptRec();
				rc.setId(rs.getInt("id"));
				rc.setPersonID(rs.getInt("personID"));
				rc.setDoctorID(rs.getInt("doctorID"));
				rc.setAptday(new Date(rs.getTimestamp("aptday").getTime()));
				rc.setRemark(rs.getString("remark"));
				rc.setOperTime(new Date(rs.getTimestamp("operTime").getTime()));
				rc.setDone(rs.getBoolean("done"));
				list.add(rc);
			}
			return list;
	    }catch(SQLException e) {
	    	e.printStackTrace();
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
	    return list;
	}
	
	public static void setDone(int aptid, boolean done){
		List<AptRec> list = new ArrayList<AptRec>();
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "UPDATE AptRec SET done = ? WHERE id = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setBoolean(1, done);
			ptmt.setInt(2, aptid);
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
}
