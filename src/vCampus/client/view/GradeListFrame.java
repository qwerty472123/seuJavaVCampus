package vCampus.client.view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import vCampus.client.controller.TeacherGrade;
import vCampus.client.view.utility.MyTableCellEditor;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class GradeListFrame {
	public static JFrame jf;
	public static JTable table;
	public GradeListFrame(Object[][] object, int courseId){
		 jf=new JFrame();
		 jf.setTitle("ID_"+Integer.toString(courseId)+"成绩登记");
		 JPanel panel = new JPanel();
		 jf.getContentPane().add(panel, BorderLayout.CENTER);

		 
		 table = new JTable();
		 table.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		 table.setModel(new DefaultTableModel(
		 	object, new String[] {"学号", "姓名", "成绩"}
		 ){

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if (columnIndex == getColumnCount() - 1)
					return true;
				return false;
			}
		});
		//MyTableCellEditor cellEditor =  new MyTableCellEditor(new JTextField("成绩"));
		//table.getColumn("成绩").setCellEditor(cellEditor);
		JTableHeader head = table.getTableHeader();
		head.setPreferredSize(new Dimension(head.getWidth(), 30));
		head.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		head.setForeground(Color.GRAY);
		
		JScrollPane scrollPane = new JScrollPane(table);
		 
		JButton btnNewButton = new JButton("保存");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Map<Integer, Integer> data = new HashMap<Integer, Integer>();
				for(int i = 0; i < table.getRowCount() - 1; i ++){
					
					if(table.getValueAt(i, 2).getClass().getName() != "java.lang.Integer")
						data.put((Integer)table.getValueAt(i, 0), (Integer)Integer.parseInt((String) table.getValueAt(i, 2)));
					else data.put((Integer)table.getValueAt(i, 0), (Integer)table.getValueAt(i, 2));
				}
				TeacherGrade.getGradeList(courseId, data);
			}
		});
		btnNewButton.setForeground(Color.GRAY);
		btnNewButton.setSize(25, 75);
		btnNewButton.setBorder(new LineBorder(Color.BLACK));
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(scrollPane);
		panel.add(btnNewButton);
		jf.setVisible(true);
		jf.setSize(450, 400);
	}
}
