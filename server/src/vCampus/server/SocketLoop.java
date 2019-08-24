package vCampus.server;

import java.net.*;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import vCampus.utility.message.Message;
import vCampus.utility.message.MessageListener;
import vCampus.utility.message.MessageLoop;

public class SocketLoop extends Thread {
	private MessageLoop socketMessageLoop;
	private Socket socket;
	SocketLoop(Socket socket) {
		setSocket(socket);
	}
	@Override
	public void run() {
		while (true) {
			try {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				while (true) {
					in.readObject();
					Thread.sleep(60);
					
				}
				out.writeObject(new Message());
				
				for (int i=0; i<100; ++i) {
					try{
						Thread.sleep(1000);
					}catch(Exception e) {
						return;
					}
					out.writeObject(new Message());
					out.flush();
					System.out.println("Sended One.. :");
				}
			} catch (InterruptedException e) {
			}
		}
	}
	public synchronized void addListener(String type, MessageListener listener) {
		socketMessageLoop.addListener(type, listener);
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
