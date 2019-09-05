package vCampus.client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.util.HashMap;
import java.util.Map;
import vCampus.client.view.utility.*;

public class TeacherGradeTable {
	private static JTable table;
	static ButtonEditor ButtonCloumn;
	//Map<JButton, Integer> data = new HashMap<JButton, Integer>();
	
	static public JScrollPane addTeacherGradeTable(Object[][] object){
		String[] columns = {"课程编号", "课程名称", "课程地点", "成绩登记"};
		table = new JTable();	
		table.setModel(new DefaultTableModel(object,columns));
		     
		table.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		table.setForeground(Color.GRAY);
		table.setRowHeight(25);
		
		//单独对最后一列修改字体
		/*
		TableColumn column = table.getColumnModel().getColumn(3);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(){
			private static final long serialVersionUID = 1L;
			Font font = new Font("微软雅黑", Font.BOLD, 22);

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                setFont(font);
                return this;
            }        	
        };
        column.setCellRenderer(tcr);
        */
        
		table.getColumn("成绩登记").setCellRenderer(new ButtonRenderer());
		ButtonCloumn = new ButtonEditor(new JCheckBox());
	    table.getColumn("成绩登记").setCellEditor(new ButtonEditor(new JCheckBox()));
				
        JTableHeader head = table.getTableHeader(); 
        head.setPreferredSize(new Dimension(head.getWidth(), 30));
        head.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        head.setForeground(Color.GRAY);
            
		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
        
		
	}
	
	
	public static int getCourseId(int row){
		return (Integer)table.getValueAt(row, 0);
	}
    public static JButton getButton(int row){
    	return (JButton) ButtonCloumn.getTableCellEditorComponent(table, 0, false, row, 3);
    }
	
	

	public static JTable getTable() {
		return table;
	}


	public static void setTable(JTable table) {
		TeacherGradeTable.table = table;
	}
	
	
}
