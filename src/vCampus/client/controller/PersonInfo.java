package vCampus.client.controller;

import java.awt.EventQueue;
import java.awt.Image;
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

import vCampus.client.ClientMain;
import vCampus.client.view.*;
import vCampus.server.dao.AccountKeyDao;
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
					boolean flag = (boolean) msg.getData().get("success");

					if (flag) {
						//成功
						System.out.println("Succeed to change password!");

					}else {
						//失败
						System.out.println("Failed to change password!");	
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

				boolean flag = (boolean) msg.getData().get("success");
				if (flag) {
					System.out.println("Succeed to submit changed information!");
					System.out.println("Please wait for the administrator to change!");
				}else {
					System.out.println("Failed to change!");	
				}

                           
            }
        });       
    }
    
    
    public static void changePhoto(String url) throws IOException{
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("token", ClientMain.getTempData().get("token"));
		ImageIcon image = new ImageIcon(url);
		data.put("photo", image);        
        Message msg = new Message("PersonInfo/change_photo", data);
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {

                        boolean flag = (boolean) msg.getData().get("success");
                        
                        if (flag) {
                            //成功
                        	System.out.println("Succeed to submit changed photo!");
                        	System.out.println("Please wait for the administrator to change!");
                        }else {
                            //失败
                            System.out.println("Failed to change photo!");	
                        }
            
            }
        });       	
    }
}
    
    

