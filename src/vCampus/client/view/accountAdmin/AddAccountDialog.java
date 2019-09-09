package vCampus.client.view.accountAdmin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddAccountDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;

	private JButton okButton;
	
	private String[] roles = {"student", "teacher", "doctor"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddAccountDialog dialog = new AddAccountDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddAccountDialog() {
		setBounds(100, 100, 348, 227);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			contentPanel.add(horizontalStrut);
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			contentPanel.add(horizontalStrut);
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			contentPanel.add(horizontalStrut);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JPanel panel_2 = new JPanel();
				panel.add(panel_2);
				panel_2.setLayout(new GridLayout(7, 2, 0, 0));
				{
					Component verticalGlue = Box.createVerticalGlue();
					panel_2.add(verticalGlue);
				}
				{
					JPanel panel_1 = new JPanel();
					panel_2.add(panel_1);
					panel_1.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel lblNewLabel = new JLabel("编号");
						lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
						panel_1.add(lblNewLabel);
					}
					{
						textField = new JTextField();
						textField.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								textField.setText("");
							}
						});
						textField.setForeground(Color.LIGHT_GRAY);
						textField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
						textField.setText("请输入编号");
						panel_1.add(textField);
						textField.setColumns(10);
					}
				}
				{
					Component verticalGlue = Box.createVerticalGlue();
					panel_2.add(verticalGlue);
				}
				{
					JPanel panel_1 = new JPanel();
					panel_2.add(panel_1);
					panel_1.setLayout(new GridLayout(1, 0, 0, 0));
					{
						JLabel lblNewLabel_1 = new JLabel("用户名");
						lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
						panel_1.add(lblNewLabel_1);
					}
					{
						textField_1 = new JTextField();
						textField_1.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								textField_1.setText("");
							}
						});
						textField_1.setForeground(Color.LIGHT_GRAY);
						textField_1.setText("请输入用户名");
						textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
						panel_1.add(textField_1);
						textField_1.setColumns(10);
					}
				}
				{
					Component verticalGlue = Box.createVerticalGlue();
					panel_2.add(verticalGlue);
				}
				{
					JPanel panel_1 = new JPanel();
					panel_2.add(panel_1);
					panel_1.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel label = new JLabel("权限");
						label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
						panel_1.add(label);
					}
					{
						comboBox = new JComboBox();
						comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
						comboBox.setModel(new DefaultComboBoxModel(roles));
						panel_1.add(comboBox);
					}
				}
				{
					Component verticalGlue = Box.createVerticalGlue();
					panel_2.add(verticalGlue);
				}
			}
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			contentPanel.add(horizontalStrut);
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			contentPanel.add(horizontalStrut);
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			contentPanel.add(horizontalStrut);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public int getNewNumber() {
		return Integer.parseInt(textField.getText());
	}
	
	public String getNewName() {
		return textField_1.getText();
	}
	
	public String getNewAuthority() {
		return roles[comboBox.getSelectedIndex()];
	}

	public JButton getOkButton() {
		return okButton;
	}
}
