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
		String type = request.getParameter("type");

		TodoDao todoDao = new TodoDao();
		List<TodoDto> todos = todoDao.getTodos();

		String responseContent = "";

		for (TodoDto todo : todos) {
			if (todo.getId() == id) {
				if (type.equals("TODO")) {
					todo.setType("DOING");
					responseContent = makeResponseContent(todo);
					responseContent += "<p class=\"submit\">→</p>";
				} else {
					todo.setType("DONE");
					responseContent = makeResponseContent(todo);
				}
				responseContent += "</li>";
				todoDao.updateTodo(todo);
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-www-form-urlencoded");
		response.getWriter().write(responseContent);
	}

	private String makeResponseContent(TodoDto todo) {
		String responseContent = "<li data-todo_id = \"" + todo.getId() + "\" data-todo_type = \"" + todo.getType() + "\">"
			+ "<input type=\"hidden\" class=\"id\" value=" + todo.getId() + ">\r\n"
			+ "<input type=\"hidden\" class=\"type\" value=" + todo.getType() + ">\r\n"
			+ "<p>" + todo.getTitle() + "</p>\r\n"
			+ "<p>" + todo.getRegdate() + "</p>\r\n"
			+ "<p>" + todo.getName() + "</p>\r\n"
			+ "<p>우선순위 : " + todo.getSequence() + "</p>\r\n";
		return responseContent;
	}
}
