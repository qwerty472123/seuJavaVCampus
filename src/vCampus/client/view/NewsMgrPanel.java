package vCampus.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import vCampus.bean.NewsBean;
import vCampus.client.controller.Auth;
import vCampus.client.controller.NewsTransponder;
import vCampus.utility.Config;

public class NewsMgrPanel extends NewsPanel{
	private NewsEditorBar newsEditorBar;
	
	private JPanel mainContainer;
	private CardLayout cardLayout;
	
	private JPanel defaultPanel;
	private JScrollPane defaultScroll;
	private int newsCnt;
	
	//private JPanel contentPanel;
	//private JScrollPane contentScroll;
	private NewsBean curNews;
	private NewsBean nullNews;
	private boolean canRemove;

	private List<NewsBean> defaultList;
	
	public NewsMgrPanel() {
		
		setLayout(new BorderLayout());

		newsEditorBar = new NewsEditorBar();
		add(newsEditorBar, BorderLayout.SOUTH);

		
		newsEditorBar.submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewsTransponder.requestNewsList(NewsMgrPanel.this);
			}
		});
		newsEditorBar.execButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!canRemove) return;

				int option = JOptionPane.showConfirmDialog(NewsMgrPanel.this, "确定要把这条资讯移除出发布序列吗？");
				if (option!=JOptionPane.YES_OPTION) return;
				
				NewsTransponder.requestDelNews(NewsMgrPanel.this, curNews.getId());
				//curNews = nullNews; // MAYBE UNSAFE
				newsEditorBar.textField.setText("");
				
			}
		});
		
		
		mainContainer = new JPanel();
		add(mainContainer, BorderLayout.CENTER);
		cardLayout = new CardLayout();
		mainContainer.setLayout(cardLayout);
		
		defaultPanel = new JPanel();
		defaultPanel.setLayout(new BoxLayout(defaultPanel, BoxLayout.Y_AXIS));
		defaultScroll = new JScrollPane(defaultPanel);
		mainContainer.add(defaultScroll, "default");
		
		defaultPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				refreshNewsCnt();
				refreshNews();
			}
			@Override
			public void componentShown(ComponentEvent e) {
				refreshNewsCnt();
				refreshNews();
			}			
		});
		
		NewsTransponder.requestNewsList(this);

		
		nullNews = new NewsBean();
		nullNews.setId(-1);
		nullNews.setTitle("没有任何消息");
		nullNews.setDate(new Date());
		nullNews.setSource("");
		nullNews.setContent("");
		curNews = nullNews;
				
	}
	
	public void switchCard() {
		/* pass
		newsEditorBar.switchNarrow();
		cardLayout.next(mainContainer);*/
	}
	
	public void refreshNewsCnt() {
		int h = (int) defaultPanel.getSize().getHeight();
		newsCnt =  h/60;
	}
	
	public void refreshNews() {
		defaultPanel.removeAll();
		

		JPanel titleCard = new JPanel();
		JLabel titleText = new JLabel(" 资 讯 列 表  ");
		titleText.setHorizontalAlignment(SwingConstants.CENTER);
		titleText.setFont(new Font("微软雅黑", Font.BOLD | Font.ITALIC, 18));
		titleCard.setLayout(new BorderLayout());
		titleCard.add(new JLabel(" "), BorderLayout.NORTH);
		titleCard.add(titleText, BorderLayout.CENTER);
		titleCard.add(new JLabel(" "), BorderLayout.SOUTH);
		JButton exitBtn = new JButton("退出登录");
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Auth.logout();
			}
		});
		titleCard.add(exitBtn, BorderLayout.WEST);		
		defaultPanel.add(titleCard);
		
		Collections.sort(defaultList);
		
		if (defaultList.size()>=1) curNews = defaultList.get(0);
		
		for (NewsBean bean: defaultList) {
			JPanel newCard = new JPanel();
			newCard.setLayout(new BorderLayout());
			newCard.setBorder(BorderFactory.createLineBorder(new Color(127, 127, 255)));
			newCard.add(new JLabel(" "), BorderLayout.NORTH);
			newCard.add(new JLabel(bean.getTitle()), BorderLayout.WEST);
			newCard.add(new JLabel(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bean.getDate())), BorderLayout.EAST);
			newCard.add(new JLabel(" "), BorderLayout.SOUTH);
			defaultPanel.add(newCard);
			newCard.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mousePressed(MouseEvent e) {
					canRemove = true;
					if (curNews!=bean) {
						for (Component c0: defaultPanel.getComponents()) {
							JPanel c = (JPanel) c0;
							c.setBackground(Color.white);
							for (Component cc: c.getComponents()) {
								cc.setBackground(Color.white);
							}
						}
						curNews=bean;
						newCard.setBackground(new Color(159, 159, 255));
						for (Component c: newCard.getComponents()) {
							c.setBackground(new Color(159, 159, 255));
						}
					}else {
						//none
					}
					newsEditorBar.textField.setText(bean.getSource());
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					if (curNews==bean) return;
					newCard.setBackground(new Color(223, 223, 255));
					for (Component c: newCard.getComponents()) {
						c.setBackground(new Color(223, 223, 255));
					}
					
				}
				@Override
				public void mouseExited(MouseEvent e) {
					if (curNews==bean) return;
					newCard.setBackground(Color.white);
					for (Component c: newCard.getComponents()) {
						c.setBackground(Color.white);
					}					
				}
				
			});
			
		}
		
		if (defaultList.size()+1<newsCnt) {
			for (int i=defaultList.size()+1; i<newsCnt; ++i) {
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
		
		curNews = nullNews;
		canRemove = false; 
	}

	public void setDefaultList(List<NewsBean> defaultList) {
		this.defaultList = defaultList;
	}
	
		
}
	