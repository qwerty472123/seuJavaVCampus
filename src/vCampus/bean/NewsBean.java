package vCampus.bean;

import java.io.Serializable;
import java.util.Date;

public class NewsBean implements Serializable, Comparable<NewsBean>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4225158321573618274L;

	private int id;
	private String title;
	private String type;
	private Date date;
	private String content;
	private String source;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public int compareTo(NewsBean o) {
		if (date.before(o.getDate())) return 1;
		else return -1;
	}
	
}
