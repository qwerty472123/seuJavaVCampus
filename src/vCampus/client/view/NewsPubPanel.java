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
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import vCampus.bean.NewsBean;
import vCampus.client.controller.NewsTransponder;

public class NewsPubPanel extends JPanel{

	private NewsPubBar newsPubBar;
	
	private JPanel contentPanel;
	private JScrollPane contentScroll;
	private JTextArea jta;
	
	public NewsPubPanel() {
		setLayout(new BorderLayout());

		newsPubBar = new NewsPubBar();
		add(newsPubBar, BorderLayout.NORTH);

		
		newsPubBar.submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(NewsPubPanel.this, "确认发布当前文章？");
				if (option!=JOptionPane.YES_OPTION) return;
				
				NewsBean bean = new NewsBean();
				bean.setTitle(newsPubBar.textField.getText());
				bean.setType("news");
				bean.setDate(new Date());
				bean.setContent(jta.getText());
				bean.setSource("<unknown>");
				
				NewsTransponder.publishNews(NewsPubPanel.this, bean);
				//TODO
				
			}
		});
		newsPubBar.resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearText();
			}
		});
		
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(1, 1));
		jta = new JTextArea();
		jta.setLineWrap(true);
		jta.setText("请在此输入正文");		
		jta.setBackground(Color.white);
		jta.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		jta.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPanel.add(jta);
		contentScroll = new JScrollPane(contentPanel);
		contentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(contentScroll, BorderLayout.CENTER);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				String str = jta.getText();
				contentPanel.removeAll();
				jta = new JTextArea();
				jta.setLineWrap(true);
				jta.setText(str);		
				jta.setBackground(Color.white);
				jta.setFont(new Font("微软雅黑", Font.PLAIN, 16));
				jta.setBorder(BorderFactory.createLineBorder(Color.black));
				contentPanel.add(jta);
				
			}
			
		});
				
	}
	
	public void clearText() {
		jta.setText("请在此输入正文");
	}

	public void clearAll() {
		newsPubBar.textField.setText("");
		clearText();
	}
	
}
