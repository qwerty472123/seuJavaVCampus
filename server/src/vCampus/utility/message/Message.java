package vCampus.utility.message;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = -8704616516568630877L;
	private static int incrUId = 0;
	private int uId;
	private String type;
	private String data;
	private int retId;
	public Message(String type) {
		this(type, null);
	}
	public Message(String type, String data) {
		this(type, data, -1);
	}
	public Message(String type, String data, int retId) {
		incrUId++;
		setuId(incrUId);
		setType(type);
		setData(data);
		setRetId(retId);
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getRetId() {
		return retId;
	}
	public void setRetId(int retId) {
		this.retId = retId;
	}
}
