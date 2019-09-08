package vCampus.client.view;

import java.util.ArrayList;

import javax.swing.JPanel;

import vCampus.bean.BookBorrowRecBean;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class LibBorrowsPanel extends JPanel{
	private JTextField txtSearch;
	public LibBorrowsPanel() {
		setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(136, 75, 268, 27);
		add(txtSearch);
		
		JButton btnSearch = new JButton("检索");
		btnSearch.setBounds(458, 75, 113, 27);
		add(btnSearch);
		
		JTextArea txtList = new JTextArea();
		txtList.setBounds(147, 144, 424, 333);
		add(txtList);
		
		JLabel label = new JLabel("借阅信息检索");
		label.setBounds(203, 28, 248, 18);
		add(label);
	}
	
	public void setBorrowList(ArrayList<BookBorrowRecBean> data) {
		
	}
}
