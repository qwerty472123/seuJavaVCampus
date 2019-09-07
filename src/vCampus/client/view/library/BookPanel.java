package vCampus.client.view.library;

import java.util.ArrayList;

import vCampus.bean.BookBean;

public interface BookPanel extends Refreshable{
	public String getSearchWord();
	public void setBookList(ArrayList<BookBean> data);
}
