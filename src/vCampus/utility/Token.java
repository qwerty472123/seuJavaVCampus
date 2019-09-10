package vCampus.utility;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Token implements Serializable {
	private static final long serialVersionUID = -2452993930079696344L;
	public static final int TOKEN_EXPIRE = Config.get().getIntValue("expireTime");
	private int userId;
	private double rand;
	private Date expire;
	private String hash;
	
	private static Random random = new Random();
	
	public Token(int userId, String pwd) {
		setUserId(userId);
		setRand(random.nextDouble());
		Date cur = new Date();
		cur.setTime(cur.getTime() + TOKEN_EXPIRE);
		setExpire(cur);
		setHash(pwd);
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getRand() {
		return rand;
	}
	public void setRand(double rand) {
		this.rand = rand;
	}
	public void newRand() {
		setRand(Math.random());
	}
	public Date getExpire() {
		return expire;
	}
	public void setExpire(Date expire) {
		this.expire = expire;
	}
	private String getHash(String password) {
		return Crypto.hash("SHA-256", userId + rand + password + rand + expire.getTime() + rand + Config.get().getString("tokenSalt"));
	}
	public boolean check(String password) {
		Date cur = new Date();
		if (cur.after(getExpire())) return false;
		return getHash(password).equals(hash);
	}
	public void setHash(String password) {
		this.hash = getHash(password);
	}
}
