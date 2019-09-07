package vCampus.client.view.library;

import java.util.ArrayList;

import vCampus.bean.BookBorrowRecBean;

public interface BorrowPanel extends Refreshable{
	public void setBorrowList(ArrayList<BookBorrowRecBean> data);
}
