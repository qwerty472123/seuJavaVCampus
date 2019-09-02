package vCampus.client.view;


import javax.swing.JPanel;


import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;

import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import javax.swing.JSeparator;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.client.controller.Auth;
import vCampus.client.view.utility.GroupifyBtnAndCard;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.Box;
import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

public class ProfilePanel extends JPanel {
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
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
		secureBtn.setForeground(Color.GRAY);
		secureBtn.setBackground(new Color(255, 250, 240));
		secureBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(secureBtn);
		
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
		profilepage.add(verticalGlue_1);
		
		JPanel panel_1 = new JPanel();
		profilepage.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel("");
		panel_1.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(ProfilePanel.class.getResource("/av.jpg")));
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		profilepage.add(verticalGlue_2);
		
		JSeparator separator = new JSeparator();
		profilepage.add(separator);
		
		JPanel panel_3 = new JPanel();
		profilepage.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue_1);
		
		JPanel panel = new JPanel();
		panel_3.add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(6, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("姓名 ");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_2 = new JLabel("性别 ");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_11 = new JLabel("生日 ");
		lblNewLabel_11.setForeground(Color.GRAY);
		lblNewLabel_11.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("New label");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(lblNewLabel_12);
		
		JLabel lblNewLabel_3 = new JLabel("Department 学院 ");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_4 = new JLabel("ID 学号 ");
		lblNewLabel_4.setForeground(Color.GRAY);
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("E-mail 邮箱 ");
		lblNewLabel_9.setForeground(Color.GRAY);
		lblNewLabel_9.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(lblNewLabel_10);
		
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
		//MaterialUIMovement.add (passwordField_2, MaterialColors.BLUE_400);
		//MaterialUIMovement.add (passwordField, MaterialColors.BLUE_400);
		//MaterialUIMovement.add (passwordField_1, MaterialColors.BLUE_400);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		panel_5.add(horizontalGlue_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EmptyBorder(20, 0, 0, 0));
		securepage.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("修改密码");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setForeground(Color.WHITE);
		
		MaterialUIMovement.add (btnNewButton, MaterialColors.BLUE_100);
		
		panel_6.add(btnNewButton);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		securepage.add(verticalGlue_3);
		
		GroupifyBtnAndCard.groupBtnsAndCards(new JButton[]{basicBtn,secureBtn}, pages);
		
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
