package vCampus.server.dao.driver;

import java.sql.*;

import vCampus.utility.Config;

public class ConnectionDriver {
	
	static {
		Config.log("Database drivers intializing...");
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException e) {
			Config.log(e);
		}
		Config.log("Database drivers intialized.");
		url = Config.get().getJSONObject("db").getString("url");
	}

	private static String url;
	
	public static final Connection createConnection() {
		Connection conn = null;
		try{
    		conn =  DriverManager.getConnection(url);
    		Config.log("Got a new connection.");    		
        } catch (SQLException e) {
        	Config.log(e);
        }
		return conn;
	}
    
}
