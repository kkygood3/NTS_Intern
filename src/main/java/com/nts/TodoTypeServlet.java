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

		String responseParams = "";

		for (TodoDto todo : todos) {
			if (todo.getId() == id) {
				switch (type) {
					case "TODO":
						todo.setType("DOING");
						break;
					case "DOING":
						todo.setType("DONE");
						break;
					default:
						break;
				}
				responseParams = "<li>"
					+ "<input type=\"hidden\" class=\"id\" value=" + id + ">\r\n"
					+ "<input type=\"hidden\" class=\"type\" value=" + todo.getType() + ">\r\n"
					+ "<p>" + todo.getTitle() + "</p>\r\n"
					+ "<p>" + todo.getRegdate() + "</p>\r\n"
					+ "<p>" + todo.getName() + "</p>\r\n"
					+ "<p>우선순위 : " + todo.getSequence() + "</p>\r\n";
				if (!todo.getType().equals("DONE"))
					responseParams += "<p class=\"submit\">→</p>";
				responseParams += "</li>";
				todoDao.updateTodo(todo);
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-www-form-urlencoded");
		response.getWriter().write(responseParams);
	}
}
