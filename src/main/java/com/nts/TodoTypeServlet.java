/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.jdbc.dao.TodoDao;
import com.nts.jdbc.dto.TodoDto;

/**
 * Servlet implementation class TodoTypeServlet
 */
@WebServlet("/todo-type")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("type");

		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			TodoTag.valueOf(type);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.setStatus(response.SC_BAD_REQUEST);
			out.append("잘못 된 값을 전송하였습니다.");
			out.close();
			return;
		}

		String nextType = "";
		if (type.equals(TodoTag.TODO.getTodoTag())) {
			nextType = TodoTag.DOING.getTodoTag();
		} else if (type.equals(TodoTag.DOING.getTodoTag())) {
			nextType = TodoTag.DONE.getTodoTag();
		}

		TodoDto targetTodoDto = new TodoDto.Builder().id(id).type(nextType).build();
		TodoDao todoDao = TodoDao.getInstance();
		try {
			todoDao.updateTodo(targetTodoDto);
			out.append("Success");

		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
			out.append("서버 갱신 오류.");
		}
		out.close();
	}
}
