package vCampus.client.view;


import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import vCampus.client.controller.Auth;
import vCampus.client.controller.TeacherGrade;
import vCampus.client.view.utility.GroupifyBtnAndCard;
import vCampus.client.view.utility.JlabelLink;
import vCampus.client.view.utility.MyTable;
import vCampus.client.view.utility.WeatherUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JSeparator;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TeacherGradePanel extends JPanel {
	
	private JPanel pages;
	
	private JPasswordField passwordField;
	private JTextField rechargeField;
	
	private JLabel settleTitle;
	private JPanel settleContent;
	
	private MyTable recordTable;
	private JTable table_1;
	private Object[][] courses;
	private JPanel lessonpage;
	private JScrollPane courseTable; 
	private Boolean init;
	
	private JLabel label_day1;
	private JLabel label_day2;
	private JLabel label_day3;
	private JLabel label_day4;
	private JLabel label_day5;
	private JLabel label_day6;
	private JLabel label_day7;
	private JTable table;
	private int beginday;
	private JLabel label_name;
	
	
	public JLabel getLabel_name() {
		return label_name;
	}

	public void setLabel_name(String label_name) {
		this.label_name.setText("教师"+label_name);
	}

	/**
	 * Create the panel.
	 */
	public TeacherGradePanel() {
		
		init = false;
		setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel menu = new JPanel();
		menu.setBackground(Color.WHITE);
		panel_2.add(menu, BorderLayout.WEST);
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_21 = new JLabel("Options");
		lblNewLabel_21.setBackground(Color.WHITE);
		lblNewLabel_21.setForeground(new Color(60, 179, 113));
		lblNewLabel_21.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_21.setHorizontalAlignment(SwingConstants.CENTER);
		menu.add(lblNewLabel_21);
		
		JButton basicBtn = new JButton("教师主页");
		basicBtn.setForeground(Color.GRAY);
		basicBtn.setBackground(new Color(255, 250, 240));
		basicBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(basicBtn);
		
		JButton secureBtn = new JButton("成绩登记");
		secureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			}
		});
		secureBtn.setForeground(Color.GRAY);
		secureBtn.setBackground(new Color(255, 250, 240));
		secureBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(secureBtn);
		
		JButton settleBtn = new JButton("退出登录");
		settleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Auth.logout();
			}
		});
		settleBtn.setForeground(Color.GRAY);
		settleBtn.setBackground(new Color(255, 250, 240));
		settleBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(settleBtn);
		
		JButton recordBtn = new JButton("账单记录");
		recordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		recordBtn.setForeground(Color.GRAY);
		recordBtn.setBackground(new Color(255, 250, 240));
		recordBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(recordBtn);
		
		pages = new JPanel();
		panel_2.add(pages, BorderLayout.CENTER);
		pages.setLayout(new CardLayout(0, 0));		
        
		
		JPanel homepage = new JPanel();
		pages.add(homepage, "name_1");
		homepage.setPreferredSize(new Dimension(625, 476));
		homepage.setBackground(Color.WHITE);
		homepage.setLayout(new BorderLayout(0, 0));
		
		
	    ImageIcon image = new ImageIcon(new ImageIcon("photo/background.jpg").getImage().getScaledInstance(800, 1200, Image.SCALE_DEFAULT));
		
		JPanel panel = new JPanel(){
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), image.getImageObserver());
            }	
		};
		homepage.add(panel, BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(625, 476));
		
		panel.setLayout(new BorderLayout());
		
		JPanel left = new JPanel();
		left.setPreferredSize(new Dimension(300, 476));
		left.setOpaque(false);
		JPanel right = new JPanel();
		right.setPreferredSize(new Dimension(325, 476));
		right.setOpaque(false);
		
		left.setLayout(new BorderLayout());
		JPanel left_1 = new JPanel(){
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				
				Graphics2D graphics2d = (Graphics2D) g.create();
		 
				graphics2d.setComposite(AlphaComposite.SrcOver.derive(5));
					
				graphics2d.setColor(Color.white);
				BufferedImage background;
				try {
					background = ImageIO.read(ImageIO.createImageInputStream(new File("photo/background.jpg")));
			      	graphics2d.drawImage(background, 0, 0, this);
				} catch (IOException e) {
					e.printStackTrace();
				}		 
				graphics2d.dispose();
			}
		};
		left_1.setForeground(Color.BLACK);
		left_1.setPreferredSize(new Dimension(300, 176));
		left_1.setBackground(Color.RED);
		left_1.setOpaque(false);
		
		JPanel left_2 = new JPanel();
		left_2.setPreferredSize(new Dimension(300, 300));
		left_2.setBackground(Color.GREEN);
		left_2.setOpaque(false);
		
		left.add(left_1, BorderLayout.CENTER);
		
		JPanel left_1_1 = new JPanel();
		left_1_1.setOpaque(false);
		left_1_1.setPreferredSize(new Dimension(300, 40));
		left_1.add(left_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("校网链接库");
		lblNewLabel_1.setOpaque(false);
		lblNewLabel_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 19));
		left_1_1.add(lblNewLabel_1);
		
		JPanel left_1_2 = new JPanel();
		JlabelLink Link1 = new JlabelLink("教务处", "https://jwc.seu.edu.cn/");
		left_1_2.add(Link1);
		left_1_2.setPreferredSize(new Dimension(300, 25));
		left_1_2.setOpaque(false);
		left_1.add(left_1_2);
		
		JPanel left_1_3 = new JPanel();
		JlabelLink Link2 = new JlabelLink("信息门户", "http://my.seu.edu.cn/login.portal");
		left_1_3.add(Link2);
		left_1_3.setOpaque(false);
		left_1_3.setPreferredSize(new Dimension(300, 25));
		left_1.add(left_1_3);
		
		JPanel left_1_4 = new JPanel();
		JlabelLink Link3 = new JlabelLink("教师在线", "http://xk.urp.seu.edu.cn/teacherService/system/showLogin.action");
		left_1_4.add(Link3);
		left_1_4.setPreferredSize(new Dimension(300, 25));
		left_1_4.setOpaque(false);
		left_1.add(left_1_4);
		
		JPanel left_1_5 = new JPanel();
		JlabelLink Link4 = new JlabelLink("教学研究", "http://58.192.114.179/teachqualityreview/home/login.action");
		left_1_5.add(Link4);
		left_1_5.setPreferredSize(new Dimension(300, 25));
		left_1_5.setOpaque(false);
		left_1.add(left_1_5);
		left.add(left_2, BorderLayout.SOUTH);
		
		JPanel panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(300, 40));
		panel_6.setOpaque(false);
		left_2.add(panel_6);
		
		JLabel label_4 = new JLabel("9月日历");
		label_4.setOpaque(false);
		label_4.setFont(new Font("微软雅黑 Light", Font.PLAIN, 19));
		panel_6.add(label_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(300, 250));
		panel_5.setOpaque(false);
		left_2.add(panel_5);
		
		table = new JTable();
		table.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		//table.setPreferredSize(new Dimension(270, 250));
		//table.setBounds(0, 0, 250, 250);
		table.setRowHeight(30);
		table.setOpaque(false);
		table.setLayout(new BorderLayout());
		Object[][] object = {
				{"Sun", "Mon", "Tue", "Wed", "Tus", "Fri", "Sat"},
				{new Integer(1), new Integer(2), new Integer(3), new Integer(4), new Integer(5), new Integer(6), new Integer(7)},
				{new Integer(8), new Integer(9), new Integer(10), new Integer(11), new Integer(12), new Integer(13), new Integer(14)},
				{new Integer(15), new Integer(16), new Integer(17), new Integer(18), new Integer(19), new Integer(20), new Integer(21)},
				{new Integer(22), new Integer(23), new Integer(24), new Integer(25), new Integer(26), new Integer(27), new Integer(28)},
				{new Integer(29), new Integer(30), null, null, null, null, null},
		};
		
		table.setModel(new DefaultTableModel(
				object, new String[] {
				"Sun", "Mon", "Tue", "Wed", "Tus", "Fri", "Sat"
			}
		));
		for(int i = 0; i < 7; i++){
			if(object[1][i] != null)
				beginday = i;
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(42);
		table.getColumnModel().getColumn(1).setPreferredWidth(42);
		table.getColumnModel().getColumn(2).setPreferredWidth(42);
		table.getColumnModel().getColumn(3).setPreferredWidth(42);
		table.getColumnModel().getColumn(4).setPreferredWidth(42);
		table.getColumnModel().getColumn(5).setPreferredWidth(42);
		table.getColumnModel().getColumn(6).setPreferredWidth(42);
		setOneRowBackgroundColor(table,0,Color.LIGHT_GRAY);
		panel_5.add(table);
		
		right.setLayout(new BorderLayout());
		JPanel right_1 = new JPanel();
		right_1.setPreferredSize(new Dimension(325, 100));
		right_1.setOpaque(false);
		
		JPanel right_2 = new JPanel(){
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				
				Graphics2D graphics2d = (Graphics2D) g.create();
		 
				graphics2d.setComposite(AlphaComposite.SrcOver.derive(5));   //1-10   1最深
					
				graphics2d.setColor(Color.white);
				BufferedImage background;
				try {
					background = ImageIO.read(ImageIO.createImageInputStream(new File("photo/background.jpg")));
			      	graphics2d.drawImage(background, 0, 0, this);
				} catch (IOException e) {
					e.printStackTrace();
				}		 
				graphics2d.dispose();
			}
		};
		right_2.setPreferredSize(new Dimension(325, 376));
		right_2.setOpaque(false);
		
		right.add(right_1, BorderLayout.NORTH);
		
		JPanel right_1_1 = new JPanel();
		right_1_1.setOpaque(false);
		right_1_1.setPreferredSize(new Dimension(325, 40));
		right_1_1.setLayout(new BorderLayout());		
		right_1.add(right_1_1,BorderLayout.NORTH);
		
		label_name = new JLabel("教师");
		label_name.setOpaque(false);
		label_name.setIcon(new ImageIcon(TeacherGradePanel.class.getResource("/user/001.png")));
		label_name.setLayout(new BorderLayout());
		right_1_1.add(label_name, BorderLayout.WEST);
		label_name.setForeground(Color.BLACK);
		label_name.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JLabel lblNewLabel_6 = new JLabel("，您好！");
		lblNewLabel_6.setOpaque(false);
		lblNewLabel_6.setLayout(new BorderLayout());
		right_1_1.add(lblNewLabel_6, BorderLayout.CENTER);
		lblNewLabel_6.setForeground(Color.BLACK);
		lblNewLabel_6.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JPanel right_1_2 = new JPanel();
		right_1_2.setOpaque(false);
		right_1_2.setPreferredSize(new Dimension(325, 40));
		right_1_2.setLayout(new BorderLayout());		
		right_1.add(right_1_2,BorderLayout.CENTER);		
		
		JLabel lblNewLabel_3 = new JLabel("         欢迎登录！");
		lblNewLabel_3.setOpaque(false);
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		right_1_2.add(lblNewLabel_3, BorderLayout.CENTER);
		right.add(right_2, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(300, 40));
		panel_1.setOpaque(false);
		right_2.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("未来7天天气：");
		lblNewLabel_2.setOpaque(false);
		lblNewLabel_2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 19));
		panel_1.add(lblNewLabel_2);
		
		JLabel label = new JLabel("");
		label.setOpaque(false);
		label.setFont(new Font("微软雅黑 Light", Font.PLAIN, 19));
		panel_1.add(label);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(300, 40));
		panel_3.setOpaque(false);
		right_2.add(panel_3);
		
		label_day1 = new JLabel("New label");
		label_day1.setOpaque(false);
		label_day1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 19));
		panel_3.add(label_day1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(300, 40));
		panel_4.setOpaque(false);
		right_2.add(panel_4);
		
		label_day2 = new JLabel("今天的天气是：");
		label_day2.setOpaque(false);
		label_day2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 19));
		panel_4.add(label_day2);
		
		JPanel panel_8 = new JPanel();
		panel_8.setPreferredSize(new Dimension(300, 40));
		panel_8.setOpaque(false);
		right_2.add(panel_8);
		
		label_day3 = new JLabel("今天的天气是：");
		label_day3.setOpaque(false);
		label_day3.setFont(new Font("微软雅黑 Light", Font.PLAIN, 19));
		panel_8.add(label_day3);
		
		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(300, 40));
		panel_9.setOpaque(false);
		panel_8.add(panel_9);
		
		JLabel label_7 = new JLabel("今天的天气是：");
		label_7.setOpaque(false);
		label_7.setFont(new Font("微软雅黑 Light", Font.PLAIN, 19));
		panel_9.add(label_7);
		
		JPanel panel_10 = new JPanel();
		panel_10.setPreferredSize(new Dimension(300, 40));
		panel_10.setOpaque(false);
		right_2.add(panel_10);
		
		label_day4 = new JLabel("今天的天气是：");
		label_day4.setOpaque(false);
		label_day4.setFont(new Font("微软雅黑 Light", Font.PLAIN, 19));
		panel_10.add(label_day4);
		
		JPanel panel_11 = new JPanel();
		panel_11.setPreferredSize(new Dimension(300, 40));
		panel_11.setOpaque(false);
		right_2.add(panel_11);
		
		label_day5 = new JLabel("今天的天气是：");
		label_day5.setOpaque(false);
		label_day5.setFont(new Font("微软雅黑 Light", Font.PLAIN, 19));
		panel_11.add(label_day5);
		
		JPanel panel_12 = new JPanel();
		panel_12.setPreferredSize(new Dimension(300, 40));
		panel_12.setOpaque(false);
		right_2.add(panel_12);
		
		label_day6 = new JLabel("今天的天气是：");
		label_day6.setOpaque(false);
		label_day6.setFont(new Font("微软雅黑 Light", Font.PLAIN, 19));
		panel_12.add(label_day6);
		
		JPanel panel_13 = new JPanel();
		panel_13.setPreferredSize(new Dimension(300, 40));
		panel_13.setOpaque(false);
		right_2.add(panel_13);
		
		label_day7 = new JLabel("今天的天气是：");
		label_day7.setOpaque(false);
		label_day7.setFont(new Font("微软雅黑 Light", Font.PLAIN, 19));
		panel_13.add(label_day7);
		
		panel.add(left, BorderLayout.EAST);		
		JSeparator separator = new JSeparator();
		panel.add(separator, BorderLayout.NORTH);
		panel.add(right, BorderLayout.CENTER);
		
	    
		lessonpage = new JPanel();
		pages.add(lessonpage, "name_2");
		lessonpage.setBackground(Color.WHITE);
		lessonpage.setLayout(new BoxLayout(lessonpage, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("所授课程：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lessonpage.add(lblNewLabel);

		GroupifyBtnAndCard.groupBtnsAndCards(new JButton[]{basicBtn, secureBtn, settleBtn, recordBtn}, pages);
		initWeather();
	}
	
	public void initCoursetable(Object[][] object){
		courseTable = TeacherGradeTable.addTeacherGradeTable(object);
		if (init == false) {
		lessonpage.add(courseTable);
		init = true;
		}
	}
	
	public void initWeather(){
		ArrayList<String> wea = WeatherUtil.getWeatherInfo("http://www.weather.com.cn/weather/101190101.shtml");
		JLabel[] labels = {label_day1, label_day2, label_day3, label_day4, label_day5, label_day6, label_day7};
		for(int i = 0; i < 7;i++ ){
			labels[i].setText(wea.get(i));
			if(wea.get(i)==("多云"))labels[i].setIcon(new ImageIcon(TeacherGradePanel.class.getResource("/weather/002.png")));
			else if(wea.get(i) == "晴")labels[i].setIcon(new ImageIcon(TeacherGradePanel.class.getResource("/weather/001.png")));
			else if(wea.get(i).contains("阴"))labels[i].setIcon(new ImageIcon(TeacherGradePanel.class.getResource("/weather/011.png")));
			else if(wea.get(i).contains("雨")){				
				if(wea.get(i).contains("小雨"))labels[i].setIcon(new ImageIcon(TeacherGradePanel.class.getResource("/weather/012.png")));
				if(wea.get(i).contains("中雨"))labels[i].setIcon(new ImageIcon(TeacherGradePanel.class.getResource("/weather/013.png")));
				if(wea.get(i).contains("大雨"))labels[i].setIcon(new ImageIcon(TeacherGradePanel.class.getResource("/weather/014.png")));
				if(wea.get(i).contains("雷阵雨"))labels[i].setIcon(new ImageIcon(TeacherGradePanel.class.getResource("/weather/015.png")));
				
			}
			else if(wea.get(i).contains("多云"))labels[i].setIcon(new ImageIcon(TeacherGradePanel.class.getResource("/weather/002.png")));
			else labels[i].setIcon(new ImageIcon(TeacherGradePanel.class.getResource("/weather/001.png")));
		}
		
		
	}
	
	

	public void setOneRowBackgroundColor(JTable table, int rowIndex,
			Color color) {
		try {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {

				private static final long serialVersionUID = 1L;

				public Component getTableCellRendererComponent(JTable table,
						Object value, boolean isSelected, boolean hasFocus,
						int row, int column) {
					setHorizontalAlignment(JLabel.CENTER);
					Component cell = super.getTableCellRendererComponent  
							(table, value, isSelected, hasFocus, row, column);
					Date date = new Date();
					@SuppressWarnings("deprecation")
					int day = date.getDate();
					if(day == column+(7 - beginday) + (row-1)*7){
						cell.setBackground(color);
						cell.setForeground(Color.BLUE);
					}
					else if (row == rowIndex) {
						cell.setBackground(color);
						cell.setForeground(Color.black);
					}
					
					else if(row > rowIndex){
						cell.setBackground(Color.white);
						cell.setForeground(Color.black);
					}else{
						cell.setBackground(Color.white);
						cell.setForeground(Color.black);
					}
                    
					return cell;
				}
			};
			int columnCount = table.getColumnCount();
			for (int i = 0; i < columnCount; i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 表格数据居中
	 * @param table
	 */
	public void setTableColumnCenter(JTable table){
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class, r);
	}
}
