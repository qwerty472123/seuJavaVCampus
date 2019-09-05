package vCampus.server.dao.model;

import java.io.Serializable;

public class BankAccount implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4719164108549937666L;
	
	int userId;
	String accountName;
	String bankPwd;
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
	public String getBankPwd() {
		return bankPwd;
	}
	public void setBankPwd(String bankPwd) {
		this.bankPwd = bankPwd;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
