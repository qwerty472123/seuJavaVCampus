package vCampus.client.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.client.view.utility.MyTable;

public class ClassSelectionPanel extends JPanel{
	private JButton btnSelect;
	private JButton btnRefresh;
	private MyTable selectTable;
	private JPanel optionPanel;
	private JPanel details;
	
	public void setClassSelectionTable(ArrayList<ArrayList<String>> data) {
		//1st dimension: lessons
		//2nd dimension: lesson details		
		for(int i=0;i<data.size();i++){
			ArrayList<String> lesson=data.get(i);
			//we suppose each column means
			//0: lesson id
			//1: lesson name
			//2: teacher name
			//3: classroom
			//4: lesson time
			//5: max selected
			//6: now selected
			//7: has a collision
			//8: chosen already
			for(int j=0;j<=6;j++) {
				selectTable.setValueAt(lesson.get(j), i, j);
				//lesson[j] is a string
			}
			
			String status="可选";
			if(lesson.get(6)==lesson.get(5))status="已满";
			if(lesson.get(7)=="yes")status="冲突";
			if(lesson.get(8)=="yes")status="已选";
			selectTable.setValueAt(status, i, 7);
		}
		
		selectTable.revalidate();
		selectTable.repaint();

	}
	
	
	public ClassSelectionPanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		optionPanel = new JPanel();
		optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
		add(optionPanel, BorderLayout.SOUTH);
		JSeparator separator = new JSeparator();
		optionPanel.add(separator);
		details = new JPanel();
		optionPanel.add(details);
		GridBagLayout gbl_details = new GridBagLayout();
		gbl_details.columnWidths = new int[]{0, 0, 0, 0};
		gbl_details.rowHeights = new int[]{0, 0, 0};
		gbl_details.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_details.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		details.setLayout(gbl_details);
		Dimension rect100=new Dimension(100,100);
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 2;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		details.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JLabel lblDescription = new JLabel("课程描述");
		panel_3.add(lblDescription);
		lblDescription.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut);
		btnSelect = new JButton("选课");
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSelect.insets = new Insets(0, 0, 0, 0);
		gbc_btnSelect.gridx = 1;
		gbc_btnSelect.gridy = 1;
		details.add(btnSelect, gbc_btnSelect);
		btnSelect.setForeground(new Color(255, 69, 0));
		btnSelect.setPreferredSize(new Dimension(200,50));
		btnSelect.setBackground(new Color(255, 255, 255));
		btnSelect.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		btnSelect.setPreferredSize(rect100);
		btnSelect.setMaximumSize(rect100);
		btnSelect.setMinimumSize(rect100);
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int cur=selectTable.getSelectedRow();
				System.out.println("From ClassSelectionPanel: request select class");
			}
			
		});
		
		MaterialUIMovement.add(btnSelect, MaterialColors.GRAY_100);
		btnRefresh = new JButton("刷新");
		GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
		gbc_btnRefresh.gridx = 2;
		gbc_btnRefresh.gridy = 1;
		details.add(btnRefresh, gbc_btnRefresh);
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setBackground(new Color(176, 196, 222));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("From ClassSelectionPanel: request refresh page");
			}
		});
		btnRefresh.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		btnRefresh.setPreferredSize(rect100);
		btnRefresh.setMaximumSize(rect100);
		btnRefresh.setMinimumSize(rect100);
		MaterialUIMovement.add(btnRefresh, MaterialColors.BLUE_200);
		selectTable = new MyTable(new String[] {
				"课程ID", "名称", "任课教师", "上课地点", "上课时间", "教学班人数", "已选择人数", "状态"
			});
		class MyStringRenderer extends JLabel implements TableCellRenderer{
			public MyStringRenderer() {
				setOpaque(true);
			}
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object str, boolean isSelected,
					boolean hasFocus, int row, int column) {

				//setBackground(MaterialColors.WHITE);
				
				Color bg=null;
				
				if(isSelected)bg=MaterialColors.PINK_50;
				
				if(((String)str).equals("冲突"))bg=(isSelected?MaterialColors.RED_200:MaterialColors.RED_100);
				else if(((String)str).equals("已满"))bg=(isSelected?MaterialColors.RED_200:MaterialColors.RED_100);
				else if(((String)str).equals("可选"))bg=(isSelected?MaterialColors.AMBER_200:MaterialColors.AMBER_100);
				else if(((String)str).equals("已选"))bg=(isSelected?MaterialColors.GREEN_200:MaterialColors.GREEN_100);
				setBackground(bg);
				
				setText((String)str);
				this.setHorizontalAlignment(SwingConstants.CENTER);
				
				// TODO Auto-generated method stub
				return this;
			}
			
		};
		selectTable.setDefaultRenderer(String.class, new MyStringRenderer());

		selectTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				mousePressed(e);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				int cur=selectTable.getSelectedRow();
				String name=(String)selectTable.getValueAt(cur, 1),
						teacher=(String)selectTable.getValueAt(cur, 2),
						classroom=(String)selectTable.getValueAt(cur, 3),
						timetable=(String)selectTable.getValueAt(cur, 4),
						status=(String)selectTable.getValueAt(cur, 7);
				lblDescription.setText("<html><h2><span>" + 
						name + 
						"</span></h2><p><strong><span>任课教师</span></strong><br><span>"
						+ teacher + 
						"</span></p><p><strong><span>上课时间</span></strong><br><span>"
						+ timetable + 
						"</span></p><p><strong><span>上课地点</span></strong><br><span>"
						+ classroom + 
						"</span></p><p><strong><span>选课状态</span></strong><br><span>"
						+ status + 
						"</span></p></html>");
				if(status.equals("已选")) {
					btnSelect.setForeground(MaterialColors.AMBER_400);
					btnSelect.setText("退选");
				}else if(status.equals("冲突")||status.equals("已满")) {
					btnSelect.setForeground(MaterialColors.GRAY_400);
					btnSelect.setText("选课");
				}else {
					btnSelect.setForeground(MaterialColors.RED_500);
					btnSelect.setText("选课");
				}
			}
		});

		JScrollPane stbContainer = new JScrollPane(selectTable);
		stbContainer.getViewport().setBackground(MaterialColors.WHITE);
		add(stbContainer, BorderLayout.CENTER);
	}

}
