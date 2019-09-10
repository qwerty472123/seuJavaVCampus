package vCampus.bean;

import java.io.Serializable;
import java.util.Date;

public class ExpenseRecBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5396351270830650438L;

	private int id;
	private int figure;  //金额
	private Date date;
	private String source;
	private String details;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
