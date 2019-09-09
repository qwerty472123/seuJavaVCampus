package vCampus.server.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.AccountKeyBean;
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
                	
                	ArrayList<AccountKeyBean> beanList=new ArrayList<AccountKeyBean>();
                	
                	for(AccountKey a:list) {
                		beanList.add(a.toBean());
                	}
                	
					data.put("noSuchAccount", list.isEmpty());
					data.put("list", beanList);
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
                String addName = (String) msg.getData().get("addName");
                String addAuthority = (String) msg.getData().get("addAuthority");
                Map<String, Object> data = new HashMap<String, Object>();
                try {
                	AccountKey a = new AccountKey();
                	a.init();
                	a.setUserId(addId);
                	a.setUserName(addName);
                	a.setAuthority(addAuthority);
                	AccountKeyDao.addAccount(a);
					data.put("success", true);
				} catch (Exception e) {
					e.printStackTrace();
					data.put("success", false);
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("accountadmin/ChangeName", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                int selectedId = (int)msg.getData().get("selectedId"); 
                String newName = (String) msg.getData().get("newName");
                Map<String, Object> data = new HashMap<String, Object>();
                try {
                	AccountKey a = new AccountKey();
                	a.setUserId(selectedId);
                	a.setUserName(newName);
                	a.setPassword(AccountKeyDao.queryPassword(selectedId));
                	a.setAuthority(AccountKeyDao.queryAuthority(selectedId));
                	AccountKeyDao.updateAccountKey(a);
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
