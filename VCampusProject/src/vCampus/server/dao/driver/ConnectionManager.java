package vCampus.server.dao.driver;

import java.sql.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import vCampus.utility.Config;

public class ConnectionManager{
	
	private static long timeout;
	private static BlockingQueue<Connection> pool = new LinkedBlockingQueue<Connection>();
	
	static{
		timeout = Config.get().getJSONObject("db").getIntValue("timeout");
		int initialSize = Config.get().getJSONObject("db").getIntValue("count");
		if (initialSize > 0) {
			for (int i = 0; i < initialSize; i++) {
				try {
					pool.put(ConnectionDriver.createConnection());
				} catch (InterruptedException e) {
					Config.log(e);
				}
			}
		}
	}

	public static Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			if (timeout <= 0) {
				conn = pool.take();
			} else {
				conn = pool.poll(timeout, TimeUnit.MILLISECONDS);
			}
		} catch (InterruptedException e) {
			Config.log(e);
			return null;
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				pool.put(conn);
			} catch (InterruptedException e) {
				Config.log(e);
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			for (int i=0; i<=60; ++i) {
				Config.log("Prepare connection(" + i + ")");
				Connection conn = getConnection();
				close(conn);
			}
		} catch (SQLException e) {
			Config.log(e);
		}
	}
	
}