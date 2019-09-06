package vCampus.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vCampus.client.view.utility.GroupifyBtnAndCard;

public class LetterPanel extends JPanel{

	private JPanel pages;
	
	public LetterPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel mainContainer = new JPanel();
		add(mainContainer);
		mainContainer.setLayout(new BorderLayout(0, 0));
		
		
		JPanel menu = new JPanel();
		menu.setBackground(Color.WHITE);
		mainContainer.add(menu, BorderLayout.WEST);
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_21 = new JLabel("Options");
		lblNewLabel_21.setBackground(Color.WHITE);
		lblNewLabel_21.setForeground(new Color(60, 179, 113));
		lblNewLabel_21.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_21.setHorizontalAlignment(SwingConstants.CENTER);
		menu.add(lblNewLabel_21);
		
		JButton defaultBtn = new JButton("最新消息");
		defaultBtn.setForeground(Color.GRAY);
		defaultBtn.setBackground(new Color(255, 250, 240));
		defaultBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(defaultBtn);
		
		
		JButton singleBtn = new JButton("单人会话");
		singleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		singleBtn.setForeground(Color.GRAY);
		singleBtn.setBackground(new Color(255, 250, 240));
		singleBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(singleBtn);
		
		
		pages = new JPanel();
		mainContainer.add(pages, BorderLayout.CENTER);
		pages.setLayout(new CardLayout(0, 0));
		
		JPanel defaultPage = new JPanel();
		pages.add(defaultPage, "name_1");
		defaultPage.setBackground(Color.WHITE);
		defaultPage.setLayout(new BoxLayout(defaultPage, BoxLayout.Y_AXIS));
		
		GroupifyBtnAndCard.groupBtnsAndCards(new JButton[]{defaultBtn, singleBtn}, pages);
		
	}
	
}
