package vCampus.server.dao;

import java.sql.*;
import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.Teacher;

public class TeacherDao{
	
	public static void update(Teacher s) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "UPDATE Teacher" +
	                " set pswd = ?, pname=? ,sex = ?, age=?, birthday=?, balance = ?,"
	    			+"faculty = ?, classtable = ?" 
	                +" where id = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, s.getPswd());
	        ptmt.setString(2, s.getName());
	        ptmt.setInt(3, s.getSex());
	        ptmt.setInt(4, s.getAge());
	        ptmt.setDate(5, new Date(s.getBirthday().getTime()));
	        ptmt.setInt(6, s.getBalance());
	        ptmt.setString(7, s.getFaculty());
	        ptmt.setString(8, s.getClassTable());
	        ptmt.setInt(9, s.getId());
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
	
	public static void addTeach(Teacher s) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "INSERT INTO Teacher(id, pswd, pname, sex, age, birthday,"+
	                "balance, faculty, classTable)"
	                    +"values("+"?,?,?,?,?,?,?,?,?)";
	    	ptmt = conn.prepareStatement(sql);	
	    	ptmt.setInt(1, s.getId());
	        ptmt.setString(2, s.getPswd());
	        ptmt.setString(3, s.getName());
	        ptmt.setInt(4, s.getSex());
	        ptmt.setInt(5, s.getAge());
	        ptmt.setDate(6, new Date(s.getBirthday().getTime()));
	        ptmt.setInt(7, s.getBalance());
	        ptmt.setString(8, s.getFaculty());
	        ptmt.setString(9, s.getClassTable());
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
	
	public static void deleTeach(int ID) throws SQLException{

		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "delete from Teacher where id=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, ID);
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
	
	
	public static Teacher getTeach(int ID) throws SQLException{
		Connection conn = null;
	    PreparedStatement ptmt = null;
		Teacher s = null;
		ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();	
	    	//String url = "jdbc:ucanaccess://test.accdb";
	    	//conn = DriverManager.getConnection(url);
	    	String sql = "select * from  Teacher where id=?";
	    	ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, ID);
			rs = ptmt.executeQuery();
			while(rs.next()){
				s = new Teacher();
				s.setId(ID);
				s.setPswd(rs.getString("pswd"));
				s.setName(rs.getString("pname"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));
				s.setBalance(rs.getInt("balance"));
				s.setFaculty(rs.getString("faculty"));
				s.setClassTable(rs.getString("classTable"));
			}
			return s;	    	
	    }catch(SQLException e) {
	    	Config.log(e);
	    	return s;
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
    
	public static void main(String[] args) throws SQLException{
		Teacher t = TeacherDao.getTeach(1000);
		System.out.print(t.getClassTable());
	}

}

