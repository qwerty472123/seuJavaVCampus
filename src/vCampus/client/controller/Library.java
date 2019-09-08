package vCampus.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vCampus.bean.BookBean;
import vCampus.bean.BookBorrowRecBean;
import vCampus.bean.BookOrderRecBean;
import vCampus.client.ClientMain;
import vCampus.client.view.ProfilePanel;
import vCampus.client.view.library.BookPanel;
import vCampus.client.view.library.BorrowPanel;
import vCampus.client.view.library.MBookPanel;
import vCampus.client.view.library.MOrderPanel;
import vCampus.client.view.library.OrderPanel;
import vCampus.client.view.library.Refreshable;
import vCampus.utility.Config;
import vCampus.utility.Token;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;

public class Library {
	
	public static final int ORDER_DUE_DAYS=15;
	public static final int BORROW_PRIME_DUE_DAYS=30;
	public static final int BORROW_RENEWAL_DAYS=7;
	public static final int BORROW_MAX_DUE_DAYS=60;
	
	private static int getUserId() {
		return -2;
	}
	
	private static void prompt(Object p,Message msg) {
		if(msg.getData().containsKey("message")) {
			JOptionPane.showMessageDialog((JPanel)p, (String)msg.getData().get("message"), "",JOptionPane.INFORMATION_MESSAGE); 
		}
		if(msg.getData().containsKey("error")) {
			JOptionPane.showMessageDialog((JPanel)p, (String)msg.getData().get("error"), "",JOptionPane.WARNING_MESSAGE); 
		}
	}
	
	public static void addBook(BookPanel p,BookBean b) {
		System.out.println("addBook");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("bookBean", b);
		Message msg = new Message("library/addBook", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					searchBooks(p);
				}else {
					//TODO
					Config.log("addBook fail " + code);
				}
			}
		});
	}
	
	public static void updateBook(BookPanel p,BookBean b) {
		System.out.println("updateBook");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("bookBean", b);
		Message msg = new Message("library/updateBook", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					searchBooks(p);
				}else {
					//TODO
					Config.log("updateBook fail " + code);
				}
			}
		});
	}
	
	public static void removeBook(BookPanel p,BookBean b) {
		System.out.println("removeBook");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("bookBean", b);
		Message msg = new Message("library/removeBook", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					searchBooks(p);
				}else {
					//TODO
					Config.log("removeBook fail " + code);
				}
			}
		});
	}
	
	public static void searchBooks(BookPanel p){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("keyword", p.getSearchWord());
		Message msg = new Message("library/searchBooks", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
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
	
	public static void orderBook(Refreshable p,int bookId) {
		orderBook(p,getUserId(),bookId);
	}
	
	public static void orderBook(Refreshable p,int userId,int bookId) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", userId);
		data.put("bookId", bookId);
		Message msg = new Message("library/orderBook", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					if(p!=null)p.refresh();
				}else {
					//TODO
					Config.log("orderBook fail " + code);
				}
			}
		});
	}
	
	public static void cancelOrder(Refreshable p,BookOrderRecBean rc) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("rcBean", rc);
		Message msg = new Message("library/cancelOrder", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					if(p!=null)p.refresh();
				}else {
					//TODO
					Config.log("cancelOrder fail " + code);
				}
			}
		});
	}
	
	public static void borrowBook(Refreshable p,int userId,int bookId) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", userId);
		data.put("bookId", bookId);
		Message msg = new Message("library/borrowBook", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					if(p!=null)p.refresh();
				}else {
					//TODO
					Config.log("borrowBook fail " + code);
				}
			}
		});
	}
	
	public static void returnBook(Refreshable p,BookBorrowRecBean rc) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("rcBean", rc);
		Message msg = new Message("library/returnBook", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					if(p!=null)p.refresh();
				}else {
					//TODO
					Config.log("returnBook fail " + code);
				}
			}
		});
	}
	
	public static void doneOrder(Refreshable p,BookOrderRecBean rc) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("rcBean", rc);
		Message msg = new Message("library/doneOrder", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					if(p!=null)p.refresh();
					
				}else {
					//TODO
					Config.log("doneOrder fail " + code);
				}
			}
		});
	}
	
	public static void queryDoneableOrder(OrderPanel p) {

		Map<String, Object> data = new HashMap<String, Object>();
		
		Message msg = new Message("library/queryDoneableOrder", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					p.setOrderList((ArrayList<BookOrderRecBean>) msg.getData().get("orderList"));
				}else {
					//TODO
					Config.log("queryDoneableOrder fail " + code);
				}
			}
		});
	}
	
	public static void queryDuedOrder(OrderPanel p) {
		Map<String, Object> data = new HashMap<String, Object>();
		
		Message msg = new Message("library/queryDuedOrder", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					p.setOrderList((ArrayList<BookOrderRecBean>) msg.getData().get("orderList"));
				}else {
					//TODO
					Config.log("queryDuedOrder fail " + code);
				}
			}
		});
	}
	
	public static void queryOrder(OrderPanel p) {
		queryOrder(p,getUserId(),-1);
	}
	
	/**
	 * 
	 * @param p
	 * @param userId -1 for all
	 * @param bookId -1 for all
	 */
	public static void queryOrder(OrderPanel p,int userId,int bookId) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", userId);
		data.put("bookId", bookId);
		Message msg = new Message("library/queryOrder", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					p.setOrderList((ArrayList<BookOrderRecBean>) msg.getData().get("orderList"));
				}else {
					//TODO
					Config.log("queryOrder fail " + code);
				}
			}
		});
	}
	
	public static void queryDuedBorrow(BorrowPanel p) {
		Map<String, Object> data = new HashMap<String, Object>();
		
		Message msg = new Message("library/queryDuedBorrow", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					p.setBorrowList((ArrayList<BookBorrowRecBean>) msg.getData().get("borrowList"));
				}else {
					//TODO
					Config.log("queryDuedBorrow fail " + code);
				}
			}
		});
	}
	
	
	public static void queryBorrow(BorrowPanel p) {
		queryBorrow(p,getUserId(),-1);
	}
	
	/**
	 * 
	 * @param p
	 * @param userId -1 for all -2 for self
	 * @param bookId -1 for all
	 */
	public static void queryBorrow(BorrowPanel p,int userId,int bookId) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", userId);
		data.put("bookId", bookId);
		Message msg = new Message("library/queryBorrow", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					if(p!=null)p.setBorrowList((ArrayList<BookBorrowRecBean>) msg.getData().get("borrowList"));
				}else {
					//TODO
					Config.log("queryBorrow fail " + code);
				}
			}
		});
	}
	public static void renewalBorrow(Refreshable p,BookBorrowRecBean rc) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("rcBean", rc);
		Message msg = new Message("library/renewalBorrow", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					if(p!=null)p.refresh();
				}else {
					//TODO
					Config.log(" fail " + code);
				}
			}
		});
	}
}
