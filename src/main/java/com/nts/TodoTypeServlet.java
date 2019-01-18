package com.nts;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todo.dao.TodoDao;
import com.nts.todo.dto.TodoDto;

/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
@WebServlet("/nextStep")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodoTypeServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		String type = request.getParameter("type");

		TodoDao todoDao = new TodoDao();
		List<TodoDto> todos = todoDao.getTodos();

		for (TodoDto todo : todos) {
			if (todo.getId() == id) {
				switch (todo.getType()) {
					case "TODO":
						todo.setType("DOING");
						break;
					case "DOING":
						todo.setType("DONE");
						break;
					default:
						break;
				}

				todoDao.updateTodo(todo);
			}
		}

		response.sendRedirect("main");
	}
}
