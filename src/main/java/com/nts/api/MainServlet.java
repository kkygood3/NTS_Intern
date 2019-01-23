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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

/**
 * main.jsp를 로드할때 마다 DB의 현재 값을 가져오는 서블렛
 * @author 박우성
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String[] todoLabel = {"TODO", "DOING", "DONE"};

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		TodoDao dao = TodoDao.getInstance();
		List<TodoDto> list = dao.getTodos();

		Map<String, List<TodoDto>> groupedList = list.stream().collect(Collectors.groupingBy(TodoDto::getType));
		for (int i = 0; i < todoLabel.length; i++)
			request.setAttribute(todoLabel[i] + "list", groupedList.get(todoLabel[i]));

		request.setAttribute("todoLabel", todoLabel);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
	}
}
