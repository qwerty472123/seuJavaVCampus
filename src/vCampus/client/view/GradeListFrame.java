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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GradeListFrame {
	private JFrame jf;
	private JTable table;
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
		 
		JTableHeader head = table.getTableHeader();
		head.setPreferredSize(new Dimension(head.getWidth(), 30));
		head.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		head.setForeground(Color.GRAY);
		
		JScrollPane scrollPane = new JScrollPane(table);
		 
		JButton btnNewButton = new JButton("保存");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton.setForeground(Color.GRAY);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(scrollPane);
		panel.add(btnNewButton);
		jf.setVisible(true);
		jf.setSize(450, 400);
	}
}
