package vCampus.client.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;

import vCampus.client.controller.AccountAdmin;
import vCampus.client.view.utility.MyTable;
import vCampus.server.dao.model.AccountKey;
import vCampus.utility.Config;

import java.awt.Font;
import java.awt.Cursor;

public class AccountAdminPanel extends JPanel {
	private JTextField textField;
	private MyTable table;
	private JScrollPane scrollPane;
	
	/**
	 * Create the panel.
	 */
	public AccountAdminPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		JLabel label = new JLabel("用户名");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		panel_2.add(label);
		
		textField = new JTextField();
		textField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		panel_2.add(textField);
		textField.setText("请输入用户名");
		textField.setColumns(10);
		//单击输入框隐藏文字
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					textField.setText("");
				}
			}
		});
		
		JButton button = new JButton("搜索");
		//搜索
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					String search_name = textField.getText();
					AccountAdmin.searchAccount(search_name);
				}
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		panel_2.add(button);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_1.add(scrollPane, BorderLayout.CENTER);
		/*
		table = new JTable();
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		table.setRowHeight(30);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"\u7528\u6237\u7F16\u53F7", "\u7528\u6237\u540D", "\u7528\u6237\u6743\u9650"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		*/
		table = new MyTable(new String[]{"用户编号","用户名","用户权限"});
		table.setEditable(false);
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		
		//新增
		JButton button_4 = new JButton("新增用户");
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					String inputValue = JOptionPane.showInputDialog("请输入新用户编号");
					if(!inputValue.isEmpty()) {
						int addId = Integer.parseInt(inputValue);
						AccountAdmin.addAccount(addId);
					}
				}
			}
		});
		button_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		panel_3.add(button_4);
		
		
		//修改权限
		JButton button_5 = new JButton("修改权限");
		button_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		panel_3.add(button_5);
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					int selected_id = Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 0));
					String new_authority = (String)JOptionPane.showInputDialog(null, "请选择新权限", "权限更改", JOptionPane.WARNING_MESSAGE, null, new Object[] {"admin","teacher","student"}, "student");
					if(new_authority != null && selected_id >= 0)
						AccountAdmin.changeAuthority(selected_id, new_authority);
					else
						Config.log("can't set authority to null!");
				}
			}
		});
		
		//点选删除
		JButton button_3 = new JButton("删除用户");
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		panel_3.add(button_3);
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					int selected_id = (int) table.getValueAt(table.getSelectedRow(), 0);
					int confirm = JOptionPane.showConfirmDialog(null, "确认删除？", "删除用户", JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.YES_OPTION && selected_id >= 0) {
						AccountAdmin.deleteAccount(selected_id);
						AccountAdmin.searchAccount("");
					}
				}
			}
		});
		//初始表格内容
		AccountAdmin.searchAccount("");

	}
	
	public void setAccountTable(List<AccountKey> list) {
		table.removeAllRows();
		for (AccountKey x : list) {
			table.addRow(new Object[] {x.getUserId(),x.getUserName(),x.getAuthority()});
		}
		scrollPane.setViewportView(table);
	}

}
