package vCampus.server.dao.model;

import java.sql.Timestamp;
import java.util.Date;

import vCampus.bean.LetterRecordBean;
import vCampus.server.dao.AccountKeyDao;

public class LetterRecord {
	
	private int id;
	private int sender;
	private int receiver;
	private Timestamp date;
	private String content;
	private boolean read;
	public LetterRecordBean toBean() {
		LetterRecordBean bean = new LetterRecordBean();
		bean.setId(id);
		bean.setSender(sender);
		bean.setReceiver(receiver);
		bean.setSenderName(AccountKeyDao.queryUserName(sender));
		bean.setReceiverName(AccountKeyDao.queryUserName(receiver));
		bean.setDate(new Date(date.getTime()));
		bean.setContent(content);
		bean.setRead(read);		
		return bean;		
	}
	public static LetterRecord createModel(LetterRecordBean bean) {
		LetterRecord model = new LetterRecord();
		model.setId(bean.getId());
		model.setSender(bean.getSender());
		model.setReceiver(bean.getReceiver());
		model.setDate(new Timestamp(bean.getDate().getTime()));
		model.setContent(bean.getContent());
		model.setRead(bean.isRead());
		return model;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
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
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	
}
