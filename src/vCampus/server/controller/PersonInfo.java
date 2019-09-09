package vCampus.server.controller;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import vCampus.utility.Token;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;
import vCampus.server.dao.*;
import vCampus.server.dao.model.Student;
import vCampus.server.*;
public class PersonInfo {	
	static {
		ServerMain.addRequestListener("PersonInfo/change_password", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
								
                Token token=(Token) msg.getData().get("token");
                int userId=token.getUserId();   
				System.out.println("OK");
                String initPsWd = (String)msg.getData().get("initPassword");
                String newPsWd = (String)msg.getData().get("newPassword");               
                Map<String, Object> data = new HashMap<String, Object>();
                                               
                if(!initPsWd.equals(AccountKeyDao.queryPassword(userId))){
                	JOptionPane.showMessageDialog(null, "原始密码错误！");
                }                              
                else{               	
                	AccountKeyDao.updatePassworrd(newPsWd, userId);
                	Student s;
					try {
						s = StudentDao.getStu(userId);
						s.setPswd(newPsWd);
						StudentDao.update(s);
						data.put("code", 200);
					} catch (SQLException e) {
						e.printStackTrace();
						data.put("code", 401);
					}
                
                }
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
        
    
		ServerMain.addRequestListener("PersonInfo/change_info", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {

                Token token=(Token) msg.getData().get("token");
                int userId=token.getUserId();                
                String name = (String)msg.getData().get("name");
                int gender = (Integer)msg.getData().get("gender"); 
                Date birth = java.sql.Date.valueOf((String)msg.getData().get("birth"));
                int age = Integer.parseInt((String)msg.getData().get("age"));
                String depart = (String)msg.getData().get("depart");
                int grade = Integer.parseInt((String)msg.getData().get("grade"));
                int stuclass = Integer.parseInt((String)msg.getData().get("class"));
                String mail = (String)msg.getData().get("mail");
                String phone = (String)msg.getData().get("phone");
                String qq = (String)msg.getData().get("qq");
                //now we start to make a response Message 
                Map<String, Object> data = new HashMap<String, Object>();
                try {
					Student s = StudentDao.getStu(userId);
					s.setName(name);
					s.setSex(gender);
					s.setBirthday(birth);
					s.setAge(age);
					s.setFaculty(depart);
					s.setGrade(grade);
					s.setStuclass(stuclass);
					s.setEmail(mail);
					s.setPhone(phone);
					s.setQq(qq);
					UpdateDao.addStu(s);
					data.put("code", 200);

				} catch (SQLException e) {
					e.printStackTrace();
					data.put("code", 401);
				}               
				((ResponseSender) transferData.get("sender")).send(data);
				return true;                     
			}			
		});
    
		ServerMain.addRequestListener("PersonInfo/change_photo", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
                Token token=(Token) msg.getData().get("token");
                int userId=token.getUserId();
                Map<String, Object> data = new HashMap<String, Object>();           
                try {                	
                	Image img = ((ImageIcon)msg.getData().get("photo")).getImage(); 
					BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB); 
					Graphics2D g2 = bi.createGraphics(); 
					g2.drawImage(img, 0, 0, null); 
					g2.dispose(); 
					ImageIO.write(bi, "PNG", new File("./photo2Change/"+Integer.toString(userId)+".PNG"));                	
					data.put("code", 200);
				} catch (IOException e) {
					data.put("code", 401);
					e.printStackTrace();
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;  
			}			
		});
		ServerMain.addRequestListener("PersonInfo/getStatus", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {

                Token token=(Token) msg.getData().get("token");
                int userId=token.getUserId();  
                Map<String, Object> data = new HashMap<String, Object>();
				try {
					String status = UpdateDao.getStatus( userId);
					if(status.equals("修改成功") || status.equals("修改失败"))UpdateDao.ChangeStatus("无", userId);
					data.put("status", status);
					data.put("code", 200);
				} catch (SQLException e) {
					e.printStackTrace();
					data.put("code", 401);
				}				
             
				((ResponseSender) transferData.get("sender")).send(data);
				return true;                     
			}			
		});
		
		
		
	}
	
}


