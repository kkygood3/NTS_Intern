/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.api;

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

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;
import com.nts.dto.TodoType;

/**
 * main.jsp를 로드할때 마다 DB의 현재 값을 가져오는 서블렛
 * @author 박우성
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {		
		TodoDao dao = TodoDao.getInstance();
		List<TodoDto> list = dao.getTodos();

		Map<TodoType, List<TodoDto>> groupedList = list.stream().collect(Collectors.groupingBy(TodoDto::getType));
		request.setAttribute("todoList", groupedList.get(TodoType.TODO));
		request.setAttribute("doingList", groupedList.get(TodoType.DOING));
		request.setAttribute("doneList", groupedList.get(TodoType.DONE));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
	}
}
