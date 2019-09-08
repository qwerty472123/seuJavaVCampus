package vCampus.client.view.lesson;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mdlaf.MaterialLookAndFeel;
import mdlaf.utils.MaterialColors;
import vCampus.bean.LessonBean;
import vCampus.client.controller.Lesson;
import vCampus.client.view.utility.MyTable;
import vCampus.client.view.utility.Refreshable;

import java.awt.BorderLayout;

public class GradePanel extends JPanel implements Refreshable{
	private MyTable table;
	public GradePanel() {
		setLayout(new BorderLayout(0, 0));
		
		table=new MyTable(new String[] {"课程ID","课程名称","教师","教室"});
/*		table.addRow(new String[] {"1","离散数学","张老师","80"});
		table.addRow(new String[] {"3","数据结构","李老师","75"});
		table.addRow(new String[] {"7","计算机组成原理","陈老师","77"});
		//table.removeRow(1);
		table.setRow(10, new String[] {"2","算法设计","王老师","60"});*/
		
		JScrollPane tableContainer=new JScrollPane(table);
		tableContainer.getViewport().setBackground(MaterialColors.WHITE);
		add(tableContainer);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel (new MaterialLookAndFeel ());
		}
		catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace ();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame=new JFrame();
					frame.setContentPane(new GradePanel());
					frame.setBounds(100, 100, 438, 478);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		Lesson.queryStuLessons(GradePanel.this);
	}

	public void setCourseTable(ArrayList<LessonBean> lessonList) {
		// TODO Auto-generated method stub
		table.removeAllRows();
		for(LessonBean c:lessonList) {
			table.addRow(new Object[] {
					c.getID(),
					c.getLessonName(),
					c.getTeacherId(),
					c.getLocation()
			});
		}
		table.revalidate();
		table.repaint();
	}
}
