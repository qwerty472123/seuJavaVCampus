package vCampus.client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewsBar extends JPanel {
	public JButton submitButton;
	public JButton foldupBtn;
	private boolean switchConstant;
	//public JButton forwardBtn;

	/**
	 * Create the panel.
	 */
	public NewsBar() {
		
		setBackground(new Color(223, 223, 255));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		submitButton = new JButton("刷新");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.insets = new Insets(0, 0, 0, 5);
		gbc_1.gridx = 0;
		gbc_1.gridy = 0;
		gbc_1.gridwidth = 3;
		add(submitButton, gbc_1);
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		submitButton.setHorizontalAlignment(SwingConstants.RIGHT);
		
		switchConstant = true;
		foldupBtn = new JButton("→");
		GridBagConstraints gbc_2 = new GridBagConstraints();
		gbc_2.gridx = 4;
		gbc_2.gridy = 0;
		gbc_2.gridwidth = 3;
		add(foldupBtn, gbc_2);
		foldupBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));

		/*
		forwardBtn = new JButton("→");
		GridBagConstraints gbc_3 = new GridBagConstraints();
		gbc_3.gridx = 8;
		gbc_3.gridy = 0;
		gbc_3.gridwidth = 3;
		add(forwardBtn, gbc_3);
		forwardBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		*/

	}
	
	public void switchNarrow() {
		if (switchConstant) foldupBtn.setText("←");
		else foldupBtn.setText("→");
	}
	
}
