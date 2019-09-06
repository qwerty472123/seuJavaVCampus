package vCampus.client.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewsBar extends JPanel {
	public JTextField textField;
	public JButton submitButton;
	public JButton foldupBtn;
	private JLabel lblNewLabel_l;
	private JLabel lblNewLabel_r;

	/**
	 * Create the panel.
	 */
	public NewsBar() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0, 1,1,1,1, 0.0,1, 0.0,0,0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblNewLabel_l = new JLabel("[");
		lblNewLabel_l.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblNewLabel_l.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel_l, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridwidth = 6;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		lblNewLabel_r = new JLabel("]");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 7;
		gbc_lblNewLabel_1.gridy = 0;
		add(lblNewLabel_r, gbc_lblNewLabel_1);
		lblNewLabel_r.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		submitButton = new JButton("筛选并刷新");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 8;
		gbc_btnNewButton.gridy = 0;
		gbc_btnNewButton.gridwidth = 2;
		add(submitButton, gbc_btnNewButton);
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		submitButton.setHorizontalAlignment(SwingConstants.RIGHT);
		
		foldupBtn = new JButton("←");
		GridBagConstraints gbc_foldupBtn = new GridBagConstraints();
		gbc_foldupBtn.gridx = 10;
		gbc_foldupBtn.gridy = 0;
		add(foldupBtn, gbc_foldupBtn);
		foldupBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));

	}
}
