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

		List<TodoDto> allTypeList = new TodoDao().getTodos();
		List<TodoDto> todoList = new ArrayList<>();
		List<TodoDto> doingList = new ArrayList<>();
		List<TodoDto> doneList = new ArrayList<>();

		System.out.println("all size : " + allTypeList.size());
		int allTypeListSize = allTypeList.size();
		for (int i = 0; i < allTypeList.size(); i++) {
			String type = allTypeList.get(i).getType();
			System.out.println("type : " + type);

			if (type.equals("TODO")) {
				System.out.println("TODO");
				todoList.add(allTypeList.get(i));
			} else if (type.equals("DOING")) {
				System.out.println("DOING");
				doingList.add(allTypeList.get(i));
			} else if (type.equals("DONE")) {
				System.out.println("DONE");
				doneList.add(allTypeList.get(i));
			}
		}
		System.out.println("todoListSize : " + todoList.size());
		System.out.println("doingListSize : " + doingList.size());
		System.out.println("doneListSize : " + doneList.size());

		request.setAttribute("todoList", todoList);
		request.setAttribute("doingList", doingList);
		request.setAttribute("doneList", doneList);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);

	}

}
