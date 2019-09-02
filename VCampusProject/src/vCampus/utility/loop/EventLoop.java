package vCampus.utility.loop;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class EventLoop extends Loop {
	private BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
	public boolean trigger(Message msg) {
		try {
			queue.put(msg);
		} catch (InterruptedException e) {
			return false;
		}
		return true;
	}
	@Override
	protected Message getMsg() throws Exception {
		return queue.take();
	}
	@Override
	protected Map<String, Object> initTransferData(Message msg) {
		return new HashMap<String, Object>();
	}
}
