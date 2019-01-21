/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.jdbc.dao.TodoDao;

/**
 * Servlet implementation class TodoTypeServlet
 */
@WebServlet("/todotype")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodoTypeServlet() {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("type");

		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (!isValidRequestParams(type)) {
			response.setStatus(response.SC_BAD_REQUEST);
			out.append("잘못 된 값을 전송하였습니다.");
			out.close();
			return;
		}

		TodoDao todoDao = TodoDaoProvider.getTodoDaoInstance();
		try {
			todoDao.updateTodo(id, type);
			out.append("Success");

		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(response.SC_BAD_REQUEST);
			out.append("서버 갱신 오류.");
		}
		out.close();
	}

	private boolean isValidRequestParams(String type) {
		if (TodoTag.TODO.getTodoTag().equals(type) || TodoTag.DOING.getTodoTag().equals(type)
			|| TodoTag.DONE.getTodoTag().equals(type)) {
			return true;
		}
		return false;
	}

}
