package com.nts;

/**
 * Servlet implementation class TodoDAO
 * Author : Jaewon Lee, lee.jaewon@nts-corp.com	
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

@WebServlet("/todo")
public class TodoDAO {
	private static String dburl = "jdbc:mysql://localhost:3306/pjt2";
	private static String dbUser = "root";
	private static String dbpasswd = "root";

	/*
	 * @getList()
	 * 
	 *  JDBC를 통하여 mysql 서버에서 데이터를 받아옴
	 *  todoList.index=> 
	 *  0 = todo 타입
	 *  1 = doing 타입
	 *  2 = done 타입
	 */

	public List<List<Todo>> getList() {

		List<List<Todo>> todoList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			todoList.add(new ArrayList<Todo>());
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "select * from todo order by regdate desc, sequence asc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				Todo entry = new Todo();
				entry.setId(rs.getInt("id"));
				entry.setTitle(rs.getString("title"));
				entry.setName(rs.getString("name"));
				entry.setSequence(rs.getInt("sequence"));
				entry.setRegdate(rs.getDate("regdate"));

				if (rs.getString("type").equals("TODO")) {
					todoList.get(0).add(entry);
				} else if (rs.getString("type").equals("DOING")) {
					todoList.get(1).add(entry);
				} else {
					todoList.get(2).add(entry);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return todoList;
	}
}
