package vCampus.bean;

import java.io.Serializable;

public class BankAccountBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4719164108549937666L;
	
	int userId;
	String accountName;
	int balance;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

}
