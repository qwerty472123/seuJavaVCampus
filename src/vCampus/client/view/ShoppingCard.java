package vCampus.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class ShoppingCard extends JPanel{

	public ShoppingCard(String title, ImageIcon ii, String lLabel, String rLabel){
		setPreferredSize(new Dimension(200, 240));
		//setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(127, 127, 255), new Color(0, 0, 255)));
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
		setLayout(new BorderLayout());
		
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		add(titleLabel, BorderLayout.NORTH);
		if (ii!=null) add(new JLabel(ii), BorderLayout.CENTER);
		//else add(new)
		
		JPanel buttom = new JPanel();
		buttom.setLayout(new BorderLayout());
		JLabel l = new JLabel(lLabel);
		JLabel r = new JLabel(rLabel);
		l.setFont(new Font("宋体", Font.PLAIN, 12));
		r.setFont(new Font("宋体", Font.PLAIN, 14));
		buttom.add(l, BorderLayout.WEST);
		buttom.add(r, BorderLayout.EAST);
		add(buttom, BorderLayout.SOUTH);
				
	}
	
	
}
