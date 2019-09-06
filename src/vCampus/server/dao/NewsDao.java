package vCampus.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.News;
import vCampus.utility.Config;

public class NewsDao {

	public static void add(News s){

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    int id = -1;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "INSERT INTO News (title, type, date, content, source) values(?,?,?,?,?)";
	        ptmt = conn.prepareStatement(sql);//, PreparedStatement.RETURN_GENERATED_KEYS);
	        
	        ptmt.setString(1, s.getTitle());
	        ptmt.setString(2, s.getType());
	        ptmt.setTimestamp(3, s.getDate());
	        ptmt.setString(4, s.getContent());
	        ptmt.setString(5, s.getSource());
	        
	        ptmt.executeUpdate();
	        //rs = ptmt.getGeneratedKeys();

	        //if (!rs.next()) throw new SQLException();
	        //id = rs.getInt(1);
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
	    s.setId(id);
		
	}
	
	public static List<News> queryNewsAfter(Timestamp date){
	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
		News s = null;
		List<News> gs = new ArrayList<>();
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "select * from News where date >= ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setTimestamp(1, date);
			rs = ptmt.executeQuery();			
			while(rs.next()){
				s = new News();
				s.setId(rs.getInt("id"));
				s.setTitle(rs.getString("title"));
				s.setType(rs.getString("type"));
				s.setDate(rs.getTimestamp("date"));
				s.setContent(rs.getString("content"));
				s.setSource(rs.getString("source"));
				gs.add(s);
			}
			//return gs;
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    	return gs;
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
	    
	    if (gs.size()<=100) return gs;
	    else {
    		List<News> limited_gs = new ArrayList<>();
	    	for (int i=gs.size()-100; i<gs.size(); ++i) {
	    		limited_gs.add(gs.get(i));
	    	}
	    	return limited_gs;
	    }
	}
	
	public static void main(String[] args) {
		Config.init("server");
		
		Date firstDate = new Date();
		
		News lr = new News();
		lr.setId(999);
		lr.setTitle("");
		lr.setType("std");
		
		Date secondDate = new Date();
		
		lr.setDate(new Timestamp(secondDate.getTime()));
		lr.setContent("ohohoh");
		lr.setSource("klh");
		
		add(lr);
		
		Config.log(firstDate + "    " + secondDate
				);
		Config.log(" = " + queryNewsAfter(new Timestamp(firstDate.getTime())).size());
	}
	
	
}
