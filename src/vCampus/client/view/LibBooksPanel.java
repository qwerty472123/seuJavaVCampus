package vCampus.client.view;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import vCampus.bean.BookBean;
import vCampus.client.controller.Library;
import vCampus.client.view.utility.MyTable;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.border.LineBorder;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class LibBooksPanel extends JPanel{
	private boolean manager;
	private JTextField txtSearch;
	private MyTable table;
	private JPanel details;
	private JButton btnSelect;
	
	public String getSearchWord() {
		return txtSearch.getText();
	}
	
	public LibBooksPanel() {
		setLayout(new BorderLayout(0, 0));
		

		
		JPanel page = new JPanel();
		add(page);
		GridBagLayout gbl_page = new GridBagLayout();
		gbl_page.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_page.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_page.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_page.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		page.setLayout(gbl_page);
		
		JLabel label = new JLabel("图书信息检索");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 0;
		page.add(label, gbc_label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LibBooksPanel.class.getResource("/education/002.png")));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		page.add(lblNewLabel, gbc_lblNewLabel);

		txtSearch = new JTextField();
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.gridx = 2;
		gbc_txtSearch.gridy = 1;
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		page.add(txtSearch, gbc_txtSearch);
		//txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("检索");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 3;
		gbc_btnSearch.gridy = 1;
		page.add(btnSearch, gbc_btnSearch);
		
		table=new MyTable(new String[] {"书名","作者","出版社","描述","书架位置","库存总量","已借出数量"});
		
		
		JScrollPane tbContainer = new JScrollPane(table);
		tbContainer.getViewport().setBackground(Color.white);
		GridBagConstraints gbc_tbContainer = new GridBagConstraints();
		gbc_tbContainer.gridwidth = 3;
		gbc_tbContainer.insets = new Insets(0, 0, 5, 5);
		gbc_tbContainer.fill = GridBagConstraints.BOTH;
		gbc_tbContainer.gridx = 1;
		gbc_tbContainer.gridy = 2;
		page.add(tbContainer, gbc_tbContainer);
		
		JPanel optionPanel = new JPanel();
		add(optionPanel, BorderLayout.SOUTH);
		optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
		
		JSeparator separator = new JSeparator();
		optionPanel.add(separator);
		details = new JPanel();
		optionPanel.add(details);
		GridBagLayout gbl_details = new GridBagLayout();
		gbl_details.columnWidths = new int[]{0, 0, 0};
		gbl_details.rowHeights = new int[]{0, 0, 0};
		gbl_details.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_details.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		details.setLayout(gbl_details);
		Dimension rect100=new Dimension(100,100);
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 2;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		details.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JLabel lblDescription = new JLabel("课程描述");
		panel_3.add(lblDescription);
		lblDescription.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		details.add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		btnSelect = new JButton("选课");
		panel.add(btnSelect);
		btnSelect.setForeground(new Color(255, 69, 0));
		btnSelect.setPreferredSize(new Dimension(200,50));
		btnSelect.setBackground(new Color(255, 255, 255));
		btnSelect.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		btnSelect.setPreferredSize(rect100);
		btnSelect.setMaximumSize(rect100);
		btnSelect.setMinimumSize(rect100);
		MaterialUIMovement.add(btnSelect, MaterialColors.GRAY_100);
		
		//----------------------------------------------
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Library.searchBooks(LibBooksPanel.this);
			}
		});
	}
	public void setBookList(ArrayList<BookBean> data) {
		table.removeAllRows();
		for(BookBean b:data) {
			table.addRow(new Object[] {b.getTitle(),b.getAuthor(),b.getPress(),b.getDescription(),b.getLocation(),b.getTotCnt(),b.getBorrowCnt()});
		}
		table.revalidate();
		table.repaint();
		//txtList.setText(s);
	}
}
