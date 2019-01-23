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
import com.nts.jdbc.dto.TodoDto;

/**
 * @author 육성렬
 */
@WebServlet("/todoType")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("type");

		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String nextType = "";
		try {
			nextType = TodoTag.valueOf(type).getNext();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.setStatus(response.SC_BAD_REQUEST);
			out.append("잘못 된 값을 전송하였습니다.");
			out.close();
			return;
		}

		TodoDto targetTodoDto = new TodoDto.Builder().id(id).type(nextType).build();
		TodoDao todoDao = TodoDao.getInstance();
		try {
			todoDao.updateTodo(targetTodoDto);
			out.append("Success");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
			out.append("서버 내부 에러 발생");
		}
		out.close();
	}
}
