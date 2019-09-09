package vCampus.client.view.accountAdmin;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import vCampus.bean.AccountKeyBean;
import vCampus.client.controller.AccountAdmin;
import vCampus.client.view.utility.MyStyle;
import vCampus.client.view.utility.MyTable;
import vCampus.utility.Config;


import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AccountPanel extends JPanel{
	private JTextField txtSearch;
	private MyTable table;
	private JPanel details;
	private JButton btnAdd;
	private JButton btnModify;
	private JButton btnDelete;
	private ArrayList<AccountKeyBean> accounts=new ArrayList<AccountKeyBean>();
	private JPanel btnPanel;
	private JTextField textField;
	private AddAccountDialog addAccountDlg;
	
	public String getSearchWord() {
		return txtSearch.getText();
	}
	
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
	
	
	private JButton newOperationButton(String title) {
		return newOperationButton(title,MyStyle.RED);
	}
	
	public AccountPanel() {
		setLayout(new BorderLayout(0, 0));
		JPanel page = new JPanel();
		add(page);
		GridBagLayout gbl_page = new GridBagLayout();
		gbl_page.columnWidths = new int[]{0, 0, 0, 0};
		gbl_page.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_page.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_page.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		page.setLayout(gbl_page);
		
		JLabel label = new JLabel("账户信息清单");
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
		
		table = new MyTable(new String[]{"用户编号","用户名","用户权限"});
		table.setEditable(false);
		table.setRowHighlight(MaterialColors.AMBER_50);
		
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
		
		JLabel label_1 = new JLabel("用户名:");
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
		Dimension rect100=new Dimension(100,100);
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
				//Library.searchBooks(AcountAdminPanel.this);
				String search_name = textField.getText();
				AccountAdmin.searchAccount(AccountPanel.this,search_name);
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*BookDialog dlgBook=new BookDialog();
				dlgBook.getOkButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						BookBean b=dlgBook.getBook();
						Library.addBook(AcountAdminPanel.this, b);
						dlgBook.dispose();
					}
				});
				dlgBook.setVisible(true);*/
				
				addAccountDlg = new AddAccountDialog();
				addAccountDlg.getOkButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int newID = addAccountDlg.getNewNumber();
						String newName = addAccountDlg.getNewName();
						String newAuthority = addAccountDlg.getNewAuthority();
						if(newName != null && newName != "")
							AccountAdmin.addAccount(AccountPanel.this, newID, newName, newAuthority);
						else
							Config.log("Illegal new name!");
						addAccountDlg.dispose();
					}
				});
				addAccountDlg.setVisible(true);
			}
		});
		
		btnModify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idx=table.getSelectedRow();
				if(idx==-1) {
					System.out.println("没有选中行!");
					return;
				}
				AccountKeyBean cur=accounts.get(idx);
				/*BookDialog dlg=new BookDialog();
				dlg.setBook(cur);
				dlg.getOkButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						BookBean b=dlg.getBook();
						Library.updateBook(AcountAdminPanel.this, b);
						dlg.dispose();
					}
					
				});
				dlg.setVisible(true);*/
				String new_name = (String)JOptionPane.showInputDialog(null, "请输入新用户名", "用户名更改", JOptionPane.WARNING_MESSAGE);
				if(new_name != null && new_name != "")
					AccountAdmin.changeName(AccountPanel.this,cur.getUserId(), new_name);
				else {
					Config.log("Rename canceld");
				}
			}
			
		});
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idx=table.getSelectedRow();
				if(idx==-1) {
					System.out.println("没有选中行!");
					return;
				}
				AccountKeyBean b=accounts.get(idx);
				int confirm = JOptionPane.showConfirmDialog(null, "确认删除？", "删除用户", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					AccountAdmin.deleteAccount(AccountPanel.this,b.getUserId());
					AccountAdmin.searchAccount(AccountPanel.this,"");
				}	
			}
		});
		
	}
	public void setAccountTable(ArrayList<AccountKeyBean> list) {
		table.removeAllRows();
		accounts.clear();
		for (AccountKeyBean x : list) {
			accounts.add(x);
			table.addRow(new Object[] {x.getUserId(),x.getUserName(),x.getAuthority()});
		}
		table.revalidate();
		table.repaint();
	}
	
	public void refresh() {
		AccountAdmin.searchAccount(this, "");
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new MaterialLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			Config.log(e);
		}
		JFrame frame=new JFrame();
		frame.setSize(new Dimension(600,400));
		frame.setContentPane(new AccountPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}



