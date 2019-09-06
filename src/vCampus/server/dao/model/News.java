package vCampus.server.dao.model;

import java.sql.Timestamp;
import java.util.Date;

import vCampus.bean.NewsBean;

public class News {
	
	private int id;
	private String title;
	private String type;
	private Timestamp date;
	private String content;
	private String source;
	public NewsBean toBean() {
		NewsBean bean = new NewsBean();
		bean.setId(id);
		bean.setTitle(title);
		bean.setType(type);
		bean.setDate(new Date(date.getTime()));
		bean.setContent(content);
		bean.setSource(source);
		return bean;		
	}
	public static News createModel(NewsBean bean) {
		News model = new News();
		model.setId(bean.getId());
		model.setTitle(bean.getTitle());
		model.setType(bean.getType());
		model.setDate(new Timestamp(bean.getDate().getTime()));
		model.setContent(bean.getContent());
		model.setSource(bean.getSource());
		return model;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
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
	
}
	