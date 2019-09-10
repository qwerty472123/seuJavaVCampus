package vCampus.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import vCampus.client.ClientMain;
import vCampus.client.view.ProfilePanel;
import vCampus.client.view.ShopPanel;
import vCampus.client.view.TeacherGradePanel;
import vCampus.utility.Config;
import vCampus.utility.Crypto;
import vCampus.utility.Token;
import vCampus.utility.loop.*;

public class Auth {

	public static void login(String userName, String ppwd, boolean auto, boolean remember, boolean isEncPwd) {
		Map<String, Object> predata = new HashMap<String, Object>();
		predata.put("userName", userName);
		ClientMain.getSocketLoop().sendMsgWithCallBack(new Message("auth/getUid", predata), new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					int userId = (int) msg.getData().get("id");
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("userName", userName);
					String pwd = ppwd;
					if (!isEncPwd) pwd = Crypto.passwordEncrypt(pwd, userId);
					final String cpwd = pwd;
					data.put("encryptedPwd", pwd);
					ClientMain.getSocketLoop().sendMsgWithCallBack(new Message("auth/login", data), new LoopOnceAdapter() {
						@Override
						public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
							int code = (int) msg.getData().get("code");
							if (code == 200) {
								Token token = (Token) msg.getData().get("token");
								ClientMain.getTempData().put("token", token);
								ClientMain.getTopFrame().getMainFrame().setTitle(userName + " - vCampus");
								
								String authority = (String)msg.getData().get("authority");
								ClientMain.getTopFrame().getMainFrame().setRole(authority);
								//管理员
								if(authority.equals("admin")) {
									
								}
								//非管理员
								else if(authority.equals("student")){
									ArrayList<String> s = new ArrayList<String>();					
									s = (ArrayList<String>)msg.getData().get("personInfo");
									((ProfilePanel) ClientMain.getTopFrame().getMainFrame().getPagePanel("个人信息")).setPersonInfo(s);   
									((ProfilePanel) ClientMain.getTopFrame().getMainFrame().getPagePanel("个人信息")).setPhoto((ImageIcon)msg.getData().get("photo")); 
									
								}
								else if(authority.equals("teacher")){
									String name = null;
									name = (String)msg.getData().get("name");
									((TeacherGradePanel) ClientMain.getTopFrame().getMainFrame().getPagePanel("教学事务")).initCoursetable((Object[][]) msg.getData().get("object")); 
									((TeacherGradePanel) ClientMain.getTopFrame().getMainFrame().getPagePanel("教学事务")).setLabel_name(name);
									ClientMain.getTopFrame().getMainFrame().setBounds(100, 100, 1080, 640);
								}
								else if(authority.equals("publisher")){
									//pass
								}
								else if(authority.equals("doctor")) {
									
								}
								else {
									// TODO : NOT MATCH						
								}
								if (remember) {
									Config.get().getJSONObject("login").put(userName, cpwd);
								} else {
									Config.get().getJSONObject("login").put(userName, "");
								}
								if (auto) {
									Config.get().put("preferLogin", userName);
								} else if (Config.get().getString("preferLogin").equals(userName)) {
									Config.get().put("preferLogin", "");
								}
								Config.save();
								ClientMain.getTopFrame().showMainFrame();
							}else {
								//TODO
								JOptionPane.showMessageDialog(null, "用户名密码错误", "vCampus", JOptionPane.ERROR_MESSAGE);
								Config.log("login fail " + code);
							}
							ClientMain.getTopFrame().getLoginFrame().setInProcess(false);
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "用户名不存在", "vCampus", JOptionPane.ERROR_MESSAGE);
					ClientMain.getTopFrame().getLoginFrame().setInProcess(false);
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
  