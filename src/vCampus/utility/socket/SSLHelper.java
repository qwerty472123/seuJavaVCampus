package vCampus.utility.socket;

import java.io.*;
import java.net.*;
import java.security.*;

import javax.net.ssl.*;
import javax.swing.JOptionPane;

import vCampus.utility.Config;

public class SSLHelper {
	private static SSLContext context;
	static {
		if (Config.getType().equals("Server")) {
			char[] serverKeyPwd = Config.get().getJSONObject("ssl").getString("serverPwd").toCharArray();
		    char[] keyPwd = Config.get().getJSONObject("ssl").getString("pwd").toCharArray();
		    FileInputStream keyStoreFIS;
			try {
				keyStoreFIS = new FileInputStream(Config.get().getJSONObject("ssl").getString("server"));
			    KeyStore keyStore = KeyStore.getInstance("pkcs12");
			    keyStore.load(keyStoreFIS, serverKeyPwd);  
			    keyStoreFIS.close();
			    KeyStore trustKeyStore = KeyStore.getInstance("jks");
		        FileInputStream trustKeyStoreFIS = new FileInputStream(Config.get().getJSONObject("ssl").getString("ca"));
		        trustKeyStore.load(trustKeyStoreFIS, keyPwd);
		        trustKeyStoreFIS.close();
			    KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			    keyManagerFactory.init(keyStore, serverKeyPwd);
			    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("sunx509");
			    trustManagerFactory.init(trustKeyStore);
			    context = SSLContext.getInstance("TLSv1.2");
			    context.init(keyManagerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(),new SecureRandom());
			} catch (Exception e) {
				Config.log(e);
				Config.log("SSL initialize failed.");
				System.exit(-1);
			}
		} else if (Config.getType().equals("Client")) {
		    char[] keyPwd = Config.get().getJSONObject("ssl").getString("pwd").toCharArray();
		    FileInputStream keyStoreFIS;
			try {
				keyStoreFIS = new FileInputStream(Config.get().getJSONObject("ssl").getString("client"));
			    KeyStore keyStore = KeyStore.getInstance("pkcs12");
			    keyStore.load(keyStoreFIS, keyPwd);  
			    keyStoreFIS.close();
			    KeyStore trustKeyStore = KeyStore.getInstance("jks");
		        FileInputStream trustKeyStoreFIS = new FileInputStream(Config.get().getJSONObject("ssl").getString("ca"));
		        trustKeyStore.load(trustKeyStoreFIS, keyPwd);
		        trustKeyStoreFIS.close();
			    KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			    keyManagerFactory.init(keyStore, keyPwd);
			    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("sunx509");
			    trustManagerFactory.init(trustKeyStore);
			    context = SSLContext.getInstance("TLSv1.2");
			    context.init(keyManagerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(),new SecureRandom());
			} catch (Exception e) {
				Config.log(e);
				JOptionPane.showMessageDialog(null, "SSL 初始化失败！", "初始化失败", JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}
		}
	}
	public static ServerSocket getServerSocket(int port) throws IOException {
		if (!Config.getType().equals("Server")) return null;
		SSLServerSocket serverSocket = (SSLServerSocket) context.getServerSocketFactory().createServerSocket(port);
		serverSocket.setNeedClientAuth(true);
		return serverSocket;
	}
	public static Socket getClientSocket(InetAddress host, int port) throws IOException {
		if (!Config.getType().equals("Client")) return null;
		return context.getSocketFactory().createSocket(host, port);
	}
}
