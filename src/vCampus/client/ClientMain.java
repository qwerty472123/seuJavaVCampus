package vCampus.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import vCampus.client.view.TopFrame;
import vCampus.utility.Config;
import vCampus.utility.loop.Loop;
import vCampus.utility.loop.LoopResponseListener;
import vCampus.utility.socket.SocketLoop;

public class ClientMain {
	
	private static TopFrame topFrame = new TopFrame();
	private static SocketLoop socketLoop;
	private static Map<String, Object> tempData = new HashMap<String, Object>();
	private static LoopResponseListener responseListener = new LoopResponseListener();
	
	static {
		Config.init("Client");
	}
	
	public static void main(String[] args) {
		initTopFrame();
		initSocket();
	}
	
	private static void initTopFrame() {
		topFrame.showLoginFrame();
	}
	
	private static void initSocket() {
		try {
			JSONObject serverCfg = Config.get().getJSONObject("server");
			InetAddress serverAddr = InetAddress.getByName(serverCfg.getString("host"));
			int port = serverCfg.getIntValue("port");
			Config.log("Connect to " + serverAddr.toString() + ":" + port);
			try {
				Socket socket = new Socket(serverAddr, port);
				socketLoop = new SocketLoop(null, socket, true);
				socketLoop.addListener(Loop.RESPONSE_TYPE, responseListener);
				socketLoop.start();
			} catch (IOException e) {
				Config.log(e);
				System.exit(-1);
			}
		} catch (UnknownHostException e) {
			Config.log(e);
			System.exit(-1);
		}
	}
	
	public static TopFrame getTopFrame() {
		return topFrame;
	}

	public static SocketLoop getSocketLoop() {
		return socketLoop;
	}

	public static Map<String, Object> getTempData() {
		return tempData;
	}
	
	public static LoopResponseListener getResponseListener() {
		return responseListener;
	}
	
}
