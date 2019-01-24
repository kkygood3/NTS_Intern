package com.nts.dao;

class TodoDaoQuery {
	protected static final String SELECT_QUERY = "select id, title, name, sequence, type, DATE_FORMAT(regdate,'%Y.%m.%d') as regdate from todo";
	protected static final String INSERT_QUERY = "insert into todo(title, name, sequence) values(?, ?, ?)";;
	protected static final String UPDATE_QUERY = "update todo set type = ? where id = ? and type = ?";
	protected static final String VERIFY_QUERY = "select * from todo where id = ? and type = ?";
}
