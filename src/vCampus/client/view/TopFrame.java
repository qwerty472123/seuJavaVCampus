package vCampus.client.view;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
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
		setCenter(loginFrame);
		setCenter(mainFrame);
	}
	
	public void setCenter(JFrame frame) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int)(toolkit.getScreenSize().getWidth()-frame.getWidth())/2;
		int y = (int)(toolkit.getScreenSize().getHeight()-frame.getHeight())/2;
		frame.setLocation(x, y);
	}
	
	public void setCenter(JDialog frame) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int)(toolkit.getScreenSize().getWidth()-frame.getWidth())/2;
		int y = (int)(toolkit.getScreenSize().getHeight()-frame.getHeight())/2;
		frame.setLocation(x, y);
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
