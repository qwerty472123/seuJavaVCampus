package vCampus.server.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.PurchaseRec;

public class PurchaseRecsDao {
	
	public void addRec(PurchaseRec s) {
	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	String sql = "INSERT INTO PurchRecSheet(personID, shopID, goodID, numGood, date, money)"
                    +"values("+"?,?,?,?,?,?)";
	    	ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, s.getPersonID());
	        ptmt.setInt(2, s.getShopID());
	        ptmt.setInt(3, s.getGoodID());
	        ptmt.setInt(4, s.getNumGood());
	        ptmt.setDate(5, new Date(s.getDate().getTime()));
	        ptmt.setInt(6, s.getMoney());
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
	
	public void deleRec(int ID) throws SQLException{
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
		    String sql = "delete from PurchRecSheet where id=?";
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
	
	public List<PurchaseRec> getRec(int personID) throws SQLException{
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
		PurchaseRec s = null;
		List<PurchaseRec> gs = new ArrayList<PurchaseRec>();
	    try{
	    	conn = ConnectionManager.getConnection();
			String sql = "select * from  PurchRecSheet where personID=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, personID);
			rs = ptmt.executeQuery();			
			while(rs.next()){
				s = new PurchaseRec();
				s.setId(rs.getInt("id"));
				s.setPersonID(rs.getInt("personID"));
				s.setShopID(rs.getInt("shopID"));
				s.setGoodID(rs.getInt("goodID"));
				s.setNumGood(rs.getInt("numGood"));
				s.setDate(rs.getDate("date"));
				s.setMoney(rs.getInt("money"));
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
	
	
	
	
}
