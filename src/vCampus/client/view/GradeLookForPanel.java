package vCampus.client.view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mdlaf.MaterialLookAndFeel;
import mdlaf.utils.MaterialColors;
import vCampus.client.view.utility.MyTable;

import java.awt.BorderLayout;

public class GradeLookForPanel extends JPanel{
	private MyTable table;
	public GradeLookForPanel() {
		setLayout(new BorderLayout(0, 0));
		
		table=new MyTable(new String[] {"课程ID","课程名称","教师","成绩"});
/*		table.addRow(new String[] {"1","离散数学","张老师","80"});
		table.addRow(new String[] {"3","数据结构","李老师","75"});
		table.addRow(new String[] {"7","计算机组成原理","陈老师","77"});
		//table.removeRow(1);
		table.setRow(10, new String[] {"2","算法设计","王老师","60"});*/
		
		JScrollPane tableContainer=new JScrollPane(table);
		tableContainer.getViewport().setBackground(MaterialColors.WHITE);
		add(tableContainer);
	}
	
	public void setGradeTable(ArrayList<ArrayList<String>> data) {
		table.removeAllRows();
		for(ArrayList<String> one:data) {
			table.addRow(one);
		}
		table.revalidate();
		table.repaint();
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
					frame.setContentPane(new GradeLookForPanel());
					frame.setBounds(100, 100, 438, 478);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
