package vCampus.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import vCampus.bean.BookBean;
import vCampus.client.ClientMain;
import vCampus.client.view.LibBooksPanel;
import vCampus.client.view.ProfilePanel;
import vCampus.utility.Config;
import vCampus.utility.Token;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;

public class Library {
	
	
	
	public static void searchBooks(LibBooksPanel p){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("keyword", p.getSearchWord());
		Message msg = new Message("library/searchBooks", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					ArrayList<BookBean> list=(ArrayList<BookBean>)msg.getData().get("bookList");
					p.setBookList(list);
				}else {
					//TODO
					Config.log("searchBooks fail " + code);
				}
			}
		});
	}
}
