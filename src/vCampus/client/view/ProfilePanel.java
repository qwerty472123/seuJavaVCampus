package vCampus.client.view;


import javax.swing.JPanel;
import vCampus.utility.*;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.client.controller.Auth;
import vCampus.client.controller.PersonInfo;
import vCampus.client.view.utility.*;

import java.awt.Color;
import javax.swing.Box;
import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.JTextArea;

public class ProfilePanel extends JPanel {
	private JLabel label_name;
	private JLabel label_gender;
	private JLabel label_birth;
	private JLabel label_age;
	private JLabel label_ID;
	private JLabel label_balance;
	private JLabel label_depart;
	private JLabel label_grade;
	private JLabel label_class;
	private JLabel label_mail;
	private JLabel label_phone;
	private JLabel label_qq;
	private JLabel label_ID_ch;
	private JLabel label_photo;
	private JLabel label_photo_ch;
	private JTextArea textArea_photopath;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JPasswordField passwordField;
	
	private JTextField textField_name;
	private JTextField textField_gender;
	private JTextField textField_birth;
	private JTextField textField_age;
	private JTextField textField_depart;
	private JTextField textField_grade;
	private JTextField textField_class;
	private JTextField textField_mail;
	private JTextField textField_phone;
	private JTextField textField_qq;

	/**
	 * Create the panel.
	 */
    public void setPhoto(ImageIcon s){
    	label_photo.setIcon(s);
    	//label_photo_ch.setIcon(s);
    	
    }
	
	
	public void setPersonInfo(ArrayList<String> s){
		label_name.setText(s.get(0));
		//0 for male  1 for female
		if(s.get(1).equals("0"))
			label_gender.setText("男");
		else label_gender.setText("女");		
		label_birth.setText(s.get(2));
		label_age.setText(s.get(3));
		label_ID.setText(s.get(4));
		label_balance.setText(s.get(5));
		label_depart.setText(s.get(6));
		label_grade.setText(s.get(7));
		label_class.setText(s.get(8));
		label_mail.setText(s.get(9));
		label_phone.setText(s.get(10));
		label_qq.setText(s.get(11));
		label_ID_ch.setText(s.get(4));
		//label_balance_ch.setText(s.get(5));
		
		textField_name.setText(s.get(0));
		if(s.get(1).equals("0"))
			textField_gender.setText("男");
		else textField_gender.setText("女");	
		textField_birth.setText(s.get(2));
		textField_age.setText(s.get(3));
		textField_depart.setText(s.get(6));
		textField_grade.setText(s.get(7));
		textField_class.setText(s.get(8));
		textField_mail.setText(s.get(9));
		textField_phone.setText(s.get(10));
		textField_qq.setText(s.get(11));		
	}
	
	
	
