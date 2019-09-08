package vCampus.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vCampus.server.dao.driver.ConnectionManager;

//表：Doctor
//字段：int ID, String drName, String drIntro, String availableTime
//                           (ten integers, split by ",", to show how many people the Doctor can serve on someday morning or afternoon)
public class DoctorsDao {

	public static String queryIntroTxt(int id) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT drIntro FROM Doctor WHERE ID = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, id);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	return rs.getString("drIntro");
	        }else {
	        	throw new SQLException();
	        }
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    	return "";
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
	
	public static String queryName(int id) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT drName FROM Doctor WHERE ID = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, id);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	return rs.getString("drName");
	        }else {
	        	throw new SQLException();
	        }
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    	return "";
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
	
	public static List<Integer> queryAvailableTime(int id) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT availableTime FROM Doctor WHERE ID = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, id);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	String timestr = rs.getString("availableTime");
	        	List<Integer> timelist = StrtoArr.strtoArr(timestr);
	        	return timelist;
	        }else {
	        	throw new SQLException();
	        }
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    	return null;
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
	
	public static List<Integer> allId() {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    List<Integer> list = new ArrayList<Integer>();
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT ID FROM Doctor";
	    	ptmt = conn.prepareStatement(sql);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	list.add(rs.getInt("ID"));
	        	return list;
	        }else {
	        	throw new SQLException();
	        }
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    	return null;
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
	
	public static void updateAvailabelTime(int id, ArrayList<Integer> timelist) throws Exception {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	if(timelist.size() != 10) {
	    		throw new Exception();
	    	}
	    	String timestr = StrtoArr.arrtoStr(timelist);
	    	String sql = "UPDATE Doctor SET availableTime = ? WHERE ID = ?";
	    	ptmt = conn.prepareStatement(sql);
	    	ptmt.setString(1, timestr);
	        ptmt.setInt(2, id);
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
