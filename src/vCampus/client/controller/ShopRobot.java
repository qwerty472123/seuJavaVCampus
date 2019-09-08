package vCampus.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.GoodBean;
import vCampus.client.ClientMain;
import vCampus.client.view.ShopPanel;
import vCampus.client.view.ShopStorePanel;
import vCampus.utility.Config;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;

public class ShopRobot {

	public static void askForGoodsList(ShopPanel sp) {
		
		ClientMain.getSocketLoop().sendMsgWithCallBack(new Message("shop/refresh"), new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
								
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					List<GoodBean> goodsList = (List<GoodBean>) msg.getData().get("list");
					sp.refreshAllCallback(goodsList);					
				}else {
					Config.log("shopping refresh fail : code " + code);
				}
			}
		});
		
	}
	

	public static void queryGood(ShopStorePanel ssp) {
		ClientMain.getSocketLoop().sendMsgWithCallBack(new Message("shop/refresh"), new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
								
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					List<GoodBean> goodsList = (List<GoodBean>) msg.getData().get("list");
					ssp.setGoodsList(goodsList);
				}else {
					Config.log("shopping refresh fail : code " + code);
				}
			}
		});
	}
	
	public static void addGood(ShopStorePanel ssp, GoodBean bean) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("good", bean);
		Message msg = new Message("shop/add", data);

		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
								
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					queryGood(ssp);
				}else {
					Config.log("shopping refresh fail : code " + code);
				}
			}
		});
	}

	public static void modifyGood(ShopStorePanel ssp, GoodBean bean) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("good", bean);
		Message msg = new Message("shop/modify", data);

		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
								
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					queryGood(ssp);
					
					
				}else {
					Config.log("shopping refresh fail : code " + code);
				}
			}
		});
	}
	
	public static void deleteGood(ShopStorePanel ssp, int id) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("goodId", id);
		Message msg = new Message("shop/delete", data);

		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
								
				int code = (int) msg.getData().get("code");
				if (code == 200) {

					queryGood(ssp);	
					
				}else {
					Config.log("shopping refresh fail : code " + code);
				}
			}
		});
	}	
	
}
