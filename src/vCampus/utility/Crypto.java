package vCampus.utility;

import java.nio.charset.*;
import java.security.*;

public class Crypto {
	public static String hash(String type, String text) {
		MessageDigest messageDigest;
		String encodeStr = "";
		try{
			messageDigest = MessageDigest.getInstance(type);
			messageDigest.update(text.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = messageDigest.digest();
			StringBuilder buffer = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				String tmp = Integer.toHexString(bytes[i] & 0xff);
				if (tmp.length() == 1) {
					buffer.append("0");
				}
				buffer.append(tmp);
			}
			encodeStr = buffer.toString();
		} catch (Exception err) {
			return "";
		}
		return encodeStr;
	}
	public static String basePasswordEncrypt(String password, int userId) {
		return Crypto.hash("SHA-256", password + "vCampusLocEnc" + userId);
	}
	public static String passwordEncrypt(String password, int userId) {
		if (Config.getType().equals("Server")) {
			return Crypto.hash("SHA-256", password + Config.get().getString("pwdSalt") + userId);
		} else {
			return basePasswordEncrypt(password, userId);
		}
	}
}
