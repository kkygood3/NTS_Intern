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

import com.nts.dto.TodoDtoList;
import com.nts.exception.ServerError500Exception;
import com.nts.service.TodoService;

/**
 * @author 전연빈
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		try {
			
			TodoService todoService = TodoService.getInstance();

			TodoDtoList todos = todoService.getTodos();
			request.setAttribute("todos", todos);
		} catch (ServerError500Exception e) {
			
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
			return;
		}

		request.getRequestDispatcher("/WEB-INF/main.jsp")
			   .forward(request, response);

	}

}
