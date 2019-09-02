package vCampus.client.view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.client.controller.Auth;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JTextField accountTextField;
	private JLabel lblNewLabel_1;
	private JPasswordField passwordTextField;
	private Box horizontalBox_1;
	private JButton loginBtn;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private Component horizontalGlue;
	private Component horizontalGlue_1;

	/**
	 * util debug
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setTitle("vCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 478);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		lblNewLabel_3 = new JLabel("(/\u2267\u25BD\u2266)/");
		lblNewLabel_3.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 45));
		panel.add(lblNewLabel_3);
		
		lblNewLabel_2 = new JLabel("vCampus\u6821\u56ED\u7CFB\u7EDF");
		lblNewLabel_2.setForeground(new Color(153, 153, 204));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 45));
		panel.add(lblNewLabel_2);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		horizontalGlue = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(7, 0, 0, 0));
		
		lblNewLabel = new JLabel("账户");
		lblNewLabel.setForeground(new Color(51, 102, 204));
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_2.add(lblNewLabel);
		
		accountTextField = new JTextField();
		panel_2.add(accountTextField);
		accountTextField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setForeground(new Color(51, 102, 204));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_2.add(lblNewLabel_1);
		
		passwordTextField = new JPasswordField();
		passwordTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 13) {
					Auth.login(accountTextField.getText(), String.valueOf(passwordTextField.getPassword()));
				}
			}
		});
		panel_2.add(passwordTextField);
		
		horizontalBox_1 = Box.createHorizontalBox();
		panel_2.add(horizontalBox_1);
		
		loginBtn = new JButton("   \u767B  \u5F55   "); 
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBackground(new Color(51, 102, 204));
		loginBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel_2.add(loginBtn);
		
		horizontalGlue_1 = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue_1);
		
		loginBtn.setBackground(MaterialColors.BLUE_400);
		MaterialUIMovement.add(loginBtn, MaterialColors.BLUE_200);
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Auth.login(accountTextField.getText(), String.valueOf(passwordTextField.getPassword()));
			}
		});
		
	}

}
