package vCampus.utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.RandomStringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

class LogWriter extends Thread {
	private BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

	public void run() {
		int type = Config.get().getJSONObject("log").getIntValue("type");
		OutputStream logFileStream = null;
		if (type == 0) {
			try {
				logFileStream = new FileOutputStream(Config.get().getJSONObject("log").getString("file"), true);
			} catch (FileNotFoundException e) {
				type = 1;
			}
		}
		while (true) {
			try {
				String str = queue.take();
				if (type == 0) {
					try {
						logFileStream.write(str.getBytes());
						logFileStream.write(10);
					} catch (IOException e) {
						try {
							logFileStream.close();
						} catch (IOException e1) {
						}
						type = 1;
						System.out.println(str);
					}
				} else if (type == 1 || true) {
					System.out.println(str);
				}
			} catch (InterruptedException e) {
			}
		}
	}
	
	public void add(String str) {
		try {
			queue.put(str);
		} catch (InterruptedException e) {
		}
	}
}

public class Config {
	private static String type = "";
	
	private static LogWriter logWriter = new LogWriter();

	private static JSONObject obj;
	
	private static void save() {
		FileOperate.writeFile("config" + getType() + ".json", JSONObject.toJSONString(obj, true));
	}

	public static void init(String type) {
		setType(type);
		String jsonText = FileOperate.readFile("config" + getType() + ".json");
		if (jsonText == null || jsonText == "") {
			obj = generateDefaultConfig();
			save();
		} else {
			obj = JSON.parseObject(jsonText);
		}
		logWriter.start();
	}

	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		Config.type = type;
	}
	
	private static JSONObject generateDefaultConfig() {
		JSONObject obj = new JSONObject();
		JSONObject logObj = new JSONObject();
		logObj.put("type", 1);
		obj.put("log", logObj);
		if (getType().equals("Server")) {
			obj.put("port", 8880);
			obj.put("tokenSalt", RandomStringUtils.randomAlphanumeric(32));
			obj.put("pwdSalt", RandomStringUtils.randomAlphanumeric(32));
			JSONObject dbObj = new JSONObject();
			dbObj.put("url", "jdbc:ucanaccess://test.accdb");
			dbObj.put("timeout", 1000);
			dbObj.put("count", 50);
			obj.put("db", dbObj);
		} else if (getType().equals("Client")) {
			JSONArray logins = new JSONArray();
			obj.put("login", logins);
			JSONObject serverObj = new JSONObject();
			serverObj.put("host", "127.0.0.1"); // for debug only
			serverObj.put("port", 8880);
			obj.put("server", serverObj);
			/* enable when debug done!
			logObj.put("type", 1);
			logObj.put("name", "client.log");
			obj.put("log", logObj);
			 */
			
		}
		return obj;
	}

	public static void log(String info) {
		logWriter.add(info);
	}

	public static void log(Exception info) {
		log(info.toString());
		StackTraceElement[] stackInfo = info.getStackTrace();
		log("Stack:");
		for(int i = 0; i < stackInfo.length; i++) {
			log(stackInfo[i].toString());
		}
		log("End.");
	}

	public static JSONObject get() {
		return obj;
	}
	
	public static void main(String[] args) {
		init("");
		System.out.println("Unit test for config.");
		System.out.println(Config.get().getJSONObject("log").getIntValue("type"));
		Config.get().put("o2", "1");
		save();
		System.out.println(Config.get().getString("o2"));
		log("pre...");
		try {
			throw new Exception("err");
		} catch (Exception err) {
			log(err);
		}
	}
}
