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
import vCampus.server.dao.model.LetterRecord;
import vCampus.utility.Config;

public class LetterRecordDao {

	public static void addLetter(LetterRecord s){

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    int id = -1;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "INSERT INTO LetterRecord (sender, receiver, date, content, read) values(?,?,?,?,?)";
	        ptmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	       
	        ptmt.setInt(1, s.getSender());
	        ptmt.setInt(2, s.getReceiver());
	        ptmt.setTimestamp(3, s.getDate());
	        ptmt.setString(4, s.getContent());
	        ptmt.setBoolean(5, s.isRead());
	        
	        ptmt.executeUpdate();
	        rs = ptmt.getGeneratedKeys();

	        if (!rs.next()) throw new SQLException();
	        id = rs.getInt(1);
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
	
	public static List<LetterRecord> queryLetter(int receiver){
		return queryLetterExecute("select * from LetterRecord where receiver=?", 1, receiver, -1);
	}
	
	public static List<LetterRecord> queryLetter(int receiver, int sender){
		return queryLetterExecute("select * from LetterRecord where receiver=? and sender=?", 2, receiver, sender);
		
	}
	
	public static List<LetterRecord> queryLetterExecute(String sql, int cnt, int receiver, int sender){
	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
		LetterRecord s = null;
		List<LetterRecord> gs = new ArrayList<>();
	    try{
	    	conn = ConnectionManager.getConnection();
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, receiver);
			if (cnt>=2) {
				ptmt.setInt(2, sender);
			}
			rs = ptmt.executeQuery();			
			while(rs.next()){
				s = new LetterRecord();
				s.setId(rs.getInt("id"));
				s.setSender(rs.getInt("sender"));
				s.setReceiver(rs.getInt("receiver"));
				s.setDate(rs.getTimestamp("date"));
				s.setContent(rs.getString("content"));
				s.setRead(rs.getBoolean("read"));
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
    		List<LetterRecord> limited_gs = new ArrayList<>();
	    	for (int i=gs.size()-100; i<gs.size(); ++i) {
	    		limited_gs.add(gs.get(i));
	    	}
	    	return limited_gs;
	    }
	}
	
	public static void main(String[] args) {
		Config.init("server");
		
		LetterRecord lr = new LetterRecord();
		lr.setId(999);
		lr.setSender(-1);
		lr.setReceiver(-1);
		lr.setDate(new Timestamp(new Date().getTime()));
		lr.setContent("ohohoh");
		
		addLetter(lr);	
		
	}
	
	
}
