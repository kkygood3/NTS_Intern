/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.servlet;

import java.io.IOException;

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
@WebServlet("/todoUpdate/*")
public class TodoTypeServlet extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		Long id = Long.parseLong(request.getParameter("id"));
		String type = request.getParameter("type");
		String updateType = "";
		if (type.equals("TODO")) {
			updateType = "DOING";
		} else if (type.equals("DOING")) {
			updateType = "DONE";
		}

		TodoDao todoDao = new TodoDao();
		TodoDto todoDto = new TodoDto();
		todoDto.setId(id);
		todoDto.setType(updateType);

		int typeUpdateState = 0;

		if (type.equals("TODO")) {
			typeUpdateState = todoDao.updateTodo(todoDto);
		} else if (type.equals("DOING")) {
			typeUpdateState = todoDao.updateTodo(todoDto);
		}

		if (typeUpdateState == 1) {
			System.out.println("TodoTypeServlet 성공");
		} else {
			System.out.println("TodoTypeServlet 실패");
		}
	}

}
