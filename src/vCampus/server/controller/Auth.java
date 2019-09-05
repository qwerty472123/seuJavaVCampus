package vCampus.server.controller;

import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import vCampus.server.ServerMain;
import vCampus.server.dao.AccountKeyDao;
import vCampus.server.dao.StudentDao;
import vCampus.server.dao.model.Student;
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
				
				
				int userId = AccountKeyDao.queryUserId(userName, encryptedPwd);
				String authority = AccountKeyDao.queryAuthority(userId);
				Map<String, Object> data = new HashMap<String, Object>();
				
				if (userId >= 0) {
					data.put("code", 200);
					Token token = new Token(userId, encryptedPwd);
					data.put("token", token);
					data.put("authority",authority);
					if(authority.equals("admin")) {
						
					}
					else {
						String[] typeSet = {".png", ".jpeg", ".gif"};
						String url = null;
						for(int i = 0; i < 3; i++){
							url = "./photo/" + Integer.toString(userId)+typeSet[i];
				            File dir = new File(url);
				            if(dir.canRead())break;			            			            	
						}
						ImageIcon image = new ImageIcon(url);
				        image.setImage(image.getImage().getScaledInstance(180, 270,Image.SCALE_DEFAULT ));
						data.put("photo", image);
						ArrayList<String> personInfo = new ArrayList<String>();
						try {
							Student s = StudentDao.getStu(userId);
							personInfo.add(s.getName());
							personInfo.add(Integer.toString(s.getSex()));
							personInfo.add(s.getBirthday().toString());
							personInfo.add(Integer.toString(s.getAge()));
							personInfo.add(Integer.toString(s.getId()));
							personInfo.add(Integer.toString(s.getBalance()));
							personInfo.add(s.getFaculty());
							personInfo.add(Integer.toString(s.getGrade()));
							personInfo.add(Integer.toString(s.getStuclass()));
							personInfo.add(s.getEmail());
							personInfo.add(s.getPhone());
							personInfo.add(s.getQq());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						data.put("personInfo", personInfo);					
					
					}					
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
