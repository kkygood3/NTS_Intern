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

/**
 * todo의 타입을 변경 시키는 servlet (todo -> doing, doing -> done)
 * @author yongjoon.Park
 *
 */
@WebServlet("/changeType")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		response.setContentType("text/html;charset=UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("type");
		int insertCount = new TodoDao().updateTodo(id, type);

		if (insertCount > 0) {
			response.getWriter().write("success");
		} else {
			// TODO AJAX 실패 시
			response.getWriter().write("fail");
		}
	}
}
