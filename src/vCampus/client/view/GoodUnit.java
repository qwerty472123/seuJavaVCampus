package vCampus.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vCampus.bean.GoodBean;

public class GoodUnit {

	private int id;
	private int col;
	private int num;
	private GoodBean good;
	private JPanel upCard;
	private JPanel downCard;
	
	public GoodUnit(GoodBean good) {
		id = good.getGoodID();
		setCol(good.getShopID());
		num = 0;
		this.good = good;
		
		ImageIcon ii  = good.getImg();
		
		int p = good.getPrice();
		upCard = new ShoppingCard(good.getGoodName(),
					ii,
					good.getCaption(),
					"￥" + p/100 + "." + (p%100)/10 + p%10);
		
		downCard = new JPanel();
		downCard.setBorder(BorderFactory.createLineBorder(Color.red));
		refreshDownCard();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public GoodBean getGood() {
		return good;
	}

	public void setGood(GoodBean good) {
		this.good = good;
	}

	public JPanel getUpCard() {
		return upCard;
	}

	public void setUpCard(JPanel upCard) {
		this.upCard = upCard;
	}

	public JPanel getDownCard() {
		return downCard;
	}

	public void setDownCard(JPanel downCard) {
		this.downCard = downCard;
	}

	public void refreshDownCard() {
		downCard.removeAll();
		downCard.setLayout(new BorderLayout());
		
		JLabel north = new JLabel(" ");
		north.setBackground(null);
		JLabel south = new JLabel(" ");
		south.setBackground(null);
		JLabel west = new JLabel(good.getGoodName());
		west.setFont(new Font("宋体", Font.BOLD, 15));
		west.setBackground(null);
		JLabel east = new JLabel(String.valueOf(num));
		east.setBackground(null);
		east.setFont(new Font("宋体", Font.BOLD, 15));
		downCard.add(north, BorderLayout.NORTH);
		downCard.add(west, BorderLayout.WEST);
		downCard.add(east, BorderLayout.EAST);
		downCard.add(south, BorderLayout.SOUTH);
		if (num<=0) {
			//downCard.setVisible(false);
		}
		else {
			//downCard.setVisible(true);
			//downCard.revalidate();
		}
	}
	
}
