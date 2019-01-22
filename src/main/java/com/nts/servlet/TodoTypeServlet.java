/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dto.TodoDto;
import com.nts.service.TodoService;

@WebServlet("/changeTodoType")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @desc TodoType 변경
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		long id = Long.parseLong(request.getParameter("id"));
		String type = request.getParameter("type");

		PrintWriter out = response.getWriter();

		try {
			TodoService todoService = new TodoService();
			
			TodoDto todoDto = new TodoDto();
			todoDto.setId(id);
			todoDto.setType(type);
			
			todoService.updateTodo(todoDto);
			
			out.write("success");
		} catch (SQLException e) {
			out.write("fail");
		}
	}

}
