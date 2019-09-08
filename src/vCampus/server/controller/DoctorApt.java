package vCampus.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.DoctorBean;
import vCampus.server.ServerMain;
import vCampus.server.dao.model.Doctor;
import vCampus.server.dao.service.DoctorTableService;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class DoctorApt {

	static {
		ServerMain.addRequestListener("SearchDoctor", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                int aptday = (int)msg.getData().get("aptday"); 
                Map<String, Object> data = new HashMap<String, Object>();
                List<Doctor> list = DoctorTableService.getAvailableDoctor(aptday);
                List<DoctorBean> list_transfer = new ArrayList<DoctorBean>();
                List<Integer> aptnum_list = new ArrayList<Integer>();
                List<Integer> avanum_list = new ArrayList<Integer>();
                for (Doctor d : list) {
                	list_transfer.add(d.toBean());
                	aptnum_list.add(DoctorTableService.queryAptNum(d.getId(), aptday));
                	avanum_list.add(DoctorTableService.queryAvailableNum(d.getId(), aptday));
                }
				data.put("noDoctorsAvailable", list.isEmpty());
				data.put("list", list_transfer);
				data.put("aptNumList", aptnum_list);
				data.put("avaNumList", avanum_list);
				data.put("success", true);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
	}
}
