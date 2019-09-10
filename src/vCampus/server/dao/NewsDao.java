package vCampus.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
	        rs = ptmt.getGeneratedKeys();

	        if (!rs.next()) throw new SQLException();
	        id = rs.getInt(1);
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
	    	Config.log(e);
	    	return gs;
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
	    
	    if (gs.size()<=100) return gs;
	    else {
    		List<News> limited_gs = new ArrayList<>();
	    	for (int i=gs.size()-100; i<gs.size(); ++i) {
	    		limited_gs.add(gs.get(i));
	    	}
	    	return limited_gs;
	    }
	}
	
	public static List<News> queryNewsByType(String type){
	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
		News s = null;
		List<News> gs = new ArrayList<>();
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "select * from News where type = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, type);
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
	    	Config.log(e);
	    	return gs;
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
	    
	    if (gs.size()<=100) return gs;
	    else {
    		List<News> limited_gs = new ArrayList<>();
	    	for (int i=gs.size()-100; i<gs.size(); ++i) {
	    		limited_gs.add(gs.get(i));
	    	}
	    	return limited_gs;
	    }
	}
	
	public static void modifyNewsType(int id, String type) {
		
		Connection conn = null;
		PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "UPDATE News set type = ? where ID = ?";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, type);
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
	
}
