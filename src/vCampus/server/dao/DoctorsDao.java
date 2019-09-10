package vCampus.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.Doctor;
import vCampus.utility.Config;

//表：Doctor
//字段：int ID, String drName, String drIntro, String availableTime,
//                           (ten integers, split by ",", to show how many people the Doctor can serve on someday morning or afternoon)
//							int age, boolean gender
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
	    	Config.log(e);
	    	return "";
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
	    	Config.log(e);
	    	return "";
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
	
	public static int queryAge(int id) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT age FROM Doctor WHERE ID = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, id);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	return rs.getInt("age");
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
	
	public static boolean queryGender(int id) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT gender FROM Doctor WHERE ID = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, id);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	return rs.getBoolean("gender");
	        }else {
	        	throw new SQLException();
	        }
	    }catch(SQLException e) {
	    	Config.log(e);
	    	return false;
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
	        while (rs.next()) {
	        	list.add(rs.getInt("ID"));
	        }
	        if(list.isEmpty()) {
	        	throw new SQLException();
	        }
	        return list;
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
	
	public static void addDoctor(Doctor d) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "INSERT INTO Doctor (ID, drName, drIntro, availableTime, age, gender) VALUES(?, ?, ?, ?, ?, ?)";
	    	ptmt = conn.prepareStatement(sql);
	    	ptmt.setInt(1, d.getId());
	        ptmt.setString(2, d.getName());
	        ptmt.setString(3, d.getIntrotxt());
	        ptmt.setString(4, d.getAvailableTime());
	        ptmt.setInt(5, d.getAge());
	        ptmt.setBoolean(6, d.isGender());
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
	
	public static void updateDoctor(Doctor d) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "UPDATE Doctor SET drName = ?, drIntro = ?, availableTime = ?, age = ?, gender = ? WHERE ID = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, d.getName());
	        ptmt.setString(2, d.getIntrotxt());
	        ptmt.setString(3, d.getAvailableTime());
	        ptmt.setInt(4, d.getAge());
	        ptmt.setBoolean(5, d.isGender());
	    	ptmt.setInt(6, d.getId());
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
	public static void deleteDoctor(int userid) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "DELETE FROM Doctor WHERE ID = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, userid);
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
