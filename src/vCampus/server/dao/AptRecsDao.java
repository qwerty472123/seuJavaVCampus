package vCampus.server.dao;

import java.sql.*;
import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.AptRec;

public class AptRecsDao {
		
	public void addRec(AptRec s){
        Connection conn = null;
        PreparedStatement ptmt = null;
        try{
        	conn = ConnectionManager.getConnection();
            
            String sql = "INSERT INTO AptRecSheet(personID, doctorID, aptdate, done)"
                        +"values("+"?,?,?,?)";	
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, s.getPersonID());
            ptmt.setInt(2, s.getDoctorID());
            ptmt.setDate(3, new Date(s.getDate().getTime()));
            ptmt.setBoolean(4, s.isDone());
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
	
	public void update(AptRec s) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
        	conn = ConnectionManager.getConnection();
            String sql = "UPDATE AptRecSheet" +
                    " set personID = ?, doctorID=?, date = ?, done = ?"+
                    " where id = ?";
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, s.getPersonID());
            ptmt.setInt(2, s.getDoctorID());
            ptmt.setDate(3, new Date(s.getDate().getTime()));
            ptmt.setBoolean(4, s.isDone());
            ptmt.setInt(5, s.getId());
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
		try {
	    	conn = ConnectionManager.getConnection();
	    	String sql = "delete from AptRecSheet where id=?";
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
	
	public static void main(String args[]) {
		AptRecsDao dao = new AptRecsDao();
		AptRec apt = new AptRec();
		dao.addRec(apt);
	}
	    
	   
}
