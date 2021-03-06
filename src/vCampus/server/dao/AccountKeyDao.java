package vCampus.server.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.AccountKeyDao;
import vCampus.utility.Config;
import vCampus.server.dao.model.AccountKey;
import vCampus.server.dao.model.Doctor;
import vCampus.server.dao.model.Student;
import vCampus.server.dao.model.Teacher;
public class AccountKeyDao {

	public static int queryUserId(String name) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT userId FROM AccountKey WHERE userName=?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, name);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	return rs.getInt("userId");
	        }else {
	        	throw new SQLException();
	        }
	    }catch(SQLException e) {
	    	Config.log(e);
	    	return -1;
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
	    	Config.log(e);
	    	return -1;
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

	public static String queryUserName(int userId) {
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "SELECT userName FROM AccountKey WHERE userId=?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, userId);
	        rs = ptmt.executeQuery();
	        if (rs.next()) {
	        	return rs.getString("userName");
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
					Config.log("new");
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
			Config.log(e);
			return list;
		}
		finally {
			try {
				if(ptmt!=null)ptmt.close();
			}catch(SQLException e) {
				Config.log(e);
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
	
	//增加用户
	public static void addAccount(AccountKey key) throws Exception {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionManager.getConnection();
			if(conn.createStatement().executeQuery("SELECT * FROM AccountKey WHERE userId = " + key.getUserId()).next()) {
				Config.log("Illegal ID!");
				throw new Exception();
			}	
			else {
				String sql = "INSERT INTO AccountKey "
						+ "(userId, userName, encryptedPwd, authority) "
						+ "VALUES(?,?,?,?) ";
				ptmt = conn.prepareStatement(sql);
				ptmt.setInt(1, key.getUserId());
				ptmt.setString(2, key.getUserName());
				ptmt.setString(3, key.getPassword());
				ptmt.setString(4, key.getAuthority());
				ptmt.execute();
				switch(key.getAuthority()){
				case "student":{
					Student s = new Student();
					s.init();
					s.setId(key.getUserId());
					StudentDao.addStu(s);
				}
				case "teacher":{
					Teacher t = new Teacher();
					t.init();
					t.setId(key.getUserId());
					TeacherDao.addTeach(t);
				}
				case "doctor":{
					Doctor d = new Doctor();
					d.init();
					d.setId(key.getUserId());
					DoctorsDao.addDoctor(d);
				}
				}
			}
		}catch(SQLException e) {
			Config.log(e);
		}
		finally {
			try {
				if(ptmt!=null) ptmt.close();
			}catch(SQLException e) {
				Config.log(e);
			}
			if(conn != null) ConnectionManager.close(conn);
		}		
	}
	
	
	public static Boolean updateAccountKey(AccountKey key){
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionManager.getConnection();
			int userId = key.getUserId();
			String userName = key.getUserName();
			ArrayList<AccountKey> list = queryAccount();
			int count = 0;
			for(AccountKey k: list){
				if(userName == k.getUserName())count++;
				if(count >= 2){
                    Config.log("用户名重复！");
					return false;
				}
			}
			String sql = "UPDATE AccountKey SET authority = ?,userName=?,encryptedPwd=?  WHERE userId = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, key.getAuthority());
			ptmt.setString(2, key.getUserName());
			ptmt.setString(3, key.getPassword());
			ptmt.setInt(4, userId);
			ptmt.execute();
			switch(key.getAuthority()){
			case "student":{
				Student s = new Student();
				s.init();
				s.setId(key.getUserId());
				StudentDao.update(s);
			}
			case "teacher":{
				Teacher t = new Teacher();
				t.init();
				t.setId(key.getUserId());
				TeacherDao.update(t);
			}
			case "doctor":{
				Doctor d = new Doctor();
				d.init();
				d.setId(key.getUserId());
				DoctorsDao.updateDoctor(d);
			}
			//add case:
			}
			return true;
		}catch(SQLException e) {
			Config.log(e);
			return false;
		}
		finally {
			try {
				if(ptmt != null) ptmt.close();
			}catch(SQLException e) {
				Config.log(e);
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
			String authority = queryAuthority(userid);
			switch(authority) {
			case "student":{
				StudentDao.deleStu(userid);
			}
			case "teacher":{
				TeacherDao.deleTeach(userid);
			}
			case "doctor":{
				DoctorsDao.deleteDoctor(userid);
			}
			}
			ptmt.execute();
		}catch(SQLException e) {
			Config.log(e);
		}
		finally {
			try {
				if(ptmt!=null) ptmt.close();
			}catch(SQLException e) {
				Config.log(e);
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
			Config.log(e);
		}
		finally {
			try {
				if(ptmt != null) ptmt.close();
			}catch(SQLException e) {
				Config.log(e);
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
			Config.log(e);
		}
		finally {
			try {
				if(ptmt != null) ptmt.close();
			}catch(SQLException e) {
				Config.log(e);
			}
			if(conn != null) ConnectionManager.close(conn);
		}
	}
	
	static ArrayList<AccountKey> queryAccount(){
		ArrayList<AccountKey> list = new ArrayList<AccountKey>();
		Connection conn = null;
		PreparedStatement ptmt = null;
		AccountKey tmp = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			String sql = "SELECT * FROM AccountKey";
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				tmp = new AccountKey();
				tmp.setUserId(rs.getInt("userId"));
				tmp.setAuthority(rs.getString("authority"));
				tmp.setUserName(rs.getString("userName"));
				tmp.setPassword(rs.getString("encryptedPwd"));
				list.add(tmp);
			}
			return list;
		}catch(SQLException e) {
			Config.log(e);
			return list;
		}
		finally {
			try {
				if(ptmt != null) ptmt.close();
			}catch(SQLException e) {
				Config.log(e);
			}
			if(conn != null) ConnectionManager.close(conn);
		}		
		
	}	
	
	static public void main(String[] args) {
		AccountKey a = new AccountKey();
		a.setUserId(10000);
		a.setUserName("Dr.Z");
		a.setPassword("zzz");
		a.setAuthority("doctor");
		try {
			addAccount(a);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			Config.log(e);
		}
	}
}
