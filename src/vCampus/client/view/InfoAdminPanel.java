package vCampus.client.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import vCampus.client.view.utility.MyTable;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class InfoAdminPanel extends JPanel {
	private MyTable table;

	/**
	 * Create the panel.
	 */
	public InfoAdminPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("待审核信息");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("查看详情");
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new MyTable(new String[] {"学生ID","学生姓名"});
		scrollPane.setViewportView(table);

		
	}

}
