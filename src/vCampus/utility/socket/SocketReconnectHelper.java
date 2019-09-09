package vCampus.utility.socket;

import java.net.Socket;

import vCampus.client.ClientMain;
import vCampus.utility.Config;

class SocketReconnectRunnable implements Runnable {
	public static final int GENERAL_SLEEP = 1000;
	public static final int LITTLE_SLEEP = 1000;
	public static final int ACCPETABLE_SLEEP = 1000;
	private int sleep = GENERAL_SLEEP;
	
	public int getSleep() {
		if (sleep < LITTLE_SLEEP) return LITTLE_SLEEP;
		return sleep;
	}
	public void setSleep(int sleep) {
		this.sleep = sleep;
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(getSleep());
			} catch (InterruptedException e) {
			} finally {
				Config.log("Try to reconnect...");
				if (SocketReconnectHelper.tryReconnect()) break;
				Config.log("Failed, wait " + 2 * getSleep());
				setSleep(2 * getSleep());
			}
		}
	}
}

public class SocketReconnectHelper {
	private static SocketReconnectRunnable runnable = null;
	private static Thread thread = null;
	public static boolean tryReconnect() {
		Socket socket = ClientMain.getSocket();
		if (socket == null) return false;
		return ClientMain.getSocketLoop().setSocket(socket);
	}
	public static void triggerReconnect(boolean hard) {
		if (thread == null || !thread.isAlive()) {
			runnable = new SocketReconnectRunnable();
			thread = new Thread(runnable);
			if (hard) runnable.setSleep(SocketReconnectRunnable.LITTLE_SLEEP); 
			thread.start();
			return;
		}
		if (hard) {
			runnable.setSleep(SocketReconnectRunnable.LITTLE_SLEEP);
			if (runnable.getSleep() > SocketReconnectRunnable.ACCPETABLE_SLEEP) thread.interrupt();
		}
	}
}
