package vCampus.server.dao.model;

import java.util.Date;

import vCampus.bean.BookOrderRecBean;

public class BookOrderRec {
	private int ID;
	private int bookId;
	private int userId;
	private Date dueTime;
	public BookOrderRecBean toBean() {
		BookOrderRecBean b=new BookOrderRecBean();
		b.setID(ID);
		b.setBookId(bookId);
		b.setUserId(userId);
		b.setDueTime(dueTime);
		return b;
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
