package vCampus.client.view;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vCampus.client.controller.Lesson;
import vCampus.client.view.lesson.LessonSelectPanel;
import vCampus.client.view.lesson.CourseTablePanel;
import vCampus.client.view.lesson.GradePanel;
import vCampus.client.view.utility.GroupifyBtnAndCard;

public class LessonPanel extends JPanel {
	private JPanel panel;
	private JPanel leftMenu;
	private JLabel label;
	private JButton tbBtn;
	private JButton ccBtn;
	private JButton gdBtn;
	private JPanel cards;
	private LessonSelectPanel classSelect;
	private CourseTablePanel classTable;
	private GradePanel classGrade;
	
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(this,msg ,"",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showError(String msg) {
		JOptionPane.showMessageDialog(this,msg ,"",JOptionPane.WARNING_MESSAGE);
	}
	
	public LessonSelectPanel getClassSelectionPanel() {
		return classSelect;
	}
	public CourseTablePanel getCourseTablePanel() {
		return classTable;
	}
	public GradePanel getGradePanel() {
		return classGrade;
	}
	
	/**
	 * Create the panel.
	 */
	public LessonPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		leftMenu = new JPanel();
		panel.add(leftMenu, BorderLayout.WEST);
		leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
		
		label = new JLabel("Options");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(60, 179, 113));
		label.setFont(new Font("΢���ź�", Font.PLAIN, 15)); 
		label.setBackground(Color.WHITE);
		leftMenu.add(label);
		
		tbBtn = new JButton("每周课表");
		tbBtn.setForeground(Color.GRAY);
		tbBtn.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		tbBtn.setBackground(new Color(255, 250, 240));
		tbBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				classTable.refresh();
			}
			
		});
		leftMenu.add(tbBtn);
		
		ccBtn = new JButton("选课系统");
		ccBtn.setForeground(Color.GRAY);
		ccBtn.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		ccBtn.setBackground(new Color(255, 250, 240));
		ccBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//LessonController.refreshCourseSelectionTable();
			}
			
		});
		leftMenu.add(ccBtn);
		
		gdBtn = new JButton("我的课程");
		gdBtn.setForeground(Color.GRAY);
		gdBtn.setFont(new Font("Dialog", Font.PLAIN, 18));
		gdBtn.setBackground(new Color(255, 250, 240));
		gdBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//LessonController.requestGrades();
				classGrade.refresh();
			}
		});
		leftMenu.add(gdBtn);
		
		cards = new JPanel();
		cards.setBackground(Color.WHITE);
		panel.add(cards, BorderLayout.CENTER);
		cards.setLayout(new CardLayout(0, 0));
		classTable = new CourseTablePanel();
		classSelect = new LessonSelectPanel();
		classGrade = new GradePanel();
		cards.add(classTable, "name_1");
		cards.add(classSelect, "name_2");
		cards.add(classGrade,"name_3");
		GroupifyBtnAndCard.groupBtnsAndCards(new JButton[] {tbBtn,ccBtn,gdBtn},cards);
	}
}
