package vCampus.client.view.library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import vCampus.bean.BookOrderRecBean;
import vCampus.bean.BookOrderRecBean;
import vCampus.client.controller.Library;
import vCampus.client.view.utility.MyTable;
import vCampus.utility.Config;
import vCampus.utility.MyDate;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MOrderPanel extends JPanel implements OrderPanel{
	private JTextField txtBookId;
	private MyTable table;
	private JPanel details;
	private JButton btnCancel;
	private ArrayList<BookOrderRecBean> recordList=new ArrayList<BookOrderRecBean>();
	private JPanel btnPanel;
	private JLabel lblDescription;
	private JButton btnDone;
	private JTextField txtUserId;
	
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
	
	public MOrderPanel() {
		setLayout(new BorderLayout(0, 0));
		JPanel page = new JPanel();
		add(page);
		GridBagLayout gbl_page = new GridBagLayout();
		gbl_page.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_page.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_page.columnWeights = new double[]{1.0, 0.0, 3.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_page.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		page.setLayout(gbl_page);
		
		JLabel label = new JLabel("预约记录清单");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 7;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		page.add(label, gbc_label);
		
		JLabel label_1 = new JLabel("条件查询");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 1;
		page.add(label_1, gbc_label_1);
		
		JLabel label_2 = new JLabel("功能性查询");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 5;
		gbc_label_2.gridy = 1;
		page.add(label_2, gbc_label_2);
		
		JLabel lblNewLabel = new JLabel("图书ID:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		page.add(lblNewLabel, gbc_lblNewLabel);

		txtBookId = new JTextField();
		GridBagConstraints gbc_txtBookId = new GridBagConstraints();
		gbc_txtBookId.insets = new Insets(0, 0, 5, 5);
		gbc_txtBookId.gridx = 2;
		gbc_txtBookId.gridy = 2;
		gbc_txtBookId.fill = GridBagConstraints.HORIZONTAL;
		page.add(txtBookId, gbc_txtBookId);
		
		table=new MyTable(new String[] {"流水ID","图书ID","预约用户ID","预约期限","状态"});
		
		
		JScrollPane tbContainer = new JScrollPane(table);
		tbContainer.getViewport().setBackground(Color.white);
		
		
		
		JButton btnNewButton = new JButton("逾期查询");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 2;
		page.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("可借出查询");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 5;
		gbc_btnNewButton_1.gridy = 3;
		page.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("用户ID:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		page.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtUserId = new JTextField();
		GridBagConstraints gbc_txtUserId = new GridBagConstraints();
		gbc_txtUserId.insets = new Insets(0, 0, 5, 5);
		gbc_txtUserId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUserId.gridx = 2;
		gbc_txtUserId.gridy = 3;
		page.add(txtUserId, gbc_txtUserId);
		txtUserId.setColumns(10);
		
		JButton btnSearch = new JButton("检索");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.BOTH;
		gbc_btnSearch.gridheight = 2;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 3;
		gbc_btnSearch.gridy = 2;
		page.add(btnSearch, gbc_btnSearch);
		GridBagConstraints gbc_tbContainer = new GridBagConstraints();
		gbc_tbContainer.gridwidth = 5;
		gbc_tbContainer.insets = new Insets(0, 0, 5, 5);
		gbc_tbContainer.fill = GridBagConstraints.BOTH;
		gbc_tbContainer.gridx = 1;
		gbc_tbContainer.gridy = 4;
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
		
		lblDescription = new JLabel("");
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
		btnCancel = newOperationButton("取消");
		btnDone = newOperationButton("借出");
		//----------------------------------------------
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Library.queryOrder(MOrderPanel.this, getUserIdTxt(), getBookIdTxt());
			}
		});
		
		btnDone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idx=table.getSelectedRow();
				if(idx==-1) {
					System.out.println("没有选中行!");
					return;
				}
				BookOrderRecBean b=recordList.get(idx);
				Library.doneOrder(MOrderPanel.this, b);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idx=table.getSelectedRow();
				if(idx==-1) {
					System.out.println("没有选中行!");
					return;
				}
				BookOrderRecBean b=recordList.get(idx);
				Library.cancelOrder(MOrderPanel.this, b);
			}
			
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int idx=table.getSelectedRow();
				BookOrderRecBean b=recordList.get(idx);
			}
		});
		
	}
	protected int getBookIdTxt() {
		// TODO Auto-generated method stub
		return txtBookId.getText().isEmpty()?-1:Integer.parseInt(txtBookId.getText());
	}

	protected int getUserIdTxt() {
		// TODO Auto-generated method stub
		return txtUserId.getText().isEmpty()?-1:Integer.parseInt(txtUserId.getText());
	}

	public void setOrderList(ArrayList<BookOrderRecBean> data) {
		recordList.clear();
		table.removeAllRows();
		data.sort(new Comparator<BookOrderRecBean>() {
			@Override
			public int compare(BookOrderRecBean o1, BookOrderRecBean o2) {
				// TODO Auto-generated method stub
				return o1.getID()==o2.getID()?0:(o1.getID()<o2.getID()?-1:1);
			}
		});
		
		for(BookOrderRecBean rc:data) {
			recordList.add(rc);
			table.addRow(new Object[] {rc.getID(),rc.getBookId(),rc.getUserId(),
					MyDate.toString(rc.getDueTime(), "yyyy-MM-dd HH:mm"),
					(rc.getDueTime().compareTo(MyDate.nowOnDay(0))<0?"已逾期"
						:(rc.isDoneable()?"可借出":"等待中"))
			});
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
		frame.setContentPane(new MOrderPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		Library.queryOrder(MOrderPanel.this, getUserIdTxt(), getBookIdTxt());
	}
}