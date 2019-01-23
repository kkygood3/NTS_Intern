/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dto.TodoDto;
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
			TodoService todoService = new TodoService();
			
			// keys : todoList, doingList, doneList
			Map<String, List<TodoDto>> result = todoService.getTodos();
			request.setAttribute("result", result);

		} catch (SQLException e) {
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/main.jsp");
		rd.forward(request, response);

	}

}
