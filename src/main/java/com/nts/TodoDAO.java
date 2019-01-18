package com.nts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class TodoDAO {
	private static String dburl = "jdbc:mysql://localhost:3306/pjt2?autoReconnect=true&useSSL=false";
	private static String dbUser = "root";
	private static String dbpasswd = "root";

	public TodoDAO() {
		super();
	}

	/*
	 * @getList()	
	 * returns JSON ARRAY generated from SQL QUERY execution.
	 */

	public static JSONArray getList() throws Exception {
		JSONArray jsonArray;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "select * from todo order by sequence desc, regdate desc, id desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			jsonArray = new JSONArray();

			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				ResultSetMetaData rmd = rs.getMetaData();

				for (int i = 1; i <= rmd.getColumnCount(); i++) {
					jsonObject.put(rmd.getColumnName(i), rs.getString(rmd.getColumnName(i)));
				}
				jsonArray.put(jsonObject);
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

		return jsonArray;
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
