package vCampus.client.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import vCampus.client.view.MainFrame;
import vCampus.server.dao.GoodsDao;
import vCampus.server.dao.model.ExpenseRec;
import vCampus.server.dao.model.Good;
import vCampus.utility.Config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShopPanel extends JPanel {
	
	private int payNum = 0;
	
	private JScrollPane jsp;
	private JPanel goodsPanel;
	
	private JPanel cartPanel;
	private JPanel cartTitle; 
	private JPanel cartList;
	private CartBar cb;
	
	private Map<Integer, List<GoodUnit>> unitMap = new HashMap<Integer, List<GoodUnit>>();
	
	/**
	 * Create the panel.
	 */
	
	
	//static {
		//Config.init("server"); // Test
	//}
		
	
	public ShopPanel() {
		
		setLayout(new BorderLayout());
		
		cartPanel = new JPanel();
		add(cartPanel, BorderLayout.SOUTH);
		cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
		
		cartTitle = new JPanel();
		cartTitle.add(new JLabel("购物车"));
		cartTitle.setVisible(false);		
		cartList = new JPanel();
		cartList.setLayout(new BoxLayout(cartList, BoxLayout.Y_AXIS));
		cartList.setVisible(false);
		cb = new CartBar();
		cb.submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ExpenseRec eps = exportExpense();

				Object[] ops = {"支付", "取消"};
				int option = JOptionPane.showOptionDialog(cb.getRootPane().getParent(),
						"<html>" + eps.getDetails() + "</html>",
						"结算",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						ops,
						ops[0]);
				
				if (option==0) {
					MainFrame mf = (MainFrame) cb.getRootPane().getParent();
					BankPanel bp = (BankPanel) mf.getPagePanel("校园银行");
					bp.newExpenseToSettle(eps);
					mf.selectCard("校园银行");
					((BankPanel) mf.getPagePanel("校园银行")).jumpToSettle();
				}
				/*
				int option = JOptionPane.showConfirmDialog(cb.getRootPane().getParent(),
							"<html>" + exportExpense().getDetails() + "</html>",
							"结算",
							JOptionPane.YES_NO_CANCEL_OPTION);
				if (option==JOptionPane.YES_OPTION) {
					System.out.println("Submitted!");
					MainFrame mf = (MainFrame) cb.getRootPane().getParent();
					//TODO
				}*/
				
			}
		});		
		cb.resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Integer i: unitMap.keySet()) {
					List<GoodUnit> gList = unitMap.get(i);
					for (GoodUnit g: gList) {
						if (g.getNum()>0) {
							g.setNum(0);
							cartList.remove(g.getDownCard());
						}
					}
				}
				refreshPayNum(0);
				cartPanel.revalidate();
			}
		});
		
		cb.foldupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (cartTitle.isVisible()) {
					cb.foldupBtn.setText("↑");
				}else {
					cb.foldupBtn.setText("↓");					
				}
				cartTitle.setVisible(!cartTitle.isVisible());
				cartList.setVisible(!cartList.isVisible());
			}
		});
		
		cartPanel.add(cartTitle);
		cartPanel.add(cartList);
		cartPanel.add(cb);
		
		goodsPanel = new JPanel();
		goodsPanel.setLayout(new BoxLayout(goodsPanel, BoxLayout.Y_AXIS));
		jsp = new JScrollPane(goodsPanel);
		//jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(jsp, BorderLayout.CENTER);
		

		
		//List<Good> goodsList = new GoodsDao().queryGoods(""); //For Test
		List<Good> goodsList = new ArrayList<Good>();
		Good good_1 = new Good();
		good_1.setGoodID(-1);
		good_1.setShopID(3);
		good_1.setGoodName("nongfushanquan");
		good_1.setPrice(180);
		good_1.setStockNum(77);
		goodsList.add(good_1);
		
		for (Good g: goodsList) {
			GoodUnit newUnit = new GoodUnit(g);
			int col = newUnit.getCol();
			if (unitMap.get(col)==null) unitMap.put(col, new ArrayList<GoodUnit>());
			unitMap.get(col).add(newUnit);			
		}
		
		List<ShopColumn> cols = new ArrayList<ShopColumn>();
		for (Integer i: unitMap.keySet()) {
			List<GoodUnit> gList = unitMap.get(i);
			List<JPanel> newList = new ArrayList<JPanel>();
			for (GoodUnit g: gList) {
				newList.add(g.getUpCard());

				g.getUpCard().addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						g.getUpCard().setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(127, 127, 255), new Color(0, 0, 255)));
				
					}
					@Override
					public void mousePressed(MouseEvent e) {
						g.getUpCard().setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(255, 127, 127), new Color(255, 127, 0)));

						g.setNum(g.getNum()+1);
						g.refreshDownCard();
						if (g.getNum()==1) {
							cartList.add(g.getDownCard());
						}
						cartList.revalidate();
						
						refreshPayNum(payNum + g.getGood().getPrice());		
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub						
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						g.getUpCard().setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(127, 127, 255), new Color(0, 0, 255)));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						g.getUpCard().setBorder(BorderFactory.createCompoundBorder(
								BorderFactory.createLineBorder(Color.blue),
								BorderFactory.createLineBorder(Color.blue)
								));
					}					
				});
				
				g.getDownCard().addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if (g.getNum()>0) g.setNum(g.getNum()-1);
						g.refreshDownCard();
						if (g.getNum()==0) {
							cartList.remove(g.getDownCard());
						}
						cartList.revalidate();

						refreshPayNum(payNum - g.getGood().getPrice());	
					}
				});
				
			}
			cols.add(new ShopColumn("class "+i, newList));
			//			
		}
		
		jsp.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				refreshGoods(cols);
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentShown(ComponentEvent e) {
				refreshGoods(cols);
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
			
		});

	}
	
	private void refreshGoods(List<ShopColumn> cols) {
		int width = jsp.getSize().width;
		int colCnt = (width-20)/250;
		if (colCnt<1) colCnt = 1;
		int rowCnt = 0;
		goodsPanel.removeAll();
		for (ShopColumn col: cols) {
			rowCnt += col.refresh(colCnt);
			goodsPanel.add(col);
		}
		goodsPanel.setPreferredSize(new Dimension(width, rowCnt*260+30*cols.size()-30));
		jsp.validate();
		jsp.repaint();
	}

	private void refreshPayNum(int newNum) {
		payNum = newNum;
		cb.textField.setText("$" + payNum/100 + "." + (payNum%100)/10 + payNum%10);
	}
	
	public ExpenseRec exportExpense() {
		String str = "<div style=\"margin:20px\">"
				+ "<p>商店购物 总金额：" + "$" + payNum/100 + "." + (payNum%100)/10 + payNum%10 + "</p>";
		str += "<p>明细：</p>"
				+ "<table width=\"360\">";
		for (Integer i: unitMap.keySet()) {
			List<GoodUnit> gList = unitMap.get(i);
			for (GoodUnit g: gList) {
				if (g.getNum()>0) {
					int p = g.getGood().getPrice()*g.getNum();
					str += "<tr>"
							+ "<td>" + g.getGood().getGoodName() + "</td>"
							+ "<td>" + g.getNum() + "</td>"
							+ "<td>" + "$"+ p/100 + "." + (p%100)/10 + p%10 + "</td><td>"
							+ "</tr>";
				}
			}
		}
		str += "</table>"
				+ "</div>";
		ExpenseRec newEpsRec = new ExpenseRec();
		newEpsRec.setId(-1);
		newEpsRec.setPersonID(0);
		newEpsRec.setFigure(payNum);
		newEpsRec.setDate(new Date());
		newEpsRec.setSource("Shop");
		newEpsRec.setDetails(str);		
		return newEpsRec;
	}
	
}
