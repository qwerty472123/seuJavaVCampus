package vCampus.client.view;


import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import vCampus.client.controller.TeacherGrade;
import vCampus.client.view.utility.GroupifyBtnAndCard;
import vCampus.client.view.utility.MyTable;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

public class TeacherGradePanel extends JPanel {
	
	private JPanel pages;
	
	private JPasswordField passwordField;
	private JTextField rechargeField;
	
	private JLabel settleTitle;
	private JPanel settleContent;
	
	private MyTable recordTable;
	private JTable table_1;
	private Object[][] courses;
	private JPanel lessonpage;
	private JScrollPane courseTable; 
	private Boolean init;
	/**
	 * Create the panel.
	 */
	public TeacherGradePanel() {
		
		init = false;
		setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel menu = new JPanel();
		menu.setBackground(Color.WHITE);
		panel_2.add(menu, BorderLayout.WEST);
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_21 = new JLabel("Options");
		lblNewLabel_21.setBackground(Color.WHITE);
		lblNewLabel_21.setForeground(new Color(60, 179, 113));
		lblNewLabel_21.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_21.setHorizontalAlignment(SwingConstants.CENTER);
		menu.add(lblNewLabel_21);
		
		JButton basicBtn = new JButton("教师主页");
		basicBtn.setForeground(Color.GRAY);
		basicBtn.setBackground(new Color(255, 250, 240));
		basicBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(basicBtn);
		
		JButton secureBtn = new JButton("成绩登记");
		secureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			}
		});
		secureBtn.setForeground(Color.GRAY);
		secureBtn.setBackground(new Color(255, 250, 240));
		secureBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(secureBtn);
		
		JButton settleBtn = new JButton("等待结算");
		settleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		settleBtn.setForeground(Color.GRAY);
		settleBtn.setBackground(new Color(255, 250, 240));
		settleBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(settleBtn);
		
		JButton recordBtn = new JButton("账单记录");
		recordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		recordBtn.setForeground(Color.GRAY);
		recordBtn.setBackground(new Color(255, 250, 240));
		recordBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(recordBtn);
		
		pages = new JPanel();
		panel_2.add(pages, BorderLayout.CENTER);
		pages.setLayout(new CardLayout(0, 0));		
        
		
		JPanel homepage = new JPanel();
		pages.add(homepage, "name_1");
		homepage.setBackground(Color.WHITE);
		homepage.setLayout(new BoxLayout(homepage, BoxLayout.Y_AXIS));
		
		lessonpage = new JPanel();
		pages.add(lessonpage, "name_2");
		lessonpage.setBackground(Color.WHITE);
		lessonpage.setLayout(new BoxLayout(lessonpage, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("所授课程：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lessonpage.add(lblNewLabel);

		GroupifyBtnAndCard.groupBtnsAndCards(new JButton[]{basicBtn, secureBtn, settleBtn, recordBtn}, pages);
		
	}
	
	public void initCoursetable(Object[][] object){
		/*
		TeacherGrade.getCourse();
	    courses = new Object[ TeacherGrade.object.length][4];
	    for(int i = 0; i <  TeacherGrade.object.length;i++)
	    	for(int j = 0; j < 4; j++){
	            courses[i][j] = TeacherGrade.object[i][j];
	    	}
        */
		courseTable = TeacherGradeTable.addTeacherGradeTable(object);
		if (init == false) {
		lessonpage.add(courseTable);
		init = true;
		}
	}

}
