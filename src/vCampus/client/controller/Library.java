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
	
	public static void addBook(LibBooksPanel p,BookBean b) {
		System.out.println("addBook");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("bookBean", b);
		Message msg = new Message("library/addBook", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					searchBooks(p);
				}else {
					//TODO
					Config.log("addBook fail " + code);
				}
			}
		});
	}
	
	public static void updateBook(LibBooksPanel p,BookBean b) {
		System.out.println("updateBook");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("bookBean", b);
		Message msg = new Message("library/updateBook", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					searchBooks(p);
				}else {
					//TODO
					Config.log("updateBook fail " + code);
				}
			}
		});
	}
	
	public static void removeBook(LibBooksPanel p,BookBean b) {
		System.out.println("removeBook");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("bookBean", b);
		Message msg = new Message("library/removeBook", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				if (code == 200) {
					searchBooks(p);
				}else {
					//TODO
					Config.log("removeBook fail " + code);
				}
			}
		});
	}
	
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
