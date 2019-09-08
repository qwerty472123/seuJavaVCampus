package vCampus.server.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import vCampus.bean.GoodBean;
import vCampus.server.ServerMain;
import vCampus.server.dao.GoodsDao;
import vCampus.server.dao.model.Good;
import vCampus.utility.Config;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class ShopRobot {
	static {
		

		ServerMain.addRequestListener("shop/refresh", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				List<Good> goodsList = GoodsDao.queryGoods("");
				
				List<GoodBean> bl = new ArrayList<>();
				
				for (Good g: goodsList) {
					GoodBean bean = g.toBean();
					int id = bean.getGoodID();
					String str = "shopimg/good_" + id + ".jpg";
					ImageIcon ii = new ImageIcon(str);
					if (ii.getImageLoadStatus()==MediaTracker.COMPLETE) {
						ii.setImage(ii.getImage().getScaledInstance(200, 225,Image.SCALE_DEFAULT ));
						bean.setImg(ii);						
					}else {
						bean.setImg(null);
					}
					
					//Config.log("!!! " + (ii!=null) + " | " + (ii!=new ImageIcon()));
					
					bl.add(bean);
				}
				
				data.put("list", bl);
				
				data.put("code", 200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("shop/add", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();
				

				GoodBean bean = ((GoodBean) msg.getData().get("good"));
				Good g = Good.createModel(bean);
				
				GoodsDao.addRec(g);
			   	
            	Image img = bean.getImg().getImage();
				BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB); 
				Graphics2D g2 = bi.createGraphics(); 
				g2.drawImage(img, 0, 0, null); 
				g2.dispose(); 
				try {
					ImageIO.write(bi, "jpg", new File("./shopimg/good_"+bean.getGoodID()+".jpg"));
					data.put("code", 200);
				} catch (IOException e) {
					Config.log(e);
					data.put("code", 401);
				}
				
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("shop/modify", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				GoodBean bean = ((GoodBean) msg.getData().get("good"));
				Good g = Good.createModel(bean);
				
				GoodsDao.update(g);
				
				Image img = bean.getImg().getImage();
				BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB); 
				Graphics2D g2 = bi.createGraphics(); 
				g2.drawImage(img, 0, 0, null); 
				g2.dispose(); 
				try {
					ImageIO.write(bi, "jpg", new File("./shopimg/good_"+bean.getGoodID()+".jpg"));
					data.put("code", 200);
				} catch (IOException e) {
					Config.log(e);
					data.put("code", 401);
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("shop/delete", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				int id = (int) msg.getData().get("goodId");
				
				GoodsDao.delRec(id);
				
				data.put("code", 200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		
	}

}
