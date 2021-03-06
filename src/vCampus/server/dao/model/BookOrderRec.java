package vCampus.server.dao.model;

import java.util.Date;

import vCampus.bean.BookBorrowRecBean;
import vCampus.bean.BookOrderRecBean;

public class BookOrderRec {
	private int ID;
	private int bookId;
	private int userId;
	private Date dueTime;
	private boolean doneable;
	public BookOrderRecBean toBean() {
		BookOrderRecBean b=new BookOrderRecBean();
		b.setID(ID);
		b.setBookId(bookId);
		b.setUserId(userId);
		b.setDueTime(dueTime);
		b.setDoneable(doneable);
		return b;
	}
	public static BookOrderRec toModel(BookOrderRecBean b) {
		BookOrderRec x = new BookOrderRec();
		x.setID(b.getID());
		x.setBookId(b.getBookId());
		x.setUserId(b.getUserId());
		x.setDueTime(b.getDueTime());
		x.setDoneable(b.isDoneable());
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
	public boolean isDoneable() {
		return doneable;
	}
	public void setDoneable(boolean doneable) {
		this.doneable = doneable;
	}
}
