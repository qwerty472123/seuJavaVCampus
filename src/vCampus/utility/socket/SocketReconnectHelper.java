package vCampus.utility.socket;

import java.net.Socket;

import javax.swing.JOptionPane;

import vCampus.client.ClientMain;
import vCampus.utility.Config;

class SocketReconnectRunnable implements Runnable {
	public static final int GENERAL_SLEEP = Config.get().getJSONObject("reconnect").getIntValue("generalInterval");
	public static final int LITTLE_SLEEP = Config.get().getJSONObject("reconnect").getIntValue("littleInterval");
	public static final int ACCPETABLE_SLEEP = Config.get().getJSONObject("reconnect").getIntValue("acceptableInterval");
	public static final int TOO_LONG_SLEEP = Config.get().getJSONObject("reconnect").getIntValue("tooLongInterval");
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
				setSleep(2 * getSleep());
				if (getSleep() >= TOO_LONG_SLEEP) {
					Object[] options = { "重连", "关闭" };
					if (0 == JOptionPane.showOptionDialog(null, "服务器连接失败，请检查网络连接状态。", "vCampus",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
							null, options, options[0])) {
						setSleep(LITTLE_SLEEP);
					} else System.exit(0);
				}
				Config.log("Failed, wait another " + getSleep());
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
