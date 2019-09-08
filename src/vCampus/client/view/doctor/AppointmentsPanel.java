package vCampus.client.view.doctor;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;

import vCampus.client.controller.DoctorApt;
import vCampus.client.view.utility.MyTable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AppointmentsPanel extends JPanel {
	private MyTable table;

	/**
	 * Create the panel.
	 */
	public AppointmentsPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);
		
				Calendar now = Calendar.getInstance();
				//DEBUG:now.add(Calendar.DAY_OF_MONTH, 3);
				String[] datesstr = new String[] {null, null, null, null, null};
				Date[] dates = new Date[] {null, null, null, null, null};
				int i = 0;
				String[] weekday = new String[] {"周日","周一","周二","周三","周四","周五","周六"};
				int[] weekdayInt = new int[] {0,0,0,0,0};
				boolean nextweek = false;
				while(i < 5) {			
					now.add(Calendar.DAY_OF_MONTH, 1);
					if(now.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
						continue;
					}
					dates[i] = now.getTime();
					datesstr[i] = new SimpleDateFormat("yyyy-MM-dd").format(dates[i]);
					weekdayInt[i] = now.get(Calendar.DAY_OF_WEEK)-1;
					if(weekdayInt[i] == 1)
						nextweek = true;
					if(nextweek == true)
						datesstr[i] = datesstr[i] + " 下" + weekday[weekdayInt[i]];
					else
						datesstr[i] = datesstr[i] + " " + weekday[weekdayInt[i]];
					++i;
				}
				
				JComboBox comboBox = new JComboBox();
				panel_2.add(comboBox);
				comboBox.setModel(new DefaultComboBoxModel(datesstr));
				
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
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("接受");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("拒绝");
		panel.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new MyTable(new String[] {"预约单号","预约人姓名","操作时间","备注"});
		scrollPane.setViewportView(table);

	}

}
