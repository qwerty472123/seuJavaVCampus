package vCampus.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vCampus.bean.NewsBean;
import vCampus.client.ClientMain;
import vCampus.client.view.MainFrame;
import vCampus.client.view.NewsMgrPanel;
import vCampus.client.view.NewsPanel;
import vCampus.client.view.NewsPubPanel;
import vCampus.utility.Config;
import vCampus.utility.Token;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;

public class NewsTransponder {

	public static void requestNewLetter(NewsPanel lp) {
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
	
	public static void requestDelNews(NewsMgrPanel np, int id) {

		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("newsId", id);
				
		Message msg = new Message("news/delete", data);
		
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					requestNewLetter(np); // refresh
				}else {
					//TODO
					Config.log("newsRequest fail " + code);
				}
			}
		});
		
	}
	
	public static void publishNews(NewsPubPanel np, NewsBean bean) {
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("userId", ((Token) ClientMain.getTempData().get("token")).getUserId());
		data.put("news", bean);
				
		Message msg = new Message("news/publish", data);
		
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					np.clearAll();
					requestNewLetter((NewsPanel)((MainFrame)np.getRootPane().getParent()).getPagePanel("校际资讯"));
					requestNewLetter((NewsPanel)((MainFrame)np.getRootPane().getParent()).getPagePanel("资讯管理"));
					((MainFrame)np.getRootPane().getParent()).selectCard("校际资讯");
				}else {
					//TODO
					Config.log("newsRequest fail " + code);
				}
			}
		});
	}
	
}
