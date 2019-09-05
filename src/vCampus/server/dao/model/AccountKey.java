package vCampus.server.dao.model;

import java.io.Serializable;

public class AccountKey implements Serializable{

	private static final long serialVersionUID = 7770510819219879356L;
	private int userId;
	private String userName;
	/*权限
	 * admin
	 * teacher
	 * student（默认）
	 */
	private String authority;
	
	public AccountKey(int pid, String pname) {
		userId = pid;
		userName = pname;
		authority = "student";
	}
	public AccountKey(int pid, String pname, String paut) {
		userId = pid;
		userName = pname;
		authority = paut;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
