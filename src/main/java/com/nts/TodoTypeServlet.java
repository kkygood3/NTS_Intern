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
import com.nts.todo.dto.Type;

/**
* FileName : TodoTypeServlet.java
* @author  : 이승수
* @date    : 2019. 1. 22.
*/
@WebServlet("/nextStep")
public class TodoTypeServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		TodoDto todo = new TodoDto();
		todo.setId(Long.parseLong(request.getParameter("id")));
		Type currentType = Type.valueOf(request.getParameter("type"));
		todo.setType(currentType.next());

		TodoDao todoDao = new TodoDao();
		todoDao.updateTodo(todo);

		response.getWriter().write("success");
	}
}