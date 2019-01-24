/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.servlet;

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
		Map<Type, List<TodoDto>> todosPerType = getTodosPerType();
		for (Type key : todosPerType.keySet()) {
			req.setAttribute(key.name().toLowerCase() + "List", todosPerType.get(key));
		}
		RequestDispatcher rd = req.getRequestDispatcher("./main.jsp");
		rd.forward(req, resp);
	}

	private Map<Type, List<TodoDto>> getTodosPerType() {
		TodoDao todoDao = TodoDao.getInstance();
		List<TodoDto> todos = todoDao.getTodos();
		return todos.stream().collect(Collectors.groupingBy(TodoDto::getType));
	}
}
