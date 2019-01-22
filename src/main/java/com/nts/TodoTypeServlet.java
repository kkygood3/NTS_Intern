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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.JDBC.TodoDao;
import com.nts.JDBC.TodoDto;

/**
 * TodoTypeServlet implementation
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@WebServlet("/updateTodo")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @doPut(request,response)
	 * todo type변경에 대한 메소드, 실패시에 http status : SC_FORBIDDEN, 403	
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String temp = null;
		String todo_info = "";

		try {
			//request receiving through InputStream
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			while ((temp = br.readLine()) != null) {
				todo_info += temp;
			}
			br.close();

			//parsing data and mapping through jackson-bind to Map
			Map<String, String> myMap = new HashMap<String, String>();
			myMap = new ObjectMapper().readValue(todo_info, HashMap.class);
			TodoDto todo = new TodoDto(Long.valueOf(myMap.get("id")), null, null, 0, null, myMap.get("type"));

			if (new TodoDao().updateTodo(todo) == 0) {
				throw new SQLException();
			}

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED);
		}
	}
}