	public ProfilePanel() {
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
		
		JButton basicBtn = new JButton("我的名片");
		basicBtn.setForeground(Color.GRAY);
		basicBtn.setBackground(new Color(255, 250, 240));
		basicBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(basicBtn);
		
		JButton secureBtn = new JButton("修改密码");
		secureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		secureBtn.setForeground(Color.GRAY);
		secureBtn.setBackground(new Color(255, 250, 240));
		secureBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(secureBtn);
		
		JButton changeBtn = new JButton("信息修改");
		changeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonInfo.getStatus();
			}
		});
		changeBtn.setForeground(Color.GRAY);
		changeBtn.setBackground(new Color(255, 250, 240));
		changeBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(changeBtn);
		
		JButton logoutBtn = new JButton("账号登出");
		logoutBtn.setForeground(Color.GRAY);
		logoutBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		logoutBtn.setBackground(new Color(255, 250, 240));
		menu.add(logoutBtn);
		
		MaterialUIMovement.add(logoutBtn, MaterialColors.RED_100);
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Auth.logout();
			}
			
		});
		
		
		JPanel pages = new JPanel();
		panel_2.add(pages, BorderLayout.CENTER);
		pages.setLayout(new CardLayout(0, 0));

		
		JPanel profilepage = new JPanel();
		pages.add(profilepage, "name_1");
		profilepage.setBackground(Color.WHITE);
		profilepage.setLayout(new BoxLayout(profilepage, BoxLayout.Y_AXIS));
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		verticalGlue_1.setBackground(Color.WHITE);
		profilepage.add(verticalGlue_1);
		
		JPanel panel_1 = new JPanel();
		profilepage.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		label_photo = new JLabel("");
		panel_1.add(label_photo);
		label_photo.setHorizontalAlignment(SwingConstants.CENTER);
		label_photo.setIcon(new ImageIcon(ProfilePanel.class.getResource("/av.jpg")));
		
		JPanel panel_10 = new JPanel();
		panel_1.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.X_AXIS));
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.WHITE);
		panel_10.add(panel_11);
		panel_11.setLayout(new GridLayout(6, 0, 0, 0));
		
		JLabel label_1 = new JLabel("Name 姓名 ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.GRAY);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11.add(label_1);
		
		label_name = new JLabel("New label");
		label_name.setHorizontalAlignment(SwingConstants.CENTER);
		label_name.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11.add(label_name);
		
		JLabel label_3 = new JLabel("Gender 性别 ");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.GRAY);
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11.add(label_3);
		
		label_gender = new JLabel("New label");
		label_gender.setHorizontalAlignment(SwingConstants.CENTER);
		label_gender.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11.add(label_gender);
		
		JLabel label_5 = new JLabel("Birthday 生日 ");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.GRAY);
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11.add(label_5);
		
		label_birth = new JLabel("New label");
		label_birth.setHorizontalAlignment(SwingConstants.CENTER);
		label_birth.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11.add(label_birth);
		
		JLabel lblAge = new JLabel("Age  年龄");
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setForeground(Color.GRAY);
		lblAge.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11.add(lblAge);
		
		label_age = new JLabel("New label");
		label_age.setHorizontalAlignment(SwingConstants.CENTER);
		label_age.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11.add(label_age);
		
		JLabel label_9 = new JLabel("ID 学号 ");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(Color.GRAY);
		label_9.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11.add(label_9);
		
		label_ID = new JLabel("New label");
		label_ID.setHorizontalAlignment(SwingConstants.CENTER);
		label_ID.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11.add(label_ID);
		
		JLabel lblBalance = new JLabel("Balance 账户余额");
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalance.setForeground(Color.GRAY);
		lblBalance.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		//panel_11.add(lblBalance);
		
		label_balance = new JLabel("New label");
		label_balance.setHorizontalAlignment(SwingConstants.CENTER);
		label_balance.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		//panel_11.add(label_balance);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		profilepage.add(verticalGlue_2);
		
		JSeparator separator = new JSeparator();
		profilepage.add(separator);
		
		JPanel panel_3 = new JPanel();
		profilepage.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		panel_3.add(tabbedPane);
		
		JPanel study = new JPanel();
		tabbedPane.addTab("学院信息", null, study, null);
		study.setBackground(Color.WHITE);
		study.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Department 学院 ");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		study.add(lblNewLabel_3);
		
		label_depart = new JLabel("New label");
		label_depart.setHorizontalAlignment(SwingConstants.CENTER);
		label_depart.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		study.add(label_depart);
		
		JLabel lblNewLabel_4 = new JLabel("Grade 年级");
		lblNewLabel_4.setForeground(Color.GRAY);
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		study.add(lblNewLabel_4);
		
		label_grade = new JLabel("New label");
		label_grade.setHorizontalAlignment(SwingConstants.CENTER);
		label_grade.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		study.add(label_grade);
		
		JLabel lblNewLabel_9 = new JLabel("Class 班级");
		lblNewLabel_9.setForeground(Color.GRAY);
		lblNewLabel_9.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		study.add(lblNewLabel_9);
		
		label_class = new JLabel("New label");
		label_class.setHorizontalAlignment(SwingConstants.CENTER);
		label_class.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		study.add(label_class);
		
		JPanel connection = new JPanel();
		connection.setBackground(Color.WHITE);
		tabbedPane.addTab("联系方式", null, connection, null);
		connection.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEmail = new JLabel("E-mail 邮箱 ");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.GRAY);
		lblEmail.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		connection.add(lblEmail);
		
		label_mail = new JLabel("New label");
		label_mail.setHorizontalAlignment(SwingConstants.CENTER);
		label_mail.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		connection.add(label_mail);
		
		JLabel lblPhone = new JLabel("Phone 电话");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setForeground(Color.GRAY);
		lblPhone.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		connection.add(lblPhone);
		
		label_phone = new JLabel("New label");
		label_phone.setHorizontalAlignment(SwingConstants.CENTER);
		label_phone.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		connection.add(label_phone);
		
		JLabel lblQqacountQq = new JLabel("qq-acount QQ账户");
		lblQqacountQq.setHorizontalAlignment(SwingConstants.CENTER);
		lblQqacountQq.setForeground(Color.GRAY);
		lblQqacountQq.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		connection.add(lblQqacountQq);
		
		label_qq = new JLabel("New label");
		label_qq.setHorizontalAlignment(SwingConstants.CENTER);
		label_qq.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		connection.add(label_qq);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue);
		
		Component verticalGlue = Box.createVerticalGlue();
		profilepage.add(verticalGlue);
		
		JPanel securepage = new JPanel();
		securepage.setBackground(Color.WHITE);
		pages.add(securepage, "name_2");
		securepage.setLayout(new BoxLayout(securepage, BoxLayout.Y_AXIS));
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		securepage.add(verticalGlue_4);
		
		JPanel panel_5 = new JPanel();
		securepage.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel_5.add(horizontalGlue_2);
		
		JPanel panel_4 = new JPanel();
		panel_5.add(panel_4);
		panel_4.setLayout(new GridLayout(6,0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("原始密码");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel_4.add(lblNewLabel);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_15 = new JLabel("[");
		lblNewLabel_15.setForeground(SystemColor.activeCaption);
		lblNewLabel_15.setFont(new Font("微软雅黑", Font.BOLD, 30));
		panel_7.add(lblNewLabel_15);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 18));
		panel_7.add(passwordField);
		
		JLabel lblNewLabel_18 = new JLabel("]");
		lblNewLabel_18.setFont(new Font("微软雅黑", Font.BOLD, 30));
		lblNewLabel_18.setForeground(SystemColor.activeCaption);
		panel_7.add(lblNewLabel_18);
		
		
		JLabel lblNewLabel_13 = new JLabel("新密码");
		lblNewLabel_13.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_13.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel_4.add(lblNewLabel_13);
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_16 = new JLabel("[");
		lblNewLabel_16.setForeground(SystemColor.activeCaption);
		lblNewLabel_16.setFont(new Font("微软雅黑", Font.BOLD, 30));
		panel_8.add(lblNewLabel_16);
		
		passwordField_1 = new JPasswordField();
		panel_8.add(passwordField_1);
		passwordField_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_19 = new JLabel("]");
		lblNewLabel_19.setFont(new Font("微软雅黑", Font.BOLD, 30));
		lblNewLabel_19.setForeground(SystemColor.activeCaption);
		panel_8.add(lblNewLabel_19);

		
		JLabel lblNewLabel_14 = new JLabel("新密码确认");
		lblNewLabel_14.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_14.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_14.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel_4.add(lblNewLabel_14);
		
		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_17 = new JLabel("[");
		lblNewLabel_17.setForeground(SystemColor.activeCaption);
		lblNewLabel_17.setFont(new Font("微软雅黑", Font.BOLD, 30));
		panel_9.add(lblNewLabel_17);
		
		passwordField_2 = new JPasswordField();
		panel_9.add(passwordField_2);
		passwordField_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_20 = new JLabel("]");
		lblNewLabel_20.setFont(new Font("微软雅黑", Font.BOLD, 30));
		lblNewLabel_20.setForeground(SystemColor.activeCaption);
		panel_9.add(lblNewLabel_20);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		panel_5.add(horizontalGlue_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EmptyBorder(20, 0, 0, 0));
		securepage.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("修改密码");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(passwordField_1.getPassword()).equals(String.valueOf(passwordField_2.getPassword())))	  
					//从char[]转化为String才可以使用equals()函数	
				    PersonInfo.changePsWd(String.valueOf(passwordField_1.getPassword()), 
				    		String.valueOf(passwordField.getPassword()));
			}
		});
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setForeground(Color.WHITE);
		
		MaterialUIMovement.add (btnNewButton, MaterialColors.BLUE_100);
		
		panel_6.add(btnNewButton);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		securepage.add(verticalGlue_3);
			
		
		JPanel changepage = new JPanel();
		pages.add(changepage, "name_3");
		changepage.setBackground(Color.WHITE);
		changepage.setLayout(new BoxLayout(changepage, BoxLayout.Y_AXIS));
		
		Component verticalGlue_1_ch = Box.createVerticalGlue();
		verticalGlue_1_ch.setBackground(Color.WHITE);
		changepage.add(verticalGlue_1_ch);
		
		JPanel panel_1_ch = new JPanel();
		changepage.add(panel_1_ch);
		panel_1_ch.setLayout(new BoxLayout(panel_1_ch, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel_1_ch.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		Component glue_5 = Box.createGlue();
		panel.add(glue_5);
		
		Component glue_3 = Box.createGlue();
		glue_3.setForeground(Color.GRAY);
		panel.add(glue_3);
		
		Component glue_2 = Box.createGlue();
		glue_2.setForeground(Color.GRAY);
		panel.add(glue_2);
		
		textArea_photopath = new JTextArea();
		textArea_photopath.setTabSize(6);
		panel.add(textArea_photopath);
		
		JButton PhotoBtn = new JButton("选择照片");
		PhotoBtn.setBorder(new LineBorder(Color.BLACK));
		PhotoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FileChooser.showFileOpenDialog(panel, textArea_photopath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		PhotoBtn.setForeground(Color.DARK_GRAY);
		PhotoBtn.setBackground(Color.LIGHT_GRAY);
		PhotoBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(PhotoBtn);
		
		JButton SubmitBtn = new JButton("上传照片");
		SubmitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PersonInfo.changePhoto(textArea_photopath.getText());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		SubmitBtn.setForeground(Color.DARK_GRAY);
		SubmitBtn.setBorder(new LineBorder(Color.BLACK));
		SubmitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		SubmitBtn.setBackground(Color.LIGHT_GRAY);
		panel.add(SubmitBtn);
		
		Component glue = Box.createGlue();
		glue.setForeground(Color.GRAY);
		panel.add(glue);
		
		Component glue_4 = Box.createGlue();
		glue_4.setForeground(Color.GRAY);
		panel.add(glue_4);
		
		Component glue_1 = Box.createGlue();
		panel.add(glue_1);
		
		//label_photo_ch = new JLabel("");
		//panel_1_ch.add(label_photo_ch);
		//label_photo_ch.setHorizontalAlignment(SwingConstants.CENTER);
		//label_photo_ch.setIcon(new ImageIcon(ProfilePanel.class.getResource("/av.jpg")));
		
		JPanel panel_10_ch = new JPanel();
		panel_1_ch.add(panel_10_ch);
		panel_10_ch.setLayout(new BoxLayout(panel_10_ch, BoxLayout.X_AXIS));
		
		JPanel panel_11_ch = new JPanel();
		panel_11_ch.setBackground(Color.WHITE);
		panel_10_ch.add(panel_11_ch);
		panel_11_ch.setLayout(new GridLayout(6, 0, 0, 0));
		
		JLabel label_1_ch = new JLabel("Name 姓名 ");
		label_1_ch.setHorizontalAlignment(SwingConstants.CENTER);
		label_1_ch.setForeground(Color.GRAY);
		label_1_ch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11_ch.add(label_1_ch);
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11_ch.add(textField_name);
		textField_name.setColumns(10);
		
		JLabel label_3_ch = new JLabel("Gender 性别 ");
		label_3_ch.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_ch.setForeground(Color.GRAY);
		label_3_ch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11_ch.add(label_3_ch);
		
		textField_gender = new JTextField();
		textField_gender.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11_ch.add(textField_gender);
		textField_gender.setColumns(10);
		
		JLabel label_5_ch = new JLabel("Birthday 生日 ");
		label_5_ch.setHorizontalAlignment(SwingConstants.CENTER);
		label_5_ch.setForeground(Color.GRAY);
		label_5_ch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11_ch.add(label_5_ch);
		
		textField_birth = new JTextField();
		textField_birth.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11_ch.add(textField_birth);
		textField_birth.setColumns(10);
		
		JLabel lblAge_ch = new JLabel("Age  年龄");
		lblAge_ch.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge_ch.setForeground(Color.GRAY);
		lblAge_ch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11_ch.add(lblAge_ch);
		
		textField_age = new JTextField();
		textField_age.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11_ch.add(textField_age);
		textField_age.setColumns(10);
		
		JLabel label_9_ch = new JLabel("ID 学号 ");
		label_9_ch.setHorizontalAlignment(SwingConstants.CENTER);
		label_9_ch.setForeground(Color.GRAY);
		label_9_ch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11_ch.add(label_9_ch);
		
		label_ID_ch = new JLabel("New label");
		label_ID_ch.setForeground(Color.GRAY);
		label_ID_ch.setHorizontalAlignment(SwingConstants.CENTER);
		label_ID_ch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_11_ch.add(label_ID_ch);
		
		Component verticalGlue_2_ch = Box.createVerticalGlue();
		changepage.add(verticalGlue_2_ch);
		
		JSeparator separator_ch = new JSeparator();
		changepage.add(separator_ch);
		
		JPanel panel_3_ch = new JPanel();
		changepage.add(panel_3_ch);
		panel_3_ch.setLayout(new BoxLayout(panel_3_ch, BoxLayout.X_AXIS));
		
		Component horizontalGlue_1_ch = Box.createHorizontalGlue();
		panel_3_ch.add(horizontalGlue_1_ch);
		
		JTabbedPane tabbedPane_ch = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_ch.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		panel_3_ch.add(tabbedPane_ch);
		
		JPanel study_ch = new JPanel();
		tabbedPane_ch.addTab("学院信息", null, study_ch, null);
		study_ch.setBackground(Color.WHITE);
		study_ch.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_3_ch = new JLabel("Department 学院 ");
		lblNewLabel_3_ch.setForeground(Color.GRAY);
		lblNewLabel_3_ch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_3_ch.setHorizontalAlignment(SwingConstants.CENTER);
		study_ch.add(lblNewLabel_3_ch);
		
		textField_depart = new JTextField();
		textField_depart.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		study_ch.add(textField_depart);
		textField_depart.setColumns(10);
		
		JLabel lblNewLabel_4_ch = new JLabel("Grade 年级");
		lblNewLabel_4_ch.setForeground(Color.GRAY);
		lblNewLabel_4_ch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_4_ch.setHorizontalAlignment(SwingConstants.CENTER);
		study_ch.add(lblNewLabel_4_ch);
		
		textField_grade = new JTextField();
		textField_grade.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		study_ch.add(textField_grade);
		textField_grade.setColumns(10);
		
		JLabel lblNewLabel_9_ch = new JLabel("Class 班级");
		lblNewLabel_9_ch.setForeground(Color.GRAY);
		lblNewLabel_9_ch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_9_ch.setHorizontalAlignment(SwingConstants.CENTER);
		study_ch.add(lblNewLabel_9_ch);
		
		textField_class = new JTextField();
		textField_class.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		study_ch.add(textField_class);
		textField_class.setColumns(10);
		
		JPanel connection_ch = new JPanel();
		connection_ch.setBackground(Color.WHITE);
		tabbedPane_ch.addTab("联系方式", null, connection_ch, null);
		connection_ch.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEmail_ch = new JLabel("E-mail 邮箱 ");
		lblEmail_ch.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_ch.setForeground(Color.GRAY);
		lblEmail_ch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		connection_ch.add(lblEmail_ch);
		
		textField_mail = new JTextField();
		textField_mail.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		connection_ch.add(textField_mail);
		textField_mail.setColumns(10);
		
		JLabel lblPhone_ch = new JLabel("Phone 电话");
		lblPhone_ch.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone_ch.setForeground(Color.GRAY);
		lblPhone_ch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		connection_ch.add(lblPhone_ch);
		
		textField_phone = new JTextField();
		textField_phone.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		connection_ch.add(textField_phone);
		textField_phone.setColumns(10);
		
		JLabel lblQqacountQq_ch = new JLabel("qq-acount QQ账户");
		lblQqacountQq_ch.setHorizontalAlignment(SwingConstants.CENTER);
		lblQqacountQq_ch.setForeground(Color.GRAY);
		lblQqacountQq_ch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		connection_ch.add(lblQqacountQq_ch);
		
		textField_qq = new JTextField();
		textField_qq.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		connection_ch.add(textField_qq);
		textField_qq.setColumns(10);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		panel_3_ch.add(horizontalGlue_4);

		JButton saveChangeBtn = new JButton("保存");
		saveChangeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> setInfo = new ArrayList<String>();
				setInfo.add(textField_name.getText());
				setInfo.add(textField_gender.getText());
				setInfo.add(textField_birth.getText());
				setInfo.add(textField_age.getText());
				setInfo.add(textField_depart.getText());
				setInfo.add(textField_grade.getText());
				setInfo.add(textField_class.getText());
				setInfo.add(textField_mail.getText());
				setInfo.add(textField_phone.getText());
				setInfo.add(textField_qq.getText());
				PersonInfo.changePersonInfo(setInfo);
			}
		});
		saveChangeBtn.setBackground(SystemColor.activeCaption);
		saveChangeBtn.setForeground(Color.WHITE);
		saveChangeBtn.setEnabled(true);	
		panel_3_ch.add(saveChangeBtn);
		saveChangeBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));		
		
	    /*	
		JButton saveChangeBtn = new JButton("保存");
		saveChangeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> setInfo = new ArrayList<String>();
				setInfo.add(textField_name.getText());
				setInfo.add(textField_gender.getText());
				setInfo.add(textField_birth.getText());
				setInfo.add(textField_age.getText());
				setInfo.add(textField_depart.getText());
				setInfo.add(textField_grade.getText());
				setInfo.add(textField_class.getText());
				setInfo.add(textField_mail.getText());
				setInfo.add(textField_phone.getText());
				setInfo.add(textField_qq.getText());
				ChangeInfoController.changePersonInfo(setInfo);
			}
		});
		saveChangeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		saveChangeBtn.setForeground(Color.DARK_GRAY);
		saveChangeBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel_3_ch.add(saveChangeBtn);
		*/
		
		
		
				
		Component horizontalGlue_ch = Box.createHorizontalGlue();
		panel_3_ch.add(horizontalGlue_ch);
		
		Component verticalGlue_ch = Box.createVerticalGlue();
		changepage.add(verticalGlue_ch);	
		
		
		GroupifyBtnAndCard.groupBtnsAndCards(new JButton[]{basicBtn,secureBtn, changeBtn}, pages);
		
/*		JLabel avator = new JLabel("New label");
		avator.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		avator.setIcon(new ImageIcon("C:\\Users\\kinoud\\Desktop\\av.jpg"));
		//image.setImage(image.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
		GridBagConstraints gbc_avator = new GridBagConstraints();
		gbc_avator.insets = new Insets(5, 5, 5, 5);
		gbc_avator.gridheight = 9;
		gbc_avator.gridwidth = 9;
		gbc_avator.gridx = 0;
		gbc_avator.gridy = 0;
		add(avator, gbc_avator);
 */
	}
	


}
