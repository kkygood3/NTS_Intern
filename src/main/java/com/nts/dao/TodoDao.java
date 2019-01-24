package com.nts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nts.Const;
import com.nts.database.DBConnection;
import com.nts.database.DBQuery;
import com.nts.dto.TodoDto;

/*
 * data 입출력을 실행하는 클래스
 * @author 시윤
 */
public class TodoDao {
	private TodoDao() {}

	/*
	 * 싱글톤을 유지하기위한 홀더클래스
	 */
	private static class TodoDaoLazyHolder {
		public static final TodoDao INSTANCE = new TodoDao();
	}

	/*
	 * 싱글톤을 유지하기 위한 메소드
	 */
	public static TodoDao getInstance() {
		return TodoDaoLazyHolder.INSTANCE;
	}

	/*
	 * todo 하나를 DB에 등록
	 * @param todo 등록할 TodoDto 인스턴스
	 */
	public int addTodo(TodoDto todo) {
		int result = 0;
		try (Connection dbConnection = DBConnection.getConnection();
			PreparedStatement ps = dbConnection.prepareStatement(DBQuery.INSERT_SQL)) {
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 전체 todo목록을 select
	 * @return DB에서 선택된 to list
	 */
	public List<TodoDto> getTodos() {
		List<TodoDto> todos = new ArrayList<TodoDto>();
		try (Connection dbConnection = DBConnection.getConnection();
			PreparedStatement ps = dbConnection.prepareStatement(DBQuery.SELECT_SQL);
			ResultSet rs = ps.executeQuery()) {
			rs.beforeFirst();
			while (rs.next()) {
				todos.add(new TodoDto(rs.getLong(Const.ID),
					rs.getString(Const.NAME),
					rs.getString(Const.REGDATE),
					rs.getInt(Const.SQQUENCE),
					rs.getString(Const.TITLE),
					rs.getString(Const.TYPE)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todos;
	}

	/*
	 * type을 변경
	 * (todo->doing or doing->done)
	 * @param type 수정할 TodoDto 인스턴스
	 */
	public int updateTodo(TodoDto todo) {
		int result = 0;
		try (Connection dbConnection = DBConnection.getConnection();
			PreparedStatement ps = dbConnection.prepareStatement(DBQuery.UPDATE_DQL)) {
			ps.setString(1, getNextType(todo.getType()));
			ps.setLong(2, todo.getId());
			ps.setString(3, todo.getType());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * type 변경 시 변경될 type 결정하는 메소드
	 */
	private String getNextType(String type) {
		type = type.toUpperCase();
		switch (type) {
			case Const.TODO:
				return Const.DOING;
			case Const.DOING:
				return Const.DONE;
		}
		return Const.UNKNOWN;
	}
}
