package vCampus.server.controller;

import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import vCampus.bean.BookBean;
import vCampus.server.ServerMain;
import vCampus.server.dao.AccountKeyDao;
import vCampus.server.dao.LibraryDao;
import vCampus.server.dao.StudentDao;
import vCampus.server.dao.model.Book;
import vCampus.server.dao.model.Student;
import vCampus.utility.Token;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class Library {
	static {
		ServerMain.addRequestListener("library/addBook", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				//int userId=(int)transferData.get("userId");
				Book b=Book.toModel((BookBean)msg.getData().get("bookBean"));
				Map<String, Object> data = new HashMap<String, Object>();
				
				LibraryDao.addBook(b);
				
				data.put("code",200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/removeBook", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				//int userId=(int)transferData.get("userId");
				
				
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				Book b=Book.toModel((BookBean)msg.getData().get("bookBean"));
				LibraryDao.removeBook(b);
				
				
				data.put("code",200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("library/updateBook", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				//int userId=(int)transferData.get("userId");
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				Book b=Book.toModel((BookBean)msg.getData().get("bookBean"));
				LibraryDao.updateBook(b);
				
				data.put("code",200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		
		ServerMain.addRequestListener("library/searchBooks", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				//int userId=(int)transferData.get("userId");
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
	}
}
