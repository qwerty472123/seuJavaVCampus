package vCampus.server.controller;

import java.util.HashMap;
import java.util.Map;

import vCampus.server.ServerMain;
import vCampus.server.dao.AccountKeyDao;
import vCampus.utility.Token;
import vCampus.utility.loop.Loop;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class Auth {
	static {
		ServerMain.addRequestListener(Loop.GENERAL_TYPE, new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				if (msg.getType().equals("auth/login")) return false;
				Token token = (Token) msg.getData().get("token");
				int userId = token.getUserId();
				String encryptedPwd = AccountKeyDao.queryPassword(userId);
				if (token.check(encryptedPwd)) {
					transferData.put("userId", userId);
					//transferData.put("role", role);
					return false;
				} else {
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("code", 400);
					((ResponseSender) transferData.get("sender")).send(data);
					return true;
				}
			}			
		});
		
		ServerMain.addRequestListener("auth/login", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				String userName = (String) msg.getData().get("userName");
				String encryptedPwd = (String) msg.getData().get("encryptedPwd");
				
				//encryptedPwd = Crypto.passwordEncrypt(encryptedPwd, userId);
				
				int userId = AccountKeyDao.queryUserId(userName, encryptedPwd);
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				if (userId >= 0) {
					data.put("code", 200);
					Token token = new Token(userId, encryptedPwd);
					data.put("token", token);
					//Config.log("UserId: " + userId);
					//Config.log("Token got: " + token.check(encryptedPwd));
				}else {
					data.put("code", 401); // username/pwd error
				}				
				
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("auth/logout", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("code", 200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
	}
}
