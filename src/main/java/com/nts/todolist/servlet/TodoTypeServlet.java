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

import com.nts.todolist.common.TodoStatus;
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
		TodoStatus todoStatus = TodoStatus.valueOf(request.getParameter("type"));
		int updateResult = TodoDao.getInstance().updateTodo(id, todoStatus);

		if (updateResult == 1) {
			response.getWriter().write("success");
		} else {
			System.out.printf("실패! 입력된 정보를 확인 (%d, %s)", id, todoStatus.getValue());
			response.getWriter().write("fail");
		}
	}
}
