package vCampus.client.view.hospital;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vCampus.utility.Config;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DoctorIntroFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_7;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DoctorIntroFrame dialog = new DoctorIntroFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			Config.log(e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public DoctorIntroFrame() {
		setAlwaysOnTop(true);
		setTitle("医生简介");
		setBounds(100, 100, 450, 541);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					lblNewLabel_3 = new JLabel("");
					lblNewLabel_3.setIcon(new ImageIcon(DoctorIntroFrame.class.getResource("/av.jpg")));
					panel_1.add(lblNewLabel_3);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(0, 2, 0, 0));
				{
					JLabel lblNewLabel = new JLabel("医生姓名");
					lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
					panel_1.add(lblNewLabel);
				}
				{
					lblNewLabel_1 = new JLabel("New label");
					lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
					panel_1.add(lblNewLabel_1);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("年龄");
					lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
					panel_1.add(lblNewLabel_2);
				}
				{
					lblNewLabel_4 = new JLabel("New label");
					lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
					panel_1.add(lblNewLabel_4);
				}
				{
					JLabel lblNewLabel_6 = new JLabel("性别");
					lblNewLabel_6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
					panel_1.add(lblNewLabel_6);
				}
				{
					lblNewLabel_7 = new JLabel("New label");
					lblNewLabel_7.setFont(new Font("微软雅黑", Font.PLAIN, 16));
					panel_1.add(lblNewLabel_7);
				}
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				JLabel label = new JLabel("医生简介");
				label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
				label.setHorizontalAlignment(SwingConstants.CENTER);
				scrollPane.setColumnHeaderView(label);
			}
			{
				lblNewLabel_5 = new JLabel("New label");
				lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				scrollPane.setViewportView(lblNewLabel_5);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("关闭");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public void set_name(String name) {
		lblNewLabel_1.setText(name);
	}

	public void set_age(int age) {
		lblNewLabel_4.setText(age+"");
	}
	
	public void set_protrait(Icon protrait) {
		lblNewLabel_3.setIcon(protrait);
	}
	public void set_gender(boolean gender) {
		lblNewLabel_7.setText(gender?"女":"男");
	}
	public void set_intro(String intro) {
		lblNewLabel_5.setText(intro);
	}
}
