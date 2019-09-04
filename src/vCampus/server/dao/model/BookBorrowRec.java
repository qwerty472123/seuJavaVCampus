package vCampus.server.dao.model;

import java.util.Date;

public class BookBorrowRec {
	private int id;
	private int personID;
	private int bookID;
	private Date outDate;
	private Date dueDate;
	private Date backDate;
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getBackDate() {
	return backDate;
	}
	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
