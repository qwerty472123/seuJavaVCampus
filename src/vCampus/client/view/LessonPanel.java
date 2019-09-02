package vCampus.client.view;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vCampus.client.view.utility.GroupifyBtnAndCard;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class LessonPanel extends JPanel {
	private JPanel panel;
	private JPanel panel_1;
	private JLabel label;
	private JButton tbBtn;
	private JButton ccBtn;
	private JPanel cards;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JToggleButton tglbtnNewToggleButton;
	private JButton btnNewButton_2;
	private JPanel optionPanel;
	private JPanel details;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JPanel panel_6;
	private Component horizontalGlue;
	private JPanel panel_7;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private Component verticalStrut_2;
	private Component verticalStrut_3;

	/**
	 * Create the panel.
	 */
	public LessonPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		label = new JLabel("Options");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(60, 179, 113));
		label.setFont(new Font("΢���ź�", Font.PLAIN, 15)); 
		label.setBackground(Color.WHITE);
		panel_1.add(label);
		
		tbBtn = new JButton("\u6211\u7684\u8BFE\u8868");
		tbBtn.setForeground(Color.GRAY);
		tbBtn.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		tbBtn.setBackground(new Color(255, 250, 240));
		panel_1.add(tbBtn);
		
		ccBtn = new JButton("\u9009\u8BFE");
		ccBtn.setForeground(Color.GRAY);
		ccBtn.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		ccBtn.setBackground(new Color(255, 250, 240));
		panel_1.add(ccBtn);
		
		cards = new JPanel();
		panel.add(cards, BorderLayout.CENTER);
		cards.setLayout(new CardLayout(0, 0));
		
		panel_3 = new JPanel();
		cards.add(panel_3, "name_1");
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tglbtnNewToggleButton = new JToggleButton("New toggle button");
		panel_4.add(tglbtnNewToggleButton);
		
		btnNewButton = new JButton("<");
		btnNewButton.setIcon(null);
		panel_4.add(btnNewButton);
		
		btnNewButton_1 = new JButton("\u2190");
		btnNewButton_1.setIcon(null);
		panel_4.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton(">");
		btnNewButton_2.setIcon(null);
		panel_4.add(btnNewButton_2);
		
		CourseTable coursetable=new CourseTable();
		panel_3.add(coursetable, BorderLayout.CENTER);

		
		panel_2 = new JPanel();
		cards.add(panel_2, "name_2");
		panel_2.setLayout(new BorderLayout(0, 0));
		
		optionPanel = new JPanel();
		panel_2.add(optionPanel, BorderLayout.SOUTH);
		optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
		
		SearchBar search=new SearchBar();
		optionPanel.add(search);
		search.foldupBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(details.isVisible())details.setVisible(false);
				else details.setVisible(true);
			}
			
		});
		
		details = new JPanel();
		optionPanel.add(details);
		details.setLayout(new GridLayout(0, 2, 0, 0));
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea.setRows(5);
		textArea.setText("�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n"+"�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n"+"�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n�?些详细信息\n");
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		details.add(scrollPane);
		
		panel_6 = new JPanel();
		details.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
		
		horizontalGlue = Box.createHorizontalGlue();
		panel_6.add(horizontalGlue);
		
		panel_7 = new JPanel();
		panel_6.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		panel_7.add(verticalStrut_2);
		
		btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		panel_7.add(btnNewButton_3);
		
		verticalStrut = Box.createVerticalStrut(20);
		panel_7.add(verticalStrut);
		
		btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		panel_7.add(btnNewButton_4);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		panel_7.add(verticalStrut_1);
		
		btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		panel_7.add(btnNewButton_5);
		
		verticalStrut_3 = Box.createVerticalStrut(20);
		panel_7.add(verticalStrut_3);
		
		
		/*this.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				System.out.println(e.getPoint());
				
			}
		});*/
		
		GroupifyBtnAndCard.groupBtnsAndCards(new JButton[] {tbBtn,ccBtn},cards);

	}
}
