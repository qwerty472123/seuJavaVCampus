package vCampus.client.view;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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

public class LibraryPanel extends JPanel {

	private JPanel panel;
	private JPanel leftMenu;
	private JLabel label;
	private JButton booksBtn;
	private JButton brrowsBtn;
	private JButton ordersBtn;
	private JPanel cards;
	private LibBorrowsPanel borrowsPanel;
	private LibBooksPanel booksPanel;
	private LibOrdersPanel ordersPanel;
	
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(this,msg ,"",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showError(String msg) {
		JOptionPane.showMessageDialog(this,msg ,"",JOptionPane.WARNING_MESSAGE);
	}
	
	public LibBorrowsPanel getBorrowsPanel() {
		return borrowsPanel;
	}
	public LibBooksPanel getBooksPanel() {
		return booksPanel;
	}
	public LibOrdersPanel getOrdersPanel() {
		return ordersPanel;
	}
	
	/**
	 * Create the panel.
	 */
	public LibraryPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		leftMenu = new JPanel();
		panel.add(leftMenu, BorderLayout.WEST);
		leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
		
		label = new JLabel("Options");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(60, 179, 113));
		label.setBackground(Color.WHITE);
		leftMenu.add(label);
		
		booksBtn = new JButton("图书检索");
		booksBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		booksBtn.setForeground(Color.GRAY);
		booksBtn.setBackground(new Color(255, 250, 240));
		leftMenu.add(booksBtn);
		
		brrowsBtn = new JButton("我的借阅");
		brrowsBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		brrowsBtn.setForeground(Color.GRAY);
		brrowsBtn.setBackground(new Color(255, 250, 240));
		brrowsBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("From LessonPanel: request class selection page");
			}
			
		});
		leftMenu.add(brrowsBtn);
		
		ordersBtn = new JButton("我的预约");
		ordersBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		ordersBtn.setForeground(Color.GRAY);
		ordersBtn.setBackground(new Color(255, 250, 240));
		ordersBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("From LessonPanel: request grade page");
			}
		});
		leftMenu.add(ordersBtn);
		
		cards = new JPanel();
		cards.setBackground(Color.WHITE);
		panel.add(cards, BorderLayout.CENTER);
		cards.setLayout(new CardLayout(0, 0));
		booksPanel = new LibBooksPanel();
		borrowsPanel = new LibBorrowsPanel();
		ordersPanel = new LibOrdersPanel();
		cards.add(booksPanel, "name_1");
		cards.add(borrowsPanel, "name_2");
		cards.add(ordersPanel,"name_3");
		GroupifyBtnAndCard.groupBtnsAndCards(new JButton[] {booksBtn,brrowsBtn,ordersBtn},cards);
	}

}
