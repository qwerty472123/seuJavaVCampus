package vCampus.client.view.library;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import vCampus.bean.BookBean;
import vCampus.client.controller.Library;
import vCampus.client.view.utility.MyTable;
import vCampus.utility.Config;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.border.LineBorder;

import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UBookPanel extends JPanel implements BookPanel{
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSearchWord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBookList(ArrayList<BookBean> data) {
		// TODO Auto-generated method stub
		
	}
}



