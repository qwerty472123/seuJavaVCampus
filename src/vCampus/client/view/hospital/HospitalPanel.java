package vCampus.client.view.hospital;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;

import vCampus.bean.AptRecBean;
import vCampus.bean.DoctorBean;
import vCampus.client.controller.DoctorApt;
import vCampus.client.view.utility.MyStyle;
import vCampus.client.view.utility.MyTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.SwingConstants;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

public class HospitalPanel extends JPanel {
	private MyTable table;
	private JScrollPane scrollPane;
	private DoctorIntroFrame introFrame;
	private MyAptDialog myAptDialog;
	/**
	 * Create the panel.
	 */
	public HospitalPanel() {
		introFrame = new DoctorIntroFrame();
		introFrame.setVisible(false);
		
		myAptDialog = new MyAptDialog();
		myAptDialog.setVisible(false);
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel label_1 = new JLabel("请注意：仅支持五个工作日之内的预约！");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(label_1, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("我的预约");
		btnNewButton.setBackground(Color.white);
		btnNewButton.setForeground(MyStyle.BLUE);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		MaterialUIMovement.add(btnNewButton, MaterialColors.GRAY_100);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DoctorApt.showMyAptRecs();
			}
		});
		panel.add(btnNewButton, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JLabel label = new JLabel("预约时间：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_2.add(label);

		//显示非周末的后五天
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
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		//上下午
		JComboBox comboBox_1 = new JComboBox();
		panel_2.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"上午", "下午"}));
		comboBox_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JButton button = new JButton("搜索");

		button.setBackground(MyStyle.RED);
		button.setForeground(Color.white);
		button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		MaterialUIMovement.add(button, MyStyle.RED_HOVER);
		
		panel_2.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedWeekday = weekdayInt[comboBox.getSelectedIndex()];
				int selectedHalf = comboBox_1.getSelectedIndex();
				DoctorApt.searchDoc(selectedWeekday, selectedHalf, dates[comboBox.getSelectedIndex()]);
			}
		});
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.SOUTH);
		
		JButton button_1 = new JButton("查看简介");
		button_1.setBackground(Color.white);
		MaterialUIMovement.add(button_1, MaterialColors.GRAY_100);
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selected_id = Integer.parseInt((String)table.getModel().getValueAt(table.getSelectedRow(), 0));
				if(selected_id >= 0)
					DoctorApt.showDoctorIntro(selected_id);
			}
		});
		panel_3.add(button_1);
		
		JButton button_2 = new JButton("预约");
		button_2.setBackground(Color.white);
		button_2.setForeground(MyStyle.RED);
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		MaterialUIMovement.add(button_2, MaterialColors.GRAY_100);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Date aptdate = dates[comboBox.getSelectedIndex()];
				if(table.getSelectedRow()>=0) {
					String remark = JOptionPane.showInputDialog("请输入您的备注信息");
					DoctorApt.addApt(Integer.parseInt((String)table.getModel().getValueAt(table.getSelectedRow(), 0)), aptdate, comboBox_1.getSelectedIndex(), remark);
				}
			}
		});
		panel_3.add(button_2);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new MyTable(new String[] {"医生编号","医生姓名","已预约人数","最大预约人数"});
		table.setEditable(false);
		
		scrollPane.setViewportView(table);

	}

	public void setDoctorTable(List<DoctorBean> list, List<Integer> aptNumList, List<Integer> avaNumList) {
		table.removeAllRows();
		int i = 0;
		for (DoctorBean x : list) {
			table.addRow(new Object[] {x.getId(),x.getName(),aptNumList.get(i),avaNumList.get(i)});
			i++;
		}
		scrollPane.setViewportView(table);
	}
	public void setDoctorIntro(String name, int age, boolean gender, String introtxt, int doctorid) {
		introFrame.set_name(name);
		introFrame.set_age(age);
		introFrame.set_gender(gender);
		introFrame.set_intro(introtxt);
		String url = "./photo/"+doctorid+".jpg";
		if(!new File(url).canRead())
			url = "./photo/defaultphoto.jpg";
		Icon i = new ImageIcon(url);
		introFrame.set_protrait(i);
		introFrame.setVisible(true);
	}
	public void setMyAptTable(List<AptRecBean> list, List<String> doctornames) {
		myAptDialog.setMyAptTable(list, doctornames);
		myAptDialog.setVisible(true);
	}
}
