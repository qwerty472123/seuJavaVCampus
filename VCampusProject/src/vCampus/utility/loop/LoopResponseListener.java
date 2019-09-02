package vCampus.utility.loop;

import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class LoopResponseListener extends LoopAlwaysAdapter {
	private Map<Integer, Deque<LoopListener> > listenerMap = new ConcurrentHashMap<Integer, Deque<LoopListener> >();
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
	@Override
	public boolean resolveMessage(Message msg, Map<String, Object> transferData) throws Exception {
		Deque<LoopListener> listeners = listenerMap.get(msg.getrId());
		return resolveListeners(listeners, msg, transferData);
	}
	
	public void addListener(int rId, LoopListener listener) {
		Deque<LoopListener> listeners = listenerMap.get(rId);
		if (listeners == null) {
			listeners = new ConcurrentLinkedDeque<LoopListener>();
			listenerMap.put(rId, listeners);
		}
		listeners.add(listener);
	}
}
