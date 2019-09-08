package vCampus.server.controller;

import java.awt.Image;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import vCampus.bean.BookBean;
import vCampus.bean.BookBorrowRecBean;
import vCampus.bean.BookOrderRecBean;
import vCampus.server.ServerMain;
import vCampus.server.dao.AccountKeyDao;
import vCampus.server.dao.LibraryDao;
import vCampus.server.dao.StudentDao;
import vCampus.server.dao.model.Book;
import vCampus.server.dao.model.BookBorrowRec;
import vCampus.server.dao.model.BookOrderRec;
import vCampus.server.dao.model.Student;
import vCampus.utility.Token;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

import vCampus.utility.MyDate;


public class Library {
	
	public static final int ORDER_DUE_DAYS=15;
	public static final int BORROW_PRIME_DUE_DAYS=30;
	public static final int BORROW_RENEWAL_DAYS=7;
	public static final int BORROW_MAX_DUE_DAYS=60;
	
	private static int getUserId(Message msg) {
		int userId=(int)msg.getData().get("userId");
		if(userId==-2) userId=((Token) msg.getData().get("token")).getUserId();
		return userId;
	}
	
	static {
		ServerMain.addRequestListener("library/addBook", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				Book b=Book.toModel((BookBean)msg.getData().get("bookBean"));
				Map<String, Object> data = new HashMap<String, Object>();
				
				LibraryDao.addBook(b);
				
				data.put("code",200);
				data.put("message", "添加书目成功!");
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/removeBook", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				Map<String, Object> data = new HashMap<String, Object>();
				
				Book b=Book.toModel((BookBean)msg.getData().get("bookBean"));
				LibraryDao.removeBook(b);
				
				
				data.put("code",200);
				data.put("message", "删除书目成功!");
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/updateBook", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {

				Map<String, Object> data = new HashMap<String, Object>();
				
				Book b=Book.toModel((BookBean)msg.getData().get("bookBean"));
				LibraryDao.updateBook(b);
				
				data.put("code",200);
				data.put("message", "更新书目成功!");
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/queryBook", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				int bookId=(int)msg.getData().get("bookId");
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				Book bk=LibraryDao.queryBook(bookId);
				data.put("book",bk.toBean());
				data.put("code",200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/searchBooks", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				String keyword=(String)msg.getData().get("keyword");
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				ArrayList<Book> list=LibraryDao.queryBook(keyword);
				ArrayList<BookBean> listBean=new ArrayList<BookBean>();
				for(Book b:list)listBean.add(b.toBean());
				
				data.put("bookList",listBean);
				data.put("code",200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/orderBook", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				int userId=getUserId(msg);
				
				int bookId=(int) msg.getData().get("bookId");
				
				BookOrderRec rc=new BookOrderRec();
				rc.setUserId(userId);
				rc.setBookId(bookId);
				rc.setDueTime(MyDate.nowOnDay(ORDER_DUE_DAYS));
				
				if(LibraryDao.addOrderRec(rc)) {
					data.put("code",200);
					data.put("message", "预约书目成功!");
				}else {
					data.put("code", 402);
					data.put("error", "预约失败!此书目暂无已借出且未预约的余量.");
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/cancelOrder", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();

				BookOrderRec rc=BookOrderRec.toModel((BookOrderRecBean) msg.getData().get("rcBean"));
				
				LibraryDao.removeOrderRec(rc);
				data.put("code",200);
				data.put("message", "已成功取消预约!");

				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/borrowBook", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				int userId=getUserId(msg);
				int bookId=(int) msg.getData().get("bookId");
				
				BookBorrowRec rc=new BookBorrowRec();
				rc.setUserId(userId);
				rc.setBookId(bookId);
				rc.setBorrowTime(MyDate.nowOnDay(0));
				rc.setDueTime(MyDate.nowOnDay(BORROW_PRIME_DUE_DAYS));
				
				
				if(LibraryDao.addBorrowRec(rc)) {
					data.put("code",200);
					data.put("message", "借书成功!");
				}
				else {
					data.put("code", 402);
					data.put("error", "借书失败!此书目暂无可借出的余量.");
				}

				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/returnBook", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();

				BookBorrowRec rc=BookBorrowRec.toModel((BookBorrowRecBean) msg.getData().get("rcBean"));
				
				LibraryDao.removeBorrowRec(rc);
				data.put("code",200);

				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/doneOrder", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();

				BookOrderRec rc=BookOrderRec.toModel((BookOrderRecBean) msg.getData().get("rcBean"));
				BookBorrowRec brc=new BookBorrowRec();
				brc.setBookId(rc.getBookId());
				brc.setUserId(rc.getUserId());
				brc.setBorrowTime(MyDate.nowOnDay(0));
				brc.setDueTime(MyDate.nowOnDay(BORROW_PRIME_DUE_DAYS));
				
				//fool here
				if(!rc.isDoneable()) {
					data.put("code", 402);
					data.put("error", "借出失败!此预约仍处于等待状态.");
				}else {
					LibraryDao.removeOrderRec(rc);
					LibraryDao.addBorrowRec(brc);
					data.put("code",200);
				}

				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/queryOrder", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				int userId=getUserId(msg);
				int bookId=(int)msg.getData().get("bookId");
				
				Map<String, Object> data = new HashMap<String, Object>();

				ArrayList<BookOrderRec> list=LibraryDao.queryOrderRec(userId, bookId);
				ArrayList<BookOrderRecBean> beanList=new ArrayList<BookOrderRecBean>();
				for(BookOrderRec rc:list)beanList.add(rc.toBean());
				
				data.put("orderList", beanList);
				data.put("code", 200);
				
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/queryDoneableOrder", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {

				Map<String, Object> data = new HashMap<String, Object>();
				//this approach is low-efficient but simple
				ArrayList<BookOrderRec> list=LibraryDao.queryOrderRec(-1, -1);
				ArrayList<BookOrderRecBean> beanList=new ArrayList<BookOrderRecBean>();
				for(BookOrderRec rc:list)
				{
					if(rc.isDoneable())
						beanList.add(rc.toBean());
				}
				data.put("orderList", beanList);
				data.put("code", 200);
				
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/queryDuedOrder", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {

				Map<String, Object> data = new HashMap<String, Object>();
				//this approach is low-efficient but simple
				ArrayList<BookOrderRec> list=LibraryDao.queryOrderRec(-1, -1);
				ArrayList<BookOrderRecBean> beanList=new ArrayList<BookOrderRecBean>();
				for(BookOrderRec rc:list)
				{
					if(rc.getDueTime().compareTo(MyDate.nowOnDay(0))<0)
						beanList.add(rc.toBean());
				}
				data.put("orderList", beanList);
				data.put("code", 200);
				
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/queryDuedBorrow", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {

				Map<String, Object> data = new HashMap<String, Object>();
				//this approach is low-efficient but simple
				ArrayList<BookBorrowRec> list=LibraryDao.queryBorrowRec(-1, -1);
				ArrayList<BookBorrowRecBean> beanList=new ArrayList<BookBorrowRecBean>();
				for(BookBorrowRec rc:list)
				{
					if(rc.getDueTime().compareTo(MyDate.nowOnDay(0))<0)
						beanList.add(rc.toBean());
				}
				data.put("borrowList", beanList);
				data.put("code", 200);
				
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		
		ServerMain.addRequestListener("library/queryBorrow", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				int userId=getUserId(msg);
				int bookId=(int)msg.getData().get("bookId");
				
				Map<String, Object> data = new HashMap<String, Object>();

				ArrayList<BookBorrowRec> list=LibraryDao.queryBorrowRec(userId, bookId);
				ArrayList<BookBorrowRecBean> beanList=new ArrayList<BookBorrowRecBean>();
				for(BookBorrowRec rc:list)beanList.add(rc.toBean());
				
				data.put("borrowList", beanList);
				data.put("code", 200);
				
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/renewalBorrow", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();

				BookBorrowRec rc=BookBorrowRec.toModel((BookBorrowRecBean) msg.getData().get("rcBean"));
				
				if(MyDate.daysBetween(rc.getBorrowTime(), rc.getDueTime())<=BORROW_MAX_DUE_DAYS) {
					rc.setDueTime(MyDate.dateOnDay(rc.getDueTime(), BORROW_RENEWAL_DAYS));
					LibraryDao.updateBorrowRec(rc);
					data.put("code", 200);
					data.put("message", "已成功续期"+BORROW_RENEWAL_DAYS+"天!");
				}else {
					data.put("code", 402);
					data.put("error", "续期失败!已达到续期最大上限.");
				}

				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
	}
}
