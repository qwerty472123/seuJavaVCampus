package vCampus.client.view.library;

import java.util.ArrayList;

import vCampus.bean.BookOrderRecBean;
import vCampus.client.view.utility.Refreshable;

public interface OrderPanel extends Refreshable{
	public void setOrderList(ArrayList<BookOrderRecBean> data);
}
