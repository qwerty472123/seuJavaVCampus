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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import vCampus.bean.NewsBean;
import vCampus.client.controller.NewsTransponder;
import vCampus.client.view.utility.MyStyle;

public class NewsPanel extends JPanel{
		
	private NewsBar newsBar;
	
	private JPanel mainContainer;
	private CardLayout cardLayout;
	
	private JPanel defaultPanel;
	private JScrollPane defaultScroll;
	private int newsCnt;
	
	private JPanel contentPanel;
	private JScrollPane contentScroll;
	private NewsBean curNews;

	private List<NewsBean> defaultList = new ArrayList<NewsBean>();
	
	public NewsPanel() {
		setLayout(new BorderLayout());

		newsBar = new NewsBar();
		add(newsBar, BorderLayout.SOUTH);

		
		newsBar.submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewsTransponder.requestNewsList(NewsPanel.this);
			}
		});
		newsBar.foldupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchCard();
			}
		});
		
		
		mainContainer = new JPanel();
		add(mainContainer, BorderLayout.CENTER);//NORTH);
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
		
		contentPanel = new JPanel();
		contentScroll = new JScrollPane(contentPanel);
		contentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		mainContainer.add(contentScroll, "content");
		mainContainer.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resetContent(curNews);
			}
			
		});
		
		curNews = new NewsBean();
		curNews.setTitle("没有任何消息");
		curNews.setDate(new Date());
		curNews.setSource("");
		curNews.setContent("");
				
	}
	
	public void switchCard() {
		newsBar.switchNarrow();
		cardLayout.next(mainContainer);
	}
	
	public void refreshNewsCnt() {
		int h = (int) defaultPanel.getSize().getHeight();
		newsCnt =  h/65;
	}
	
	public void refreshNews() {
		defaultPanel.removeAll();
		

		JPanel titleCard = new JPanel();
		JLabel titleText = new JLabel(" 最 新 资 讯 ");
		titleText.setFont(MyStyle.FONT_L);
		titleText.setForeground(MyStyle.RED);
		titleText.setHorizontalAlignment(SwingConstants.CENTER);
		//titleText.setFont(new Font("微软雅黑", Font.BOLD | Font.ITALIC, 30));
		titleCard.setLayout(new BorderLayout());
		titleCard.add(new JLabel(" "), BorderLayout.NORTH);
		titleCard.add(titleText, BorderLayout.CENTER);
		titleCard.add(new JLabel(" "), BorderLayout.SOUTH);
		defaultPanel.add(titleCard);
		
		Collections.sort(defaultList);
		
		if (defaultList.size()>=1) curNews = defaultList.get(0);
		
		for (NewsBean bean: defaultList) {
			JPanel newCard = new JPanel();
			newCard.setLayout(new BorderLayout());
			newCard.setBorder(BorderFactory.createLineBorder(new Color(127, 127, 255)));
			JLabel l = new JLabel(bean.getTitle());
			l.setFont(new Font("微软雅黑", Font.PLAIN, 15));
			JLabel r = new JLabel(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bean.getDate()));
			r.setFont(new Font("微软雅黑", Font.PLAIN, 15));			
			newCard.add(new JLabel(" "), BorderLayout.NORTH);
			newCard.add(l, BorderLayout.WEST);
			newCard.add(r, BorderLayout.EAST);
			newCard.add(new JLabel(" "), BorderLayout.SOUTH);
			defaultPanel.add(newCard);
			newCard.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					curNews = bean;
					resetContent(curNews);
					switchCard();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					newCard.setBackground(new Color(223, 223, 255));
					for (Component c: newCard.getComponents()) {
						c.setBackground(new Color(223, 223, 255));
					}
				}
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
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
		
	}

	public void setDefaultList(List<NewsBean> defaultList) {
		this.defaultList = defaultList;
	}
	
	public void resetContent(NewsBean bean) {
		
		contentPanel.removeAll();
		contentPanel.setLayout(new BorderLayout());

		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		JLabel head = new JLabel(bean.getTitle());
		head.setFont(new Font("微软雅黑", Font.BOLD, 28));
		head.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(head, BorderLayout.NORTH);					
		JLabel detail = new JLabel(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bean.getDate())
					+ "      " + bean.getSource());
		detail.setFont(new Font("微软雅黑", Font.ITALIC, 12));
		detail.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(detail, BorderLayout.CENTER);
		titlePanel.add(new JLabel(" "), BorderLayout.SOUTH);
		
		contentPanel.add(titlePanel, BorderLayout.NORTH);
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(1, 1));
		
		JTextArea jta = new JTextArea();
		jta.setLineWrap(true);
		jta.setText(bean.getContent());
		jta.setEditable(false);
		
		jta.setBackground(Color.white);
		jta.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		textPanel.add(jta);
		contentPanel.add(textPanel);

		//roll to the head
		jta.requestFocus();
		jta.select(0, 0);
	}
	
	
	
}
