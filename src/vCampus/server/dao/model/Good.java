package vCampus.server.dao.model;

import java.io.Serializable;

public class Good implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7770510819219879356L;
	
	private int goodID;
	private int shopID;
	private String goodName;
	private int price;
	private String caption;
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
