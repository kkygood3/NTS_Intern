package com.nts.database;

/*
 * SQL문을 모아놓은 클래스
 * @author 시윤
 */
public class DBQuery {
	public static final String INSERT_SQL = "insert into todo(title, name, sequence) values(?, ?, ?)";
	public static final String SELECT_SQL = "select id, title, name, sequence, type, DATE_FORMAT(regdate, '%Y.%m.%d') as regdate from todo";
	public static final String UPDATE_DQL = "update todo set type = ? where id = ? and type = ?";
}
