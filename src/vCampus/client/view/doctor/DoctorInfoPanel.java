package vCampus.client.view.doctor;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.client.controller.Auth;
import vCampus.client.controller.DoctorInfo;
import vCampus.client.controller.PersonInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.Box;

public class DoctorInfoPanel extends JPanel {

	JLabel nameLbl;
	JLabel genderLbl;
	JLabel ageLbl;
	JLabel iconLbl;
	JLabel introLbl;
	
	/**
	 * Create the panel.
	 */
	public DoctorInfoPanel() {
		DoctorInfo.getDoctorInfo();
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
		basicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoctorInfo.getDoctorInfo();
			}
		});
		basicBtn.setForeground(Color.GRAY);
		basicBtn.setBackground(new Color(255, 250, 240));
		basicBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(basicBtn);
		
		JButton logoutBtn = new JButton("账号登出");
		logoutBtn.setForeground(Color.GRAY);
		logoutBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		logoutBtn.setBackground(new Color(255, 250, 240));
		menu.add(logoutBtn);
		
		MaterialUIMovement.add(logoutBtn, MaterialColors.RED_100);
		
		JPanel panel = new JPanel();
		panel_2.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		Component glue = Box.createGlue();
		panel_5.add(glue);
		
		JPanel panel_3 = new JPanel();
		panel_5.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		iconLbl = new JLabel("");
		panel_3.add(iconLbl);
		iconLbl.setIcon(new ImageIcon("./photo/defaultphoto.jpg"));
		
		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label = new JLabel("姓名");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		panel_1.add(label);
		
		nameLbl = new JLabel("New label");
		nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nameLbl.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		panel_1.add(nameLbl);
		
		JLabel label_1 = new JLabel("性别");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_1);
		
		genderLbl = new JLabel("New label");
		genderLbl.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		genderLbl.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(genderLbl);
		
		JLabel label_2 = new JLabel("年龄");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_2);
		
		ageLbl = new JLabel("New label");
		ageLbl.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		ageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ageLbl);
		
		Component glue_1 = Box.createGlue();
		panel_5.add(glue_1);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("简介");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		
		introLbl = new JLabel("New label");
		panel_6.add(introLbl);
		introLbl.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Auth.logout();
			}
			
		});
	}

	public void setInfoTxt(String name, boolean gender, int age, String introtxt, int index) {
		nameLbl.setText(name);
		genderLbl.setText(gender?"女":"男");
		ageLbl.setText(age+"");
		introLbl.setText(introtxt);
		String url = "./photo/"+index+".jpg";
		if(!new File(url).canRead())
			url = "./photo/defaultphoto.jpg";
		iconLbl.setIcon(new ImageIcon(url));
	}
}
