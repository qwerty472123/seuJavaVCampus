package vCampus.client.view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import vCampus.bean.ExpenseRecBean;
import vCampus.client.controller.Bank;
import vCampus.utility.socket.ResponseSender;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class BankAllowPanel extends JPanel{
	private JLabel settleTitle;
	private JPanel settleContent;
	private JScrollPane settleScroll;

	public BankAllowPanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		settleTitle = new JLabel("当前没有需核验的充值请求");
		settleTitle.setHorizontalAlignment(SwingConstants.CENTER);
		settleTitle.setFont(new Font("微软雅黑", Font.ITALIC, 24));
		add(settleTitle, BorderLayout.NORTH);

		
		settleContent = new JPanel();
		settleContent.setLayout(new BoxLayout(settleContent, BoxLayout.Y_AXIS));
		settleScroll = new JScrollPane(settleContent);
		add(settleScroll, BorderLayout.CENTER);
	}
	
	public void newCheckToSettle(String userName, int count, final ResponseSender sender) {
		settleTitle.setText("当前需核验的充值请求");
		
		final JPanel newPanel = new JPanel();
		newPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createEmptyBorder(5, 5, 5, 5),
					BorderFactory.createLineBorder(MaterialColors.LIGHT_GREEN_400)));
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
		int p = count;
		JLabel title_1 = new JLabel("收款方： " + userName);		
		title_1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		title_1.setBackground(null);
		JLabel title_2 = new JLabel("待支付总额： ￥" + (p/100) + "." + (p%100)/10 + p%10);		
		title_2.setFont(new Font("微软雅黑", Font.BOLD, 16));
		title_2.setBackground(null);
		newPanel.add(title_1);
		newPanel.add(title_2);
		MaterialUIMovement.add(newPanel, MaterialColors.LIGHT_GREEN_400);
		newPanel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] ops = {"同意充值", "拒绝充值", "取消"};
				int option = JOptionPane.showOptionDialog(BankAllowPanel.this,
						"<html>"
						+ "<p>" + title_1.getText() + "</p>"
						+ "<p>" + title_2.getText() + "</p>"
						+ "<p>请仔细核验上述信息是否合法，并作出选择：</p>"
						+ "</html>",
						"vCampus",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						ops,
						ops[0]);
				Map<String, Object> data = new HashMap<String, Object>();
				if (option==0) {
					data.put("code", 200);
					sender.send(data);
					settleContent.remove(newPanel);
					BankAllowPanel.this.revalidate();
					BankAllowPanel.this.repaint();
				}else if(option==1) {
					data.put("code", 405);
					sender.send(data);
					settleContent.remove(newPanel);
					BankAllowPanel.this.revalidate();
					BankAllowPanel.this.repaint();
				}
			}
			
		});
		settleContent.add(newPanel);
		this.revalidate();
		this.repaint();
	}

}
