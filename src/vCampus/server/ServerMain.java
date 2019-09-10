package vCampus.server;

import java.util.Date;
import java.util.Deque;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import vCampus.utility.Config;
import vCampus.utility.loop.Loop;
import vCampus.utility.loop.LoopListener;
import vCampus.utility.loop.LoopResponseListener;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.LaterMsgSweeper;
import vCampus.utility.socket.ServerSocketLoop;
import vCampus.utility.socket.SocketLoop;

public class ServerMain {
	
	static {
		Config.init("Server");
		try {
			Class.forName("vCampus.server.dao.driver.ConnectionManager");
		} catch (ClassNotFoundException e) {
			Config.log(e);
		}
	}

	private static Map<String, Deque<LoopListener> > serverSharedListenerMap = new ConcurrentHashMap<String, Deque<LoopListener> >();
	private static LoopResponseListener responseListener = new LoopResponseListener();
	private static Map<UUID, Deque<Message> > laterSenderMap = new ConcurrentHashMap<UUID, Deque<Message> >();
	private static Map<UUID, SocketLoop > uuidSocketMap = new ConcurrentHashMap<UUID, SocketLoop >();
	private static UUID bankerUUID = null;
	private static Date bankerExpire = null;
	
	public static UUID getBankerUUID() {
		if (bankerExpire != null && new Date().after(bankerExpire)) {
			bankerUUID = null;
			bankerExpire = null;
		}
		return bankerUUID;
	}

	public static void setBankerUUID(UUID bankerUUID, Date expire) {
		if (getBankerUUID() != null) {
			UUID oldUUID = getBankerUUID();
			if (oldUUID.equals(bankerUUID)) return;
			if (getUuidSocketMap().containsKey(oldUUID)) {
				getUuidSocketMap().get(oldUUID).sendMsg(new Message("banker/dispose"));
			} else {
				if (!ServerMain.getLaterSenderMap().containsKey(oldUUID)) {
					ServerMain.getLaterSenderMap().put(oldUUID, new ConcurrentLinkedDeque<Message>());
				}
				ServerMain.getLaterSenderMap().get(oldUUID).add(new Message("banker/dispose"));
				LaterMsgSweeper.notify(oldUUID);
			}
		}
		ServerMain.bankerExpire = expire;
		ServerMain.bankerUUID = bankerUUID;
	}

	public static LoopResponseListener getResponseListener() {
		return responseListener;
	}

	public static Map<UUID, SocketLoop> getUuidSocketMap() {
		return uuidSocketMap;
	}

	public static Map<String, Deque<LoopListener> > getServerSharedListenerMap() {
		return serverSharedListenerMap;
	}
	
	public static Map<UUID, Deque<Message> > getLaterSenderMap() {
		return laterSenderMap;
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
		initRequestListener("PersonInfo");
		initRequestListener("Library");
		initRequestListener("ShopRobot");
		initRequestListener("Bank");
		initRequestListener("NewsTransponder");
		initRequestListener("AccountAdmin");
		initRequestListener("TeacherGrade");
		initRequestListener("DoctorApt");
		initRequestListener("Lesson");
		initRequestListener("AptAdmin");
		initRequestListener("DoctorInfo");
	}
	
	public static void main(String[] args) {
		initRequestListener();
		initResponseListener();
		new ServerSocketLoop().start();
	}

}
