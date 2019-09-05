package vCampus.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.server.ServerMain;
import vCampus.server.dao.GoodsDao;
import vCampus.server.dao.model.Good;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class ShopRobot {
	static {
		

		ServerMain.addRequestListener("shop/refresh", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				

				System.out.println("????");
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				List<Good> goodsList = GoodsDao.queryGoods("");
				
				data.put("list", goodsList);
				
				data.put("code", 200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		
	}

}
