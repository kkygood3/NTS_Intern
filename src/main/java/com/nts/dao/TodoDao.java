package com.nts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.dto.TodoDto;

/**
 * DB에서 데이터를 가져오는 Dao클래스
 * @author 박우성
 */
public class TodoDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";

	public int addTodo(TodoDto todo) {
		//todo를 DB에 추가
		
		
		
		
		return 0;
	}
	
	public int updateTodo(TodoDto todo) {
		//선택한 todo 상태를 변환
		
		
		
		
		return 0;
	}
	
	public List getTodos() {
		ArrayList<TodoDto> roles = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(dburl,dbUser,dbpasswd);
			
			String sql = "SELECT * FROM todo";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int sequence = rs.getInt(4);
				String type = rs.getString(5);
				String regdate = rs.getString(6);
				
				roles.add(new TodoDto(id,title,name,sequence,type,regdate));
			}
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return roles;
	}
}
