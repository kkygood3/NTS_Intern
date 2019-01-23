/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dto.TodoDto;
import com.nts.service.TodoService;
	
/**
 * @desc todo 등록 서블릿
 * @author 전연빈
 */
@WebServlet("/newtodo")
public class TodoAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @desc newtodo.jsp 파일로 포워딩
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/newtodo.jsp")
			   .forward(request, response);
	}

	/**
	 * @desc 새로운 todo 등록
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		TodoDto todoDto = new TodoDto();

		todoDto.setName(request.getParameter("personName"));
		todoDto.setTitle(request.getParameter("title"));
		todoDto.setSequence(Integer.parseInt(request.getParameter("sequence")));

		try {
			TodoService todoService = TodoService.getInstance();
			todoService.addTodo(todoDto);
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		response.sendRedirect("/main");
	}

}
