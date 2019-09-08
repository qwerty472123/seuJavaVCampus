package vCampus.client.view.lesson;

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
import vCampus.bean.LessonTime;
import vCampus.client.controller.Lesson;
import vCampus.client.view.utility.Refreshable;

public class CourseTablePanel extends JPanel implements Refreshable{
	private CourseTable coursetable;
	private JComboBox comboWeek;
	public CourseTablePanel(){
		setLayout(new BorderLayout(0, 0));
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.NORTH);
		JLabel lblNewLabel = new JLabel("选择周次");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel_4.add(lblNewLabel);
		comboWeek = new JComboBox();
		comboWeek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//LessonController.requestWeek(Integer.parseInt((String)comboWeek.getSelectedItem()));
				Lesson.queryCourseTable4Week(CourseTablePanel.this, Integer.parseInt((String)comboWeek.getSelectedItem()));
			}
		});
		comboWeek.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		comboWeek.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"}));
		panel_4.add(comboWeek);
		coursetable=new CourseTable();
		add(coursetable, BorderLayout.CENTER);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		Lesson.queryCourseTable4Week(CourseTablePanel.this, Integer.parseInt((String)comboWeek.getSelectedItem()));
	}

	public void setCourseTable(ArrayList<LessonTime> timeList) {
		// TODO Auto-generated method stub
		coursetable.clearAll();
		
		for(LessonTime t: timeList) {
			//coursetable.addCourse(, MaterialColors.RED_100);
			coursetable.addCourse(t.getLessonName(), t.getLocation(),
					 t.getDay(), t.getStart(), t.getEnd(), MaterialColors.RED_100);
		}
		coursetable.revalidate();
		coursetable.repaint();
		
	}
}
