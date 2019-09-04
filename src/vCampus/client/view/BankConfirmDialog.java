package vCampus.client.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import vCampus.server.dao.model.ExpenseRec;

public class BankConfirmDialog extends JDialog {
	
	//private BankPanel bp;
	private ExpenseRec erec;
	private boolean success;
	
	public BankConfirmDialog(Frame f, ExpenseRec erec) {
		super(f, "", true);
		setSize(new Dimension(300, 200));
		add(new JLabel("正提交支付请求..."));
		
		//this.bp = bp;
		this.erec = erec;
		
		// TO modify
		success = true;
	}
	

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
