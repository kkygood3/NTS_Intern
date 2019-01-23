/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

/**
 * todo_register.jsp페이지를 띄우고 데이터 등록을 수행하는 서블렛
 * @author 박우성
 */
@WebServlet("/register")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * GET method 호출시 todo_register.jsp페이지로 포워딩 수행
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/todo_register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * POST method 호출시 todo_register.jsp페이지에서 받은 패러미터로 할일 등록 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String todoTitle = request.getParameter("todo_title");
		String todoName = request.getParameter("todo_name");
		boolean invalidInput = todoTitle.length() > 24 || todoTitle.length() < 1 || todoName.length() > 10 || todoName.length() < 1;
		if (invalidInput) {
			response.sendRedirect("error/invalid_access.jsp");
		} else {
			int priority = new Integer(request.getParameter("priority"));

			TodoDao dao = TodoDao.getInstance();
			dao.addTodo(new TodoDto(todoTitle, todoName, priority));

			response.sendRedirect("main");
		}
	}
}
