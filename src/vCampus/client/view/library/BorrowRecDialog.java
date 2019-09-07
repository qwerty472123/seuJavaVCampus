package vCampus.client.view.library;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vCampus.bean.BookBean;
import vCampus.bean.BookBorrowRecBean;

import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;

public class BorrowRecDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUserId;
	private JTextField txtBookId;
	private JButton okButton;
	private JButton cancelButton;
	private int rcId;
	private JTextField txtBorrowTime;
	private JTextField txtDueTime;
	private Date borrowDate;
	private Date dueDate;
	
	public JButton getOkButton() {return okButton;}
	public JButton getCancelButton() {return cancelButton;}
	public BookBorrowRecBean getBookBorrowRec() {
		BookBorrowRecBean b=new BookBorrowRecBean();
		b.setBookId(Integer.parseInt(txtBookId.getText()));
		b.setUserId(Integer.parseInt(txtUserId.getText()));
		b.setBorrowTime(borrowDate);
		b.setDueTime(dueDate);
		b.setID(rcId);
		return b;
	}
	
	public void setBookBorrowRec(BookBorrowRecBean b) {
		rcId=b.getID();
		if(b.getBookId()!=-1)txtBookId.setText(String.valueOf(b.getBookId()));
		if(b.getUserId()!=-1)txtUserId.setText(String.valueOf(b.getUserId()));
		txtBorrowTime.setText(b.getBorrowTime().toString());
		txtDueTime.setText(b.getDueTime().toString());
		borrowDate=b.getBorrowTime();
		dueDate=b.getDueTime();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BorrowRecDialog dialog = new BorrowRecDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BorrowRecDialog() {
		setModal(true);
		setBounds(100, 100, 397, 245);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1, 1, 5, 2, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("借阅信息");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.gridwidth = 4;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("图书ID");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtBookId = new JTextField();
			txtBookId.setEnabled(false);
			GridBagConstraints gbc_txtBookId = new GridBagConstraints();
			gbc_txtBookId.insets = new Insets(0, 0, 5, 5);
			gbc_txtBookId.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtBookId.gridx = 2;
			gbc_txtBookId.gridy = 1;
			contentPanel.add(txtBookId, gbc_txtBookId);
			txtBookId.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("借阅者ID");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 2;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtUserId = new JTextField();
			GridBagConstraints gbc_txtUserId = new GridBagConstraints();
			gbc_txtUserId.insets = new Insets(0, 0, 5, 5);
			gbc_txtUserId.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtUserId.gridx = 2;
			gbc_txtUserId.gridy = 2;
			contentPanel.add(txtUserId, gbc_txtUserId);
			txtUserId.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("借出时间");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 3;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtBorrowTime = new JTextField();
			txtBorrowTime.setEnabled(false);
			GridBagConstraints gbc_txtBorrowTime = new GridBagConstraints();
			gbc_txtBorrowTime.insets = new Insets(0, 0, 5, 5);
			gbc_txtBorrowTime.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtBorrowTime.gridx = 2;
			gbc_txtBorrowTime.gridy = 3;
			contentPanel.add(txtBorrowTime, gbc_txtBorrowTime);
			txtBorrowTime.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("归还期限");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 4;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtDueTime = new JTextField();
			txtDueTime.setEnabled(false);
			GridBagConstraints gbc_txtDueTime = new GridBagConstraints();
			gbc_txtDueTime.insets = new Insets(0, 0, 0, 5);
			gbc_txtDueTime.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDueTime.gridx = 2;
			gbc_txtDueTime.gridy = 4;
			contentPanel.add(txtDueTime, gbc_txtDueTime);
			txtDueTime.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("确认");
				//okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("取消");
				//cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						BorrowRecDialog.this.dispose();
					}
					
				});
			}
		}
	}
}
