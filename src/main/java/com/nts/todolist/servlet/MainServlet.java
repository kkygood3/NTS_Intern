/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todolist.common.TodoStatus;
import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.dto.TodoDto;

/**
 * main.jsp에 todo list를 조회 후 출력해주는 servlet
 * @author yongjoon.Park
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		List<TodoDto> includeAllType = TodoDao.getInstance().getTodos();

		List<TodoDto> todoList = new ArrayList<>();
		List<TodoDto> doingList = new ArrayList<>();
		List<TodoDto> doneList = new ArrayList<>();

		groupingList(includeAllType, todoList, doingList, doneList);

		request.setAttribute("todoList", todoList);
		request.setAttribute("doingList", doingList);
		request.setAttribute("doneList", doneList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
		requestDispatcher.forward(request, response);
	}

	private void groupingList(List<TodoDto> includeAllType, List<TodoDto> todoList, List<TodoDto> doingList,
		List<TodoDto> doneList) {
		
		for (TodoDto todo : includeAllType) {
			String type = todo.getType();
			
			if (TodoStatus.TODO.getValue().equals(type)) {
				todoList.add(todo);
			} else if (TodoStatus.DOING.getValue().equals(type)) {
				doingList.add(todo);
			} else {
				doneList.add(todo);
			}
		}
	}

}
