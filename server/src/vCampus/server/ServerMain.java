package vCampus.server;

import java.io.*;
import java.net.*;

import vCampus.utility.Config;
import vCampus.utility.message.*;

public class ServerMain {
    public static void main(String args[]) {
    	try {
    		@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(8888);
			while (true) {
				Socket socket = serverSocket.accept();
				Config.log(socket.getInetAddress().toString()+" 已连接到服务器.");
				new SocketLoop(socket).start();
			}
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
    }
}
