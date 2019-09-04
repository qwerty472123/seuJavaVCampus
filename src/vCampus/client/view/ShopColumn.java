package vCampus.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

public class ShopColumn extends JPanel {
	
	private JPanel mp;
	private List<JPanel> goodsList;

	public ShopColumn(String title, List<JPanel> goodsList){
		
		setBorder(BorderFactory.createLineBorder(Color.gray));
		
		setLayout(new BorderLayout());
		add(new JLabel(title), BorderLayout.WEST);
		
		mp = new JPanel();
		add(mp, BorderLayout.CENTER);
		
		this.goodsList = goodsList;
		refresh(1);	
	}
	
	public int refresh(int colCnt) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		mp.setLayout(gridBagLayout);
		
		gridBagLayout.columnWidths = new int[colCnt];
		for (int i=0; i<colCnt; ++i) gridBagLayout.columnWidths[i] = 240;
		
		int rowCnt = (goodsList.size()-1)/colCnt + 1;
		gridBagLayout.rowHeights = new int[rowCnt];
		for (int i=0; i<rowCnt; ++i) gridBagLayout.rowHeights[i] = 250;
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		for (JPanel sc:goodsList){
			mp.add(sc, gbc);
			++gbc.gridx;
			if (gbc.gridx>=colCnt){
				gbc.gridx=0;
				++gbc.gridy;
			}	
		}
		
		return rowCnt;
	}
	
	
}
