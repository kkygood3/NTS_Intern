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

import com.nts.todolist.common.TodoType;
import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.dto.TodoDto;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		List<TodoDto> allTypeList = TodoDao.getInstance().getTodos();
		List<TodoDto> todoList = new ArrayList<>();
		List<TodoDto> doingList = new ArrayList<>();
		List<TodoDto> doneList = new ArrayList<>();

		for (TodoDto todoDto : allTypeList) {
			TodoType type = TodoType.valueOf(todoDto.getType());

			if (type == TodoType.TODO) {
				todoList.add(todoDto);
			} else if (type == TodoType.DOING) {
				doingList.add(todoDto);
			} else if (type == TodoType.DONE) {
				doneList.add(todoDto);
			}
		}

		request.setAttribute("todoList", todoList);
		request.setAttribute("doingList", doingList);
		request.setAttribute("doneList", doneList);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
		requestDispatcher.forward(request, response);

	}

	//	private List<TodoDto> getTodoDtoList(ㅁ) {
	//		List<TodoDto> todoDtoList = new ArrayList<>();
	//
	//		for (TodoDto todoDto : allTypeList) {
	//
	//			if (todoDtoType == todoType) {
	//				todoDtoList.add(todoDto);
	//			}
	//		}
	//		return todoDtoList;
	//	}

}
