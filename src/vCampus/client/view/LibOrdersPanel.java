package vCampus.client.view;

import java.util.ArrayList;

import javax.swing.JPanel;

import vCampus.bean.BookOrderRecBean;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class LibOrdersPanel extends JPanel{
	private JTextField txtSearch;
	public LibOrdersPanel() {
		setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(198, 111, 268, 27);
		add(txtSearch);
		
		JLabel label = new JLabel("预约信息检索");
		label.setBounds(263, 69, 182, 18);
		add(label);
		
		JTextArea txtList = new JTextArea();
		txtList.setBounds(209, 180, 424, 333);
		add(txtList);
		
		JButton btnSearch = new JButton("检索");
		btnSearch.setBounds(520, 111, 113, 27);
		add(btnSearch);
	}
	
	public void setOrderList(ArrayList<BookOrderRecBean> data) {
		
	}
}
