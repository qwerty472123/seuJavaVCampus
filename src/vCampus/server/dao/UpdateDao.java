package vCampus.server.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.Student;

public class UpdateDao {
	public static void update(Student s) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "UPDATE Update" +
	                " set pswd = ?, pname=? ,sex = ?, age=?, birthday=?, balance = ?,"+
	        		" grade = ?, stuclass = ?, faculty = ?, GPA = ?, classTable = ?, email=?, phone =?,qq=?" +
	                " where id = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, s.getPswd());
	        ptmt.setString(2, s.getName());
	        ptmt.setInt(3, s.getSex());
	        ptmt.setInt(4, s.getAge());
	        ptmt.setDate(5, new Date(s.getBirthday().getTime()));
	        ptmt.setInt(6, s.getBalance());
	        ptmt.setInt(7, s.getGrade());
	        ptmt.setInt(8, s.getStuclass());
	        ptmt.setString(9, s.getFaculty());
	        ptmt.setFloat(10, s.getGPA());
	        ptmt.setString(11, s.getTimeTable());
	        ptmt.setString(12, s.getEmail());
	        ptmt.setString(13, s.getPhone());
	        ptmt.setString(14, s.getQq());
	        ptmt.setInt(15, s.getId());
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
	
	public static void addStu(Student s) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	if(getStu(s.getId())!= null){
	    		update(s);
	    		ChangeStatus("申请修改",s.getId());
	    		return;
	    	}
	    	String sql = "INSERT INTO Update(id, pswd, pname, sex, age, birthday,"+
	                "balance, grade, stuclass, faculty, GPA, classTable, email, phone, qq, status)"
	                    +"values("+"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    	ptmt = conn.prepareStatement(sql);
	    	ptmt.setInt(1, s.getId());
	        ptmt.setString(2, s.getPswd());
	        ptmt.setString(3, s.getName());
	        ptmt.setInt(4, s.getSex());
	        ptmt.setInt(5, s.getAge());
	        ptmt.setDate(6, new Date(s.getBirthday().getTime()));
	        ptmt.setInt(7, s.getBalance());
	        ptmt.setInt(8, s.getGrade());
	        ptmt.setInt(9, s.getStuclass());
	        ptmt.setString(10, s.getFaculty());
	        ptmt.setFloat(11, s.getGPA());
	        ptmt.setString(12, s.getTimeTable());
	        ptmt.setString(13, s.getEmail());
	        ptmt.setString(14, s.getPhone());
	        ptmt.setString(15, s.getQq());
	        ptmt.setString(16,"申请修改");
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
	
	public static void deleStu(int ID) throws SQLException{

		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "delete from Update where id=?";
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
	
	
	public static Student getStu(int ID) throws SQLException{
		Connection conn = null;
	    PreparedStatement ptmt = null;
		Student s = new Student();
		ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();	    	
	    	String sql = "select * from  Update where id=?";
	    	ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, ID);
			rs = ptmt.executeQuery();
			while(rs.next()){			
				s.setId(ID);
				s.setPswd(rs.getString("pswd"));
				s.setName(rs.getString("pname"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));
				s.setBalance(rs.getInt("balance"));
				s.setGrade(rs.getInt("grade"));
				s.setStuclass(rs.getInt("stuclass"));
				s.setFaculty(rs.getString("faculty"));
				s.setGPA(rs.getFloat("GPA"));
				s.setTimeTable(rs.getString("classTable"));
				s.setEmail(rs.getString("email"));
				s.setPhone(rs.getString("phone"));
				s.setQq(rs.getString("qq"));
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
	

	public static List<Student> queryAllUpdates() throws SQLException{
		Connection conn = null;
	    PreparedStatement ptmt = null;
		Student s = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList<Student>();
	    try{
	    	conn = ConnectionManager.getConnection();	    	
	    	String sql = "select * from  Update WHERE status = ?";    	
	    	ptmt = conn.prepareStatement(sql);
	    	ptmt.setString(1, "申请修改");
			rs = ptmt.executeQuery();
			while(rs.next()){
				s = new Student();
				s.setId(rs.getInt("id"));
				s.setPswd(rs.getString("pswd"));
				s.setName(rs.getString("pname"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));
				s.setBalance(rs.getInt("balance"));
				s.setGrade(rs.getInt("grade"));
				s.setStuclass(rs.getInt("stuclass"));
				s.setFaculty(rs.getString("faculty"));
				s.setGPA(rs.getFloat("GPA"));
				s.setTimeTable(rs.getString("classTable"));
				s.setEmail(rs.getString("email"));
				s.setPhone(rs.getString("phone"));
				s.setQq(rs.getString("qq"));
				list.add(s);
			}
			return list;	    	
	    }catch(SQLException e) {
	    	Config.log(e);
	    	return list;
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
	
	public static void ChangeStatus(String status,int id) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "UPDATE Update" +
	                " set status = ?"+
	                " where id = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, status);
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
	
	public static String getStatus(int ID) throws SQLException{
		Connection conn = null;
	    PreparedStatement ptmt = null;
		ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();	    	
	    	String sql = "select * from  Update WHERE id = ?";
	    	ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, ID);
			rs = ptmt.executeQuery();	
			rs.next();
		    return rs.getString("status");
				    	
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
	
	
}

