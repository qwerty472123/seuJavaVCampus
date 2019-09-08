package vCampus.client.view.library;

import java.util.ArrayList;

import vCampus.bean.BookBorrowRecBean;
import vCampus.client.view.utility.Refreshable;

public interface BorrowPanel extends Refreshable{
	public void setBorrowList(ArrayList<BookBorrowRecBean> data);
}
