/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.jdbc.dao.TodoDao;
import com.nts.jdbc.dto.Todo;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		TodoDao todoDao = TodoDaoProvider.getTodoDaoInstance();

		try {
			List<Todo> list = todoDao.getTodos();
			List<Todo> todoList = new ArrayList();
			List<Todo> doingList = new ArrayList();
			List<Todo> doneList = new ArrayList();

			for (Todo item : list) {
				if (item.getType().equals(TodoTag.TODO.getTodoTag())) {
					todoList.add(item);
				} else if (item.getType().equals(TodoTag.DOING.getTodoTag())) {
					doingList.add(item);
				} else {
					doneList.add(item);
				}
			}
			List<Todo> result = new ArrayList();
			result.addAll(todoList);
			request.setAttribute("DoingStart", result.size());
			result.addAll(doingList);
			request.setAttribute("DoneStart", result.size());
			result.addAll(doneList);
			request.setAttribute("TodoList", result);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(response.SC_BAD_REQUEST, e.getMessage());
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);
	}
}
