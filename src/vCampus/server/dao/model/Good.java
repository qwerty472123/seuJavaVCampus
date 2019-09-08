package vCampus.server.dao.model;

import javax.swing.ImageIcon;

import vCampus.bean.GoodBean;

public class Good {
	
	private int goodID;
	private int shopID;
	private String goodName;
	private int price;
	private String caption;
	
	public GoodBean toBean() {
		GoodBean bean = new GoodBean();
		bean.setGoodID(goodID);
		bean.setShopID(shopID);
		bean.setGoodName(goodName);
		bean.setPrice(price);
		bean.setCaption(caption);
		bean.setImg(null);
		return bean;
	}
	public static Good createModel(GoodBean bean) {
		Good g = new Good();
		g.setGoodID(bean.getGoodID());
		g.setShopID(bean.getShopID());
		g.setGoodName(bean.getGoodName());
		g.setPrice(bean.getPrice());
		g.setCaption(bean.getCaption());
		return g;
	}
	
	public int getGoodID() {
		return goodID;
	}
	public void setGoodID(int goodID) {
		this.goodID = goodID;
	}
	
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public int getShopID() {
		return shopID;
	}
	public void setShopID(int shopID) {
		this.shopID = shopID;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}

}
