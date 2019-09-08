package vCampus.server.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.BookBorrowRec;

public class BookRecsDao {
	public void addRec(BookBorrowRec s){

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "INSERT INTO BookRecSheet(personID, bookID, outDate, dueDate, backDate)"
	                    +"values("+"?,?,?,?,?)";
	        ptmt = conn.prepareStatement(sql);
	        
	        ptmt.setInt(1, s.getPersonID());
	        ptmt.setInt(2, s.getBookID());
	        ptmt.setDate(3, new Date(s.getOutDate().getTime()));
	        ptmt.setDate(4, new Date(s.getDueDate().getTime()));
	        ptmt.setDate(5, new Date(s.getBackDate().getTime()));
	        
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
	
	public void delRec(int ID) throws SQLException{
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "delete from BookRecSheet where id=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, ID);
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
	
	public List<BookBorrowRec> getRec(int personID){
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
		BookBorrowRec s = null;
		List<BookBorrowRec> gs = new ArrayList<BookBorrowRec>();
	    try{
	    	conn = ConnectionManager.getConnection();

			String sql = "select * from  BookRecSheet where personID=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, personID);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				s = new BookBorrowRec();
				s.setId(rs.getInt("id"));
				s.setPersonID(rs.getInt("personID"));
				s.setBookID(rs.getInt("bookID"));
				s.setOutDate(rs.getDate("outDate"));
				s.setDueDate(rs.getDate("dueDate"));
				s.setBackDate(rs.getDate("backDate"));
				gs.add(s);
			}
			return gs;
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
		
	}	
	
	
	public void update(BookBorrowRec s) throws SQLException{

		Connection conn = null;
	    PreparedStatement ptmt = null;

	    try {
	    	conn = ConnectionManager.getConnection();
	        String sql = "UPDATE StudentSheet" +
	                " set personID = ?, bookID=?, outDate = ?, dueDate=?, backDate=?"+
	                " where id = ?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, s.getPersonID());
	        ptmt.setInt(2, s.getBookID());
	        ptmt.setDate(3, new Date(s.getOutDate().getTime()));
	        ptmt.setDate(4, new Date(s.getDueDate().getTime()));
	        ptmt.setDate(5, new Date(s.getBackDate().getTime()));
	        ptmt.setInt(6, s.getId());
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
	
	
}
