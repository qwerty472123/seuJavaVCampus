package vCampus.client.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vCampus.server.dao.model.Good;

public class GoodUnit {

	private int id;
	private int col;
	private int num;
	private Good good;
	private JPanel upCard;
	private JPanel downCard;
	
	public GoodUnit(Good good) {
		id = good.getGoodID();
		setCol(good.getShopID());
		num = 0;
		this.good = good;
		
		ImageIcon ii = new ImageIcon("test2.gif");
		
		int p = good.getPrice();
		upCard = new ShoppingCard(good.getGoodName(),
					ii,
					good.getStockNum() + " left",
					"$" + p/100 + "." + (p%100)/10 + p%10);
		
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

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
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
		downCard.add(new JLabel(" "), BorderLayout.NORTH);
		downCard.add(new JLabel(good.getGoodName()), BorderLayout.WEST);
		downCard.add(new JLabel(String.valueOf(num)), BorderLayout.EAST);
		downCard.add(new JLabel(" "), BorderLayout.SOUTH);
		if (num<=0) {
			//downCard.setVisible(false);
		}
		else {
			//downCard.setVisible(true);
			//downCard.revalidate();
		}
	}
	
}