package vCampus.client.view;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JScrollPane;

import vCampus.client.view.utility.ButtonEditor;
import vCampus.client.view.utility.ButtonRenderer;
import vCampus.client.view.utility.MyTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Color;

public class HospitalPanel extends JPanel {
	private MyTable table;

	/**
	 * Create the panel.
	 */
	public HospitalPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.NORTH);
		
		JLabel label_1 = new JLabel("请注意：仅支持五个工作日之内的预约！");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JLabel label = new JLabel("预约时间：");
		panel_2.add(label);

		//显示非周末的后五天
		Calendar now = Calendar.getInstance();
		String[] dates = new String[] {null, null, null, null, null};
		int i = 0;
		String[] weekday = new String[] {"周日","周一","周二","周三","周四","周五","周六"};
		int[] weekdayInt = new int[] {0,0,0,0,0};
		while(i < 5) {
			now.add(Calendar.DAY_OF_MONTH, 1);
			if(now.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				continue;
			}
			Date date = now.getTime();
			dates[i] = new SimpleDateFormat("yyyy-MM-dd").format(date);
			weekdayInt[i] = now.get(Calendar.DAY_OF_WEEK)-1;
			dates[i] = dates[i] + weekday[weekdayInt[i]];
			++i;
		}
		
		JComboBox comboBox = new JComboBox();
		panel_2.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(dates));
		
		//上下午
		JComboBox comboBox_1 = new JComboBox();
		panel_2.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"上午", "下午"}));
		
		JButton button = new JButton("搜索");
		panel_2.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedWeekday = weekdayInt[comboBox.getSelectedIndex()];
				int selectedHalf = comboBox_1.getSelectedIndex();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new MyTable(new String[] {"医生编号","医生姓名","医生简介"});
		
		

		scrollPane.setViewportView(table);

	}

}
