package vCampus.utility.loop;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {
	private static final long serialVersionUID = -8704616516568630877L;
	private static int incrUId = 0;
	private int uId;
	private String type;
	private Map<String, Object> data;
	private int rId;
	public Message(String type) {
		this(type, new HashMap<String, Object>());
	}
	public Message(String type, Map<String, Object> data) {
		this(type, data, -1);
	}
	public Message(String type, Map<String, Object> data, int rId) {
		incrUId++;
		setuId(incrUId);
		setrId(rId);
		setType(type);
		setData(data);
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
