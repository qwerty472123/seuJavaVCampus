package vCampus.client.controller;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import vCampus.client.ClientMain;
import vCampus.client.view.*;
import vCampus.utility.Config;
import vCampus.utility.Token;
import vCampus.utility.loop.*;

public class PersonInfo {
    public static void changePsWd(String newPsWd, String initPsWd) {
	        Map<String, Object> data = new HashMap<String, Object>();
	        data.put("token", ClientMain.getTempData().get("token"));
	        data.put("newPassword", newPsWd);
	        data.put("initPassword", initPsWd);	        	        
	        Message msg = new Message("PersonInfo/change_password", data);	        
	        //define a callback
			ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
				@Override
				public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
					boolean flag = 200 == ((int) msg.getData().get("code"));

					if (flag) {
						//成功
						Config.log("Succeed to change password!");
						JOptionPane.showMessageDialog(null, "修改密码成功，请重新登录！");
						ClientMain.getTopFrame().showLoginFrame();
					}else {
						//失败
						Config.log("Failed to change password!");
						JOptionPane.showMessageDialog(null, "修改密码失败！");
					}
	            }
	        });	
	}
    
    public static void changePersonInfo(ArrayList<String> setInfo){
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", setInfo.get(0));
        if(setInfo.get(1).equals("男"))
        	data.put("gender", 0);
        else data.put("gender", 1);
        data.put("birth", setInfo.get(2));
        data.put("age", setInfo.get(3));
        data.put("depart", setInfo.get(4));
        data.put("grade", setInfo.get(5));
        data.put("class", setInfo.get(6));
        data.put("mail", setInfo.get(7));
        data.put("phone", setInfo.get(8));
        data.put("qq", setInfo.get(9));
        
        Message msg = new Message("PersonInfo/change_info", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {

				boolean flag = 200 == ((int) msg.getData().get("code"));
				if (flag) {
					JOptionPane.showMessageDialog(null, "提交成功！待管理员确认并修改！");
					Config.log("Succeed to submit changed information!");
					Config.log("Please wait for the administrator to change!");
				}else {
					JOptionPane.showMessageDialog(null, "提交失败！");
					Config.log("Failed to change!");	
				}

                           
            }
        });       
    }
    
    
    public static void changePhoto(String url) throws IOException{
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("token", ClientMain.getTempData().get("token"));
        Config.log(Integer.toString(((Token)ClientMain.getTempData().get("token")).getUserId()));
		ImageIcon image = new ImageIcon(url);
		if (image.getImageLoadStatus()!=MediaTracker.COMPLETE) image = null;		
		data.put("photo", image);
        Message msg = new Message("PersonInfo/change_photo", data);
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
                        boolean flag = 200 == ((int) msg.getData().get("code"));                       
                        if (flag) {
                            //成功
                        	JOptionPane.showMessageDialog(null, "上传成功！待管理员确认并修改！");
                        	Config.log("Succeed to submit changed photo!");
                        	Config.log("Please wait for the administrator to change!");
                        }else {
                            //失败
                        	JOptionPane.showMessageDialog(null, "上传失败！");
                            Config.log("Failed to change photo!");	
                        }
            
            }
        });       	
    }
    
    public static void getStatus(){
        Map<String, Object> data = new HashMap<String, Object>();
        Message msg = new Message("PersonInfo/getStatus", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {

				boolean flag = 200 == ((int) msg.getData().get("code"));
				if (flag) {
					String status = (String) msg.getData().get("status");
					if(status.equals("修改成功"))JOptionPane.showMessageDialog(null, "信息修改成功，请重登确认！");
					else if(status.equals("修改失败"))JOptionPane.showMessageDialog(null, "管理员审核未通过，信息修改失败！");
					Config.log("Succeed to change information!");
				}else {
					Config.log("Failed to change!");	
				}

                           
            }
        });       
    }
    
    
}
    
    

