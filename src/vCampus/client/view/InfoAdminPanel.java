package vCampus.client.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import vCampus.bean.StudentBean;
import vCampus.client.controller.AccountAdmin;
import vCampus.client.view.utility.MyStyle;
import vCampus.client.view.utility.MyTable;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.Component;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;

public class InfoAdminPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MyTable table;
    private JScrollPane scrollPane;
    private String[] photoList;
    private JComboBox comboBox_1;
    private JPanel panel_1;
    private JLabel label_photo;
    private JRadioButton btn_choose; 
	public String[] getPhotoList() {
		return photoList;
	}

	public void setPhotoList(String[] photoList) {
		this.photoList = photoList;
	}

	/**
	 * Create the panel.
	 */
	public InfoAdminPanel() {
		//photoList = null;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("待审核信息");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);
		
		scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new MyTable(new String[] {"学号","姓名","性别","生日","年级","班级","学院","电邮","电话","QQ"});
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JButton btn_deny = new JButton("否定修改");
		btn_deny.setBackground(Color.white);
		btn_deny.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		btn_deny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1){
					JOptionPane.showMessageDialog(null, "未选中行！");
				}else{
					int stuId = Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 0));
					AccountAdmin.StuInfoUpdate("修改失败", stuId);
				}			
			}
		});
		panel.add(btn_deny);
		
		JButton btn_update = new JButton("同意修改");
		btn_update.setBackground(Color.white);
		btn_update.setForeground(MyStyle.RED);
		btn_update.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() == -1){
					JOptionPane.showMessageDialog(null, "未选中行！");
				}else{
					int stuId = Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 0));
					AccountAdmin.StuInfoUpdate("修改成功", stuId);
				}
			}
		});
		panel.add(btn_update);
		
		JButton btn_refresh = new JButton("刷新");
		btn_refresh.setBackground(Color.white);
		btn_refresh.setForeground(MyStyle.LIGHT_GRAY);
		btn_refresh.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountAdmin.RefreshStuInfoUpdate();
			}
		});
		panel.add(btn_refresh);
		
		panel_1 = new JPanel();
		add(panel_1);
		
		label_photo = new JLabel();
		//ImageIcon image = new ImageIcon(JPanel.class.getResource("/av.jpg"));
		//image.setImage(image.getImage().getScaledInstance(50, 70,Image.SCALE_DEFAULT ));
		//panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//label_photo.setIcon(image);
		//panel_1.add(label_photo);
		
		JLabel lblNewLabel = new JLabel("上传照片：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		panel_1.add(lblNewLabel);
		
		comboBox_1 = new JComboBox();
		//comboBox_1.setBounds(new Rectangle(20,40));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[]{"暂无请刷新"}));
		panel_1.add(comboBox_1);
		
		JButton btnNewButton = new JButton("查看");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                 AccountAdmin.getPhoto((String)comboBox_1.getSelectedItem());
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		panel_1.add(btnNewButton);
		
		btn_choose = new JRadioButton("是否更换");
		btn_choose.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		panel_1.add(btn_choose);
		
		JButton btn_change = new JButton("执行");
		btn_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					AccountAdmin.changePhoto(btn_choose.isSelected(), (String)comboBox_1.getSelectedItem());							
				    AccountAdmin.deltePhoto((String)comboBox_1.getSelectedItem());
			}
		});
		btn_change.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		panel_1.add(btn_change);
	}

	public void setUpdateList(ArrayList<ArrayList<String>> list) {
		table.removeAllRows();
		for (ArrayList<String> s : list) {
			table.addRow(new Object[] {s.get(0), s.get(1), s.get(2),s.get(3),s.get(4),s.get(5), s.get(6),s.get(7),s.get(8),s.get(9)});
		}
		this.remove(scrollPane);
		scrollPane.setViewportView(table);
		this.add(scrollPane);
	}
	
   public void refreshComboBox(){
	   panel_1.remove(comboBox_1);
	   if(photoList == null)comboBox_1.setModel(new DefaultComboBoxModel(new String[]{"暂无请刷新"}));
	   else comboBox_1.setModel(new DefaultComboBoxModel(photoList));
	   panel_1.add(comboBox_1);
   }
		
   public void setPhoto(ImageIcon a){
	   panel_1.remove(label_photo);
	   label_photo.setIcon(a);
	   panel_1.add(label_photo);	   
   }

}
