package vCampus.server.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.StudentBean;
import vCampus.server.ServerMain;
import vCampus.server.dao.UpdateDao;
import vCampus.server.dao.model.Student;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class InfoAdmin {

	static {
		ServerMain.addRequestListener("infoAdmin/showUpdates", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                Map<String, Object> data = new HashMap<String, Object>();
                List<Student> list = new ArrayList<Student>();
                List<StudentBean> list_transfer = new ArrayList<StudentBean>();
                try {
					list = UpdateDao.queryAllUpdates();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
                for (Student d : list) {
                	list_transfer.add(d.toBean());
                }
                data.put("updateList", list_transfer);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
	}
}
