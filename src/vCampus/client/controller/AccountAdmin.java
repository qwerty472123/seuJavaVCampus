package vCampus.client.controller;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vCampus.bean.AccountKeyBean;
import vCampus.client.ClientMain;
import vCampus.client.view.InfoAdminPanel;
import vCampus.client.view.ProfilePanel;
import vCampus.client.view.accountAdmin.AccountPanel;
import vCampus.utility.Config;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;

public class AccountAdmin {

	public static void searchAccount(AccountPanel p,String search_name) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("searchName", search_name);       	      
        Message msg = new Message("accountadmin/SearchAccount", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				if((boolean)msg.getData().get("noSuchAccount"))
					Config.log("No accounts matched!");
				if (flag) {
					//成功
					ArrayList<AccountKeyBean> list = (ArrayList<AccountKeyBean>) msg.getData().get("list");
					p.setAccountTable(list);
					
				}else {
					//失败
					Config.log("Failed to search for account!");	
				}                            
            }
        });	
	}
	
	public static void deleteAccount(AccountPanel p,int delete_id) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("deleteId", delete_id);       	        
        Message msg = new Message("accountadmin/DeleteAccount", data);        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				p.refresh();
				if (flag) {
					//成功
					Config.log("Succeed to delete account " + delete_id + " !");
					
				}else {
					//失败
					Config.log("Failed to delete an account!");	
				}
            }
        });	
	}
	
	public static void addAccount(AccountPanel p, int add_id, String add_name, String add_authority) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("addId", add_id);
        data.put("addName", add_name);
        data.put("addAuthority", add_authority);
        Message msg = new Message("accountadmin/AddAccount", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				p.refresh();
				if (flag) {
					//成功
					Config.log("Succeed to add account " + add_id + " !");

				}else {
					//失败
					Config.log("Failed to add account " + add_id + " !");	
				}
            }
        });	
	}
	
	public static void changeName(AccountPanel p,int selected_id, String new_name) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("selectedId", selected_id);
        data.put("newName", new_name);
        Message msg = new Message("accountadmin/ChangeName", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				p.refresh();
				if (flag) {
					//成功
					
					Config.log("Succeed to change account " + selected_id +"'s name to " + new_name + "!");

				}else {
					//失败
					Config.log("Failed to change account " + selected_id +"'s name to " + new_name + "!");	
				}
            }
        });	
	}
	
	
	public static void RefreshStuInfoUpdate() {
		Map<String, Object> data = new HashMap<String, Object>();
        Message msg = new Message("accountadmin/RefreshStuInfoUpdate", data);
        //getphotolist();
        //((InfoAdminPanel) ClientMain.getTopFrame().getMainFrame().getPagePanel("信息审核")).refreshComboBox();
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				if (flag) {
					//成功
					Config.log("success!");
					String[] a = (String[])msg.getData().get("list");
					((InfoAdminPanel) ClientMain.getTopFrame().getMainFrame().getPagePanel("信息审核")).setPhotoList(a);
					((InfoAdminPanel) ClientMain.getTopFrame().getMainFrame().getPagePanel("信息审核")).refreshComboBox();
					ArrayList<ArrayList<String>> updateList = (ArrayList<ArrayList<String>>)msg.getData().get("table");
					((InfoAdminPanel) ClientMain.getTopFrame().getMainFrame().getPagePanel("信息审核")).setUpdateList(updateList);
				}else {
					//失败
					Config.log("Failed to refresh!");	
				}
            }
        });	
	}
	
	public static void StuInfoUpdate(String status, int stuId) {
		Map<String, Object> data = new HashMap<String, Object>();
        Message msg = new Message("accountadmin/StuInfoUpdate", data);
        data.put("stuId", stuId);
        data.put("status", status);
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				if (flag) {
					//成功
					Config.log("success!");
					JOptionPane.showMessageDialog(null, "更新成功！");
				}else {
					//失败
					Config.log("Failed to refresh!");	
					JOptionPane.showMessageDialog(null, "更新失败！");
				}
            }
        });	
	}	
	public static void getPhoto(String path) {
		Map<String, Object> data = new HashMap<String, Object>();
        Message msg = new Message("accountadmin/getPhoto", data);
        data.put("path", path);
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				if (flag) {
					//成功
					 Config.log("success!");
					 if(msg.getData().containsKey("photo")){
						 JFrame jf=new JFrame();
						 jf.setTitle(path);
						 JPanel panel = new JPanel();
						 jf.getContentPane().add(panel, BorderLayout.CENTER);
						 JLabel label_photo = new JLabel();
						 ImageIcon image = (ImageIcon)msg.getData().get("photo");
						 image.setImage(image.getImage().getScaledInstance(210, 260,Image.SCALE_DEFAULT ));
						 label_photo.setIcon(image);
						 panel.add(label_photo);				 
						 jf.setVisible(true);
						 jf.setBounds(300, 300, 230, 300);
					 }
					 else{
						 JOptionPane.showMessageDialog(null, "该照片不存在！请刷新！");
					 }
		
				}else {
					//失败
					JOptionPane.showMessageDialog(null, "该照片不存在！请刷新！");
					Config.log("Failed to refresh!");	

				}
            }
			
			
			
        });	
	}
	
	
	public static void changePhoto(Boolean judge , String path) {
		Map<String, Object> data = new HashMap<String, Object>();
        Message msg = new Message("accountadmin/changePhoto", data);
        data.put("judge", judge);
        data.put("path", path);
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				if (flag) {
					//成功
					Config.log("success!");
					if(judge){
						try {
							//Image img = ImageIO.read(new File("./photo/"+path));
							Image img = ((ImageIcon)msg.getData().get("photo")).getImage(); 
							BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB); 
							Graphics2D g2 = bi.createGraphics(); 
							g2.drawImage(img, 0, 0, null); 
							g2.dispose(); 
							ImageIO.write(bi, "PNG", new File("./photo/"+path));
							JOptionPane.showMessageDialog(null, "修改成功！");
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "修改失败！");
							Config.log(e);
						}    
					}
					//data.put("code", 200);					
					//((InfoAdminPanel) ClientMain.getTopFrame().getMainFrame().getPagePanel("信息审核")).setPhoto((ImageIcon)msg.getData().get("photo")); 
				}else {
					//失败
					Config.log("Failed to change photo!");	

				}
            }
			
			
			
        });	
	}
	
	public static void deltePhoto(String path) {
		Map<String, Object> data = new HashMap<String, Object>();
        Message msg = new Message("accountadmin/deltePhoto", data);
        data.put("path", path);
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				if (flag) {
					//成功
					Config.log("success!");
				}else {
					//失败
					Config.log("Failed to change photo!");	

				}
            }
			
			
			
        });	
	}	
}
