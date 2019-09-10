package vCampus.client.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

@SuppressWarnings("serial")
public class BankAllowPanel extends JFrame{
	private JLabel settleTitle;
	private JPanel settleContent;
	private JScrollPane settleScroll;
	private int settleCnt = 0;

	public BankAllowPanel() {
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		settleTitle = new JLabel("当前没有需结算的账单");
		settleTitle.setHorizontalAlignment(SwingConstants.CENTER);
		settleTitle.setFont(new Font("微软雅黑", Font.ITALIC, 24));
		add(settleTitle, BorderLayout.NORTH);

		
		settleContent = new JPanel();
		settleContent.setLayout(new BoxLayout(settleContent, BoxLayout.Y_AXIS));
		settleScroll = new JScrollPane(settleContent);
		add(settleScroll, BorderLayout.CENTER);
	}

}
