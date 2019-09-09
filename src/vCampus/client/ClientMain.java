package vCampus.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.alibaba.fastjson.JSONObject;

import vCampus.client.view.TopFrame;
import vCampus.utility.Config;
import vCampus.utility.loop.Loop;
import vCampus.utility.loop.LoopListener;
import vCampus.utility.loop.LoopResponseListener;
import vCampus.utility.socket.SSLHelper;
import vCampus.utility.socket.SocketLoop;
import vCampus.utility.loop.Message;

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
	
	public static Socket getSocket() {
		try {
			JSONObject serverCfg = Config.get().getJSONObject("server");
			InetAddress serverAddr = InetAddress.getByName(serverCfg.getString("host"));
			int port = serverCfg.getIntValue("port");
			Config.log("Connect to " + serverAddr.toString() + ":" + port);
			try {
				return SSLHelper.getClientSocket(serverAddr, port);
			} catch (IOException e) {
				return null;
			}
		} catch (UnknownHostException e) {
			return null;
		}
	}
	
	private static void initSocket() {
		getTempData().put("uuid", UUID.randomUUID());
		getTempData().put("list", new ConcurrentLinkedDeque<Message>());
		socketLoop = new SocketLoop(null, getSocket(), true);
		socketLoop.addListener(Loop.RESPONSE_TYPE, responseListener);
		socketLoop.start();
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
