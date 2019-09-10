package vCampus.server.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import vCampus.bean.ExpenseRecBean;
import vCampus.client.ClientMain;
import vCampus.server.ServerMain;
import vCampus.server.dao.AccountKeyDao;
import vCampus.server.dao.BankAccountDao;
import vCampus.server.dao.EpsRecsDao;
import vCampus.server.dao.model.BankAccount;
import vCampus.server.dao.model.ExpenseRec;
import vCampus.utility.Crypto;
import vCampus.utility.Token;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class Bank {
	static {

		ServerMain.addRequestListener("bank/refresh", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();

				BankAccount ba = BankAccountDao.queryAccount((int)transferData.get("userId"));
				data.put("account", ba.toBean());
				
				data.put("code", 200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("bank/newrec", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();

				int userId = (int) transferData.get("userId");
				ExpenseRecBean newRec = (ExpenseRecBean) msg.getData().get("rec");
				newRec.setDate(new Date());
				//BankAccountDao.queryBalance(userId);
				
				int rowCnt = BankAccountDao.addBalance(userId, -newRec.getFigure());
				
				if (rowCnt>0) {
					data.put("code", 200);
					EpsRecsDao.addRec(ExpenseRec.createModel(newRec, (int)transferData.get("userId")));
					data.put("rec", newRec);
					
				}else {
					data.put("code", 999);
				}
				
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}
		});
		
		ServerMain.addRequestListener("bank/getrec", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();

				int userId = (int) transferData.get("userId");
				
				List<ExpenseRec> recs = EpsRecsDao.getRec(userId);
				
				List< ArrayList<String> > strform = new ArrayList< ArrayList<String> >();
				
				for (ExpenseRec ex: recs) {
					ArrayList<String> rec = new ArrayList<>();
					rec.add(String.valueOf(ex.getId()));
					rec.add(String.valueOf(ex.getPersonID()));
					int p = ex.getFigure();
					String attach = "-";
					if (p < 0) {
						p = -p;
						attach = "+";
					}
					rec.add(attach + " ￥" + p/100 + "." + (p%100)/10 + p%10);
					rec.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ex.getDate()));
					
					rec.add(ex.getSource());
					rec.add(ex.getDetails());
					strform.add(rec);
				}

				data.put("code", 200);
				data.put("reclist", strform);				
				
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}
		});

		ServerMain.addRequestListener("bank/pop", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				List<ExpenseRec> recs = EpsRecsDao.getAllRecs();
				
				List< ArrayList<String> > strform = new ArrayList< ArrayList<String> >();
				
				for (ExpenseRec ex: recs) {
					ArrayList<String> rec = new ArrayList<>();
					rec.add(String.valueOf(ex.getId()));
					rec.add(String.valueOf(ex.getPersonID()));
					int p = ex.getFigure();
					String attach = "-";
					if (p < 0) {
						p = -p;
						attach = "+";
					}
					rec.add(attach + " ￥" + p/100 + "." + (p%100)/10 + p%10);
					rec.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ex.getDate()));
					
					rec.add(ex.getSource());
					rec.add(ex.getDetails());
					strform.add(rec);
				}

				data.put("code", 200);
				data.put("reclist", strform);				
				
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}
		});
		
		ServerMain.addRequestListener("bank/reclaim", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				UUID uuid = (UUID) transferData.get("uuid");
				ServerMain.setBankerUUID(uuid, ((Token) msg.getData().get("token")).getExpire());
				return true;
			}
		});

		ServerMain.addRequestListener("bank/recharge", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				Map<String, Object> data = new HashMap<String, Object>();
				final int userId = (int)transferData.get("userId");
				String pwd = Crypto.passwordEncrypt((String)msg.getData().get("pwd"), userId);
				String encryptedPwd = AccountKeyDao.queryPassword(userId);
				if (!encryptedPwd.equals(pwd)) {
					data.put("code", 403);
					((ResponseSender) transferData.get("sender")).send(data);
					return true;
				}
				int count = -1;
				String strCnt = (String) msg.getData().get("count");
				if (Pattern.compile("^[\\d\\.]*$").matcher(strCnt).matches()) {
					count=(int)Math.round(Double.valueOf(strCnt)*100);
				}
				if (count < 500 || count > 50000) {
					data.put("code", 404);
					((ResponseSender) transferData.get("sender")).send(data);
					return true;
				}
				UUID uuid = ServerMain.getBankerUUID();
				if (uuid == null || !ServerMain.getUuidSocketMap().containsKey(uuid)) {
					data.put("code", 406);
					((ResponseSender) transferData.get("sender")).send(data);
					return true;
				}
				String userName = AccountKeyDao.queryUserName(userId);
				Map<String, Object> data2 = new HashMap<String, Object>();
				data2.put("name", userName);
				data2.put("count", count);
				final int fcount = count;
				final ResponseSender sender = (ResponseSender) transferData.get("sender");
				ServerMain.getUuidSocketMap().get(uuid).sendMsgWithCallBack(new Message("banker/recharge", data2), new LoopOnceAdapter() {
					@Override
					public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
						Map<String, Object> data = new HashMap<String, Object>();
						int code = (int) msg.getData().get("code");
						if (code == 200) {
							int rowCnt = BankAccountDao.addBalance(userId, fcount);
							if (rowCnt>0) {
								data.put("code", 200);
								ExpenseRecBean bean = new ExpenseRecBean();
								bean.setId(-1);
								bean.setFigure(-fcount);
								bean.setDate(new Date());
								bean.setSource("Bank");
								bean.setDetails("<div style=\"margin:20px\"><p>银行充值 总金额：￥" + (fcount/100) + "." + (fcount%100)/10 + fcount%10 + "</p><p>经过银行职员核准！</p></div>");
								EpsRecsDao.addRec(ExpenseRec.createModel(bean, userId));
							}else {
								data.put("code", 407);
							}
							sender.send(data);
						}else if (code == 405) {
							data.put("code", 405);
							sender.send(data);
						}
						return true;
					}
				});
				return true;
			}
		});
		
	
	}
}
