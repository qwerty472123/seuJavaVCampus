package vCampus.client.view.hospital;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vCampus.bean.AptRecBean;
import vCampus.bean.DoctorBean;
import vCampus.client.view.utility.MyTable;
import vCampus.utility.Config;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class MyAptDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private MyTable table;
	JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MyAptDialog dialog = new MyAptDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			Config.log(e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public MyAptDialog() {
		setBounds(100, 100, 846, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				table = new MyTable(new String[] {"预约单号","预约医生","预约时间","操作时间","备注","是否已接受"});
				table.setEditable(false);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void setMyAptTable(List<AptRecBean> list, List<String> doctornames) {
		table.removeAllRows();
		int i = 0;
		for (AptRecBean x : list) {
			table.addRow(new Object[] {x.getId(),doctornames.get(i),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(x.getAptday()),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(x.getOperTime()),x.getRemark(),(x.isDone()?"是":"否")});
			i++;
		}
		scrollPane.setViewportView(table);
	}
}
