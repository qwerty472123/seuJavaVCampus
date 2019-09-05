package vCampus.client.view;

import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.UIManager;


import mdlaf.*;
import mdlaf.animation.*;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{

	private JPanel contentPane;
	private JButton lastSelectedItem;
	private HashMap<String,JPanel> pagePanels=new HashMap<String,JPanel>();
	private HashMap<String,JButton> pageButtons=new HashMap<String,JButton>();
	private JPanel tablets;
	private JPanel cards;
	private ActionListener menuClickHandler;
	private static final int MENU_WIDTH=250;
	private static final int TAB_HEIGHT=90;
	
	/**
	 * 参数可选: student teacher admin
	 * @param roleName
	 */
	public void setRole(String roleName) {
		pagePanels.clear();
		pageButtons.clear();
		tablets.removeAll();
		cards.removeAll();
		if(roleName.equals("student")) {
			//此处定义学生模式的所有页面
			addMainPage("个人信息","/culture/028.png",new ProfilePanel());
			addMainPage("课表&选课","/education/025.png",new LessonPanel());
			addMainPage("校园银行","/finance/005.png",new BankPanel());
			addMainPage("图书馆系统","/education/047.png",new LibraryPanel());
			addMainPage("在线商店","/finance/043.png",new ShopPanel());
		}else if(roleName.equals("teacher")) {
			//此处定义教师模式的所有页面
			//addMainPage...
		}else if(roleName.equals("admin")) {
			//此处定义管理员模式的所有页面
			addMainPage("用户管理","",new AccountAdminPanel());
		}
		this.tablets.setPreferredSize(new Dimension(MENU_WIDTH,(int)(TAB_HEIGHT*(pagePanels.size()))));
	}
	
	public void addMainPage(String pageName,String iconFilePath,JPanel pagePanel) {
		pagePanels.put(pageName, pagePanel);
		JButton b=new JButton(pageName);
		b.setIcon(new ImageIcon(MainFrame.class.getResource(iconFilePath)));
		b.setPreferredSize(new Dimension(MENU_WIDTH,TAB_HEIGHT));
		b.setMinimumSize(new Dimension(MENU_WIDTH,TAB_HEIGHT));
		b.setHorizontalAlignment(SwingConstants.LEFT);
		b.setForeground(new Color(100, 149, 237));
		b.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		b.setBackground(new Color(255, 255, 255)); 
		b.addActionListener(menuClickHandler);
		MaterialUIMovement.add(b, MaterialColors.GRAY_100);
		this.tablets.add(b);
		this.cards.add(pagePanel,"name_"+pageName);
		pageButtons.put(pageName, b);
	}
	
/*	public void removeMainPage(String pageName) {
		JPanel p=pagePanels.get(pageName);
		JButton b=pageButtons.get(pageName);
		pagePanels.remove(p);
		pageButtons.remove(b);
		this.tablets.remove(b);
		this.cards.remove(p);
	}*/
	
	public JPanel getPagePanel(String pageName) {
		return pagePanels.get(pageName);
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel (new MaterialLookAndFeel ());
		}
		catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace ();
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setRole("admin");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public MainFrame(){
		
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 966, 661);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel menu = new JPanel();
		menu.setBackground(Color.WHITE);
		menu.setBorder(null);
		
		contentPane.add(menu, BorderLayout.WEST);
		  
		tablets = new JPanel();
		JScrollPane scrollpane=new JScrollPane(tablets);
		tablets.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setForeground(Color.ORANGE);
		verticalBox.setBorder(null);
		verticalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		menu.add(verticalBox);
		
		JLabel lblNewLabel_1 = new JLabel("vCampus Beta");
		lblNewLabel_1.setForeground(new Color(255, 140, 0));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		verticalBox.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("(/\u2267\u25BD\u2266)/");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 60));
		verticalBox.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menu.add(scrollpane);
		

		cards = new JPanel();
		cards.setBackground(Color.WHITE);
		contentPane.add(cards, BorderLayout.CENTER); 
		cards.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.NORTH);
		
		menuClickHandler=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				selectCard(((JButton)e.getSource()).getText());
				
			}
		};
		
		setRole("student");
	}
	
	
	public void selectCard(String name) {
		
		if(lastSelectedItem!=null) {
			//lastSelectedItem.setBackground(new Color(255,255,255));
			lastSelectedItem.setForeground(new Color(100, 149, 237));
		}
		//((JButton)e.getSource()).setBackground(new Color(109,158,235));
		pageButtons.get(name).setForeground(new Color(244,13,100));
		lastSelectedItem = pageButtons.get(name);
		((CardLayout)cards.getLayout()).show(cards, "name_"+name);
	}

}
