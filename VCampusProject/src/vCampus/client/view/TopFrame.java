package vCampus.client.view;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mdlaf.MaterialLookAndFeel;
import vCampus.utility.Config;

public class TopFrame {
	
	private LoginFrame loginFrame;
	private MainFrame mainFrame;
	
	public TopFrame() {
		try {
			UIManager.setLookAndFeel(new MaterialLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			Config.log(e);
		}
		loginFrame = new LoginFrame();
		mainFrame = new MainFrame();
	}
	
	public void showNone() {
		loginFrame.setVisible(false);
		mainFrame.setVisible(false);
	}
	
	public void showLoginFrame() {
		loginFrame.setVisible(true);
		mainFrame.setVisible(false);
	}
	
	public void showMainFrame() {
		loginFrame.setVisible(false);
		mainFrame.setVisible(true);
	}

	public LoginFrame getLoginFrame() {
		return loginFrame;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}
	
}
