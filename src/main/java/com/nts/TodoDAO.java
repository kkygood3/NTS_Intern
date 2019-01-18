package com.nts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class TodoDAO {
	private static String dburl = "jdbc:mysql://localhost:3306/pjt2?autoReconnect=true&useSSL=false";
	private static String dbUser = "root";
	private static String dbpasswd = "root";

	public TodoDAO() {
		super();
	}

	public static List<List<TodoDTO>> getToDoList() throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<List<TodoDTO>> result = new ArrayList<>();
		List<TodoDTO> list_todo = new ArrayList<>();
		List<TodoDTO> list_doing = new ArrayList<>();
		List<TodoDTO> list_done = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "select id, title, name, sequence, type, regdate from todo order by sequence asc, regdate desc, id desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("id"));
				TodoDTO dto = new TodoDTO();
				dto.setName(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setName(rs.getString("name"));
				dto.setSequence(rs.getInt("sequence"));
				dto.setType(rs.getString("type"));
				dto.setRegdate(rs.getDate("regdate"));
				if (dto.getType().equals("TODO")) {
					list_todo.add(dto);
				} else if (dto.getType().equals("DOING")) {
					list_doing.add(dto);
				} else if (dto.getType().equals("DONE")) {
					list_done.add(dto);
				}
			}

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

		result.add(list_todo);
		result.add(list_doing);
		result.add(list_done);

		return result;
	}

	/*
	 * @addOne()	
	 * add single todo item into db
	 */
	public static int addOne(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		//variable status to get the execution state;
		int status = 0;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "insert into todo(title, name, sequence) values(?,?,?)";
			ps = conn.prepareStatement(sql);

			ps.setString(1, request.getParameter("title"));
			ps.setString(2, request.getParameter("name"));
			ps.setInt(3, Integer.valueOf(request.getParameter("sequence")));

			status = ps.executeUpdate();

			if (status > 0) {
				System.out.println("삽입");
			} else {
				System.out.println("실패");
			}

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
		return status;
	}

}
