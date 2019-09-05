package vCampus.client.controller;

import java.util.HashMap;
import java.util.Map;

import vCampus.client.ClientMain;
import vCampus.client.view.BankConfirmDialog;
import vCampus.client.view.BankPanel;
import vCampus.server.dao.model.BankAccount;
import vCampus.server.dao.model.ExpenseRec;
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
					BankAccount ba = (BankAccount) msg.getData().get("account");
					bp.setAccount(ba);					
				}else {
					Config.log("bankinfo refresh fail : code " + code);
				}
			}
		});
		
	}
	
	public static void pay(BankConfirmDialog d) {
		
		ExpenseRec erec = d.getErec();
		
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
					d.setErec((ExpenseRec) msg.getData().get("rec"));
					d.dispose();
				}else {
					d.setSuccess(false);
					Config.log("bankinfo refresh fail : code " + code);
				}
			}
		});
	}
	
	
	
}
