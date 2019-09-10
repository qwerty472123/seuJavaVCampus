package vCampus.client.view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.client.controller.Auth;
import vCampus.utility.Config;

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
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.alibaba.fastjson.JSONObject;

import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPasswordField passwordTextField;
	private JButton loginBtn;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private Component horizontalGlue;
	private Component horizontalGlue_1;
	private JComboBox<String> account;
	private JPanel panel_3;
	private JCheckBox chkRemember;
	private Component horizontalGlue_2;
	private JCheckBox chkAuto;
	private Box horizontalBox;
	private JPanel panel_4;
	private JLabel lblNewLabel_4;
	private boolean inProcess = false;
	private String encPwd = "";

	public boolean isInProcess() {
		return inProcess;
	}



	public void setInProcess(boolean inProcess) {
		loginBtn.setEnabled(!inProcess);
		this.inProcess = inProcess;
	}



	/**
	 * util debug
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
					frame.addAccount("test");
					frame.addAccount("apple");
					frame.addAccount("hi");
				} catch (Exception e) {
					Config.log(e);
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
		setBounds(100, 100, 425, 499);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{186, 221, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
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
		
		panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		contentPane.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_4.add(panel_1);
		panel_1.setBackground(new Color(255, 255, 255));
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
		
		account = new JComboBox<String>();
		account.setEditable(true);
		panel_2.add(account);
		
		lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setForeground(new Color(51, 102, 204));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_2.add(lblNewLabel_1);
		
		passwordTextField = new JPasswordField();
		passwordTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginProcess();
				}
			}
		});
		panel_2.add(passwordTextField);
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		chkRemember = new JCheckBox("记住密码");
		LoginFrame that = this;
		chkRemember.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(!that.getRemeber()) {
					that.setAuto(false);
					that.cleanPwd();
				}
			}
		});
		chkRemember.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		chkRemember.setBackground(new Color(255, 255, 255));
		chkRemember.setForeground(new Color(105, 105, 105));
		panel_3.add(chkRemember);
		
		horizontalGlue_2 = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue_2);
		
		chkAuto = new JCheckBox("自动登录");
		chkAuto.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		chkAuto.setBackground(new Color(255, 255, 255));
		chkAuto.setForeground(new Color(105, 105, 105));
		chkAuto.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(that.getAuto()) {
					that.setRemeber(true);
				}
			}
		});
		panel_3.add(chkAuto);
		
		horizontalBox = Box.createHorizontalBox();
		panel_2.add(horizontalBox);
		
		loginBtn = new JButton("   \u767B  \u5F55   "); 
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBackground(new Color(51, 102, 204));
		loginBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel_2.add(loginBtn);
		
		horizontalGlue_1 = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue_1);
		
		loginBtn.setBackground(MaterialColors.BLUE_400);
		MaterialUIMovement.add(loginBtn, MaterialColors.BLUE_200);
		
		lblNewLabel_4 = new JLabel("Beta 9.10");
		lblNewLabel_4.setForeground(new Color(176, 196, 222));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		panel_4.add(lblNewLabel_4, BorderLayout.SOUTH);
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginProcess();
			}
		});
		
		JSONObject jsonObject = Config.get().getJSONObject("login");
		boolean hasAccount = false;
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            addAccount(entry.getKey());
            hasAccount = true;
        }
        if (Config.get().getString("preferLogin").equals("")) {
        	if (hasAccount) {
        		selectAccount(0);
        		updAccount(account.getItemAt(0));
        	}
        } else {
        	selectAccount(Config.get().getString("preferLogin"));
        	updAccount(Config.get().getString("preferLogin"));
        }
        account.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				updAccount((String)e.getItem());
			}
		});
	}
	
	public void cleanPwd() {
		String pwd = String.valueOf(passwordTextField.getPassword());
		if (pwd.equals("@ENCRYPTED_PWD@")) {
			passwordTextField.setText("");
		}
	}
	
	public void updAccount(String name) {
		setAuto(Config.get().getString("preferLogin").equals(name) && !name.equals(""));
		if (Config.get().getJSONObject("login").containsKey(name)) {
			String pwd = Config.get().getJSONObject("login").getString(name);
			setRemeber(!pwd.equals(""));
			if(!pwd.equals("")) {
				passwordTextField.setText("@ENCRYPTED_PWD@");
				encPwd = pwd;
			} else passwordTextField.setText("");
		} else passwordTextField.setText("");
	}
	
	
	public void addAccount(String name) {
		account.addItem(name);
	}
	
	public void removeAccount(String name) {
		account.removeItem(name);
	}
	public void removeAccount(int idx) {
		account.removeItemAt(idx);
	}
	public void selectAccount(String name) {
		account.setSelectedItem(name);
	}
	
	public void selectAccount(int idx) {
		account.setSelectedIndex(idx);
	}
	
	public String getAccount() {
		return (String)account.getSelectedItem();
	}
	
	public boolean getRemeber() {
		return chkRemember.isSelected();
	}
	
	public void setRemeber(boolean sel) {
		chkRemember.setSelected(sel);
	}
	
	public boolean getAuto() {
		return chkAuto.isSelected();
	}
	
	public void setAuto(boolean sel) {
		chkAuto.setSelected(sel);
	}
	
	public void loginProcess() {
		if(isInProcess()) return;
		setInProcess(true);
		String pwd = String.valueOf(passwordTextField.getPassword());
		if (pwd.equals("@ENCRYPTED_PWD@")) {
			Auth.login(getAccount(), encPwd, getAuto(), getRemeber(), true);
		} else {
			Auth.login(getAccount(), pwd, getAuto(), getRemeber(), false);
		}
	}
	

}
