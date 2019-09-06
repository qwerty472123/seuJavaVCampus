package vCampus.client.view;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import vCampus.bean.BookBean;
import vCampus.client.controller.Library;
import vCampus.client.view.utility.MyTable;
import vCampus.utility.Config;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.border.LineBorder;

import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LibBooksPanel extends JPanel{
	private boolean manager;
	private JTextField txtSearch;
	private MyTable table;
	private JPanel details;
	private JButton btnAdd;
	private JButton btnModify;
	private JButton btnDelete;
	private ArrayList<BookBean> bookList=new ArrayList<BookBean>();
	private JPanel btnPanel;
	private JLabel lblDescription;
	
	
	public String getSearchWord() {
		return txtSearch.getText();
	}
	
	private JButton newOperationButton(String title) {
		JButton b = new JButton(title);
		btnPanel.add(b);
		b.setForeground(new Color(255, 69, 0));
		b.setPreferredSize(new Dimension(200,50));
		b.setBackground(new Color(255, 255, 255));
		b.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		Dimension rect100=new Dimension(100,100);
		b.setPreferredSize(rect100);
		b.setMaximumSize(rect100);
		b.setMinimumSize(rect100);
		MaterialUIMovement.add(b, MaterialColors.GRAY_100);
		return b;
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
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		details.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JSeparator separator = new JSeparator();
		panel_3.add(separator);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut);
		
		lblDescription = new JLabel("图书信息");
		panel_3.add(lblDescription);
		lblDescription.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut_1);
		
		JSeparator separator_1 = new JSeparator();
		panel_3.add(separator_1);
		
		btnPanel = new JPanel();
		GridBagConstraints gbc_btnPanel = new GridBagConstraints();
		gbc_btnPanel.fill = GridBagConstraints.BOTH;
		gbc_btnPanel.gridx = 1;
		gbc_btnPanel.gridy = 1;
		details.add(btnPanel, gbc_btnPanel);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnAdd = newOperationButton("新建");
		btnModify = newOperationButton("更改");
		btnDelete = newOperationButton("删除");
		//----------------------------------------------
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Library.searchBooks(LibBooksPanel.this);
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BookDialog dlgBook=new BookDialog();
				dlgBook.getOkButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						BookBean b=dlgBook.getBook();
						Library.addBook(LibBooksPanel.this, b);
						dlgBook.dispose();
					}
				});
				dlgBook.setVisible(true);
			}
		});
		
		btnModify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idx=table.getSelectedRow();
				if(idx==-1) {
					System.out.println("没有选中行!");
					return;
				}
				BookBean cur=bookList.get(idx);
				BookDialog dlg=new BookDialog();
				dlg.setBook(cur);
				dlg.getOkButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						BookBean b=dlg.getBook();
						Library.updateBook(LibBooksPanel.this, b);
						dlg.dispose();
					}
					
				});
				dlg.setVisible(true);
			}
			
		});
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idx=table.getSelectedRow();
				if(idx==-1) {
					System.out.println("没有选中行!");
					return;
				}
				BookBean b=bookList.get(idx);
				Library.removeBook(LibBooksPanel.this, b);
			}
			
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int idx=table.getSelectedRow();
				BookBean b=bookList.get(idx);
				lblDescription.setText("<html><h2><span>《" + 
						b.getTitle() + 
						"》</span></h2>"
						+ "<p>&nbsp;&nbsp;<strong><span>作者:</span></strong><span>"
						+ b.getAuthor() + 
						"</span>&nbsp;&nbsp;<strong><span>出版社:</span></strong><span>"
						+ b.getPress() + 
						"</span>&nbsp;&nbsp;<strong><span>简介:</span></strong><span>"
						+ b.getDescription() + 
						"</span></p></html>");
			}
		});
		
	}
	public void setBookList(ArrayList<BookBean> data) {
		bookList.clear();
		table.removeAllRows();
		for(BookBean b:data) {
			bookList.add(b);
			table.addRow(new Object[] {b.getTitle(),b.getAuthor(),b.getPress(),b.getDescription(),b.getLocation(),b.getTotCnt(),b.getBorrowCnt()});
		}
		table.revalidate();
		table.repaint();
		//txtList.setText(s);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new MaterialLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			Config.log(e);
		}
		JFrame frame=new JFrame();
		frame.setSize(new Dimension(600,400));
		frame.setContentPane(new LibBooksPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}



