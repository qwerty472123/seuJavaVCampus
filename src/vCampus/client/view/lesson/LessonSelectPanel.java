package vCampus.client.view.lesson;

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
import vCampus.bean.LessonBean;
import vCampus.bean.LessonTime;
import vCampus.client.controller.Lesson;
import vCampus.client.view.utility.MyStyle;
import vCampus.client.view.utility.MyTable;
import vCampus.client.view.utility.Refreshable;

public class LessonSelectPanel extends JPanel implements Refreshable{
	private static final long serialVersionUID = 1L;
	private JButton btnSelect;
	private JButton btnRefresh;
	private MyTable table;
	private JPanel optionPanel;
	private JPanel details;
	ArrayList<LessonBean> lessons=new ArrayList<LessonBean>();
	private ArrayList<String> nameList;
	
	public ArrayList<String> getNameList() {
		return nameList;
	}


	public LessonSelectPanel() {
		nameList = new ArrayList<String>();
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
				int idx=table.getSelectedRow();
				if(idx<0) {
					System.out.println("没选中行!");
					return;
				}
				
				LessonBean c=lessons.get(idx);
				
				String status=c.getStatus();
				int ID=c.getID();
				if(status.equals("冲突")||status.equals("已满")) {
					JOptionPane.showMessageDialog(LessonSelectPanel.this, "所选课程"+status+"!","",JOptionPane.WARNING_MESSAGE);
				}else if(status.equals("可选")){
					//LessonController.selectClass(ID);
					Lesson.selectLesson(LessonSelectPanel.this, ID);
				}else if(status.equals("已选")) {
					Lesson.cancelLesson(LessonSelectPanel.this, ID);
					//LessonController.cancelClass(ID);
				}
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
				//LessonController.refreshCourseSelectionTable();
				Lesson.queryAllLessons(LessonSelectPanel.this);
			}
		});
		btnRefresh.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		btnRefresh.setPreferredSize(rect100);
		btnRefresh.setMaximumSize(rect100);
		btnRefresh.setMinimumSize(rect100);
		MaterialUIMovement.add(btnRefresh, MaterialColors.BLUE_200);
		table = new MyTable(new String[] {
				"课程ID", "名称", "任课教师", "上课地点", "上课时间", "教学班人数", "已选择人数", "状态"
			});
		
		table.setColumnWidth(7, 40);
		table.setColumnWidth(6, 20);
		table.setColumnWidth(5, 20);
		table.setColumnWidth(4, 200);
		table.setColumnWidth(3, 100);
		table.setColumnWidth(2, 100);
		table.setColumnWidth(1, 100);
		table.setColumnWidth(0, 50);
		
		table.addWordHighlight("冲突", MyStyle.WARNING);
		table.addWordHighlight("可选", MaterialColors.AMBER_100);
		table.addWordHighlight("已选", MyStyle.AVAILABLE);
		table.addWordHighlight("已满", MyStyle.WARNING);
		table.setRowHighlight(MaterialColors.PINK_50);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				mousePressed(e);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				int cur=table.getSelectedRow();
				String name=(String)table.getValueAt(cur, 1),
						teacher=(String)table.getValueAt(cur, 2),
						classroom=(String)table.getValueAt(cur, 3),
						timetable=(String)table.getValueAt(cur, 4),
						status=(String)table.getValueAt(cur, 7);
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

		JScrollPane stbContainer = new JScrollPane(table);
		stbContainer.getViewport().setBackground(MaterialColors.WHITE);
		add(stbContainer, BorderLayout.CENTER);
	}


	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		Lesson.queryAllLessons(LessonSelectPanel.this);
	}
	
	
	private String toDescription(int s,int t,ArrayList<LessonTime> arr) {
		String res="";
		res="第"+s+"~"+t+"周 ";
		
		for(LessonTime c:arr) {
			res+="周"+c.getDay();
			res+="("+c.getStart()+"~"+c.getEnd()+"节) ";
		}
		
		return res;
	}

	public void setLessonTable(ArrayList<LessonBean> lessonList, ArrayList<String> nameList) {
		// TODO Auto-generated method stub
		table.removeAllRows();
		lessons.clear();
		this.nameList = nameList;
		//"课程ID", "名称", "任课教师", "上课地点", "上课时间", "教学班人数", "已选择人数", "状态"
		int i = 0;
		for(LessonBean b:lessonList) {
			lessons.add(b);
			table.addRow(new Object[] {
					b.getID(),
					b.getLessonName(),
					nameList.get(i),
					b.getLocation(),
					toDescription(b.getStartWeek(),b.getEndWeek(),b.getTimeTable()),
					b.getMaxNum(),
					b.getCurNum(),
					b.getStatus()
			});
			i++;
		}
		table.revalidate();
		table.repaint();
	}

}
