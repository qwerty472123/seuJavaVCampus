package vCampus.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.NewsBean;
import vCampus.client.ClientMain;
import vCampus.client.view.NewsPanel;
import vCampus.utility.Config;
import vCampus.utility.Token;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;

public class NewsTransponder {

	public static void requestNewLetter(NewsPanel lp, String reg) {
		Map<String, Object> data = new HashMap<String, Object>();
				
		Message msg = new Message("news/default", data);
		
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				
				int code = (int) msg.getData().get("code");
				if (code == 200) {					
					List<NewsBean> rlist = (List<NewsBean>) msg.getData().get("result");										
					lp.setDefaultList(rlist);
					lp.refreshNewsCnt();
					lp.refreshNews();
				}else {
					//TODO
					Config.log("newsRequest fail " + code);
				}
			}
		});
	}
	
}
