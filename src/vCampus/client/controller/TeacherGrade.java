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
public class TeacherGrade {
	public static Object[][] object;
    public static void getCourse() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("token", ClientMain.getTempData().get("token"));        
        Message msg = new Message("TeacherGrade/get_course", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");                
				if (flag) {
					//成功
					System.out.println("Succeed to update!");
				    object = (Object[][]) msg.getData().get("object");
				    

				}else {
					//失败
					System.out.println("Failed to update!");
					object = null;
				}
                                       
                            
            }
        });	
}	

}
