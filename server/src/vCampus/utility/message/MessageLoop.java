package vCampus.utility.message;

import java.util.*;
import java.util.concurrent.*;

public class MessageLoop extends Thread {
	private Map<String, List<MessageListener> > listenerMap = new HashMap<String, List<MessageListener> >();
	private BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
	@Override
	public void run() {
		while (true) {
			try {
				Message msg = queue.take();
				List<MessageListener> listeners = listenerMap.get(msg.getType());
				if (listeners != null) {
					Iterator<MessageListener> iterator = listeners.iterator();
			        while (iterator.hasNext()) {
			        	MessageListener listener = iterator.next();
			        	boolean interrupt = false;
			        	if (listener.isAvailiable()) {
			        		interrupt = listener.resolveMessage(msg);
			        	}
			        	if (listener.isRemovable()) iterator.remove();
			        	if (interrupt) break;
			        }
				}
			} catch (InterruptedException e) {
			}
		}
	}
	public synchronized boolean trigger(Message msg) {
		try {
			queue.put(msg);
		} catch (InterruptedException e) {
			return false;
		}
		return true;
	}
	public synchronized void addListener(String type, MessageListener listener) {
		List<MessageListener> listeners = listenerMap.get(type);
		if (listeners == null) {
			listeners = new LinkedList<MessageListener>();
			listenerMap.put(type, listeners);
		}
		listeners.add(listener);
	}
}
