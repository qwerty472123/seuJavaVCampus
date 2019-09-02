package vCampus.utility.socket;

import java.io.IOException;
import java.net.ServerSocket;

import vCampus.server.ServerMain;
import vCampus.utility.Config;

public class ServerSocketLoop extends Thread {

	private static int port;
	private ServerSocket serverSocket;
	
	static {
		port = Config.get().getIntValue("port");
	}

	public ServerSocketLoop() {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			Config.log(e);
			System.exit(-1);
		}
		Config.log("Server Port: " + port);
	}
	
	public void run() {
		while (true) {
			try {
				new SocketLoop(ServerMain.getServerSharedListenerMap(), serverSocket.accept(), false).start();
			} catch (IOException e) {
				Config.log(e);
			}
		}	
	}
}
