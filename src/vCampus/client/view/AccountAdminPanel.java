package vCampus.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vCampus.client.view.accountAdmin.AccountPanel;
import vCampus.client.view.library.UBookPanel;
import vCampus.client.view.library.UBorrowPanel;
import vCampus.client.view.library.UOrderPanel;
import vCampus.client.view.utility.GroupifyBtnAndCard;

public class AccountAdminPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel leftMenu;
	private JPanel cards;
	private JButton btnLogout;
	private AccountPanel accountPanel;
	
	/**
	 * Create the panel.
	 */
	public AccountAdminPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		leftMenu = new JPanel();
		panel.add(leftMenu, BorderLayout.NORTH);
		leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.X_AXIS));

		
		btnLogout = new JButton("账户登出");
		btnLogout.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnLogout.setForeground(Color.GRAY);
		btnLogout.setBackground(new Color(255, 250, 240));
		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		leftMenu.add(btnLogout);
		
		cards = new JPanel();
		cards.setBackground(Color.WHITE);
		panel.add(cards, BorderLayout.CENTER);
		cards.setLayout(new CardLayout(0, 0));
		accountPanel = new AccountPanel();
		cards.add(accountPanel, "name_1");
	}
}
