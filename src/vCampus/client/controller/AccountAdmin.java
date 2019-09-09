package vCampus.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import vCampus.bean.AccountKeyBean;
import vCampus.client.ClientMain;
import vCampus.client.view.accountAdmin.AccountPanel;
import vCampus.utility.Config;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;

public class AccountAdmin {

	public static void searchAccount(AccountPanel p,String search_name) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("searchName", search_name);       	      
        Message msg = new Message("accountadmin/SearchAccount", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				if((boolean)msg.getData().get("noSuchAccount"))
					Config.log("No accounts matched!");
				if (flag) {
					//成功
					ArrayList<AccountKeyBean> list = (ArrayList<AccountKeyBean>) msg.getData().get("list");
					p.setAccountTable(list);
					
				}else {
					//失败
					Config.log("Failed to search for account!");	
				}                            
            }
        });	
	}
	
	public static void deleteAccount(AccountPanel p,int delete_id) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("deleteId", delete_id);       	        
        Message msg = new Message("accountadmin/DeleteAccount", data);        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				p.refresh();
				if (flag) {
					//成功
					Config.log("Succeed to delete account " + delete_id + " !");
					
				}else {
					//失败
					Config.log("Failed to delete an account!");	
				}
            }
        });	
	}
	
	public static void addAccount(AccountPanel p, int add_id, String add_name, String add_authority) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("addId", add_id);
        data.put("addName", add_name);
        data.put("addAuthority", add_authority);
        Message msg = new Message("accountadmin/AddAccount", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				p.refresh();
				if (flag) {
					//成功
					Config.log("Succeed to add account " + add_id + " !");

				}else {
					//失败
					Config.log("Failed to add account " + add_id + " !");	
				}
            }
        });	
	}
	
	public static void changeName(AccountPanel p,int selected_id, String new_name) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("selectedId", selected_id);
        data.put("newName", new_name);
        Message msg = new Message("accountadmin/ChangeName", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");
				p.refresh();
				if (flag) {
					//成功
					
					Config.log("Succeed to change account " + selected_id +"'s name to " + new_name + "!");

				}else {
					//失败
					Config.log("Failed to change account " + selected_id +"'s name to " + new_name + "!");	
				}
            }
        });	
	}
}
