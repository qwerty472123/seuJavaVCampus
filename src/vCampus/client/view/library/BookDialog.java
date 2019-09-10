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
import vCampus.utility.Config;

import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class BookDialog extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtAuthor;
	private JTextField txtPress;
	private JTextField txtLocation;
	private JTextField txtTitle;
	private JSpinner spinTot;
	private JTextArea txtDescription;
	private JButton okButton;
	private JButton cancelButton;
	private int bookId;
	private int orderCnt;
	private int orderStore;
	private int borrowCnt;
	
	public JButton getOkButton() {return okButton;}
	public JButton getCancelButton() {return cancelButton;}
	public BookBean getBook() {
		BookBean b=new BookBean();
		b.setTitle(txtTitle.getText());
		b.setAuthor(txtAuthor.getText());
		b.setPress(txtPress.getText());
		b.setDescription(txtDescription.getText());
		b.setLocation(txtLocation.getText());
		b.setTotCnt((int)spinTot.getValue());
		b.setBorrowCnt(borrowCnt);
		b.setOrderCnt(orderCnt);
		b.setOrderStore(orderStore);
		b.setID(bookId);
		return b;
	}
	
	
	public void setBook(final BookBean b) {
		bookId=b.getID();
		txtTitle.setText(b.getTitle());
		txtAuthor.setText(b.getAuthor());
		txtDescription.setText(b.getDescription());
		txtLocation.setText(b.getLocation());
		txtPress.setText(b.getPress());
		spinTot.setValue(b.getTotCnt());
		borrowCnt=b.getBorrowCnt();
		orderCnt=b.getOrderCnt();
		orderStore=b.getOrderStore();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			BookDialog dialog = new BookDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			Config.log(e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public BookDialog() {
		setModal(true);
		setBounds(100, 100, 357, 367);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1, 1, 5, 2, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("书目信息");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.gridwidth = 4;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("书名");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtTitle = new JTextField();
			GridBagConstraints gbc_txtTitle = new GridBagConstraints();
			gbc_txtTitle.insets = new Insets(0, 0, 5, 5);
			gbc_txtTitle.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTitle.gridx = 2;
			gbc_txtTitle.gridy = 1;
			contentPanel.add(txtTitle, gbc_txtTitle);
			txtTitle.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("作者");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 2;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtAuthor = new JTextField();
			GridBagConstraints gbc_txtAuthor = new GridBagConstraints();
			gbc_txtAuthor.insets = new Insets(0, 0, 5, 5);
			gbc_txtAuthor.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtAuthor.gridx = 2;
			gbc_txtAuthor.gridy = 2;
			contentPanel.add(txtAuthor, gbc_txtAuthor);
			txtAuthor.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("出版社");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 3;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtPress = new JTextField();
			GridBagConstraints gbc_txtPress = new GridBagConstraints();
			gbc_txtPress.insets = new Insets(0, 0, 5, 5);
			gbc_txtPress.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPress.gridx = 2;
			gbc_txtPress.gridy = 3;
			contentPanel.add(txtPress, gbc_txtPress);
			txtPress.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("描述");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.gridheight = 2;
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 4;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtDescription = new JTextArea();
			txtDescription.setText("");
			GridBagConstraints gbc_txtDescription = new GridBagConstraints();
			gbc_txtDescription.gridheight = 2;
			gbc_txtDescription.insets = new Insets(0, 0, 5, 5);
			gbc_txtDescription.fill = GridBagConstraints.BOTH;
			gbc_txtDescription.gridx = 2;
			gbc_txtDescription.gridy = 4;
			contentPanel.add(txtDescription, gbc_txtDescription);
		}
		{
			JLabel lblNewLabel = new JLabel("位置");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 6;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtLocation = new JTextField();
			GridBagConstraints gbc_txtLocation = new GridBagConstraints();
			gbc_txtLocation.insets = new Insets(0, 0, 5, 5);
			gbc_txtLocation.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtLocation.gridx = 2;
			gbc_txtLocation.gridy = 6;
			contentPanel.add(txtLocation, gbc_txtLocation);
			txtLocation.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("总量");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 7;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			spinTot = new JSpinner();
			spinTot.setModel(new SpinnerNumberModel(0, 0, 999, 1));
			GridBagConstraints gbc_spinTot = new GridBagConstraints();
			gbc_spinTot.anchor = GridBagConstraints.WEST;
			gbc_spinTot.insets = new Insets(0, 0, 0, 5);
			gbc_spinTot.gridx = 2;
			gbc_spinTot.gridy = 7;
			contentPanel.add(spinTot, gbc_spinTot);
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
						BookDialog.this.dispose();
					}
					
				});
			}
		}
	}

}
