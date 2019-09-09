package vCampus.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import vCampus.bean.BankAccountBean;
import vCampus.bean.ExpenseRecBean;
import vCampus.client.ClientMain;
import vCampus.client.view.BankConfirmDialog;
import vCampus.client.view.BankPanel;
import vCampus.utility.Config;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.Token;

public class Bank {

	public static void refreshInfo(BankPanel bp) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", ((Token)ClientMain.getTempData().get("token")).getUserId());
		Message msg = new Message("bank/refresh", data);		

		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
								
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					BankAccountBean ba = (BankAccountBean) msg.getData().get("account");
					bp.setAccount(ba);					
				}else {
					Config.log("bankinfo refresh fail : code " + code);
				}
			}
		});
		
	}
	
	public static void pay(BankConfirmDialog d) {
		
		ExpenseRecBean erec = d.getErec();
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", ((Token)ClientMain.getTempData().get("token")).getUserId());
		data.put("rec", erec);
		Message msg = new Message("bank/newrec", data);		

		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
								
				int code = (int) msg.getData().get("code");
				if (code == 200) {					
					d.setSuccess(true);
					d.setErec((ExpenseRecBean) msg.getData().get("rec"));
					d.dispose();
				}else {
					d.setSuccess(false);
					if (code == 999) {
						JOptionPane.showMessageDialog(d, "余额不足！");
						d.dispose();						
					}
					Config.log("bankinfo refresh fail : code " + code);
				}
			}
		});
	}
	
	public static void askForRec(BankPanel bp) {		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", ((Token)ClientMain.getTempData().get("token")).getUserId());
		Message msg = new Message("bank/getrec", data);		

		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
								
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					bp.setRecordData((List< ArrayList<String> >) msg.getData().get("reclist"));
					bp.refreshRecordTable();
				}else {
					Config.log("bankrec refresh fail : code " + code);
				}
			}
		});
		
	}
	
	
}
