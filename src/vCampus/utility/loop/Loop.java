package vCampus.utility.loop;

import java.util.*;
import java.util.concurrent.*;

import vCampus.utility.Config;

public abstract class Loop extends Thread {
	public static final String GENERAL_TYPE = "general";
	public static final String RESPONSE_TYPE = "response";
	private Map<String, Deque<LoopListener> > listenerMap = new ConcurrentHashMap<String, Deque<LoopListener> >();
	private Map<String, Deque<LoopListener> > sharedListenerMap = null;
	public Loop() {}
	public Loop(Map<String, Deque<LoopListener> > sharedListenerMap) {
		setSharedListenerMap(sharedListenerMap);
	}
	private boolean resolveListeners(Deque<LoopListener> listeners, Message msg, Map<String, Object> transferData) throws Exception {
		if (listeners != null) {
			Iterator<LoopListener> iterator = listeners.iterator();
	        while (iterator.hasNext()) {
	        	LoopListener listener = iterator.next();
	        	boolean interrupt = false;
	        	if (listener.isAvailiable()) {
	        		interrupt = listener.resolveMessage(msg, transferData);
	        	}
	        	if (listener.isRemovable()) {
	        		iterator.remove();
	        	}
	        	if (interrupt) return true;
	        }
		}
		return false;
	}
	private void resolveMessage(Message msg, Map<String, Object> transferData) throws Exception {
		Deque<LoopListener> listeners;
		if (sharedListenerMap != null) {
			listeners = sharedListenerMap.get(GENERAL_TYPE);
			if (resolveListeners(listeners, msg, transferData)) return;
		}
		listeners = listenerMap.get(GENERAL_TYPE);
		if (resolveListeners(listeners, msg, transferData)) return;
		String[] msgSplit = msg.getType().split("\\/");
		String curName = "";
		for (int i = 0; i < msgSplit.length; i++) {
			curName += msgSplit[i];
			Config.log("Trigger " + curName);
			if (sharedListenerMap != null) {
				listeners = sharedListenerMap.get(curName);
				if (resolveListeners(listeners, msg, transferData)) return;
			}
			listeners = listenerMap.get(curName);
			if (resolveListeners(listeners, msg, transferData)) return;
			curName += "/";
		}
	}
	abstract protected Message getMsg() throws Exception;
	abstract protected Map<String, Object> initTransferData(Message msg);
	protected void errorHandler(Exception e, Message msg, Map<String, Object> transferData) {
	}
	public void run() {
		while (true) {
			try {
				Message msg = getMsg();
				if (msg == null) return;
				Map<String, Object> transferData = initTransferData(msg);
				try {
					resolveMessage(msg, transferData);
				} catch (Exception e) {
					Config.log(e);
					errorHandler(e, msg, transferData);
				}
			} catch (Exception e) {
				Config.log(e);
			}
		}
	}
	public void addListener(String type, LoopListener listener) {
		Deque<LoopListener> listeners = listenerMap.get(type);
		if (listeners == null) {
			listeners = new ConcurrentLinkedDeque<LoopListener>();
			listenerMap.put(type, listeners);
		}
		listeners.add(listener);
	}
	public Map<String, Deque<LoopListener>> getSharedListenerMap() {
		return sharedListenerMap;
	}
	public void setSharedListenerMap(Map<String, Deque<LoopListener>> sharedListenerMap) {
		this.sharedListenerMap = sharedListenerMap;
	}
}
