/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		TodoDao todoDao = new TodoDao();
		
		try {
			// keys : todoList, doingList, doneList
			Map<String, List<TodoDto>> result = todoDao.selectTodoListAll();
			request.setAttribute("result", result);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/main.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// todo 내용 셋팅

		// todo update

		// update 결과 값을 가지고 request담기

		// 결과값 전달
	}
}
