package vCampus.client.view.utility;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

public class GroupifyBtnAndCard {
	
	public static void groupBtnsAndCards(JButton[] btns,JPanel card,
			Color select,Color hover) {
		Color normal=btns[0].getForeground();
		
		
		ActionListener ac=new ActionListener() {
			private JButton last=null;
			public void actionPerformed(ActionEvent e) {
				
				
				/*String name=((JButton)e.getSource()).getText();
				System.out.println(name+" clicked");*/
				if(last!=null)
					last.setForeground(normal);
				((JButton)e.getSource()).setForeground(select);
				last=(JButton)e.getSource();
				
				int n=btns.length;
				for(int i=0;i<n;i++)
					if(btns[i]==e.getSource()) {
						/*if(name.equals("�ҵĿα�")) {
							System.out.println("i="+i);
						}*/

						((CardLayout)card.getLayout()).show(card,"name_"+(i+1));
						
						break;
					}
			}
		};
		
		for(JButton b:btns) {
			MaterialUIMovement.add (b, hover);
			b.addActionListener(ac);
		}
	}
	
	public static void groupBtnsAndCards(JButton[] btns,JPanel card) {
		groupBtnsAndCards(btns,card,MaterialColors.DEEP_ORANGE_A100,MaterialColors.AMBER_100);
	}
	
}
