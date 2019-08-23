package vCampus.client.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoginFrame {
	
	private JFrame mf;
	
	public LoginFrame(){
		mf = new JFrame("VCampus Login");
		LoginPanel loginv = new LoginPanel();

	    mf.setLayout(null);

	    JPanel mp = loginv.getPanel();
	    mf.add(mp);

	    mf.setSize(mp.getWidth(), mp.getHeight());
	    mp.setLocation(0, 0);
	    	    
	    mf.setResizable(false);
	    mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void display(){
	    mf.setVisible(true);		
	}
	
	public void hide(){
	    mf.setVisible(false);		
	}
	
    
}
