package vCampus.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import vCampus.bean.NewsBean;
import vCampus.client.ClientMain;
import vCampus.client.controller.NewsTransponder;
import vCampus.client.view.utility.GroupifyBtnAndCard;
import vCampus.utility.Config;
import vCampus.utility.Token;

public class InformationPanel extends JPanel{

	private JPanel pages;
	
	
	private NewsBar newsBar;
	private JPanel defaultPanel;
	private JScrollPane defaultScroll;
	private int newsCnt;

	private List<NewsBean> defaultList;
	
	public InformationPanel() {
		setLayout(new BorderLayout());

		newsBar = new NewsBar();
		add(newsBar, BorderLayout.SOUTH);

		
		newsBar.submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewsTransponder.requestNewLetter(InformationPanel.this, "");
			}
		});
		
		JPanel mainContainer = new JPanel();
		add(mainContainer, BorderLayout.CENTER);//NORTH);
		mainContainer.setLayout(new CardLayout());
		
		defaultPanel = new JPanel();
		defaultPanel.setLayout(new BoxLayout(defaultPanel, BoxLayout.Y_AXIS));
		defaultScroll = new JScrollPane(defaultPanel);
		mainContainer.add(defaultScroll);
		
		defaultPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				refreshNewsCnt();
				refreshNews();
			}
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				refreshNewsCnt();
				refreshNews();
			}			
		});

		NewsTransponder.requestNewLetter(this, "");
		
	}
	
	public void refreshNewsCnt() {
		int h = (int) defaultPanel.getSize().getHeight();
		newsCnt =  h/60;
	}
	
	public void refreshNews() {
		defaultPanel.removeAll();
		
		for (NewsBean bean: defaultList) {
			JPanel newCard = new JPanel();
			newCard.setLayout(new BorderLayout());
			newCard.setBorder(BorderFactory.createLineBorder(new Color(127, 127, 255)));
			newCard.add(new JLabel(" "), BorderLayout.NORTH);
			newCard.add(new JLabel(bean.getTitle()), BorderLayout.WEST);
			newCard.add(new JLabel(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bean.getDate())), BorderLayout.EAST);
			newCard.add(new JLabel(" "), BorderLayout.SOUTH);
			defaultPanel.add(newCard);
		}
		
		if (defaultList.size()<newsCnt) {
			for (int i=defaultList.size(); i<newsCnt; ++i) {
				JPanel newCard = new JPanel();
				newCard.setLayout(new BorderLayout());
				newCard.add(new JLabel(" "), BorderLayout.NORTH);
				newCard.add(new JLabel(" "), BorderLayout.WEST);
				newCard.add(new JLabel(" "), BorderLayout.EAST);
				newCard.add(new JLabel(" "), BorderLayout.SOUTH);
				defaultPanel.add(newCard);
			}
		}

		this.revalidate();
		
	}

	public void setDefaultList(List<NewsBean> defaultList) {
		this.defaultList = defaultList;
	}
	
	
	
}
