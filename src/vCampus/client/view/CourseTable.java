package vCampus.client.view;
import javax.swing.JPanel;


import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;



public class CourseTable extends JPanel {
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JPanel am;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private JPanel pm;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;
	private JPanel ngt;
	private Component horizontalStrut_4;
	private Component horizontalStrut_5;
	private Component horizontalStrut_6;
	private JLabel label_12;

	private ArrayList<JPanel> coursesBox;
	
	/**
	 * remove all courses that are being displayed
	 */
	public void clearAll() {
		for(JPanel p:coursesBox) {
			remove(p);
		}
		coursesBox.clear();
	}
	
	/**
	 * 
	 * @param day 1~5
	 * @param t1 1~12
	 * @param t2 t1~12
	 * @param coursename
	 * @param classroom Jx-xxx
	 * @return
	 */
	public JPanel addCourse(String coursename,String classroom,int day,int t1,int t2,Color color) {
		JPanel p = new JPanel();
		p.setForeground(Color.GRAY);
		p.setBackground(new Color(255, 250, 250));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = t2-t1+1;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = day+1;
		gbc_panel.gridy = t1;
		add(p, gbc_panel);
		p.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel tag = new JLabel("<html>"+coursename+"<br>"+classroom+"</html>");
		tag.setHorizontalAlignment(SwingConstants.CENTER);
		p.add(tag);
		tag.setBackground(color);
		MaterialUIMovement.add(tag,MaterialColors.AMBER_300);
		coursesBox.add(p);
		return p;
		
	}
	
	
	/**
	 * Create the panel.
	 */
	public CourseTable() {
		coursesBox=new ArrayList<JPanel>();
		
		setForeground(Color.GRAY);
		setBackground(new Color(255, 250, 250));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblNewLabel_5 = new JLabel("\u8BFE\u7A0B\u8868");
		lblNewLabel_5.setForeground(Color.GRAY);
		lblNewLabel_5.setBackground(new Color(255, 250, 250));
		lblNewLabel_5.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 0;
		add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalStrut_6.setForeground(Color.GRAY);
		GridBagConstraints gbc_horizontalStrut_6 = new GridBagConstraints();
		gbc_horizontalStrut_6.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_6.gridx = 1;
		gbc_horizontalStrut_6.gridy = 0;
		add(horizontalStrut_6, gbc_horizontalStrut_6);
		 
		lblNewLabel = new JLabel(" \u661F\u671F\u4E00 ");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setBackground(new Color(255, 250, 250));
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		lblNewLabel_1 = new JLabel(" \u661F\u671F\u4E8C ");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setBackground(new Color(255, 250, 250));
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel(" \u661F\u671F\u4E09 ");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setBackground(new Color(255, 250, 250));
		lblNewLabel_2.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 0;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel(" \u661F\u671F\u56DB ");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setBackground(new Color(255, 250, 250));
		lblNewLabel_3.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 5;
		gbc_lblNewLabel_3.gridy = 0;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("\u661F\u671F\u4E94");
		lblNewLabel_4.setForeground(Color.GRAY);
		lblNewLabel_4.setBackground(new Color(255, 250, 250));
		lblNewLabel_4.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 6;
		gbc_lblNewLabel_4.gridy = 0;
		add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		am = new JPanel();
		am.setForeground(Color.GRAY);
		am.setBackground(new Color(255, 250, 250));
		GridBagConstraints gbc_am = new GridBagConstraints();
		gbc_am.gridheight = 5;
		gbc_am.insets = new Insets(0, 0, 5, 5);
		gbc_am.fill = GridBagConstraints.BOTH;
		gbc_am.gridx = 0;
		gbc_am.gridy = 1;
		add(am, gbc_am);
		am.setLayout(new BoxLayout(am, BoxLayout.X_AXIS));
		
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setForeground(Color.GRAY);
		horizontalStrut.setBackground(new Color(255, 250, 250));
		am.add(horizontalStrut);
		
		lblNewLabel_7 = new JLabel("\u4E0A\u5348");
		lblNewLabel_7.setForeground(Color.GRAY);
		lblNewLabel_7.setBackground(new Color(255, 250, 250));
		am.add(lblNewLabel_7);
		//MaterialUIMovement.add(lblNewLabel_7,MaterialColors.RED_100);
		lblNewLabel_7.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setForeground(Color.GRAY);
		horizontalStrut_1.setBackground(new Color(255, 250, 250));
		am.add(horizontalStrut_1);
		
		label = new JLabel("1");
		label.setForeground(Color.GRAY);
		label.setBackground(new Color(255, 250, 250));
		label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		add(label, gbc_label);
		
		label_1 = new JLabel("2"); 
		label_1.setForeground(Color.GRAY);
		label_1.setBackground(new Color(255, 250, 250));
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 2;
		add(label_1, gbc_label_1);
		
		label_2 = new JLabel("3");
		label_2.setForeground(Color.GRAY);
		label_2.setBackground(new Color(255, 250, 250));
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 3;
		add(label_2, gbc_label_2);
		
		label_3 = new JLabel("4");
		label_3.setForeground(Color.GRAY);
		label_3.setBackground(new Color(255, 250, 250));
		label_3.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 4;
		add(label_3, gbc_label_3);
		
		label_4 = new JLabel("5");
		label_4.setForeground(Color.GRAY);
		label_4.setBackground(new Color(255, 250, 250));
		label_4.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 1;
		gbc_label_4.gridy = 5;
		add(label_4, gbc_label_4);
		
		label_5 = new JLabel("6");
		label_5.setForeground(Color.GRAY);
		label_5.setBackground(new Color(255, 250, 250));
		label_5.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 1;
		gbc_label_5.gridy = 6;
		add(label_5, gbc_label_5);
		
		label_6 = new JLabel("7");
		label_6.setForeground(Color.GRAY);
		label_6.setBackground(new Color(255, 250, 250));
		label_6.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 1;
		gbc_label_6.gridy = 7;
		add(label_6, gbc_label_6);
		
		pm = new JPanel();
		pm.setForeground(Color.GRAY);
		pm.setBackground(new Color(255, 250, 250));
		GridBagConstraints gbc_pm = new GridBagConstraints();
		gbc_pm.gridheight = 5;
		gbc_pm.insets = new Insets(0, 0, 5, 5);
		gbc_pm.fill = GridBagConstraints.BOTH;
		gbc_pm.gridx = 0;
		gbc_pm.gridy = 6;
		add(pm, gbc_pm);
		pm.setLayout(new BoxLayout(pm, BoxLayout.X_AXIS));
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setForeground(Color.GRAY);
		horizontalStrut_2.setBackground(new Color(255, 250, 250));
		pm.add(horizontalStrut_2);
		
		lblNewLabel_8 = new JLabel("\u4E0B\u5348");
		lblNewLabel_8.setForeground(Color.GRAY);
		lblNewLabel_8.setBackground(new Color(255, 250, 250));
		pm.add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		
		horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setForeground(Color.GRAY);
		horizontalStrut_3.setBackground(new Color(255, 250, 250));
		pm.add(horizontalStrut_3);
		
		label_7 = new JLabel("8");
		label_7.setForeground(Color.GRAY);
		label_7.setBackground(new Color(255, 250, 250));
		label_7.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 1;
		gbc_label_7.gridy = 8;
		add(label_7, gbc_label_7);
		
		label_8 = new JLabel("9");
		label_8.setForeground(Color.GRAY);
		label_8.setBackground(new Color(255, 250, 250));
		label_8.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 1;
		gbc_label_8.gridy = 9;
		add(label_8, gbc_label_8);
		
		label_9 = new JLabel("10");
		label_9.setForeground(Color.GRAY);
		label_9.setBackground(new Color(255, 250, 250));
		label_9.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 1;
		gbc_label_9.gridy = 10;
		add(label_9, gbc_label_9);
		
		ngt = new JPanel();
		ngt.setForeground(Color.GRAY);
		ngt.setBackground(new Color(255, 250, 250));
		GridBagConstraints gbc_ngt = new GridBagConstraints();
		gbc_ngt.gridheight = 3;
		gbc_ngt.insets = new Insets(0, 0, 5, 5);
		gbc_ngt.fill = GridBagConstraints.BOTH;
		gbc_ngt.gridx = 0;
		gbc_ngt.gridy = 11;
		add(ngt, gbc_ngt);
		ngt.setLayout(new BoxLayout(ngt, BoxLayout.X_AXIS));
		
		horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalStrut_4.setForeground(Color.GRAY);
		horizontalStrut_4.setBackground(new Color(255, 250, 250));
		ngt.add(horizontalStrut_4);
		
		lblNewLabel_9 = new JLabel("\u665A\u4E0A");
		lblNewLabel_9.setForeground(Color.GRAY);
		lblNewLabel_9.setBackground(new Color(255, 250, 250));
		ngt.add(lblNewLabel_9);
		lblNewLabel_9.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		
		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalStrut_5.setForeground(Color.GRAY);
		horizontalStrut_5.setBackground(new Color(255, 250, 250));
		ngt.add(horizontalStrut_5);
		
		label_10 = new JLabel("11");
		label_10.setForeground(Color.GRAY);
		label_10.setBackground(new Color(255, 250, 250));
		label_10.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.gridx = 1;
		gbc_label_10.gridy = 11;
		add(label_10, gbc_label_10);
		
		label_11 = new JLabel("12");
		label_11.setForeground(Color.GRAY);
		label_11.setBackground(new Color(255, 250, 250));
		label_11.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.insets = new Insets(0, 0, 5, 5);
		gbc_label_11.gridx = 1;
		gbc_label_11.gridy = 12;
		add(label_11, gbc_label_11);
		
		label_12 = new JLabel("13");
		label_12.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_12.setForeground(Color.GRAY);
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.insets = new Insets(0, 0, 5, 5);
		gbc_label_12.gridx = 1;
		gbc_label_12.gridy = 13;
		add(label_12, gbc_label_12);
	}
}
















