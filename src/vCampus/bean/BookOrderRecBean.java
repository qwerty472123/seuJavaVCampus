package vCampus.bean;

import java.io.Serializable;
import java.util.Date;

public class BookOrderRecBean implements Serializable{
	private int ID;
	private int bookId;
	private int userId;
	private Date dueTime;
	private boolean doneable;
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
