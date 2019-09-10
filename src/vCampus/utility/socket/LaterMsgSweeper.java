package vCampus.utility.socket;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import vCampus.server.ServerMain;
import vCampus.utility.Config;
import vCampus.utility.Token;

class DateUUIDPair {
	private Date date;
	private UUID uuid;
	public DateUUIDPair(Date date, UUID uuid) {
		super();
		setDate(date);
		setUuid(uuid);
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}

public class LaterMsgSweeper extends Thread {
	public static final int SWEEP_INTERVAL = Config.get().getIntValue("sweepTime");
	public static ConcurrentLinkedDeque<DateUUIDPair> dateUUIDQueue = new ConcurrentLinkedDeque<DateUUIDPair>();
	public static Map<UUID, Iterator<DateUUIDPair>> UUIDMap = new ConcurrentHashMap<UUID, Iterator<DateUUIDPair>>();
	private static Object dequeAddLock = new Object();
	static {
		new LaterMsgSweeper().start();
	}
	public void run() {
		while (true) {
			try {
				Thread.sleep(SWEEP_INTERVAL);
			} catch (InterruptedException e) {
			}
			Date cur = new Date();
			Iterator<DateUUIDPair> iter = dateUUIDQueue.iterator();
			while(iter.hasNext()) {
				DateUUIDPair val = iter.next();
				if (cur.after(val.getDate())) {
					ServerMain.getLaterSenderMap().remove(val.getUuid());
					UUIDMap.remove(val.getUuid());
					try {
						iter.remove();
					} catch (IllegalStateException err) {
					}
				} else break;
			}
		}
	}
	public static int getSweepInterval() {
		return SWEEP_INTERVAL;
	}
	public static void notify(UUID uuid) {
		if (UUIDMap.containsKey(uuid)) {
			try {
				UUIDMap.get(uuid).remove();
			} catch (IllegalStateException err) {
			}
			UUIDMap.remove(uuid);
		}
		Date cur = new Date();
		cur.setTime(cur.getTime() + Token.TOKEN_EXPIRE);
		Iterator<DateUUIDPair> iter = null;
		synchronized (dequeAddLock) {
			dateUUIDQueue.add(new DateUUIDPair(cur, uuid));
			iter = dateUUIDQueue.descendingIterator();
			try {
				iter.next();
			} catch (NoSuchElementException err) {
				iter = null;
			}
		}
		if (iter != null) UUIDMap.put(uuid, iter);
		Config.log("Log clean time: " + cur.toString() + "|" + uuid.toString());
	}
}
