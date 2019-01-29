package com.nts;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.todo.dao.TodoDao;
import com.nts.todo.dto.TodoDto;
import com.nts.todo.dto.TodoDto.TodoType;

/**
 * TodoTypeServlet implementation
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@WebServlet("/updatetodo")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * todo type변경에 대한 메소드, 실패시에 http status : SC_FORBIDDEN, 403	
	 * @throws IOException 
	 * @throws ServletException 
	 * @doPut(request,response)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String temp = "";
		String todoInfo = "";

		//request receiving through InputStream
		try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));) {
			while ((temp = br.readLine()) != null) {
				todoInfo += temp;
			}
		} catch (IOException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		try {
			//parsing data and mapping through jackson-bind to Map
			TodoDto todo = new ObjectMapper().readValue(todoInfo, TodoDto.class);

			//replace the type to nextType;
			String currentType = todo.getType();
			todo.setType(TodoType.getNextType(currentType));

			if (new TodoDao().updateTodo(todo) > 0) {
				response.getWriter().write("SUCCESS");
			} else {
				response.sendError(HttpServletResponse.SC_NO_CONTENT);
			}

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
