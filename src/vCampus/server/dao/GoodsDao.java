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
	public void addRec(Good s){

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "INSERT INTO Goods (goodID, shopID, goodName, price, stockNum)"
	                    +"values("+"?,?,?,?,?)";
	        ptmt = conn.prepareStatement(sql);
	       
	        ptmt.setInt(1, s.getGoodID());
	        ptmt.setInt(2, s.getShopID());
	        ptmt.setString(3,s.getGoodName());
	        ptmt.setFloat(4, s.getPrice());
	        ptmt.setInt(5, s.getStockNum());
	        
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
	
	public void delRec(int goodID, int shopID) throws SQLException{
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "delete from Goods where goodID=? AND shopID=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, goodID);
	        ptmt.setInt(2, shopID);
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
	
	
	public Good getRec(int goodID, int shopID){
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
				s.setStockNum(rs.getInt("stockNum"));
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
	
	public void update(Good s) throws SQLException{

		Connection conn = null;
	    PreparedStatement ptmt = null;

	    try {
	    	conn = ConnectionManager.getConnection();
	        String sql = "UPDATE Goods" +
	                " set price= ?, goodName = ?, stockNum = ?"+
	                " where goodID = ? AND shopID = ?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setFloat(1, s.getPrice());
	        ptmt.setString(2, s.getGoodName());
	        ptmt.setInt(3, s.getStockNum());
	        ptmt.setInt(4, s.getGoodID());
	        ptmt.setInt(5, s.getShopID());
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
	
	public List<Good> queryGoods(String name){
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
				s.setStockNum(rs.getInt("stockNum"));
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
			System.out.println(g.getGoodID() + " " + g.getGoodName() + " " + g.getPrice());
		}
		
	}
	
	
}
