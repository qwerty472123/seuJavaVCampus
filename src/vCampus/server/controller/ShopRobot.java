package vCampus.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.GoodBean;
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
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				List<Good> goodsList = GoodsDao.queryGoods("");
				
				List<GoodBean> bl = new ArrayList<>();
				for (Good g: goodsList) {
					bl.add(g.toBean());
				}
				
				data.put("list", bl);
				
				data.put("code", 200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		
	}

}
