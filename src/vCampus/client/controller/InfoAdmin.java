package vCampus.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.StudentBean;
import vCampus.client.ClientMain;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;

public class InfoAdmin {

	public static void showUpdates() {
		Map<String, Object> data = new HashMap<String, Object>();    	      
        Message msg = new Message("infoAdmin/showUpdates", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				List<StudentBean> updatelist = (List<StudentBean>)msg.getData().get("updateList");
				
            }
        });	
	}
}
