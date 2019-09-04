package vCampus.client.view;


import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.client.view.utility.GroupifyBtnAndCard;
import vCampus.client.view.utility.MyTable;
import vCampus.server.dao.model.ExpenseRec;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.UIManager;

public class BankPanel extends JPanel {
	
	private JPanel pages;
	
	private JPasswordField passwordField;
	private JTextField rechargeField;
	
	private JLabel settleTitle;
	private JPanel settleContent;
	
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
		
		JLabel label_3 = new JLabel("New label");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		panel.add(label_3);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		JLabel label_4 = new JLabel("");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(label_4);
		
		JLabel label_1 = new JLabel("Name 姓名 ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.GRAY);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(label_1);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_9 = new JLabel("Account 账户");
		lblNewLabel_9.setForeground(Color.GRAY);
		lblNewLabel_9.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(lblNewLabel_10);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue);
		
		Component verticalGlue = Box.createVerticalGlue();
		profilepage.add(verticalGlue);
		
		JPanel securepage = new JPanel();
		securepage.setBackground(Color.WHITE);
		pages.add(securepage, "name_2");
		securepage.setLayout(new BoxLayout(securepage, BoxLayout.Y_AXIS));
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		securepage.add(verticalGlue_4);
		
		JPanel panel_5 = new JPanel();
		securepage.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel_5.add(horizontalGlue_2);
		
		JPanel panel_4 = new JPanel();
		panel_5.add(panel_4);
		panel_4.setLayout(new GridLayout(6,0, 0, 0));
		
		Component verticalGlue_5 = Box.createVerticalGlue();
		panel_4.add(verticalGlue_5);
		
		JLabel lblNewLabel = new JLabel("密码（再次验证）");
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
		securepage.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("充值");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setForeground(Color.WHITE);
		
		MaterialUIMovement.add (btnNewButton, MaterialColors.BLUE_100);
		
		panel_6.add(btnNewButton);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		securepage.add(verticalGlue_3);
		
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
		settlePage.add(settleContent, BorderLayout.CENTER);
		
		JPanel recordPage = new JPanel();
		recordPage.setLayout(new BorderLayout(0, 0));
		pages.add(recordPage, "name_4");
		
		recordTable = new MyTable(new String[] {"流水ID","用户ID","金额","日期", "来源", "备注"});
		JScrollPane tableContainer=new JScrollPane(recordTable);
		tableContainer.getViewport().setBackground(MaterialColors.WHITE);
		refreshRecordTable();
		recordPage.add(tableContainer, BorderLayout.CENTER);
		
		JPanel detailCol = new JPanel();
		detailCol.setBorder(BorderFactory.createLineBorder(Color.black));
		detailCol.setLayout(new BoxLayout(detailCol, BoxLayout.Y_AXIS));
		detailCol.add(new JLabel("详情"));		
		JLabel detailLabel = new JLabel("c");
		JScrollPane djsp = new JScrollPane(detailLabel);
		djsp.setPreferredSize(new Dimension(9999, 150));
		detailCol.add(djsp);
		recordPage.add(detailCol, BorderLayout.SOUTH);
		
		recordTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int cur=recordTable.getSelectedRow();
				String detail=(String)recordTable.getValueAt(cur, 5);
				detail = "<html>" + detail + "</html>";
				detailLabel.setText(detail);
			}			
		});
		
		
		GroupifyBtnAndCard.groupBtnsAndCards(new JButton[]{basicBtn, secureBtn, settleBtn, recordBtn}, pages);
		
	}
	
	
	public void jumpToSettle() {
		((CardLayout)pages.getLayout()).show(pages, "name_3");
	}
	
	public void newExpenseToSettle(ExpenseRec newEps) {
		settleTitle.setText("当前等待结算的账单");
		
		JPanel newPanel = new JPanel();
		newPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createEmptyBorder(5, 5, 5, 5),
					BorderFactory.createLineBorder(Color.green)));
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
		newPanel.add(new JLabel("<html>" + newEps.getDetails() + "</html>"));
		JButton newBtn = new JButton("确认支付"); 
		JPanel btnBox = new JPanel();
		btnBox.setLayout(new BoxLayout(btnBox, BoxLayout.X_AXIS));
		btnBox.add(Box.createHorizontalGlue()); 
		btnBox.add(newBtn);
		btnBox.add(Box.createHorizontalGlue());
		newPanel.add(btnBox);
		
		//newBtn.
		//TODO
		
		settleContent.add(newPanel);
		this.revalidate();
	}
	
	public void refreshRecordTable() {
		MyTable table = recordTable;
		table.removeAllRows();
		
		List< ArrayList<String>> data = new ArrayList< ArrayList<String>>();
		
		for (int i=0; i<20; ++i) {
			data.add(new ArrayList<String>(Arrays.asList(new String[] {"3","数据结构","李老师","数据结构","李老师","75"})));
			data.add(new ArrayList<String>(Arrays.asList(new String[] {"3","数据结构","李老师","数据结构","李老师","75"})));
			data.add(new ArrayList<String>(Arrays.asList(new String[] {"3","数据结构","李老师","数据结构","李老师","75"})));			
		}
		
		for(ArrayList<String> one:data) {
			table.addRow(one);
		}
		table.revalidate();
		table.repaint();
	}
		

}
