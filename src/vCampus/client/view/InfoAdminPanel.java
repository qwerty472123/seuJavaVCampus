package vCampus.client.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import vCampus.bean.StudentBean;
import vCampus.client.controller.AccountAdmin;
import vCampus.client.view.utility.MyStyle;
import vCampus.client.view.utility.MyTable;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoAdminPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MyTable table;
    private JScrollPane scrollPane;
	/**
	 * Create the panel.
	 */
	public InfoAdminPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("待审核信息");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		JButton btn_deny = new JButton("否定修改");
		btn_deny.setBackground(Color.white);
		btn_deny.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		btn_deny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1){
					JOptionPane.showMessageDialog(null, "未选中行！");
				}else{
					int stuId = Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 0));
					AccountAdmin.StuInfoUpdate("修改失败", stuId);
				}			
			}
		});
		panel.add(btn_deny);
		
		JButton btn_update = new JButton("同意修改");
		btn_update.setBackground(Color.white);
		btn_update.setForeground(MyStyle.RED);
		btn_update.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() == -1){
					JOptionPane.showMessageDialog(null, "未选中行！");
				}else{
					int stuId = Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 0));
					AccountAdmin.StuInfoUpdate("修改成功", stuId);
				}
			}
		});
		panel.add(btn_update);
		
		JButton btn_refresh = new JButton("刷新");
		btn_refresh.setBackground(Color.white);
		btn_refresh.setForeground(MyStyle.LIGHT_GRAY);
		btn_refresh.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountAdmin.RefreshStuInfoUpdate();
			}
		});
		panel.add(btn_refresh);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new MyTable(new String[] {"学号","姓名","性别","生日","年级","班级","学院","电邮","电话","QQ"});
		scrollPane.setViewportView(table);
	}

	public void setUpdateList(ArrayList<ArrayList<String>> list) {
		table.removeAllRows();
		for (ArrayList<String> s : list) {
			table.addRow(new Object[] {s.get(0), s.get(1), s.get(2),s.get(3),s.get(4),s.get(5), s.get(6),s.get(7),s.get(8),s.get(9)});
		}
		this.remove(scrollPane);
		scrollPane.setViewportView(table);
		this.add(scrollPane);
	}
}
