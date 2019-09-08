package vCampus.server.dao.model;

import java.util.Date;

import vCampus.bean.ExpenseRecBean;

public class ExpenseRec {
	
	private int id;
	private int personID;
	private int figure;  //金额
	private Date date;
	private String source;
	private String details;
	
	public ExpenseRecBean toBean() {
		ExpenseRecBean bean = new ExpenseRecBean();
		bean.setId(id);
		bean.setPersonID(personID);
		bean.setFigure(figure);
		bean.setDate(date);
		bean.setSource(source);
		bean.setDetails(details);		
		return bean;
	}
	public static ExpenseRec createModel(ExpenseRecBean bean) {
		ExpenseRec rec = new ExpenseRec();
		rec.setId(bean.getId());
		rec.setPersonID(bean.getPersonID());
		rec.setFigure(bean.getFigure());
		rec.setDate(bean.getDate());
		rec.setSource(bean.getSource());
		rec.setDetails(bean.getDetails());
		return rec;
	}
	
	public int getFigure() {
		return figure;
	}
	public void setFigure(int figure) {
		this.figure = figure;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
