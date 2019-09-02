package vCampus.utility.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import vCampus.client.ClientMain;
import vCampus.server.ServerMain;
import vCampus.utility.Config;
import vCampus.utility.loop.Loop;
import vCampus.utility.loop.LoopListener;
import vCampus.utility.loop.Message;

public class SocketLoop extends Loop {
	
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public SocketLoop(Map<String, Deque<LoopListener> > sharedListenerMap, Socket _socket, boolean swap) {
		super(sharedListenerMap);
		socket = _socket;
		try {
			if (swap) {
				in = new ObjectInputStream(socket.getInputStream());
				out = new ObjectOutputStream(socket.getOutputStream());
			} else {
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
			}
		} catch (Exception e) {
			in = null;
			out = null;
			Config.log(e);
		}
	}

	@Override
	protected Message getMsg() throws Exception {
		if (in == null) return null;
		Message msg = null;
		try {
			msg = (Message)in.readObject();
		} catch (IOException e) {
			Config.log(e);
			in = null;
			out = null;
		}
		return msg;
	}

	@Override
	protected Map<String, Object> initTransferData(Message msg) {
		Map<String, Object> transferData = new HashMap<String, Object>();
		transferData.put("sender", new ResponseSender(this, msg.getuId()));
		return transferData;
	}

	@Override
	protected void errorHandler(Exception e, Message msg, Map<String, Object> transferData) {
		if (Config.getType().equals("Server")) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("code", 401);
			((ResponseSender) transferData.get("sender")).send(data);
		}
	}
	
	public void sendMsg(Message msg) {
		if (out == null) return;
		try {
			if (Config.getType().equals("Client") && ClientMain.getTempData().containsKey("token")) {
				msg.getData().put("token", ClientMain.getTempData().get("token"));
			}
			synchronized (out) {
				out.writeObject(msg);
			}
		} catch (IOException e) {
			Config.log(e);
			in = null;
			out = null;
		}
	}
	
	public void sendMsgWithCallBack(Message msg, LoopListener listener) {
		if (Config.getType().equals("Client")) {
			ClientMain.getResponseListener().addListener(msg.getuId(), listener);
		} else if (Config.getType().equals("Server")) {
			ServerMain.getResponseListener().addListener(msg.getuId(), listener);
		} else {
			Config.log("Error type for sendMsgWithCb!");
		}
		sendMsg(msg);
	}
	
}
