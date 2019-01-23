/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;
import com.nts.dto.TodoDto.Type;

/**
 * 메인화면을 보여주기 위한 서블릿
 * @author jinwoo.bae
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 일정을 모두 불러온다음 상태값에 따라 리스트로 나눈후 리스트들을 main.jsp로 포워딩해줌
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TodoDao todoDao = TodoDao.getInstance();
		List<TodoDto> todos = todoDao.getTodos();
		Map<Type, List<TodoDto>> todosPerType = todos.stream()
			.collect(Collectors.groupingBy(TodoDto::getType));

		List<TodoDto> todoList = todosPerType.get(Type.TODO);
		List<TodoDto> doingList = todosPerType.get(Type.DOING);
		List<TodoDto> doneList = todosPerType.get(Type.DONE);

		req.setAttribute("todoList", todoList);
		req.setAttribute("doingList", doingList);
		req.setAttribute("doneList", doneList);

		RequestDispatcher rd = req.getRequestDispatcher("./main.jsp");
		rd.forward(req, resp);
	}

}
