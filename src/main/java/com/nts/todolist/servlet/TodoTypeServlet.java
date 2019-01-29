/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todolist.common.StringUtils;
import com.nts.todolist.common.TodoStatus;
import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.exception.DatabaseAccessException;

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
		throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");

		String requestId = request.getParameter("id");
		String requestType = request.getParameter("type");
		
		if(!StringUtils.isValid(requestId, requestType)) {
			printErrorAlertToJsp(response);
			return;
		}
		
		long id = Long.parseLong(requestId);
		TodoStatus todoStatus = TodoStatus.valueOf(requestType);
		
		try {
			int updateResult = TodoDao.getInstance().updateTodo(id, todoStatus.getNextStatus());
			
			if(updateResult != 1) {
				response.getWriter().write("fail");
				return;
			}
			
			response.getWriter().write("success");
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/error.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
	}

	private void printErrorAlertToJsp(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.write("<script>");
		printWriter.write("alert('알맞은 값을 입력하십시오.');");
		printWriter.write("location.href='/todoForm'");
		printWriter.write("</script>");
		printWriter.close();
	}
}
