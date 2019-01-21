package com.nts;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * MainServlet implementation
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		try {
			List<TodoDto> list_all = TodoDao.getTodos();
			List<TodoDto> todoList = new ArrayList<>();
			List<TodoDto> doingList = new ArrayList<>();
			List<TodoDto> doneList = new ArrayList<>();
			for (TodoDto item : list_all) {
				if (item.getType().equals("TODO")) {
					todoList.add(item);
				} else if (item.getType().equals("DOING")) {
					doingList.add(item);
				} else if (item.getType().equals("DONE")) {
					doneList.add(item);
				}
			}

			request.setAttribute("todoList", todoList);
			request.setAttribute("doingList", doingList);
			request.setAttribute("doneList", doneList);

			request.getRequestDispatcher("/main.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
