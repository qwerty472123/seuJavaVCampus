package vCampus.client.view;

import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.*;

import vCampus.bean.ExpenseRecBean;
import vCampus.client.controller.Bank;

public class BankConfirmDialog extends JDialog {
	
	private ExpenseRecBean erec;
	private boolean success;
	
	public BankConfirmDialog(Frame f, ExpenseRecBean erec) {
		super(f, "", true);
		setSize(new Dimension(300, 200));
		add(new JLabel("正提交支付请求..."));
		
		this.setErec(erec);
		success = true;
		Bank.pay(this);
	}
	

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}


	public ExpenseRecBean getErec() {
		return erec;
	}


	public void setErec(ExpenseRecBean erec) {
		this.erec = erec;
	}
}
