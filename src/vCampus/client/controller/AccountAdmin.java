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
	
	public static void deleteAccount(int delete_id) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("deleteId", delete_id);       	        
        Message msg = new Message("accountadmin/DeleteAccount", data);        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");

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
	
	public static void addAccount(int add_id) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("addId", add_id);       	        
        Message msg = new Message("accountadmin/AddAccount", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");

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
	
	public static void changeAuthority(int selected_id, String new_authority) {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("selectedId", selected_id);
        data.put("newAuthority", new_authority);
        Message msg = new Message("accountadmin/ChangeAuthority", data);	        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = (boolean) msg.getData().get("success");

				if (flag) {
					//成功
					
					Config.log("Succeed to change account " + selected_id +"'s authority to " + new_authority + "!");

				}else {
					//失败
					Config.log("Failed to change account " + selected_id +"'s  authority to " + new_authority + "!");	
				}
            }
        });	
	}
}
