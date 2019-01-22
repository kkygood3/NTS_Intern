/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
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

@WebServlet("/nextStep")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		String requestType = request.getParameter("type");

		Type currentType = Type.valueOf(requestType);

		TodoDao todoDao = new TodoDao();
		List<TodoDto> todos = todoDao.getTodos();

		for (TodoDto todo : todos) {
			if (todo.getId() == id) {
				todo.setType(currentType.next());
				todoDao.updateTodo(todo);
			}
		}
		response.getWriter().write("success");
	}

	private enum Type {
		TODO, DOING, DONE;

		private static Type[] types = values();

		public String next() {
			return types[this.ordinal() + 1].toString();
		}
	}
}