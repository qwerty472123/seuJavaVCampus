package vCampus.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.*;
import vCampus.utility.Config;

public class GoodsDao {
	public static void addRec(Good s){

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "INSERT INTO Goods (goodID, shopID, goodName, price, caption)"
	                    +"values("+"?,?,?,?,?)";
	        ptmt = conn.prepareStatement(sql);
	       
	        ptmt.setInt(1, s.getGoodID());
	        ptmt.setInt(2, s.getShopID());
	        ptmt.setString(3,s.getGoodName());
	        ptmt.setInt(4, s.getPrice());
	        ptmt.setString(5, s.getCaption());
	        
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
	
	public static void delRec(int goodID) {
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "delete from Goods where goodID=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, goodID);
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
	
	
	public static Good getRec(int goodID, int shopID){
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
		Good s = null;
	    try{
	    	conn = ConnectionManager.getConnection();

			String sql = "select * from Goods where goodID=? AND shopID=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, goodID);
			ptmt.setInt(2, shopID);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				s = new Good();
				s.setGoodID(rs.getInt("goodID"));
				s.setShopID(rs.getInt("shopID"));
				s.setGoodName(rs.getString("goodName"));
				s.setPrice(rs.getInt("price"));
				s.setCaption(rs.getString("caption"));
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
	
	public static void update(Good s){

		Connection conn = null;
	    PreparedStatement ptmt = null;

	    try {
	    	conn = ConnectionManager.getConnection();
	        String sql = "UPDATE Goods" +
	                " set shopID = ?, price= ?, goodName = ?, Caption = ?"+
	                " where goodID = ?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, s.getShopID());
	        ptmt.setInt(2, s.getPrice());
	        ptmt.setString(3, s.getGoodName());
	        ptmt.setString(4, s.getCaption());
	        ptmt.setInt(5, s.getGoodID());
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
	
	public static List<Good> queryGoods(String name){		
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    Good s = null;
		List<Good> gs = new ArrayList<Good>();
	    try{
	    	conn = ConnectionManager.getConnection();
			String sql = "select * from Goods where goodName like ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, "%"+name+"%");
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				s = new Good();
				s.setGoodID(rs.getInt("goodID"));
				s.setGoodName(rs.getString("goodName"));
				s.setPrice(rs.getInt("price"));
				s.setShopID(rs.getInt("shopID"));
				s.setCaption(rs.getString("Caption"));
				gs.add(s);
			}
			return gs;
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
		
	}
	
	public static void main(String[] args) {
		Config.init("Server");
		try {
			Class.forName("vCampus.server.dao.driver.ConnectionManager");
		} catch (ClassNotFoundException e) {
			Config.log(e);
		}
		
		GoodsDao gd = new GoodsDao();
		List<Good> goods = gd.queryGoods("");
		for (Good g: goods) {
			Config.log(g.getGoodID() + " " + g.getGoodName() + " " + g.getPrice());
		}
		
	}
	
	
}
