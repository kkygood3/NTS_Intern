/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.dto.TodoDto;
import com.nts.todolist.exception.DatabaseAccessException;

/**
 * DB에서 todo list를 조회 후 main.jsp에 출력해주는 servlet
 * @author yongjoon.Park
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		RequestDispatcher requestDispatcher;
		try {
			List<TodoDto> allTodos = TodoDao.getInstance().getTodos();
			Map<String, List<TodoDto>> groupByType = groupingList(allTodos);

			request.setAttribute("groupByType", groupByType);

			requestDispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
			requestDispatcher.forward(request, response);
		} catch (DatabaseAccessException e) {
			request.setAttribute("errorMessage", e.getMessage());
			requestDispatcher = request.getRequestDispatcher("/WEB-INF/error.jsp");
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}

	}

	/**
	 * DB에서 받아온 모든 todo list(allTodos)를 TodoStatus에 맞게 분류하여 Map<String, List<TodoDto>>으로 반환
	 * @author yongjoon.Park
	 * @param allTodos TodoStatus에 맞는 type으로 구성된 todo list값
	 */
	private Map<String, List<TodoDto>> groupingList(final List<TodoDto> allTodos) {
		return allTodos.stream()
			.collect(Collectors.groupingBy(TodoDto::getType));
	}

}
