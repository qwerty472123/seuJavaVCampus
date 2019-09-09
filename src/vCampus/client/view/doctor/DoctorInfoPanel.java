package vCampus.client.view.doctor;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.client.controller.Auth;
import vCampus.client.controller.PersonInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class DoctorInfoPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public DoctorInfoPanel() {
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
			}
		});
		basicBtn.setForeground(Color.GRAY);
		basicBtn.setBackground(new Color(255, 250, 240));
		basicBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		menu.add(basicBtn);
		
		JButton secureBtn = new JButton("工作时间");
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
		
		JPanel panel = new JPanel();
		panel_2.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("");
		panel_3.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(DoctorInfoPanel.class.getResource("/av.jpg")));
		
		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label = new JLabel("姓名");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		panel_1.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("性别");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);
		
		JLabel label_2 = new JLabel("年龄");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel_4.add(lblNewLabel_4);
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Auth.logout();
			}
			
		});
	}

}
