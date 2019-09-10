package vCampus.server.controller;

import java.util.HashMap;
import java.util.Map;

import vCampus.server.ServerMain;
import vCampus.server.dao.DoctorsDao;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class DoctorInfo {

	static {
		ServerMain.addRequestListener("doctor/getDoctorInfo", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {   
				int doctorID = (int)msg.getData().get("userId");
                Map<String, Object> data = new HashMap<String, Object>();
                String name = DoctorsDao.queryName(doctorID);
                boolean gender = DoctorsDao.queryGender(doctorID);
                int age = DoctorsDao.queryAge(doctorID);
                String introtxt = DoctorsDao.queryIntroTxt(doctorID);
                System.out.println(name);
                data.put("name", name);
                data.put("gender", gender);
                data.put("age", age);
                data.put("introtxt", introtxt);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
	}
}
