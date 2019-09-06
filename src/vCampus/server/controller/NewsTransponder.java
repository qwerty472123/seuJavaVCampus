package vCampus.server.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.NewsBean;
import vCampus.server.ServerMain;
import vCampus.server.dao.NewsDao;
import vCampus.server.dao.model.News;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class NewsTransponder {
	
	static {
		
		ServerMain.addRequestListener("news/default", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();

				List<News> list = NewsDao.queryNewsAfter(new Timestamp(0));
				List<NewsBean> rlist = new ArrayList<>();
				for (News rec: list) {
					rlist.add(rec.toBean());
				}
				
				data.put("result", rlist);
				
				data.put("code", 200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
	}

}
