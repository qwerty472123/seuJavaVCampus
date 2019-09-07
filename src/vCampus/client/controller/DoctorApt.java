package vCampus.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.DoctorBean;
import vCampus.client.ClientMain;
import vCampus.client.view.HospitalPanel;
import vCampus.utility.Config;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;

public class DoctorApt {

	public static void searchDoc(int aptDay, int aptHalf) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("aptday", aptDay*10 + aptHalf);       	      
        Message msg = new Message("SearchDoctor", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				if((boolean)msg.getData().get("noDoctorsAvailable"))
					Config.log("No doctors available at the selected time!");
				if (flag) {
					//成功
					List<DoctorBean> list = (List<DoctorBean>) msg.getData().get("list");
					List<Integer> aptNumList = (List<Integer>) msg.getData().get("aptNumList");
					List<Integer> avaNumList = (List<Integer>) msg.getData().get("avaNumList");
					((HospitalPanel)ClientMain.getTopFrame().getMainFrame().getPagePanel("医生预约")).setDoctorTable(list, aptNumList, avaNumList);
				}else {
					//失败
					Config.log("Failed to search for doctors!");	
				}                            
            }
        });	
	}
	public static void addApt(int personId, int docId, int aptDay, int aptHalf) {
		
	}
}
