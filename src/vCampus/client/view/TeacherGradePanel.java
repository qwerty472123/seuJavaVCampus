package vCampus.client.view;


import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.client.controller.TeacherGrade;
import vCampus.client.view.utility.GroupifyBtnAndCard;
import vCampus.client.view.utility.MyTable;
import vCampus.server.dao.model.ExpenseRec;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import com.jgoodies.forms.factories.DefaultComponentFactory;

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
	
	/**
	 * Create the panel.
	 */
	public TeacherGradePanel() {
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
				TeacherGrade.getCourse();
				courses = TeacherGrade.object;
				JScrollPane courseTable = TeacherGradeTable.addTeacherGradeTable(courses);
				lessonpage.add(courseTable);
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
				
	
		/*
		JButton[] btnCourse = new JButton[TeacherGradeTable.getTable().getRowCount()];
		for(int i = 0; i < TeacherGradeTable.getTable().getRowCount(); i++){
			btnCourse[i] = TeacherGradeTable.getButton(i);
		}
		*/
		GroupifyBtnAndCard.groupBtnsAndCards(new JButton[]{basicBtn, secureBtn, settleBtn, recordBtn}, pages);
		
	}
	

}
