package vCampus.client.controller;

import java.util.HashMap;
import java.util.Map;

import vCampus.client.ClientMain;
import vCampus.client.view.doctor.DoctorInfoPanel;
import vCampus.utility.Token;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;

public class DoctorInfo {

	public static void getDoctorInfo() {
		Map<String, Object> data = new HashMap<String, Object>();
		int doctorId = ((Token)ClientMain.getTempData().get("token")).getUserId();
        data.put("userId", doctorId);
        Message msg = new Message("doctor/getDoctorInfo", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				String name = (String) msg.getData().get("name");
				int age = (int) msg.getData().get("age");
				boolean gender = (boolean) msg.getData().get("gender");
				String introtxt = (String) msg.getData().get("introtxt");
				((DoctorInfoPanel)ClientMain.getTopFrame().getMainFrame().getPagePanel("我的信息")).setInfoTxt(name,gender,age,introtxt, doctorId);
            }
        });	
	}
}
