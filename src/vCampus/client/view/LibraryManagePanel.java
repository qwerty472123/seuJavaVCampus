package vCampus.client.view;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vCampus.client.view.library.MBookPanel;
import vCampus.client.view.library.MBorrowPanel;
import vCampus.client.view.library.MOrderPanel;
import vCampus.client.view.utility.GroupifyBtnAndCard;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LibraryManagePanel extends JPanel {

	private JPanel panel;
	private JPanel leftMenu;
	private JButton booksBtn;
	private JButton borrowsBtn;
	private JButton ordersBtn;
	private JPanel cards;
	private MBorrowPanel borrowsPanel;
	private MBookPanel booksPanel;
	private MOrderPanel ordersPanel;
	
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(this,msg ,"",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showError(String msg) {
		JOptionPane.showMessageDialog(this,msg ,"",JOptionPane.WARNING_MESSAGE);
	}
	
	public MBorrowPanel getBorrowsPanel() {
		return borrowsPanel;
	}
	public MBookPanel getBooksPanel() {
		return booksPanel;
	}
	public MOrderPanel getOrdersPanel() {
		return ordersPanel;
	}
	
	/**
	 * Create the panel.
	 */
	public LibraryManagePanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		leftMenu = new JPanel();
		panel.add(leftMenu, BorderLayout.NORTH);
		leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.X_AXIS));
		
		booksBtn = new JButton("图书管理");
		booksBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		booksBtn.setForeground(Color.GRAY);
		booksBtn.setBackground(new Color(255, 250, 240));
		leftMenu.add(booksBtn);
		
		borrowsBtn = new JButton("借阅管理");
		borrowsBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		borrowsBtn.setForeground(Color.GRAY);
		borrowsBtn.setBackground(new Color(255, 250, 240));
		leftMenu.add(borrowsBtn);
		
		ordersBtn = new JButton("预约管理");
		ordersBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		ordersBtn.setForeground(Color.GRAY);
		ordersBtn.setBackground(new Color(255, 250, 240));
		leftMenu.add(ordersBtn);
		
		cards = new JPanel();
		cards.setBackground(Color.WHITE);
		panel.add(cards, BorderLayout.CENTER);
		cards.setLayout(new CardLayout(0, 0));
		booksPanel = new MBookPanel();
		borrowsPanel = new MBorrowPanel();
		ordersPanel = new MOrderPanel();
		cards.add(booksPanel, "name_1");
		cards.add(borrowsPanel, "name_2");
		cards.add(ordersPanel,"name_3");
		
		booksBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				booksPanel.refresh();
			}
		});
		
		borrowsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				borrowsPanel.refresh();
			}
		});
		
		ordersBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ordersPanel.refresh();
			}
		});
		
		
		GroupifyBtnAndCard.groupBtnsAndCards(new JButton[] {booksBtn,borrowsBtn,ordersBtn},cards);
	
	}

}
