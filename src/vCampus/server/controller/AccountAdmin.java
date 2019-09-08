package vCampus.server.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.server.ServerMain;
import vCampus.server.dao.*;
import vCampus.server.dao.StudentDao;
import vCampus.server.dao.model.*;
import vCampus.server.dao.model.Student;
import vCampus.utility.Token;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class AccountAdmin {

	static {
		ServerMain.addRequestListener("accountadmin/SearchAccount", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                String searchName = (String)msg.getData().get("searchName"); 
                Map<String, Object> data = new HashMap<String, Object>();
                try {
                	List<AccountKey> list = AccountKeyDao.searchForName(searchName);
					data.put("noSuchAccount", list.isEmpty());
					data.put("list", list);
					data.put("success", true);
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					data.put("success", false);
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("accountadmin/AddAccount", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                int addId = (int)msg.getData().get("addId"); 
                Map<String, Object> data = new HashMap<String, Object>();
                try {
                	AccountKeyDao.addAccount(addId, "Unnamed" + addId, "123456", "student");
					data.put("success", true);
				} catch (Exception e) {
					e.printStackTrace();
					data.put("success", false);
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("accountadmin/ChangeAuthority", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                int selectedId = (int)msg.getData().get("selectedId"); 
                String newAuthority = (String) msg.getData().get("newAuthority");
                Map<String, Object> data = new HashMap<String, Object>();
                try {
                	AccountKeyDao.updateAuthority(selectedId, newAuthority);
					data.put("success", true);
				} catch (Exception e) {
					e.printStackTrace();
					data.put("success", false);
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("accountadmin/DeleteAccount", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                int deleteId = (int)msg.getData().get("deleteId"); 
                Map<String, Object> data = new HashMap<String, Object>();
                try {
                	AccountKeyDao.deleteAccount(deleteId);
					data.put("success", true);
				} catch (Exception e) {
					e.printStackTrace();
					data.put("success", false);
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
	}
}
