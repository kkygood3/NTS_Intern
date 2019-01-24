/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dto.TodoDto;
import com.nts.dto.TodoListContainer;
import com.nts.exception.DataAccessException;
import com.nts.service.TodoService;

/**
 *
 * @description : /main에 접근 시 service를 통해 DB의 Todo List를 조회해 전달함
 * @filename : MainServlet.java
 * @package : com.nts.servlet
 * @author : Seokhyeon Choi
 * @method : void doGet(HttpServletRequest request, HttpServletResponse
 *         response)
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @description : DB에서 Todo List를 조회한 결과를 request scope에 추가하고 jsp로 forwarding
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8;");
		
		List<TodoDto> todoList = null;
		
		try {
			todoList = TodoService.getInstance().getTodos();
		} catch (DataAccessException e) {
			response.sendError(500, e.getErrorMessage());
		}
		
		TodoListContainer todoListContainer = new TodoListContainer(todoList);

		request.setAttribute("todoList", todoListContainer);
		request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
	}

}
