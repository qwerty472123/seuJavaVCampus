package vCampus.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import vCampus.bean.GoodBean;
import vCampus.utility.Config;

public class ShopStoreDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private JTextField txtShopId;
	private JTextField txtGoodName;
	private JTextField txtPrice;
	private JTextField txtGoodId;
	private JTextArea txtDescription;
	private JButton selButton;
	private JButton okButton;
	private JButton cancelButton;
	
	private ImageIcon ii = null;
	
	public JButton getOkButton() {return okButton;}
	public JButton getCancelButton() {return cancelButton;}
	public GoodBean getGood() {
		GoodBean bean = new GoodBean();
		bean.setGoodID(Integer.parseInt(txtGoodId.getText()));
		bean.setShopID(Integer.parseInt(txtShopId.getText()));
		bean.setGoodName(txtGoodName.getText());
		bean.setPrice((int) (Double.parseDouble(txtPrice.getText())*100));
		bean.setCaption(txtDescription.getText());
		bean.setImg(ii);
		return bean;
	}
	
	
	public void setGood(GoodBean bean) {
		txtGoodId.setText(String.valueOf(bean.getGoodID()));
		txtShopId.setText(String.valueOf(bean.getShopID()));
		txtGoodName.setText(bean.getGoodName());
		int p = bean.getPrice();
		txtPrice.setText(p/100 + "." + (p%100)/10 + p%10);
		txtDescription.setText(bean.getCaption());
		ii = bean.getImg();
	}

	/**
	 * Create the dialog.
	 */
	public ShopStoreDialog() {
		setModal(true);
		setBounds(100, 100, 357, 367);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1, 1, 1.0, 2, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("商品信息");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.gridwidth = 4;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("商品编号");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtGoodId = new JTextField();
			GridBagConstraints gbc_txtGoodId = new GridBagConstraints();
			gbc_txtGoodId.insets = new Insets(0, 0, 5, 5);
			gbc_txtGoodId.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtGoodId.gridx = 2;
			gbc_txtGoodId.gridy = 1;
			contentPanel.add(txtGoodId, gbc_txtGoodId);
			txtGoodId.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("分类编号");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 2;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtShopId = new JTextField();
			GridBagConstraints gbc_txtShopId = new GridBagConstraints();
			gbc_txtShopId.insets = new Insets(0, 0, 5, 5);
			gbc_txtShopId.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtShopId.gridx = 2;
			gbc_txtShopId.gridy = 2;
			contentPanel.add(txtShopId, gbc_txtShopId);
			txtShopId.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("商品名称");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 3;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtGoodName = new JTextField();
			GridBagConstraints gbc_txtGoodName = new GridBagConstraints();
			gbc_txtGoodName.insets = new Insets(0, 0, 5, 5);
			gbc_txtGoodName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtGoodName.gridx = 2;
			gbc_txtGoodName.gridy = 3;
			contentPanel.add(txtGoodName, gbc_txtGoodName);
			txtGoodName.setColumns(10);
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
			JLabel lblNewLabel = new JLabel("单价");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 6;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtPrice = new JTextField();
			GridBagConstraints gbc_txtPrice = new GridBagConstraints();
			gbc_txtPrice.insets = new Insets(0, 0, 5, 5);
			gbc_txtPrice.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPrice.gridx = 2;
			gbc_txtPrice.gridy = 6;
			contentPanel.add(txtPrice, gbc_txtPrice);
			txtPrice.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("图片");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 7;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			selButton = new JButton("选择文件..");
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(0, 0, 0, 5);
			gbc_button.gridx = 2;
			gbc_button.gridy = 7;
			contentPanel.add(selButton, gbc_button);
			
			selButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileFilter() {
						@Override
						public boolean accept(File f) {
							return (f.getName().endsWith(".jpg") || f.isDirectory());
						}
						@Override
						public String getDescription() {
							return null;
						}						
					});
					chooser.showOpenDialog(ShopStoreDialog.this);
					try {
						File f = chooser.getSelectedFile();
						InputStream is=new FileInputStream(f);
						BufferedImage bi=ImageIO.read(is);
						Image im=(Image)bi;
						ii = new ImageIcon(im);
						//Config.log("!!!!!!" + ii.equals(new ImageIcon()));					
					}catch(IOException ioe) {
						Config.log(ioe);
					}
					
				}
				
			});
			
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("确认");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("取消");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ShopStoreDialog.this.dispose();
					}
					
				});
			}
		}
	}


}
