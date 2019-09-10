package vCampus.client.view;

import java.awt.Toolkit;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mdlaf.MaterialLookAndFeel;
import vCampus.client.ClientMain;
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
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int)(toolkit.getScreenSize().getWidth()-loginFrame.getWidth())/2;
		int y = (int)(toolkit.getScreenSize().getHeight()-loginFrame.getHeight())/2;
		loginFrame.setLocation(x, y);
		x = (int)(toolkit.getScreenSize().getWidth()-mainFrame.getWidth())/2;
		y = (int)(toolkit.getScreenSize().getHeight()-mainFrame.getHeight())/2;
		mainFrame.setLocation(x, y);
	}
	
	public void showNone() {
		loginFrame.setVisible(false);
		mainFrame.setVisible(false);
	}
	
	public void showLoginFrame() {
		loginFrame.setVisible(true);
		mainFrame.setVisible(false);
		ClientMain.getTempData().remove("token");
		loginFrame.updAccount(loginFrame.getAccount());
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
