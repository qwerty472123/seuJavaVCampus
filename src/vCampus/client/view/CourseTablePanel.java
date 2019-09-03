package vCampus.client.view;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mdlaf.utils.MaterialColors;
import vCampus.client.view.utility.*;

public class CourseTablePanel extends JPanel{
	private CourseTable coursetable;
	private JComboBox comboWeek;
	
	public void setClassTable(ArrayList<ArrayList<String>> data) {
		//we suppose that the 1st dimension contains different courses
		//and the 2nd dimension contains details of one specific course
		
		coursetable.clearAll();
		
		for(ArrayList<String> course: data) {
			//we suppose the values in course are:
			//0: (string)course name
			//1: (string)classroom
			//2: (int)day
			//3: (int)start time
			//4: (int)end time
			System.out.println(course);
			coursetable.addCourse(course.get(0), course.get(1), Integer.parseInt(course.get(2)), Integer.parseInt(course.get(3)), Integer.parseInt(course.get(4)), MaterialColors.RED_100);
		}
		coursetable.revalidate();
		coursetable.repaint();
	}
	
	CourseTablePanel(){
		setLayout(new BorderLayout(0, 0));
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.NORTH);
		JLabel lblNewLabel = new JLabel("选择周次");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel_4.add(lblNewLabel);
		comboWeek = new JComboBox();
		comboWeek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(comboWeek.getSelectedItem());
				System.out.println("From CourseTablePanel: request class table");
			}
		});
		comboWeek.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		comboWeek.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"}));
		panel_4.add(comboWeek);
		coursetable=new CourseTable();
		add(coursetable, BorderLayout.CENTER);
	}
}
