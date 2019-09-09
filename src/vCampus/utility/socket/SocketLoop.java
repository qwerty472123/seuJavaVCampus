package vCampus.utility.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

import vCampus.client.ClientMain;
import vCampus.server.ServerMain;
import vCampus.utility.Config;
import vCampus.utility.loop.Loop;
import vCampus.utility.loop.LoopListener;
import vCampus.utility.loop.Message;

public class SocketLoop extends Loop {
	
	private Socket socket = null;
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;
	private boolean swap;
	private UUID uuid;
	private Object waitRecObj = new Object();

	public SocketLoop(Map<String, Deque<LoopListener> > sharedListenerMap, Socket _socket, boolean _swap) {
		super(sharedListenerMap);
		socket = _socket;
		swap = _swap;
		initSocket();
	}
	
	private boolean sendOldMsg(Deque<Message> msgs) {
		Iterator<Message> iterator = msgs.iterator();
        while (iterator.hasNext()) {
        	Message msg = iterator.next();
        	try {
        		sendMsg(msg);
        	} catch (Exception err) {
        		Config.log(err);
        		return false;
        	}
        	iterator.remove();
        }
        return true;
	}
	
	@SuppressWarnings("unchecked")
	private boolean initSocket() {
		if (socket == null) return false;
		try {
			if (swap) {
				in = new ObjectInputStream(socket.getInputStream());
				out = new ObjectOutputStream(socket.getOutputStream());
				uuid = (UUID) ClientMain.getTempData().get("uuid");
				out.writeObject(uuid);
				return sendOldMsg((Deque<Message>) ClientMain.getTempData().get("list"));
			} else {
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
				uuid = (UUID) in.readObject();
				if (ServerMain.getLaterSenderMap().containsKey(uuid)) {
					return sendOldMsg(ServerMain.getLaterSenderMap().get(uuid));
				} else return true;
			}
		} catch (Exception e) {
			in = null;
			out = null;
			Config.log(e);
			return false;
		}
	}

	@Override
	protected Message getMsg() throws Exception {
		if (in == null) {
			if(swap) {
				SocketReconnectHelper.triggerReconnect(false);
				synchronized (waitRecObj) {
					waitRecObj.wait();
				}
			}
			else return null;
		}
		Message msg = null;
		try {
			msg = (Message)in.readObject();
		} catch (IOException e) {
			Config.log(e);
			in = null;
			out = null;
			if (swap) {
				SocketReconnectHelper.triggerReconnect(false);
				synchronized (waitRecObj) {
					waitRecObj.wait();
				}
				return getMsg();
			}
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
	
	@SuppressWarnings("unchecked")
	private void sendMsgLater(Message msg) {
		if (Config.getType().equals("Client")) {
			((Deque<Message>) ClientMain.getTempData().get("list")).add(msg);
		} else if (Config.getType().equals("Server")) {
			if (!ServerMain.getLaterSenderMap().containsKey(uuid)) {
				ServerMain.getLaterSenderMap().put(uuid, new ConcurrentLinkedDeque<Message>());
			}
			ServerMain.getLaterSenderMap().get(uuid).add(msg);
		} else {
			Config.log("Error type for sendMsgLater!");
		}
	}
	
	public void sendMsg(Message msg) {
		if (out == null) {
			sendMsgLater(msg);
			if (swap) SocketReconnectHelper.triggerReconnect(true);
			return;
		}
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
			sendMsgLater(msg);
			if (swap) SocketReconnectHelper.triggerReconnect(true);
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
	
	public boolean setSocket(Socket _socket) {
		if (!swap) {
			Config.log("Illegal call to setSocket!");
			return false;
		}
		socket = _socket;
		if(initSocket()) {
			synchronized(waitRecObj) {
				waitRecObj.notifyAll();
			}
			return true;
		}else return false;
	}
	
}
