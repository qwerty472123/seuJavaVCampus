package vCampus.server.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.AptRecBean;
import vCampus.server.ServerMain;
import vCampus.server.dao.AptRecsDao;
import vCampus.server.dao.StudentDao;
import vCampus.server.dao.model.AptRec;
import vCampus.utility.Config;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class AptAdmin {

	static {
		ServerMain.addRequestListener("doctor/SearchAppointments", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {   
                Date aptDate = (Date) msg.getData().get("aptdate");
                int doctorId = (int) msg.getData().get("doctorid");
                Map<String, Object> data = new HashMap<String, Object>();
                List<AptRec> list = AptRecsDao.queryAptByDoctorAndTime(doctorId, new java.sql.Date(aptDate.getTime()));
                List<AptRecBean> list_transfer = new ArrayList<AptRecBean>();
                List<String> list_name = new ArrayList<String>();
                for(AptRec i : list) {
                	list_transfer.add(i.toBean());
                	try {
						list_name.add(StudentDao.getStu(i.getPersonID()).getName());
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						Config.log(e);
					}
                }
                data.put("list", list_transfer);
                data.put("namelist", list_name);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		ServerMain.addRequestListener("doctor/SetAptDone", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                int aptId = (int) msg.getData().get("aptid");
                boolean isdone = (boolean) msg.getData().get("isdone");
                AptRecsDao.setDone(aptId, isdone);
                Map<String, Object> data = new HashMap<String, Object>();
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
	}
}
