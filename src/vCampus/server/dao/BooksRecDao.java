package vCampus.server.dao;
import java.sql.*;
import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.Book;


public class BooksRecDao {
	public void addRec(Book s){

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "INSERT INTO Books(bookName, stockNum, dueDays)"
	                    +"values("+"?,?,?)";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1,s.getBookName());
	        ptmt.setInt(2, s.getStockNum());
	        ptmt.setInt(3, s.getDueDays());
	        
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
	        String sql = "delete from Books where id=?";
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

	
	public Book getRec(int ID){
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
		Book s = null;
	    try{
	    	conn = ConnectionManager.getConnection();

			String sql = "select * from  Books where id=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, ID);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				s = new Book();
				s.setId(rs.getInt("id"));
				s.setBookName(rs.getString("bookName"));
				s.setStockNum(rs.getInt("stockNum"));
				s.setDueDays(rs.getInt("dueDays"));
			}
			return s;
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    	return s;
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
	
	
	public void update(Book s) throws SQLException{

		Connection conn = null;
	    PreparedStatement ptmt = null;

	    try {
	    	conn = ConnectionManager.getConnection();
	        String sql = "UPDATE Books" +
	                " set bookName= ?, stockNum = ?, dueDays = ?"+
	                " where id=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, s.getBookName());
	        ptmt.setInt(2, s.getStockNum());
	        ptmt.setInt(3, s.getDueDays());
	        ptmt.setInt(4, s.getId());
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
