package vCampus.utility.socket;

import java.util.Map;
import java.util.UUID;

import vCampus.server.ServerMain;
import vCampus.utility.loop.Loop;
import vCampus.utility.loop.Message;

public class ResponseSender {
	private SocketLoop socketLoop = null;
	private UUID uuid = null;
	private int id;
	public ResponseSender(SocketLoop socketLoop, int id) {
		setSocketLoop(socketLoop);
		setId(id);
	}
	public ResponseSender(UUID uuid, int id) {
		setUuid(uuid);
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
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public void send(Map<String, Object> data) {
		if (getUuid() != null) {
			ServerMain.getUuidSocketMap().get(getUuid()).sendMsg(new Message(Loop.RESPONSE_TYPE, data, getId()));
		} else if (getSocketLoop() != null) {
			getSocketLoop().sendMsg(new Message(Loop.RESPONSE_TYPE, data, getId()));
		}
	}
}
