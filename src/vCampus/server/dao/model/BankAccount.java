package vCampus.server.dao.model;

import vCampus.bean.BankAccountBean;

public class BankAccount {
	
	int userId;
	String accountName;
	String bankPwd;
	int balance;
	
	// protect password
	public BankAccountBean toBean() {
		BankAccountBean bean = new BankAccountBean();
		bean.setUserId(userId);
		bean.setAccountName(accountName);
		bean.setBankPwd("");
		bean.setBalance(balance);
		return bean;		
	}
	public static BankAccount createModel(BankAccountBean bean) {
		BankAccount ba = new BankAccount();
		ba.setUserId(bean.getUserId());
		ba.setAccountName(bean.getAccountName());
		ba.setBankPwd(bean.getBankPwd());
		ba.setBalance(bean.getBalance());
		return ba;
	}
	
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
