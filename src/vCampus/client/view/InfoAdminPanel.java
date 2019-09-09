package vCampus.client.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import vCampus.bean.StudentBean;
import vCampus.client.view.utility.MyTable;
import java.awt.BorderLayout;
import java.util.List;

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
		
		table = new MyTable(new String[] {"学号","姓名","性别","生日","年级","班级","学院","电邮","电话","QQ"});
		scrollPane.setViewportView(table);
	}

	public void setUpdateList(List<StudentBean> list) {
		table.removeAllRows();
		for (StudentBean s : list) {
			table.addRow(new Object[] {s.getId(), s.getName(), s.getSex(), s.getBirthday(), s.getGrade(), s.getClass(), s.getFaculty(), s.getEmail() ,s.getPhone(), s.getQq()});
		}
	}
}
