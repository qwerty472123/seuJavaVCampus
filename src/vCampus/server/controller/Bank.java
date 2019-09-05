package vCampus.server.controller;

import java.util.HashMap;
import java.util.Map;

import vCampus.server.ServerMain;
import vCampus.server.dao.BankAccountDao;
import vCampus.server.dao.EpsRecsDao;
import vCampus.server.dao.model.BankAccount;
import vCampus.server.dao.model.ExpenseRec;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class Bank {
	static {

		ServerMain.addRequestListener("bank/refresh", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();

				BankAccount ba = BankAccountDao.queryAccount((int)msg.getData().get("userId"));
				data.put("account", ba);
				
				data.put("code", 200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("bank/newrec", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();

				int userId = (int) msg.getData().get("userId");
				ExpenseRec newRec = (ExpenseRec) msg.getData().get("rec");

				//BankAccountDao.queryBalance(userId);
				
				int rowCnt = BankAccountDao.addBalance(userId, -newRec.getFigure());
				
				if (rowCnt>0) {
					data.put("code", 200);					
					EpsRecsDao.addRec(newRec);
					data.put("rec", newRec);
					
				}else {
					data.put("code", 999);
				}
				
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});

		
	
	}
}
