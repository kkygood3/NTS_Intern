/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.jdbc.dao.TodoDao;
import com.nts.jdbc.dto.TodoDto;

/**
 * Servlet implementation class TodoAddServlet
 */
@WebServlet("/addtodo")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodoAddServlet() {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String title = request.getParameter("todo_title");
		String name = request.getParameter("todo_name");
		int sequence = Integer.parseInt(request.getParameter("todo_sequence"));

		if (!isValidRequestParams(title, name, sequence)) {
			response.sendError(response.SC_BAD_REQUEST, "잘못 된 값을 전송하였습니다.");
			return;
		}

		TodoDao todoDao = TodoDaoProvider.getTodoDaoInstance();
		TodoDto newTodoDto = new TodoDto(title, name, sequence);
		try {
			todoDao.addTodo(newTodoDto);
			response.sendRedirect("./main");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	private boolean isValidRequestParams(String title, String name, int sequence) {
		title = title.trim();
		name = name.trim();

		if (title.length() == 0 || title.length() > 24) {
			return false;
		}

		if (name.length() == 0) {
			return false;
		}

		if (sequence < 1 || sequence > 3) {
			return false;
		}

		return true;
	}
}
