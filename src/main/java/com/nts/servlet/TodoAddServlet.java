/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dto.TodoDto;
import com.nts.exception.ServerError500Exception;
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

		try {
			todoDto.setSequence(Integer.parseInt(request.getParameter("sequence")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청을 보냈습니다.");
			return;
		}

		try {
			TodoService todoService = TodoService.getInstance();
			todoService.addTodo(todoDto);
		} catch (ServerError500Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			return;
		}

		response.sendRedirect("/main");
	}

}
