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

		boolean success = false;
		List<Todo> todoList = new ArrayList<>();
		List<Todo> doingList = new ArrayList<>();
		List<Todo> doneList = new ArrayList<>();
		TodoDao todoDao = TodoDaoProvider.getTodoDaoInstance();

		try {
			List<Todo> list = todoDao.getTodos();
			divideTodoList(list, todoList, doingList, doneList);
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("Success", success);
		request.setAttribute("TodoList", todoList);
		request.setAttribute("DoingList", doingList);
		request.setAttribute("DoneList", doneList);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);
	}

	void divideTodoList(List<Todo> originList, List<Todo> todoList, List<Todo> doingList, List<Todo> doneList) {

		for (Todo todo : originList) {
			if (todo.getType().equals(TodoTag.TODO.getTodoTag())) {
				todoList.add(todo);
			} else if (todo.getType().equals(TodoTag.DOING.getTodoTag())) {
				doingList.add(todo);
			} else {
				doneList.add(todo);
			}
		}
	}

}
