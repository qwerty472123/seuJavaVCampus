package vCampus.client.controller;

import java.util.List;
import java.util.Map;

import vCampus.client.ClientMain;
import vCampus.client.view.ShopPanel;
import vCampus.server.dao.model.Good;
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
					List<Good> goodsList = (List<Good>) msg.getData().get("list");
					sp.refreshAllCallback(goodsList);	
				}else {
					Config.log("shopping refresh fail : code " + code);
				}
			}
		});
		
	}

}
