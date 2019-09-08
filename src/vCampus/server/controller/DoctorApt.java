package vCampus.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.AptRecBean;
import vCampus.bean.DoctorBean;
import vCampus.server.ServerMain;
import vCampus.server.dao.AptRecsDao;
import vCampus.server.dao.DoctorsDao;
import vCampus.server.dao.model.AptRec;
import vCampus.server.dao.model.Doctor;
import vCampus.server.dao.service.DoctorTableService;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class DoctorApt {

	static {
		ServerMain.addRequestListener("hospital/SearchDoctor", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                int aptday = (int)msg.getData().get("aptday"); 
                Date aptdate = (Date) msg.getData().get("aptdate");
                Map<String, Object> data = new HashMap<String, Object>();
                List<Doctor> list = DoctorTableService.getAvailableDoctor(aptday);
                List<DoctorBean> list_transfer = new ArrayList<DoctorBean>();
                List<Integer> aptnum_list = new ArrayList<Integer>();
                List<Integer> avanum_list = new ArrayList<Integer>();
                for (Doctor d : list) {
                	list_transfer.add(d.toBean());
                	aptnum_list.add(DoctorTableService.queryAptNum(d.getId(), new java.sql.Date(aptdate.getTime())));
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
		ServerMain.addRequestListener("hospital/ShowDoctorIntro", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                int doctorID = (int)msg.getData().get("doctorID"); 
                Map<String, Object> data = new HashMap<String, Object>();
                String introtxt = DoctorsDao.queryIntroTxt(doctorID);
                data.put("introTxt", introtxt);
                data.put("name", DoctorsDao.queryName(doctorID));
                data.put("age", DoctorsDao.queryAge(doctorID));
                data.put("gender", DoctorsDao.queryGender(doctorID));
				data.put("success", true);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		ServerMain.addRequestListener("hospital/AddAppointment", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) { 
				AptRecBean rec = (AptRecBean) msg.getData().get("rec");
                AptRecsDao.addApt(AptRec.toModel(rec));
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("success", true);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		ServerMain.addRequestListener("hospital/ShowMyAptRecs", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				int personID = (int)msg.getData().get("personID");
				List<AptRec> list = AptRecsDao.queryAptByPerson(personID);
				List<AptRecBean> list_transfer = new ArrayList<AptRecBean>();
				List<String> doctornames = new ArrayList<String>();
				for(AptRec i:list) {
					list_transfer.add(i.toBean());
					doctornames.add(DoctorsDao.queryName(i.getDoctorID()));
				}
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("aptlist", list_transfer);
				data.put("doctornames", doctornames);
				data.put("success", true);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
	}
}
