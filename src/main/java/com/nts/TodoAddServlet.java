package com.nts;

import java.io.IOException;

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
@WebServlet("/todoAdd")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodoAddServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String title = request.getParameter("title");
		String name = request.getParameter("owner");
		int sequence = Integer.parseInt(request.getParameter("priority"));

		TodoDto todo = new TodoDto(title, name, sequence);

		TodoDao todoDao = new TodoDao();
		todoDao.addTodo(todo);

		response.sendRedirect("main");
	}
}
