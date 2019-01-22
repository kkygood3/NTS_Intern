/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.dto.TodoDto;

/**
 * 입력받은 todo (title, name, sequence)를 Database에 추가시키는 servlet
 * @author yongjoon.Park
 *
 */
@WebServlet("/todoAdd")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String title = (String)request.getParameter("title");
		String name = (String)request.getParameter("name");
		int sequence = Integer.parseInt((String)request.getParameter("sequence"));

		TodoDto newTodo = new TodoDto(title, name, sequence);

		int insertCount = new TodoDao().addTodo(newTodo);
		if (insertCount > 0) {
			response.sendRedirect("/main");
		} else {
			// TODO insert 실패 시 표기 (servlet)
		}
	}

}
