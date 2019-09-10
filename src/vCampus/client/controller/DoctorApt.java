package vCampus.client.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.AptRecBean;
import vCampus.bean.DoctorBean;
import vCampus.client.ClientMain;
import vCampus.client.view.hospital.HospitalPanel;
import vCampus.utility.Config;
import vCampus.utility.Token;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;

public class DoctorApt {

	public static void searchDoc(int aptDay, int aptHalf, Date aptDate) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("aptday", aptDay*10 + aptHalf);
        Calendar cal = Calendar.getInstance();
		cal.setTime(aptDate);
		cal.set(Calendar.HOUR_OF_DAY,aptHalf==0?8:14);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		aptDate=cal.getTime();
        data.put("aptdate", aptDate);
        Message msg = new Message("hospital/SearchDoctor", data);	        
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
	public static void addApt(int doctorId, Date aptDate, int aptHalf, String remark) {
		Map<String, Object> data = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(aptDate);
		cal.set(Calendar.HOUR_OF_DAY,aptHalf==0?8:14);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		aptDate=cal.getTime();
        AptRecBean rec =new AptRecBean();
        rec.setDoctorID(doctorId);
        rec.setPersonID(((Token)ClientMain.getTempData().get("token")).getUserId());
        rec.setAptday(aptDate);
        rec.setRemark(remark);
        rec.setOperTime(new Date());
        data.put("rec", rec);
        Message msg = new Message("hospital/AddAppointment", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				if (flag) {
					//成功
					Config.log("Succeed to add an appointment!");
;				}else {
					//失败
					Config.log("Failed to add an appointment!");	
				}                            
            }
        });	
	}
	public static void showDoctorIntro(int doctorId) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("doctorID", doctorId);       	      
        Message msg = new Message("hospital/ShowDoctorIntro", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				if (flag) {
					//成功
					String introTxt = (String)msg.getData().get("introTxt");
					String name = (String)msg.getData().get("name");
					int age = (int)msg.getData().get("age");
					boolean gender = (boolean)msg.getData().get("gender");
					((HospitalPanel)ClientMain.getTopFrame().getMainFrame().getPagePanel("医生预约")).setDoctorIntro(name,age,gender,introTxt,doctorId);
				}else {
					//失败
					Config.log("Failed to get doctor's introduction!");	
				}                            
            }
        });	
	}
	
	public static void showMyAptRecs() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("personID", ((Token)ClientMain.getTempData().get("token")).getUserId());
        Message msg = new Message("hospital/ShowMyAptRecs", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				if (flag) {
					//成功
					List<AptRecBean> list = (List<AptRecBean>)msg.getData().get("aptlist");
					List<String> doctornames = (List<String>) msg.getData().get("doctornames");
					((HospitalPanel)ClientMain.getTopFrame().getMainFrame().getPagePanel("医生预约")).setMyAptTable(list, doctornames);;
				}else {
					//失败
					Config.log("Failed to get my appointment records");	
				}                            
            }
        });	
	}
}
