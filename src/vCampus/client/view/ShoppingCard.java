package vCampus.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class ShoppingCard extends JPanel{

	public ShoppingCard(String title, ImageIcon ii, String lLabel, String rLabel){
		setPreferredSize(new Dimension(200, 225));
		//setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(127, 127, 255), new Color(0, 0, 255)));
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.lightGray),
				BorderFactory.createLineBorder(Color.lightGray)
				));
		setLayout(new BorderLayout());
		
		add(new JLabel(title), BorderLayout.NORTH);
		if (ii!=null) add(new JLabel(ii), BorderLayout.CENTER);
		//else add(new)		
		
		JPanel buttom = new JPanel();
		buttom.setLayout(new BorderLayout());
		buttom.add(new JLabel(lLabel), BorderLayout.WEST);
		buttom.add(new JLabel(rLabel), BorderLayout.EAST);
		add(buttom, BorderLayout.SOUTH);
				
	}
	
	
}
