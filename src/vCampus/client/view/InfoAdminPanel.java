package vCampus.client.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import vCampus.client.view.utility.MyTable;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;

public class InfoAdminPanel extends JPanel {
	private MyTable table;

	/**
	 * Create the panel.
	 */
	public InfoAdminPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("待审核信息");
		add(label, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new MyTable(new String[] {"学生ID","信息对比","操作"});
		scrollPane.setViewportView(table);

	}

}
