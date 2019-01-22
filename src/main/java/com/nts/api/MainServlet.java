/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.dto.TodoDto;

import com.nts.dao.*;

/**
 * main.jsp를 로드할때 마다 DB의 현재 값을 가져오는 서블렛
 * @author 박우성
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");

		TodoDao dao = new TodoDao();
		List<TodoDto> list = dao.getTodos();

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(list);
		
		request.setAttribute("json", json);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
	}
}
