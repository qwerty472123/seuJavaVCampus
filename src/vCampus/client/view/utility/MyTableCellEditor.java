package vCampus.client.view.utility;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyTableCellEditor extends DefaultCellEditor {

	private static final long serialVersionUID = 1L;
	private JTextField text;
	public MyTableCellEditor(JTextField text) {
		super(text);
		// TODO Auto-generated constructor stub
		this.text = text; 
	}

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        // TODO Auto-generated method stub
        text.setText(value.toString());
        return text; 
    }
    
}
