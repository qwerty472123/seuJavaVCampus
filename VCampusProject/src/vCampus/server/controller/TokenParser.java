package vCampus.server.controller;

import vCampus.server.dao.AccountKeyDAO;
import vCampus.utility.Token;

public class TokenParser {
	
	public static boolean isValid(Token token) {
		int userId = token.getUserId();
		String pwd = AccountKeyDAO.queryPassword(userId);
		return token.check(pwd);
	}
	
}
