/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
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
* FileName : TodoAddServlet.java
* @author  : 이승수
* @date    : 2019. 1. 22.
*/
@WebServlet("/todoAdd")
public class TodoAddServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		TodoDto newTodo = new TodoDto();
		newTodo.setTitle(request.getParameter("title"));
		newTodo.setName(request.getParameter("name"));
		newTodo.setSequence(Integer.parseInt(request.getParameter("sequence")));

		TodoDao todoDao = new TodoDao();
		todoDao.addTodo(newTodo);

		response.sendRedirect("main");
	}
}