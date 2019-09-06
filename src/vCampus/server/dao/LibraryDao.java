package vCampus.server.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import vCampus.server.dao.driver.ConnectionManager;
import vCampus.server.dao.model.BookBorrowRec;
import vCampus.server.dao.model.BookOrderRec;
import vCampus.server.dao.model.Lesson;
import vCampus.utility.Config;
import vCampus.server.dao.model.Book;

public class LibraryDao {
	/**
	 * id for rc is not needed
	 * @param rc
	 */
	public static boolean addBorrowRec(BookBorrowRec rc) {
	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	Book b=queryBook(rc.getBookId());
	    	if(b.getBorrowCnt()>=b.getTotCnt())return false;
	    	conn = ConnectionManager.getConnection();
	        String sql = "INSERT INTO BookBorrow(bookId, userId, borrowTime,dueTime)"
	                    +"values(?,?,?,?)";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, rc.getBookId());
	        ptmt.setInt(2, rc.getUserId());
	        ptmt.setDate(3, new Date(rc.getBorrowTime().getTime()));
	        ptmt.setDate(4, new Date(rc.getDueTime().getTime()));
	        ptmt.execute();
	        b.setBorrowCnt(b.getBorrowCnt()+1);
	        updateBook(b);
	        return true;
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
	    return false;
	}
	public static void removeBorrowRec(BookBorrowRec rc) {
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "delete from BookBorrow where ID=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1,rc.getID());
	        ptmt.execute();
	        Book b=queryBook(rc.getBookId());
	        b.setBorrowCnt(b.getBorrowCnt()-1);
	        updateBook(b);
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
	public static void updateBorrowRec(BookBorrowRec rc) {

		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try {
	    	conn = ConnectionManager.getConnection();
	        String sql = "UPDATE BookBorrow" +
	                " set bookId=?, userId=?, borrowTime=?,dueTime=?"+
	                " where ID=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1,rc.getBookId());
	        ptmt.setInt(2, rc.getUserId());
	        ptmt.setDate(3, new Date(rc.getBorrowTime().getTime()));
	        ptmt.setDate(4, new Date(rc.getDueTime().getTime()));
	        ptmt.setInt(5, rc.getID());
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
	public static boolean addOrderRec(BookOrderRec rc) {
		//可能有多用户同时预约,而这可能导致超量预约,故加锁
		synchronized(LibraryDao.class) {
			Book b=queryBook(rc.getBookId());
			//有借出的才能预约
			if(b.getBorrowCnt()==0)return false;
			
		    Connection conn = null;
		    PreparedStatement ptmt = null;
		    try{
		    	conn = ConnectionManager.getConnection();
		        String sql = "INSERT INTO BookOrder(bookId, userId ,dueTime)"
		                    +"values(?,?,?)";
		        ptmt = conn.prepareStatement(sql);
		        ptmt.setInt(1, rc.getBookId());
		        ptmt.setInt(2, rc.getUserId());
		        ptmt.setDate(3, new Date(rc.getDueTime().getTime()));
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
			
		    b.setOrderCnt(b.getOrderCnt()+1);
		    updateBook(b);
			return true;
		}
	}
	public static void removeOrderRec(BookOrderRec rc) {
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "delete from BookOrder where ID=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1,rc.getID());
	        ptmt.execute();
	        Book b=queryBook(rc.getBookId());
	        b.setOrderCnt(b.getOrderCnt()-1);
	        updateBook(b);
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
	
	/**
	 * 
	 * @param userId -1 for all
	 * @return
	 */
	public static ArrayList<BookBorrowRec> queryBorrowRec(int userId){
		ArrayList<BookBorrowRec> ans=new ArrayList<BookBorrowRec>();
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	boolean all=(userId==-1);
			String sql = "select * from BookBorrow where ID=?";
			if(all)sql="select * from BookBorrow";
			ptmt = conn.prepareStatement(sql);
			if(!all)ptmt.setInt(1, userId);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				BookBorrowRec rc = new BookBorrowRec();
				rc.setBookId(rs.getInt("bookId"));
				rc.setUserId(rs.getInt("userId"));
				rc.setBorrowTime(rs.getDate("borrowTime"));
				rc.setDueTime(rs.getDate("dueTime"));
				ans.add(rc);
			}
			return ans;
	    }catch(SQLException e) {
	    	e.printStackTrace();
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
	    return ans;
	}
	
	/**
	 * 
	 * @param userId -1 for all
	 * @return
	 */
	public static ArrayList<BookOrderRec> queryOrderRec(int userId){
		ArrayList<BookOrderRec> ans=new ArrayList<BookOrderRec>();
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	boolean all=(userId==-1);
			String sql = "select * from BookOrder where ID=?";
			if(all)sql="select * from BookOrder";
			ptmt = conn.prepareStatement(sql);
			if(!all)ptmt.setInt(1, userId);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				BookOrderRec rc = new BookOrderRec();
				rc.setBookId(rs.getInt("bookId"));
				rc.setUserId(rs.getInt("userId"));
				rc.setDueTime(rs.getDate("dueTime"));
				ans.add(rc);
			}
			return ans;
	    }catch(SQLException e) {
	    	e.printStackTrace();
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
	    return ans;
	}
	
	/**
	 * ID for b is not needed
	 * @param b
	 */
	public static void addBook(Book b) {

	    Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "INSERT INTO Book(title, author, press,description,location,totCnt,borrowCnt,orderCnt)"
	                    +"values(?,?,?,?,?,?,?,?)";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, b.getTitle());
	        ptmt.setString(2,b.getAuthor());
	        ptmt.setString(3,b.getPress());
	        ptmt.setString(4, b.getDescription());
	        ptmt.setString(5, b.getLocation());
	        ptmt.setInt(6,b.getTotCnt());
	        ptmt.setInt(7,b.getBorrowCnt());
	        ptmt.setInt(8, b.getOrderCnt());
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
	
	public static void removeBook(int bookId) {
		Book b=new Book();
		b.setID(bookId);
		removeBook(b);
	}
	
	public static void removeBook(Book b) {
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	        String sql = "delete from Book where ID=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1,b.getID());
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
	
	public static void updateBook(Book b) {
		Connection conn = null;
	    PreparedStatement ptmt = null;

	    try {
	    	conn = ConnectionManager.getConnection();
	        String sql = "UPDATE Book" +
	                " set title=?, author=?, press=?,description=?,location=?,totCnt=?,borrowCnt=?,orderCnt=?"+
	                " where ID=?";
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, b.getTitle());
	        ptmt.setString(2,b.getAuthor());
	        ptmt.setString(3,b.getPress());
	        ptmt.setString(4, b.getDescription());
	        ptmt.setString(5, b.getLocation());
	        ptmt.setInt(6,b.getTotCnt());
	        ptmt.setInt(7,b.getBorrowCnt());
	        ptmt.setInt(8, b.getOrderCnt());
	        ptmt.setInt(9, b.getID());
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
	
	/**
	 * 
	 * @param keyword * for all
	 * @return
	 */
	public static ArrayList<Book> queryBook(String keyword){
		ArrayList<Book> ans=new ArrayList<Book>();
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
	    	boolean all=keyword.equals("*");
			String sql = "select * from Book where "
					+ " title LIKE ? OR "
					+ " description LIKE ? ";
			if(all)sql="select * from Book";
			ptmt = conn.prepareStatement(sql);
			if(!all)ptmt.setString(1, "%"+keyword+"%");
			if(!all)ptmt.setString(2, "%"+keyword+"%");
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				Book b = new Book();
				b.setID(rs.getInt("ID"));
				b.setTitle(rs.getString("title"));
				b.setAuthor(rs.getString("author"));
				b.setPress(rs.getString("press"));
				b.setDescription(rs.getString("description"));
				b.setLocation(rs.getString("location"));
				b.setTotCnt(rs.getInt("totCnt"));
				b.setBorrowCnt(rs.getInt("borrowCnt"));
				b.setOrderCnt(rs.getInt("orderCnt"));
				ans.add(b);
			}
			return ans;
	    }catch(SQLException e) {
	    	e.printStackTrace();
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
    	return ans;
	}
	
	public static Book queryBook(int bookId) {
		Book b=null;
		Connection conn = null;
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    try{
	    	conn = ConnectionManager.getConnection();
			String sql = "select * from Book where ID=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, bookId);
			rs = ptmt.executeQuery();
			
			while(rs.next()){
				b=new Book();
				b.setID(rs.getInt("ID"));
				b.setTitle(rs.getString("title"));
				b.setAuthor(rs.getString("author"));
				b.setPress(rs.getString("press"));
				b.setDescription(rs.getString("description"));
				b.setLocation(rs.getString("location"));
				b.setTotCnt(rs.getInt("totCnt"));
				b.setBorrowCnt(rs.getInt("borrowCnt"));
				b.setOrderCnt(rs.getInt("orderCnt"));
			}
			return b;
	    }catch(SQLException e) {
	    	e.printStackTrace();
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
    	return b;
	}

	public static void main(String[] args) {
		Config.init("Server");
		/*ArrayList<Book> res=queryBook("物理");
		for(Book b: res) {
			System.out.println(
					b.getTitle()+","
					+b.getAuthor()+','
					+b.getPress()
					);
		}*/
		BookBorrowRec rc=new BookBorrowRec();
		rc.setBookId(2);
		rc.setUserId(5);
		Calendar c=Calendar.getInstance();
		rc.setBorrowTime(new Date(c.getTime().getTime()));
		c.add(Calendar.DATE, 30);
		rc.setDueTime(new Date(c.getTime().getTime()));
		addBorrowRec(rc);
		
		
		/*ArrayList<BookBorrowRec> res=queryBorrowRec(-1);
		for(BookBorrowRec x:res) {
			System.out.println(
					x.getBookId()+","
					+x.getUserId()+","
					+x.getBorrowTime()+","
					+x.getDueTime()
					);
		}*/
	}




}
