package vCampus.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.BankAccount;
import vCampus.utility.Config;

public class BankAccountDao {

	public static BankAccount queryAccount(int userId) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT * FROM BankAccount WHERE userId=?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, userId);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	BankAccount a = new BankAccount();
	        	a.setUserId(userId);
	        	a.setAccountName(rs.getString("accountName"));
	        	a.setBankPwd(rs.getString("bankPwd"));
	        	a.setBalance(rs.getInt("balance"));        	
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
	

	public static int queryBalance(int userId) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT balance FROM BankAccount WHERE userId=?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, userId);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	return rs.getInt("balance");
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
		
	public static int addBalance(int userId, int num) {		
		Connection conn = null;
		PreparedStatement ptmt = null;
	    int lns = 0;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "UPDATE BankAccount set balance = balance + ? where userId = ? AND balance+?>=0";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, num);
	        ptmt.setInt(2, userId);
	        ptmt.setInt(3, num);
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
	
}
