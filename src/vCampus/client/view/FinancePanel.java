package vCampus.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mdlaf.utils.MaterialColors;
import vCampus.client.controller.Bank;
import vCampus.client.view.utility.MyTable;

public class FinancePanel extends JPanel{

	private List< ArrayList<String>> recordData;

	private MyTable recordTable;
	
	public FinancePanel() {
		setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel recordPage = new JPanel();
		recordPage.setLayout(new BorderLayout(0, 0));

		add(recordPage);
		
		recordTable = new MyTable(new String[] {"流水ID","用户ID","金额","日期", "收款方"});
		recordTable.setEditable(false);
		JScrollPane tableContainer=new JScrollPane(recordTable);
		tableContainer.getViewport().setBackground(MaterialColors.WHITE);
		Bank.popRecs(this);
		recordPage.add(tableContainer, BorderLayout.CENTER);
		
		JPanel detailCol = new JPanel();
		//detailCol.setBorder(BorderFactory.createLineBorder(Color.black));
		
		detailCol.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
		
		detailCol.setLayout(new BoxLayout(detailCol, BoxLayout.Y_AXIS));
		
		JPanel detailTitle = new JPanel();
		detailTitle.setLayout(new BorderLayout());
		detailCol.add(detailTitle);		
		JLabel label_5 = new JLabel(" 详 情 ");
		label_5.setFont(new Font("微软雅黑", Font.ITALIC, 16));
		detailTitle.add(label_5, BorderLayout.CENTER);
		
		JLabel detailLabel = new JLabel("");
		JScrollPane djsp = new JScrollPane(detailLabel);
		djsp.setPreferredSize(new Dimension(9999, 150));
		detailCol.add(djsp);
		recordPage.add(detailCol, BorderLayout.SOUTH);
		
		recordTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int cur=recordTable.getSelectedRow();
				String detail = recordData.get(cur).get(5);
				detail = "<html>" + detail + "</html>";
				detailLabel.setText(detail);
			}
		});

	}
	

	public void setRecordData(List<ArrayList<String>> recordData) {
		this.recordData = recordData;
	}
	
	public void refreshRecordTable() {		
		MyTable table = recordTable;
		table.removeAllRows();
		for(ArrayList<String> one:recordData) {
			ArrayList<String> cur = new ArrayList<>();
			for (String str: one) {
				cur.add(str);
			}
			cur.remove(5);
			table.addRow(cur);
		}
		table.revalidate();
		table.repaint();
	}
	
}
