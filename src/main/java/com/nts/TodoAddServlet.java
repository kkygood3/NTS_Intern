package com.nts;

/*
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodoAddServlet
 */
@WebServlet("/todoadd")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * @doPost(request,response)
	 * add todo에 대한 request를 받아서 처리함.
	 * 실패시에 alert 프린트.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		try {
			if (TodoDao.addTodo(request) > 0) {
				response.sendRedirect("/jaewonlee/main");
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>"
					+ "alert('새로운 TODO를 넣는데에 성공했습니다.');"
					+ "history.back();"
					+ "</script>");
				out.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
