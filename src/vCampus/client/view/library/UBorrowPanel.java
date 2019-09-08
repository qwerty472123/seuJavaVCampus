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

import vCampus.bean.BookBean;
import vCampus.bean.BookBorrowRecBean;
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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class UBorrowPanel extends JPanel implements BorrowPanel{
	private MyTable table;
	private JPanel details;
	private ArrayList<BookBorrowRecBean> recordList=new ArrayList<BookBorrowRecBean>();
	private JPanel btnPanel;
	private JButton btnRenewal;
	
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
	
	public UBorrowPanel() {
		setLayout(new BorderLayout(0, 0));
		JPanel page = new JPanel();
		add(page);
		GridBagLayout gbl_page = new GridBagLayout();
		gbl_page.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_page.rowHeights = new int[]{0, 0, 0, 0};
		gbl_page.columnWeights = new double[]{0.0, 3.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_page.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		page.setLayout(gbl_page);
		
		JLabel label = new JLabel("借阅记录清单");
		label.setForeground(new Color(255, 102, 102));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 4;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		page.add(label, gbc_label);
		
		table=new MyTable(new String[] {"流水ID","图书ID","借书用户ID","借书时间","归还期限","状态"});
		table.setColumnWidth(3,200);
		table.setColumnWidth(4,200);
		table.setColumnWidth(5,50);
		table.setRowHighlight(MaterialColors.AMBER_50);
		table.addWordHighlight("正常", MyStyle.NORMAL);
		table.addWordHighlight("已逾期", MyStyle.WARNING);
		
		JScrollPane tbContainer = new JScrollPane(table);
		tbContainer.getViewport().setBackground(Color.white);
		GridBagConstraints gbc_tbContainer = new GridBagConstraints();
		gbc_tbContainer.gridwidth = 4;
		gbc_tbContainer.insets = new Insets(0, 0, 5, 0);
		gbc_tbContainer.fill = GridBagConstraints.BOTH;
		gbc_tbContainer.gridx = 0;
		gbc_tbContainer.gridy = 1;
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
		
		JSeparator separator_1 = new JSeparator();
		panel_3.add(separator_1);
		
		btnPanel = new JPanel();
		GridBagConstraints gbc_btnPanel = new GridBagConstraints();
		gbc_btnPanel.fill = GridBagConstraints.BOTH;
		gbc_btnPanel.gridx = 1;
		gbc_btnPanel.gridy = 1;
		details.add(btnPanel, gbc_btnPanel);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnRenewal = newOperationButton("续期");
		btnRenewal.setForeground(MyStyle.RED);
		//----------------------------------------------
		btnRenewal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idx=table.getSelectedRow();
				if(idx==-1) {
					System.out.println("没有选中行!");
					return;
				}
				BookBorrowRecBean b=recordList.get(idx);
				Library.renewalBorrow(UBorrowPanel.this, b);
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int idx=table.getSelectedRow();
				BookBorrowRecBean b=recordList.get(idx);
			}
		});
		
	}

	public void setBorrowList(ArrayList<BookBorrowRecBean> data) {
		recordList.clear();
		table.removeAllRows();
		data.sort(new Comparator<BookBorrowRecBean>() {
			@Override
			public int compare(BookBorrowRecBean o1, BookBorrowRecBean o2) {
				// TODO Auto-generated method stub
				return o1.getID()==o2.getID()?0:(o1.getID()<o2.getID()?-1:1);
			}
		});
		
		for(BookBorrowRecBean b:data) {
			recordList.add(b);
			table.addRow(new Object[] {b.getID(),b.getBookId(),b.getUserId(),
					MyDate.toString(b.getBorrowTime(), "yyyy-MM-dd HH:mm"),
					MyDate.toString(b.getDueTime(), "yyyy-MM-dd HH:mm"),
					(b.getDueTime().compareTo(MyDate.nowOnDay(0))<0?"已逾期":"正常")
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
		frame.setContentPane(new UBorrowPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		Library.queryBorrow(UBorrowPanel.this);
	}
}
