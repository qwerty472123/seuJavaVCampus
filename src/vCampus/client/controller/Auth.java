package vCampus.client.controller;

import java.util.HashMap;
import java.util.Map;

import vCampus.client.ClientMain;
import vCampus.utility.Config;
import vCampus.utility.Token;
import vCampus.utility.loop.*;

public class Auth {

	public static void login(String userName, String pwd) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("encryptedPwd", pwd);
		Message msg = new Message("auth/login", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					Token token = (Token) msg.getData().get("token");
					ClientMain.getTempData().put("token", token);
					ClientMain.getTopFrame().getMainFrame().setTitle(userName + " - vCampus");
					ClientMain.getTopFrame().showMainFrame();
				}else {
					//TODO
					Config.log("login fail " + code);
				}
			}
		});
	}
	
	public static void logout() {
		ClientMain.getSocketLoop().sendMsgWithCallBack(new Message("auth/logout"), new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				ClientMain.getTempData().remove("token");
				ClientMain.getTopFrame().showLoginFrame();
			}
		});
	}
	
}
 