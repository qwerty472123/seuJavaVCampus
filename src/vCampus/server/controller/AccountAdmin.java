package vCampus.server.controller;

import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import vCampus.bean.AccountKeyBean;
import vCampus.server.ServerMain;
import vCampus.server.dao.*;
import vCampus.server.dao.StudentDao;
import vCampus.server.dao.model.*;
import vCampus.server.dao.model.Student;
import vCampus.utility.Token;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class AccountAdmin {

	static {
		ServerMain.addRequestListener("accountadmin/SearchAccount", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                String searchName = (String)msg.getData().get("searchName"); 
                Map<String, Object> data = new HashMap<String, Object>();
                try {
                	List<AccountKey> list = AccountKeyDao.searchForName(searchName);
                	
                	ArrayList<AccountKeyBean> beanList=new ArrayList<AccountKeyBean>();
                	
                	for(AccountKey a:list) {
                		beanList.add(a.toBean());
                	}
                	
					data.put("noSuchAccount", list.isEmpty());
					data.put("list", beanList);
					data.put("success", true);
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					Config.log(e);
					data.put("success", false);
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("accountadmin/AddAccount", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                int addId = (int)msg.getData().get("addId"); 
                String addName = (String) msg.getData().get("addName");
                String addAuthority = (String) msg.getData().get("addAuthority");
                Map<String, Object> data = new HashMap<String, Object>();
                try {
                	AccountKey a = new AccountKey();
                	a.init();
                	a.setUserId(addId);
                	a.setUserName(addName);
                	a.setAuthority(addAuthority);
                	AccountKeyDao.addAccount(a);
					data.put("success", true);
				} catch (Exception e) {
					Config.log(e);
					data.put("success", false);
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("accountadmin/ChangeName", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                int selectedId = (int)msg.getData().get("selectedId"); 
                String newName = (String) msg.getData().get("newName");
                Map<String, Object> data = new HashMap<String, Object>();
                try {
                	AccountKey a = new AccountKey();
                	a.setUserId(selectedId);
                	a.setUserName(newName);
                	a.setPassword(AccountKeyDao.queryPassword(selectedId));
                	a.setAuthority(AccountKeyDao.queryAuthority(selectedId));
                	AccountKeyDao.updateAccountKey(a);
					data.put("success", true);
				} catch (Exception e) {
					Config.log(e);
					data.put("success", false);
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("accountadmin/DeleteAccount", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                int deleteId = (int)msg.getData().get("deleteId"); 
                Map<String, Object> data = new HashMap<String, Object>();
                try {
                	AccountKeyDao.deleteAccount(deleteId);
					data.put("success", true);
				} catch (Exception e) {
					Config.log(e);
					data.put("success", false);
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("accountadmin/RefreshStuInfoUpdate", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) { 
                Map<String, Object> data = new HashMap<String, Object>();
        		String path = "./photo2Change";		
        		File file = new File(path);		
        		File[] fs = file.listFiles();
        		String[] list;
        		if(fs.length > 0){
        			list = new String[fs.length];
        			for(int i = 0;i < fs.length;i++){
        				list[i] = fs[i].getName();
        				Config.log(fs[i].getName());
        			}
        		}
        		else list = null;
        		data.put("list",list);                
                ArrayList<Student> List = new ArrayList<Student>();
                ArrayList<ArrayList<String>> updateList = new ArrayList<ArrayList<String>>();
                ArrayList<String> tmp = new ArrayList<String>();
                try {
                	List = (ArrayList<Student>) UpdateDao.queryAllUpdates();
                	
                	for(Student s: List){
                		tmp.add(Integer.toString(s.getId()));
                		tmp.add(s.getName());
                		if(s.getSex()==0)tmp.add("男");
                		else tmp.add("女");
                		tmp.add(s.getBirthday().toString());
                		tmp.add(Integer.toString(s.getGrade()));
                		tmp.add(Integer.toString(s.getStuclass()));
                		tmp.add(s.getFaculty());
                		tmp.add(s.getEmail());
                		tmp.add(s.getPhone());
                		tmp.add(s.getQq());
                		updateList.add(tmp);
                		
                	}
                	data.put("table", updateList);
                	data.put("success", true);
				} catch (SQLException e) {
					Config.log(e);
					data.put("success", false);
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("accountadmin/StuInfoUpdate", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                Map<String, Object> data = new HashMap<String, Object>();
                int stuId = (int)msg.getData().get("stuId"); 
                String status = (String)msg.getData().get("status");
                UpdateDao.ChangeStatus(status, stuId);
                data.put("success", true);
                if(status.equals("修改成功")){                	
                	try {
                		Student s = UpdateDao.getStu(stuId);
						StudentDao.update(s);
					} catch (SQLException e) {
						Config.log(e);
						data.put("success", false);
					}
                }
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("accountadmin/getPhoto", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                Map<String, Object> data = new HashMap<String, Object>();
                String path = (String)msg.getData().get("path"); 
                File file = new File("./photo2Change/"+path);
                if(file.exists()){
                	ImageIcon image = new ImageIcon("./photo2Change/"+path);
                	image.setImage(image.getImage().getScaledInstance(50, 70,Image.SCALE_DEFAULT ));
                	data.put("photo", image);
                	data.put("success", true);
                }
                //if(image == null)data.put("success", false);
                else{
                	data.put("success", false);
                }
                
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("accountadmin/changePhoto", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                Map<String, Object> data = new HashMap<String, Object>();
                String path = (String)msg.getData().get("path"); 
                boolean judge = (boolean)msg.getData().get("judge"); 

                Config.log("./photo2Change/"+path);
                File file = new File("./photo2Change/"+path);
                if(file.exists()){   
                		ImageIcon image = new ImageIcon("./photo2Change/"+path);
                		if(judge) data.put("photo", image);              	
                		data.put("success", true);               	
                		//file.delete();
                }
                else data.put("success", false);
                
				((ResponseSender) transferData.get("sender")).send(data);
				//file.delete();
				return true;
			}			
		});
		
		ServerMain.addRequestListener("accountadmin/deltePhoto", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {  
                Map<String, Object> data = new HashMap<String, Object>();
                String path = (String)msg.getData().get("path"); 
                Config.log("./photo2Change/"+path);
                File file = new File("./photo2Change/"+path);
                if(file.exists()){                	
                	file.delete();
                	data.put("success", true);
                }
                else data.put("success", false);               
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
	}
			
}
