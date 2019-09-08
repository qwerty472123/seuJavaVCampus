package vCampus.client.view;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.bean.BankAccountBean;
import vCampus.bean.ExpenseRecBean;
import vCampus.client.controller.Bank;
import vCampus.client.view.utility.GroupifyBtnAndCard;
import vCampus.client.view.utility.MyTable;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;

public class BankPanel extends JPanel {
	
	private BankAccountBean account;
	private JLabel balanceLabel;
	private JLabel nameLabel;
	
	private JPanel pages;
	
	private JPasswordField passwordField;
	private JTextField rechargeField;
	
	private JLabel settleTitle;
	private JPanel settleContent;
	private JScrollPane settleScroll;
	private int settleCnt = 0;
	
	private JButton refreshBtn;	
	private List< ArrayList<String>> recordData;
	private MyTable recordTable;
	
	/**
	 * Create the panel.
	 */
	public BankPanel() {
		setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel menu = new JPanel();
		menu.setBackground(Color.WHITE);
		panel_2.add(menu, BorderLayout.WEST);
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_21 = new JLabel("Options");
		lblNewLabel_21.setBackground(Color.WHITE);
		lblNewLabel_21.setForeground(new Color(60, 179, 113));
		lblNewLabel_21.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_21.setHorizontalAlignment(SwingConstants.CENTER);
		menu.add(lblNewLabel_21);
		
		JButton basicBtn = new JButton("账户余额");
		basicBtn.setForeground(Color.GRAY);
		basicBtn.setBackground(new Color(255, 250, 240));
		basicBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(basicBtn);
		
		JButton secureBtn = new JButton("账户充值");
		secureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		secureBtn.setForeground(Color.GRAY);
		secureBtn.setBackground(new Color(255, 250, 240));
		secureBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(secureBtn);
		
		JButton settleBtn = new JButton("等待结算");
		settleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		settleBtn.setForeground(Color.GRAY);
		settleBtn.setBackground(new Color(255, 250, 240));
		settleBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(settleBtn);
		
		JButton recordBtn = new JButton("账单记录");
		recordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		recordBtn.setForeground(Color.GRAY);
		recordBtn.setBackground(new Color(255, 250, 240));
		recordBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(recordBtn);
		
		
		pages = new JPanel();
		panel_2.add(pages, BorderLayout.CENTER);
		pages.setLayout(new CardLayout(0, 0));
		
		JPanel profilepage = new JPanel();
		pages.add(profilepage, "name_1");
		profilepage.setBackground(Color.WHITE);
		profilepage.setLayout(new BoxLayout(profilepage, BoxLayout.Y_AXIS));
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		profilepage.add(verticalGlue_1);
		
		JPanel panel_1 = new JPanel();
		profilepage.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel("");
		panel_1.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(BankPanel.class.getResource("/av.jpg")));
		

		Component verticalGlue_mid = Box.createVerticalGlue();
		profilepage.add(verticalGlue_mid);
		
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		profilepage.add(verticalGlue_2);
		
		JSeparator separator = new JSeparator();
		profilepage.add(separator);
		
