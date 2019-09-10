package vCampus.client.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.bean.ExpenseRecBean;
import vCampus.bean.GoodBean;
import vCampus.client.ClientMain;
import vCampus.client.controller.ShopRobot;
import vCampus.client.view.MainFrame;
import vCampus.utility.Token;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;

public class ShopPanel extends JPanel {
	
	private int payNum = 0;
	
	private JScrollPane jsp;
	private JPanel goodsPanel;
	
	private JPanel cartPanel;
	private JPanel cartTitle; 
	private JPanel cartList;
	private CartBar cb;
	private int cartCnt;
	
	private Map<Integer, List<GoodUnit>> unitMap = new HashMap<Integer, List<GoodUnit>>();
	private List<ShopColumn> cols;
	private Component horizontalGlue;
	
	/**
	 * Create the panel.
	 */	
	public ShopPanel() {
		
		setLayout(new BorderLayout());
		
		cartPanel = new JPanel();
		add(cartPanel, BorderLayout.SOUTH);
		cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
		
		cartTitle = new JPanel();
		cartTitle.setLayout(new BoxLayout(cartTitle, BoxLayout.X_AXIS));
		JLabel label0 = new JLabel(new ImageIcon(MainFrame.class.getResource("/basic/044.png")));
		cartTitle.add(label0);		
		JLabel label = new JLabel("  购物车  ");
		label.setFont(new Font("微软雅黑", Font.BOLD, 24));
		cartTitle.add(label);
		//cartTitle.add(com);
		cartTitle.setVisible(false);		
		cartList = new JPanel();
		cartList.setLayout(new BoxLayout(cartList, BoxLayout.Y_AXIS));
		cartList.setVisible(false);
		cb = new CartBar();
		cb.submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cartCnt==0) {                            //Which means none selected goods
					JOptionPane.showMessageDialog(cb.getRootPane().getParent(), "您的购物车中没有商品！");
					return;
				}				
				
				ExpenseRecBean eps = exportExpense();

				Object[] ops = {"去支付", "取消"};
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
					refreshAll();
				}
				
			}
		});		
		cb.resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshAll();
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
		
		horizontalGlue = Box.createHorizontalGlue();
		cartTitle.add(horizontalGlue);
		cartPanel.add(cartList);
		cartPanel.add(cb);
		
		goodsPanel = new JPanel();
		goodsPanel.setLayout(new BoxLayout(goodsPanel, BoxLayout.Y_AXIS));
		jsp = new JScrollPane(goodsPanel);
		//jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(jsp, BorderLayout.CENTER);
		
		
		//refreshAll(); //We cannot init it before socket hasn't been built
		cols = new ArrayList<>();
		
		jsp.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				refreshGoods(cols);
			}
			@Override
			public void componentShown(ComponentEvent e) {
				refreshGoods(cols);
			}			
		});

		refreshAll();
		
	}
	
	private void refreshGoods(List<ShopColumn> cols) {
		int width = jsp.getSize().width;
		int colCnt = (width-15)/250;
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
		cb.textField.setText("￥" + payNum/100 + "." + (payNum%100)/10 + payNum%10);
	}
	
	public ExpenseRecBean exportExpense() {
		String str = "<div style=\"margin:20px\">"
				+ "<p>商店购物 总金额：" + "￥" + payNum/100 + "." + (payNum%100)/10 + payNum%10 + "</p>";
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
							+ "<td>" + "￥"+ p/100 + "." + (p%100)/10 + p%10 + "</td><td>"
							+ "</tr>";
				}
			}
		}
		str += "</table>"
				+ "</div>";
		ExpenseRecBean newEpsRec = new ExpenseRecBean();
		newEpsRec.setId(-1);
		newEpsRec.setFigure(payNum);
		newEpsRec.setDate(new Date());
		newEpsRec.setSource("Shop");
		newEpsRec.setDetails(str);		
		return newEpsRec;
	}
	
	public void refreshAll() {
		unitMap.clear();
		cartList.removeAll();		
		ShopRobot.askForGoodsList(this);	
	}
	
	public void refreshAllCallback(List<GoodBean> goodsList) {
		
		for (GoodBean g: goodsList) {
			GoodUnit newUnit = new GoodUnit(g);
			int col = newUnit.getCol();
			if (unitMap.get(col)==null) unitMap.put(col, new ArrayList<GoodUnit>());
			unitMap.get(col).add(newUnit);			
		}
		
		cols = new ArrayList<ShopColumn>();
		for (Integer i: unitMap.keySet()) {
			List<GoodUnit> gList = unitMap.get(i);
			List<JPanel> newList = new ArrayList<JPanel>();
			for (GoodUnit g: gList) {
				newList.add(g.getUpCard());

				g.getUpCard().addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// pass
					}
					@Override
					public void mousePressed(MouseEvent e) {
						g.getUpCard().setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(255, 140, 0), new Color(255, 140, 0)));
						
						if (g.getNum()==0) {
							int option = JOptionPane.showConfirmDialog(ShopPanel.this,
										"确认购买 "+g.getGood().getGoodName()+" ?",
										"加入购物车",
										JOptionPane.YES_NO_OPTION);
							if (option!=JOptionPane.YES_OPTION) return;							
						}
						
						g.setNum(g.getNum()+1);
						g.refreshDownCard();
						if (g.getNum()==1) {
							cartList.add(g.getDownCard());
							cartCnt += 1;
						}
						cartList.revalidate();
						
						refreshPayNum(payNum + g.getGood().getPrice());		
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						g.getUpCard().setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(255, 165, 0), new Color(255, 165, 0)));					
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						g.getUpCard().setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(255, 165, 0), new Color(255, 165, 0)));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						g.getUpCard().setBorder(BorderFactory.createLineBorder(Color.lightGray));
					}					
				});
				
				MaterialUIMovement.add(g.getDownCard(), MaterialColors.ORANGE_100);
				g.getDownCard().addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if (g.getNum()>0) g.setNum(g.getNum()-1);
						g.refreshDownCard();
						if (g.getNum()==0) {
							cartList.remove(g.getDownCard());
							cartCnt -= 1;
						}
						cartList.revalidate();

						refreshPayNum(payNum - g.getGood().getPrice());	
					}
				});
				
			}
			cols.add(new ShopColumn(" ", newList));
			//TO perify
		}
		cartCnt = 0;
		refreshPayNum(0);
		refreshGoods(cols);
		this.revalidate();

	}
	
}
