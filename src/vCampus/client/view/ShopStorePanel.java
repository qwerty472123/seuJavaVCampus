package vCampus.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.bean.GoodBean;
import vCampus.client.controller.ShopRobot;
import vCampus.client.view.utility.MyStyle;
import vCampus.client.view.utility.MyTable;

public class ShopStorePanel extends JPanel {
	
	private JPanel page;
	
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg, "", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "", JOptionPane.WARNING_MESSAGE);
	}
	

	private JTextField txtSearch;
	private MyTable table;
	private JPanel details;
	private JButton btnAdd;
	private JButton btnModify;
	private JButton btnDelete;
	private ArrayList<GoodBean> goodsList=new ArrayList<>();
	private JPanel btnPanel;
	private JLabel lblDescription;
	private JTextField textField;
	
	
	private JButton newOperationButton(String title,Color color) {
		JButton b = new JButton(title);
		btnPanel.add(b);
		b.setForeground(color);
		b.setPreferredSize(new Dimension(200,50));
		b.setBackground(new Color(255, 255, 255));
		b.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		Dimension rect100=new Dimension(100,100);
		b.setPreferredSize(rect100);
		b.setMaximumSize(rect100);
		b.setMinimumSize(rect100);
		MaterialUIMovement.add(b, MaterialColors.GRAY_100);
		return b;
	}
	
	/**
	 * Create the panel.
	 */
	public ShopStorePanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		setLayout(new BorderLayout(0, 0));
		
		page = new JPanel();
		add(page, BorderLayout.CENTER);
		
		GridBagLayout gbl_page = new GridBagLayout();
		gbl_page.columnWidths = new int[]{0, 0, 0, 0};
		gbl_page.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_page.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_page.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		page.setLayout(gbl_page);
		
		JLabel label = new JLabel("商品管理");
		label.setFont(MyStyle.FONT_L);
		label.setForeground(MyStyle.RED);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 3;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		page.add(label, gbc_label);
		
		JLabel lblNewLabel_1 = new JLabel("条件查询");
		lblNewLabel_1.setFont(MyStyle.FONT_S);
		lblNewLabel_1.setForeground(MyStyle.LIGHT_GRAY);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		page.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel label_2 = new JLabel("条件查询");
		label_2.setForeground(new Color(153, 153, 153));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 0);
		gbc_label_2.gridx = 2;
		gbc_label_2.gridy = 1;
		page.add(label_2, gbc_label_2);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		page.add(textField, gbc_textField);
		//txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("检索");
		btnSearch.setForeground(MyStyle.WHITE);
		btnSearch.setBackground(MyStyle.RED);
		btnSearch.setFont(MyStyle.FONT_M);
		MaterialUIMovement.add(btnSearch, MyStyle.RED_HOVER);
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.gridheight = 2;
		gbc_btnSearch.fill = GridBagConstraints.BOTH;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 2;
		gbc_btnSearch.gridy = 2;
		page.add(btnSearch, gbc_btnSearch);
		
		table=new MyTable(new String[] {"ID","分组","品名","单价","描述"});
		
		
		JScrollPane tbContainer = new JScrollPane(table);
		tbContainer.getViewport().setBackground(Color.white);

		txtSearch = new JTextField();
		txtSearch.setFont(MyStyle.FONT_M);
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.fill = GridBagConstraints.BOTH;
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.gridx = 1;
		gbc_txtSearch.gridy = 3;
		page.add(txtSearch, gbc_txtSearch);		
		
		JLabel label_1 = new JLabel("关键词:");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setForeground(new Color(102, 102, 102));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 3;
		page.add(label_1, gbc_label_1);
		GridBagConstraints gbc_tbContainer = new GridBagConstraints();
		gbc_tbContainer.gridwidth = 3;
		gbc_tbContainer.insets = new Insets(0, 0, 5, 0);
		gbc_tbContainer.fill = GridBagConstraints.BOTH;
		gbc_tbContainer.gridx = 0;
		gbc_tbContainer.gridy = 5;
		page.add(tbContainer, gbc_tbContainer);
		
		JPanel optionPanel = new JPanel();
		add(optionPanel, BorderLayout.SOUTH);
		optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
		details = new JPanel();
		optionPanel.add(details);
		GridBagLayout gbl_details = new GridBagLayout();
		gbl_details.columnWidths = new int[]{0, 0, 0};
		gbl_details.rowHeights = new int[]{0, 0, 0};
		gbl_details.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_details.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		details.setLayout(gbl_details);
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		details.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JSeparator separator = new JSeparator();
		panel_3.add(separator);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut);
		
		lblDescription = new JLabel("");
		panel_3.add(lblDescription);
		lblDescription.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut_1);
		
		JSeparator separator_1 = new JSeparator();
		panel_3.add(separator_1);
		
		btnPanel = new JPanel();
		GridBagConstraints gbc_btnPanel = new GridBagConstraints();
		gbc_btnPanel.fill = GridBagConstraints.BOTH;
		gbc_btnPanel.gridx = 1;
		gbc_btnPanel.gridy = 1;
		details.add(btnPanel, gbc_btnPanel);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnAdd = newOperationButton("新建",MaterialColors.GREEN_400);
		btnModify = newOperationButton("更改",MaterialColors.BLUE_400);
		btnDelete = newOperationButton("删除",MaterialColors.BLUE_GRAY_400);
		//----------------------------------------------
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShopRobot.queryGood(ShopStorePanel.this, txtSearch.getText());
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ShopStoreDialog dlg = new ShopStoreDialog();
				dlg.getOkButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						GoodBean g = dlg.getGood();
						ShopRobot.addGood(ShopStorePanel.this, g);
						dlg.dispose();
					}
				});
				dlg.setVisible(true);
			}
		});
		
		btnModify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idx=table.getSelectedRow();
				if(idx<0) {
					Config.log("没有选中行!");
					return;
				}
				GoodBean cur = goodsList.get(idx);
				ShopStoreDialog dlg = new ShopStoreDialog();
				dlg.setGood(cur);
				dlg.getOkButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						GoodBean g = dlg.getGood();
						ShopRobot.modifyGood(ShopStorePanel.this, g);
						dlg.dispose();
					}
					
				});
				dlg.setVisible(true);
			}
			
		});
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idx=table.getSelectedRow();
				if(idx==-1) {
					Config.log("没有选中行!");
					return;
				}
				ShopRobot.deleteGood(ShopStorePanel.this, 
						goodsList.get(idx).getGoodID());
			}
			
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int idx=table.getSelectedRow();
				GoodBean bean = goodsList.get(idx);
				lblDescription.setText("<html><h2><span><i>" + 
						bean.getGoodName() + 
						"</i></span></h2>"
						+ "<p>&nbsp;&nbsp;<strong><span>分组:</span></strong><span>"
						+ bean.getShopID() +
						"</span>&nbsp;&nbsp;<strong><span>简介:</span></strong><span>"
						+ bean.getCaption() + 
						"</span></p></html>");
			}
		});
		
	}
	
	public void setGoodsList(List<GoodBean> data) {
		goodsList.clear();
		table.removeAllRows();
		for(GoodBean bean: data) {
			goodsList.add(bean);
			int p = bean.getPrice();
			String str = "￥" + p/100 + "." + (p%100)/10 + p%10;
			table.addRow(new Object[] {bean.getGoodID(), bean.getShopID(), bean.getGoodName(), str, bean.getCaption()});
		}
		table.revalidate();
		table.repaint();
	}
}
