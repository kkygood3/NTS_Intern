/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dto.TodoDto;
import com.nts.exception.ServerError500Exception;
import com.nts.service.TodoService;

/**
 * @author 전연빈
 */
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

		String type = request.getParameter("type");
		long id = 0;

		try {
			
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청을 보냈습니다.");
			return;
		}

		PrintWriter out = response.getWriter();
		try {
			
			TodoDto todoDto = new TodoDto();
			todoDto.setId(id);
			todoDto.setType(type);

			TodoService todoService = TodoService.getInstance();
			todoService.updateTodo(todoDto);

			out.write("success");
		} catch (ServerError500Exception e) {
			
			System.out.println(e.getMessage());
			out.write("fail");
		}
	}

}
