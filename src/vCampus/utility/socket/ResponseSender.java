package vCampus.utility.socket;

import java.util.Map;

import vCampus.utility.loop.Loop;
import vCampus.utility.loop.Message;

public class ResponseSender {
	private SocketLoop socketLoop;
	private int id;
	public ResponseSender(SocketLoop socketLoop, int id) {
		setSocketLoop(socketLoop);
		setId(id);
	}
	private SocketLoop getSocketLoop() {
		return socketLoop;
	}
	private void setSocketLoop(SocketLoop socketLoop) {
		this.socketLoop = socketLoop;
	}
	private int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public void send(Map<String, Object> data) {
		getSocketLoop().sendMsg(new Message(Loop.RESPONSE_TYPE, data, getId()));
	}
}
