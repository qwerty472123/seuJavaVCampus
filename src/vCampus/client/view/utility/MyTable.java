package vCampus.client.view.utility;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import mdlaf.utils.MaterialColors;

public class MyTable extends JTable {
	
	class MyTableModel extends AbstractTableModel{
		private ArrayList<String> columnNames;
		private ArrayList<ArrayList<Object>> data=new ArrayList<ArrayList<Object>>();
		
		public void removeRow(int row) {
			data.remove(row);
		}
		
		public void removeAllRows() {
			// TODO Auto-generated method stub
			data.clear();
		}
		
		MyTableModel(String[] colNames){
			columnNames=new ArrayList<String>();
			for(String x:colNames)
				columnNames.add(x);
		}
		
		@Override
		public String getColumnName(int col) {
			return columnNames.get(col);
		}
		
		@Override
		public boolean isCellEditable(int row, int col)
        { return true; }
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnNames.size();
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			return data.get(row).get(col);
		}
		
		@Override
		public void setValueAt(Object obj,int row,int col) {
			int cur=getRowCount()-1;
			if(cur<row)
				for(int i=0;i<row-cur;i++) {
					ArrayList<Object> item=new ArrayList<Object>();
					for(int j=0;j<getColumnCount();j++)
						item.add("空");
					data.add(item);
				}
			//System.out.println("columncount="+getColumnCount());
			//System.out.println("set value at row "+row+",col "+col+" data has "+data.size()+" rows,"+data.get(0).size()+" cols");
			data.get(row).set(col, obj);
			fireTableCellUpdated(row, col);
		}
		
		@Override
		public Class getColumnClass(int c) {
	        return getValueAt(0, c).getClass();
	    }

		
	}
	
	/**
	 * NOT IMPELEMENTED YET
	 * @author kinoud
	 *
	 */
	class MyTableEditor implements TableCellEditor{

		@Override
		public void addCellEditorListener(CellEditorListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void cancelCellEditing() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isCellEditable(EventObject arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void removeCellEditorListener(CellEditorListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean shouldSelectCell(EventObject arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean stopCellEditing() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public void addRow(Object[] x) {
		int row=getRowCount();
		for(int i=0;i<getColumnCount();i++) {
			this.setValueAt(x[i], row, i);
		}
	}
	
	public void addRow(ArrayList<String> x) {
		int row=getRowCount();
		for(int i=0;i<getColumnCount();i++) {
			this.setValueAt(x.get(i), row, i);
		}
	}
	
	public void removeRow(int row) {
		((MyTableModel)getModel()).removeRow(row);
	}
	
	public void removeAllRows() {
		((MyTableModel)getModel()).removeAllRows();
	}
	
	public void setRow(int row,Object[] x) {
		for(int i=0;i<getColumnCount();i++) {
			this.setValueAt(x[i], row, i);
		}
	}
	
	
	
	public MyTable(String[] colNames){
		setModel(new MyTableModel(colNames));
		getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
}