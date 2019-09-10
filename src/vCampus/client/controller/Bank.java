package vCampus.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import vCampus.bean.BankAccountBean;
import vCampus.bean.ExpenseRecBean;
import vCampus.client.ClientMain;
import vCampus.client.view.BankAllowPanel;
import vCampus.client.view.BankConfirmDialog;
import vCampus.client.view.BankPanel;
import vCampus.client.view.FinancePanel;
import vCampus.utility.Config;
import vCampus.utility.Crypto;
import vCampus.utility.Token;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.LoopRemovableAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class Bank {

	public static void refreshInfo(BankPanel bp) {
		
		Map<String, Object> data = new HashMap<String, Object>();
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
					}
					d.dispose();
					Config.log("bankinfo refresh fail : code " + code);
				}
			}
		});
	}
	
	public static void askForRec(BankPanel bp) {		
		
		Map<String, Object> data = new HashMap<String, Object>();
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
	
	public static void popRecs(FinancePanel fp) {

		ClientMain.getSocketLoop().sendMsgWithCallBack(new Message("bank/pop"), new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
								
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					fp.setRecordData((List< ArrayList<String> >) msg.getData().get("reclist"));
					fp.refreshRecordTable();
				}else {
					Config.log("bankrec refresh fail : code " + code);
				}
			}
		});
	}
	
	public static void recharge(String pwd, String count, BankPanel bp) {
		Map<String, Object> data = new HashMap<String, Object>();
		int userId = ((Token) ClientMain.getTempData().get("token")).getUserId();
		data.put("pwd", Crypto.basePasswordEncrypt(pwd, userId));
		data.put("count", count);
		ClientMain.getSocketLoop().sendMsgWithCallBack(new Message("bank/recharge", data), new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					bp.refreshInfo();
					askForRec(bp);
					JOptionPane.showMessageDialog(null, "充值成功", "vCampus", JOptionPane.INFORMATION_MESSAGE);
				}else if (code == 403) {
					JOptionPane.showMessageDialog(null, "密码错误", "vCampus", JOptionPane.ERROR_MESSAGE);
				}else if (code == 404) {
					JOptionPane.showMessageDialog(null, "金额不合法", "vCampus", JOptionPane.ERROR_MESSAGE);
				}else if (code == 405) {
					JOptionPane.showMessageDialog(null, "银行职员拒绝了您的充值请求", "vCampus", JOptionPane.ERROR_MESSAGE);
				}else if (code == 406) {
					JOptionPane.showMessageDialog(null, "银行职员未在线", "vCampus", JOptionPane.ERROR_MESSAGE);
				}else if (code == 407) {
					JOptionPane.showMessageDialog(null, "充值登记失败，请联系系统管理员", "vCampus", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	public static void bankerPrepare() {
		final LoopRemovableAdapter listener = new LoopRemovableAdapter() {
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				((BankAllowPanel)ClientMain.getTopFrame().getMainFrame().getPagePanel("批准充值")).newCheckToSettle(
						(String)msg.getData().get("name"), 
						(int)msg.getData().get("count"), 
						(ResponseSender)transferData.get("sender"));
			}
		};
		ClientMain.getSocketLoop().addListener("banker/recharge", listener);
		ClientMain.getSocketLoop().addListener("banker/dispose", new LoopOnceAdapter() {
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				listener.remove();
				ClientMain.getTopFrame().showLoginFrame();
				JOptionPane.showMessageDialog(null, "其他银行职员已替你顶上了！休息一会儿吧(*^_^*)", "vCampus", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
}
