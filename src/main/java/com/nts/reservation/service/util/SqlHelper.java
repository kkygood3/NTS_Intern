package com.nts.reservation.service.util;

/**
 * sql문 조작하는 클래스
 * @author si yoon
 *
 */
public class SqlHelper {
	private SqlHelper() {}
	
	/**
	 * sql문에 조건절 추가해준다. sql문에서 :key 형식으로 저정해주면 바꿔준다.
	 * :는 꼭 붙여서 써야함
	 * @param orgStatement 원래 sql문
	 * @param key 조건 들어갈 위치
	 * @param value 변경할 조건
	 * @return 생성된 sql문
	 */
	public static String getSqlStatement(final String orgStatement, String key, String value) {
		String statement = new String(orgStatement);
		
		return statement.replaceAll(":" + key, value);
	}
}
