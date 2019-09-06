package vCampus.server.dao.model;

import java.util.Date;

import vCampus.bean.BookBean;
import vCampus.bean.BookBorrowRecBean;

public class BookBorrowRec {
	private int ID;
	private int bookId;
	private int userId;
	private Date borrowTime;
	private Date dueTime;
	public BookBorrowRecBean toBean() {
		BookBorrowRecBean b=new BookBorrowRecBean();
		b.setID(ID);
		b.setBookId(bookId);
		b.setUserId(userId);
		b.setBorrowTime(borrowTime);
		b.setDueTime(dueTime);
		return b;
	}
	public static BookBorrowRec toModel(BookBorrowRecBean b) {
		BookBorrowRec x = new BookBorrowRec();
		x.setID(b.getID());
		x.setBookId(b.getBookId());
		x.setUserId(b.getUserId());
		x.setBorrowTime(b.getBorrowTime());
		x.setDueTime(b.getDueTime());
		return x;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}
	public Date getDueTime() {
		return dueTime;
	}
	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
}
