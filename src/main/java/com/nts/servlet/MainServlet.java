/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		TodoDao todoDao = new TodoDao();
		List<TodoDto> todos = todoDao.getTodos();
		List<TodoDto> todoList = new ArrayList<TodoDto>();
		List<TodoDto> doingList = new ArrayList<TodoDto>();
		List<TodoDto> doneList = new ArrayList<TodoDto>();

		for (TodoDto todo : todos) {
			if (todo.getType() == Type.TODO) {
				todoList.add(todo);
			} else if (todo.getType() == Type.DOING) {
				doingList.add(todo);
			} else if (todo.getType() == Type.DONE) {
				doneList.add(todo);
			}
		}

		req.setAttribute("todoList", todoList);
		req.setAttribute("doingList", doingList);
		req.setAttribute("doneList", doneList);

		RequestDispatcher rd = req.getRequestDispatcher("./main.jsp");
		rd.forward(req, resp);
	}

}