		JPanel panel_3 = new JPanel();
		profilepage.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue_1);
		
		JPanel panel = new JPanel();
		panel_3.add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label_2 = new JLabel("当前余额");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.GRAY);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		panel.add(label_2);
		
		balanceLabel = new JLabel("New label");
		balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		balanceLabel.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		panel.add(balanceLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		JLabel label_4 = new JLabel("");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(label_4);
		
		/*
		JLabel label_1 = new JLabel("Name 姓名 ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.GRAY);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(label_1);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(lblNewLabel_5);
		*/
		
		JLabel lblNewLabel_9 = new JLabel("Account 账户");
		lblNewLabel_9.setForeground(Color.GRAY);
		lblNewLabel_9.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_9);
		
		nameLabel = new JLabel("New label");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(nameLabel);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue);
		
		Component verticalGlue = Box.createVerticalGlue();
		profilepage.add(verticalGlue);
		
		JPanel rechargepage = new JPanel();
		rechargepage.setBackground(Color.WHITE);
		pages.add(rechargepage, "name_2");
		rechargepage.setLayout(new BoxLayout(rechargepage, BoxLayout.Y_AXIS));
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		rechargepage.add(verticalGlue_4);
		
		JPanel panel_5 = new JPanel();
		rechargepage.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel_5.add(horizontalGlue_2);
		
		JPanel panel_4 = new JPanel();
		panel_5.add(panel_4);
		panel_4.setLayout(new GridLayout(6,0, 0, 0));
		
		Component verticalGlue_5 = Box.createVerticalGlue();
		panel_4.add(verticalGlue_5);
		
		JLabel lblNewLabel = new JLabel("密码（独立验证）");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel_4.add(lblNewLabel);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_15 = new JLabel("[");
		lblNewLabel_15.setForeground(SystemColor.activeCaption);
		lblNewLabel_15.setFont(new Font("微软雅黑", Font.BOLD, 30));
		panel_7.add(lblNewLabel_15);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 18));
		panel_7.add(passwordField);
		
		JLabel lblNewLabel_18 = new JLabel("]");
		lblNewLabel_18.setFont(new Font("微软雅黑", Font.BOLD, 30));
		lblNewLabel_18.setForeground(SystemColor.activeCaption);
		panel_7.add(lblNewLabel_18);
		
		
		JLabel lblNewLabel_13 = new JLabel("金额（5~500元）");
		lblNewLabel_13.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_13.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel_4.add(lblNewLabel_13);
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_16 = new JLabel("[");
		lblNewLabel_16.setForeground(SystemColor.activeCaption);
		lblNewLabel_16.setFont(new Font("微软雅黑", Font.BOLD, 30));
		panel_8.add(lblNewLabel_16);
		
		rechargeField = new JTextField();
		panel_8.add(rechargeField);
		rechargeField.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_19 = new JLabel("]");
		lblNewLabel_19.setFont(new Font("微软雅黑", Font.BOLD, 30));
		lblNewLabel_19.setForeground(SystemColor.activeCaption);
		panel_8.add(lblNewLabel_19);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		panel_5.add(horizontalGlue_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EmptyBorder(20, 0, 0, 0));
		rechargepage.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton rechargeButton = new JButton("充值");
		rechargeButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		rechargeButton.setBackground(SystemColor.activeCaption);
		rechargeButton.setForeground(Color.WHITE);		
		MaterialUIMovement.add (rechargeButton, MaterialColors.BLUE_100);
		panel_6.add(rechargeButton);
		
		rechargeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(rechargeButton.getRootPane().getParent(), "充值功能尚未开放，敬请期待");
			}
		});
		
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		rechargepage.add(verticalGlue_3);
		
		JPanel settlePage = new JPanel();
		settlePage.setBackground(Color.WHITE);
		pages.add(settlePage, "name_3");
		settlePage.setLayout(new BorderLayout());
		
		settleTitle = new JLabel("当前没有需结算的账单");
		settleTitle.setHorizontalAlignment(SwingConstants.CENTER);
		settleTitle.setFont(new Font("微软雅黑", Font.ITALIC, 24));
		settlePage.add(settleTitle, BorderLayout.NORTH);

		
		settleContent = new JPanel();
		settleContent.setLayout(new BoxLayout(settleContent, BoxLayout.Y_AXIS));
		settleScroll = new JScrollPane(settleContent);
		settlePage.add(settleScroll, BorderLayout.CENTER);
		
		JPanel recordPage = new JPanel();
		recordPage.setLayout(new BorderLayout(0, 0));
		pages.add(recordPage, "name_4");
		
		recordTable = new MyTable(new String[] {"流水ID","用户ID","金额","日期", "收款方"});
		recordTable.setEditable(false);
		JScrollPane tableContainer=new JScrollPane(recordTable);
		tableContainer.getViewport().setBackground(MaterialColors.WHITE);
		Bank.askForRec(this);
		recordPage.add(tableContainer, BorderLayout.CENTER);
		
		Color light_green_color = new Color(191, 255, 191);
		JPanel detailCol = new JPanel();
		detailCol.setBorder(BorderFactory.createLineBorder(Color.black));
		detailCol.setBackground(light_green_color);
		detailCol.setLayout(new BoxLayout(detailCol, BoxLayout.Y_AXIS));
		
		JPanel detailTitle = new JPanel();
		detailTitle.setLayout(new BorderLayout());
		detailCol.add(detailTitle);		
		JLabel label_5 = new JLabel(" 详 情 ");
		label_5.setBackground(light_green_color);
		label_5.setFont(new Font("微软雅黑", Font.ITALIC, 16));
		detailTitle.add(label_5, BorderLayout.CENTER);
		refreshBtn = new JButton("刷新表单");
		detailTitle.add(refreshBtn, BorderLayout.EAST);
		
		BankPanel tmp = this;
		refreshBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Bank.askForRec(tmp);
			}
		});
		
		JLabel detailLabel = new JLabel("");
		detailLabel.setBackground(light_green_color);
		JScrollPane djsp = new JScrollPane(detailLabel);
		djsp.setPreferredSize(new Dimension(9999, 150));
		detailCol.add(djsp);
		recordPage.add(detailCol, BorderLayout.SOUTH);
		
		recordTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int cur=recordTable.getSelectedRow();
				String detail = recordData.get(cur).get(5);
				detail = "<html>" + detail + "</html>";
				detailLabel.setText(detail);
			}
		});
		
		
		GroupifyBtnAndCard.groupBtnsAndCards(new JButton[]{basicBtn, secureBtn, settleBtn, recordBtn}, pages);

		refreshInfo();
	}
	
	
	public void jumpToSettle() {
		((CardLayout)pages.getLayout()).show(pages, "name_3");
		
		JScrollBar jsb = settleScroll.getVerticalScrollBar();
		jsb.setValue(jsb.getMaximum());
	}
	
	public void jumpToResult() {
		((CardLayout)pages.getLayout()).show(pages, "name_4");	
	}
	
	public void newExpenseToSettle(ExpenseRecBean newEps) {
		
		++settleCnt;
		settleTitle.setText("当前等待结算的账单");
		
		JPanel newPanel = new JPanel();
		newPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createEmptyBorder(5, 5, 5, 5),
					BorderFactory.createLineBorder(Color.green)));
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
		int p = newEps.getFigure();
		JLabel title_1 = new JLabel("收款方： " + newEps.getSource());		
		title_1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		JLabel title_2 = new JLabel("待支付总额： $" + (p/100) + "." + (p%100)/10 + p%10);		
		title_2.setFont(new Font("微软雅黑", Font.BOLD, 16));
		newPanel.add(title_1);
		newPanel.add(title_2);
		newPanel.add(new JLabel("<html>" + newEps.getDetails() + "</html>"));
		JButton newBtn = new JButton("确认支付"); 
		JPanel btnBox = new JPanel();
		btnBox.setLayout(new BoxLayout(btnBox, BoxLayout.X_AXIS));
		btnBox.add(Box.createHorizontalGlue()); 
		btnBox.add(newBtn);
		btnBox.add(Box.createHorizontalGlue());
		newPanel.add(btnBox);
		
		newBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] ops = {"确认支付", "取消"};
				int option = JOptionPane.showOptionDialog(newBtn.getRootPane().getParent(),
						"<html>"
						+ "<p>" + title_1.getText() + "</p>"
						+ "<p>" + title_2.getText() + "</p>"
						+ "</html>",
						"结算",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						ops,
						ops[0]);
				
				if (option==0) {
					BankConfirmDialog newDialog = new BankConfirmDialog((Frame) newBtn.getRootPane().getParent(), newEps);
					newDialog.setLocation((int)newBtn.getRootPane().getParent().getLocation().getX()+newBtn.getRootPane().getParent().getWidth()/2,
							(int)newBtn.getRootPane().getParent().getLocation().getY() + newBtn.getRootPane().getParent().getHeight()/3);
										
					newDialog.addWindowListener(new WindowAdapter() {
						private void pro() {
							if (newDialog.isSuccess()) {
								refreshInfo();
								//addRecord(newDialog.getErec());
								Bank.askForRec(BankPanel.this);
								jumpToResult();
								settleContent.remove(newPanel);
								--settleCnt;
								if (settleCnt==0) {
									settleTitle.setText("当前没有需结算的账单");									
								}
								settleContent.revalidate();
							}							
						}
						@Override
						public void windowClosing(WindowEvent e) {
							pro();
						}
						@Override
						public void windowClosed(WindowEvent e) {
							pro();
						}						
					});
					
					newDialog.setVisible(true);

				}
				
			}
		});
		
		settleContent.add(newPanel);
		this.revalidate();
	}

	public void setRecordData(List< ArrayList<String>> recordData) {
		this.recordData = recordData;
	}
	
	public void refreshRecordTable() {		
		MyTable table = recordTable;
		table.removeAllRows();
		for(ArrayList<String> one:recordData) {
			ArrayList<String> cur = new ArrayList<>();
			for (String str: one) {
				cur.add(str);
			}
			cur.remove(5);
			table.addRow(cur);
		}
		table.revalidate();
		table.repaint();
	}

	public BankAccountBean getAccount() {
		return account;
	}
	public void setAccount(BankAccountBean account) {
		this.account = account;
		
		int p = account.getBalance();
		balanceLabel.setText("$" + p/100 + "." + (p%100)/10 + p%10);
		nameLabel.setText(account.getAccountName());
		
		pages.revalidate();//To modify
	}
		
	public void refreshInfo() {
		Bank.refreshInfo(this);
	}


}
