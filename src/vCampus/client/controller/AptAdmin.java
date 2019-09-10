package vCampus.client.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.AptRecBean;
import vCampus.client.ClientMain;
import vCampus.client.view.doctor.AppointmentsPanel;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;

public class AptAdmin {

	public static void searchAptForDoctor(Date aptDate, int aptHalf) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("aptdate", aptDate);
        Calendar cal = Calendar.getInstance();
		cal.setTime(aptDate);
		cal.set(Calendar.HOUR_OF_DAY,aptHalf==0?8:14);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		aptDate=cal.getTime();
        data.put("aptdate", aptDate);
        Message msg = new Message("doctor/SearchAppointments", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				List<AptRecBean> list = (List<AptRecBean>) msg.getData().get("list");
				List<String> namelist = (List<String>) msg.getData().get("namelist");
				((AppointmentsPanel)ClientMain.getTopFrame().getMainFrame().getPagePanel("预约查看")).setAptTable(list, namelist);
            }
        });	
	}
	public static void setAptDone(int aptid, boolean done) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("aptid", aptid);
        data.put("isdone", done);
        Message msg = new Message("doctor/SetAptDone", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				((AppointmentsPanel)ClientMain.getTopFrame().getMainFrame().getPagePanel("预约查看")).setDone(done);;
            }
        });	
	}
}
