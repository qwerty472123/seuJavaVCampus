package vCampus.server;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import vCampus.utility.Config;
import vCampus.utility.loop.Loop;
import vCampus.utility.loop.LoopListener;
import vCampus.utility.loop.LoopResponseListener;
import vCampus.utility.socket.ServerSocketLoop;

public class ServerMain {

	private static Map<String, Deque<LoopListener> > serverSharedListenerMap = new ConcurrentHashMap<String, Deque<LoopListener> >();
	private static LoopResponseListener responseListener = new LoopResponseListener();
	
	public static LoopResponseListener getResponseListener() {
		return responseListener;
	}

	public static Map<String, Deque<LoopListener> > getServerSharedListenerMap() {
		return serverSharedListenerMap;
	}

	static {
		Config.init("Server");
		try {
			Class.forName("vCampus.server.dao.driver.ConnectionManager");
		} catch (ClassNotFoundException e) {
			Config.log(e);
		}
	}
	
	public static void addRequestListener(String type, LoopListener listener) {
		Deque<LoopListener> listeners = serverSharedListenerMap.get(type);
		if (listeners == null) {
			listeners = new ConcurrentLinkedDeque<LoopListener>();
			serverSharedListenerMap.put(type, listeners);
		}
		listeners.add(listener);
	}
	
	public static void initResponseListener() {
		addRequestListener(Loop.RESPONSE_TYPE, responseListener);
	}
	
	private static void initRequestListener(String name) {
		try {
			Class.forName("vCampus.server.controller." + name);
		} catch (ClassNotFoundException e) {
			Config.log(e);
			System.exit(-1);
		}
	}
	
	private static void initRequestListener() {
		initRequestListener("Auth");
	}
	
	public static void main(String[] args) {
		initRequestListener();
		initResponseListener();
		new ServerSocketLoop().start();
	}

}