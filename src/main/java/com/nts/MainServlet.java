/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todo.dao.TodoDao;
import com.nts.todo.dto.TodoDto;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		TodoDao todoDao = new TodoDao();
		List<TodoDto> totalTodo = todoDao.getTodos();
		List<TodoDto> todos = new ArrayList<>();
		List<TodoDto> doings = new ArrayList<>();
		List<TodoDto> dones = new ArrayList<>();

		for (TodoDto todo : totalTodo) {
			switch (todo.getType()) {
				case "TODO":
					todos.add(todo);
					break;
				case "DOING":
					doings.add(todo);
					break;
				case "DONE":
					dones.add(todo);
					break;
				default:
					break;
			}
		}

		request.setAttribute("todos", todos);
		request.setAttribute("doings", doings);
		request.setAttribute("dones", dones);

		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		rd.forward(request, response);
	}
}