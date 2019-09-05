package vCampus.server.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.AccountKey;
import vCampus.utility.Config;

public class AccountKeyDao {


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
	
	public static String queryAuthority(int userId) {
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT authority FROM AccountKey WHERE userId=?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, userId);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	return rs.getString("authority");
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
	
	public static List<AccountKey> searchForName(String name) throws SQLException {
		Config.log("search account...");
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		List<AccountKey> list = new ArrayList<AccountKey>();
		try{
			conn = ConnectionManager.getConnection();
			String sql = "SELECT * FROM AccountKey WHERE userName LIKE ?";
			ptmt = conn.prepareStatement(sql);
			name = "%" + name + "%";
			ptmt.setString(1, name);
			rs = ptmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					System.out.println("new");
					int userId = rs.getInt("userId");
					String userName = rs.getString("userName");
					String authority = rs.getString("authority");
					AccountKey account = new AccountKey(userId, userName, authority);
					list.add(account);
				}
				return list;
			}
			else {
				throw new SQLException();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return list;
		}
		finally {
			try {
				if(ptmt!=null)ptmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			if(conn != null) ConnectionManager.close(conn);
		}
	}
	
	public static void updatePassworrd(String PsWd, int Id) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "UPDATE AccountKey" +
                    " set encryptedPwd = ?"+
                    " where userId = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, PsWd);
	        ptmt.setInt(2, Id);
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
	
	//增加用户
	public static void addAccount(int newid, String newname, String newpasswd, String newauthority) throws Exception {
		
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionManager.getConnection();
			if(conn.createStatement().executeQuery("SELECT * FROM AccountKey WHERE userId = " + newid).next()) {
				Config.log("Illegal ID!");
				throw new Exception();
			}	
			else {
				String sql = "INSERT INTO AccountKey "
						+ "(userId, userName, encryptedPwd, authority) "
						+ "VALUES(?,?,?,?) ";
				ptmt = conn.prepareStatement(sql);
				ptmt.setInt(1, newid);
				ptmt.setString(2, newname);
				ptmt.setString(3, newpasswd);
				ptmt.setString(4, newauthority);
				ptmt.execute();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ptmt!=null) ptmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			if(conn != null) ConnectionManager.close(conn);
		}
	}
	
	//删除用户
	public static void deleteAccount(int userid) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionManager.getConnection();
			String sql = "DELETE FROM AccountKey WHERE userId = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, userid);
			ptmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ptmt!=null) ptmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			if(conn != null) ConnectionManager.close(conn);
		}
	}
	
	//更新权限
	public static void updateAuthority(int id, String newauth) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionManager.getConnection();
			String sql = "UPDATE AccountKey SET authority = ? WHERE userId = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, newauth);
			ptmt.setInt(2, id);
			ptmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ptmt != null) ptmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			if(conn != null) ConnectionManager.close(conn);
		}
	}
	
	//更新用户名
	public static void updateName(String name, String newname) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionManager.getConnection();
			String sql = "UPDATE AccountKey SET userName = ? WHERE userName = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, newname);
			ptmt.setString(2, name);
			ptmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ptmt != null) ptmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			if(conn != null) ConnectionManager.close(conn);
		}
	}
	
	
}
