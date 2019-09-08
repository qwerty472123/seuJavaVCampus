package vCampus.bean;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class GoodBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7770510819219879356L;

	private int goodID;
	private int shopID;
	private String goodName;
	private int price;
	private String caption;
	
	private ImageIcon img;

	public int getGoodID() {
		return goodID;
	}
	public void setGoodID(int goodID) {
		this.goodID = goodID;
	}
	public int getShopID() {
		return shopID;
	}
	public void setShopID(int shopID) {
		this.shopID = shopID;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
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
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}




	
}
