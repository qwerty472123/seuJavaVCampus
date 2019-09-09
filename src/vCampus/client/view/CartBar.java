package vCampus.client.view;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.SwingConstants;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.client.view.utility.MyStyle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CartBar extends JPanel {
	public JLabel textField;
	public JButton submitButton;
	public JButton foldupBtn;
	public JButton resetButton;

	/**
	 * Create the panel.
	 */
	public CartBar() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0, 1,1,1,1, 0.0,1, 0.0,0,0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		textField = new JLabel("");
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridwidth = 6;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		

		submitButton = new JButton("结算");
		submitButton.setForeground(MyStyle.RED);
		submitButton.setBackground(Color.white);
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		MaterialUIMovement.add(submitButton, MaterialColors.GRAY_100);		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 8;
		gbc_btnNewButton.gridy = 0;
		add(submitButton, gbc_btnNewButton);
		
		resetButton = new JButton("重置并刷新");
		resetButton.setForeground(MyStyle.DEEP_GRAY);
		resetButton.setBackground(Color.white);
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		MaterialUIMovement.add(resetButton, MaterialColors.GRAY_100);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 9;
		gbc_button.gridy = 0;
		add(resetButton, gbc_button);
		
		foldupBtn = new JButton("↑");
		foldupBtn.setForeground(Color.black);
		foldupBtn.setBackground(Color.white);
		foldupBtn.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		MaterialUIMovement.add(foldupBtn, MaterialColors.GRAY_100);
		GridBagConstraints gbc_foldupBtn = new GridBagConstraints();
		gbc_foldupBtn.gridx = 10;
		gbc_foldupBtn.gridy = 0;
		add(foldupBtn, gbc_foldupBtn);

	}

}
