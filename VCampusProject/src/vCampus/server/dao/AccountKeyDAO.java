package vCampus.server.dao;

import java.sql.*;

import vCampus.server.dao.driver.ConnectionManager;

public class AccountKeyDAO {


	//-1 : not match
	public static int queryUserId(String name, String pwd) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT userId FROM AccountKey WHERE userName=? AND encryptedPwd=?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, name);
	        ptmt.setString(2, pwd);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	return rs.getInt("userId");
	        }else {
	        	throw new SQLException();
	        }
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    	return -1;
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
	
	public static String queryPassword(int userId) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT encryptedPwd FROM AccountKey WHERE userId=?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, userId);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	return rs.getString("encryptedPwd");
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
	
	//test
	public static void main(String[] args) {
		System.out.println(queryUserId("testtimi", "123456"));
	}
	
}
