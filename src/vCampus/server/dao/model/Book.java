package vCampus.server.dao.model;

import vCampus.bean.BookBean;

public class Book {
	private int ID;
	private String title;
	private String author;
	private String press;
	private String description;
	private String location;
	private int totCnt;
	private int borrowCnt;
	private int orderCnt;
	public BookBean toBean() {
		BookBean b=new BookBean();
		b.setID(ID);
		b.setTitle(title);
		b.setAuthor(author);
		b.setPress(press);
		b.setDescription(description);
		b.setLocation(location);
		b.setTotCnt(totCnt);
		b.setBorrowCnt(borrowCnt);
		b.setOrderCnt(orderCnt);
		return b;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}
	public int getBorrowCnt() {
		return borrowCnt;
	}
	public void setBorrowCnt(int borrowCnt) {
		this.borrowCnt = borrowCnt;
	}
	public int getOrderCnt() {
		return orderCnt;
	}
	public void setOrderCnt(int orderCnt) {
		this.orderCnt = orderCnt;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
}