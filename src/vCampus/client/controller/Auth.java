package vCampus.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;


import vCampus.client.ClientMain;
import vCampus.client.view.ProfilePanel;
import vCampus.client.view.ShopPanel;
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
					
					ArrayList<String> s = new ArrayList<String>();					
					s = (ArrayList<String>)msg.getData().get("personInfo");
					String authority = (String)msg.getData().get("authority");
					ClientMain.getTopFrame().getMainFrame().setRole(authority);
					//管理员
					if(authority.equals("admin")) {
						
					}
					//非管理员
					else {
						((ProfilePanel) ClientMain.getTopFrame().getMainFrame().getPagePanel("个人信息")).setPersonInfo(s);   
						((ProfilePanel) ClientMain.getTopFrame().getMainFrame().getPagePanel("个人信息")).setPhoto((ImageIcon)msg.getData().get("photo")); 
						
					}
					
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
  