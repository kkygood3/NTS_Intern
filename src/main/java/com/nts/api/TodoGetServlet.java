package com.nts.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.dto.TodoDto;

import com.nts.dao.*;

/**
 * main.jsp를 로드할때 마다 DB의 현재 값을 가져오는 서블렛
 * @author 박우성
 */
@WebServlet("/todo")
public class TodoGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");

		TodoDao dao = new TodoDao();
		List<TodoDto> list = dao.getTodos();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(list);

		PrintWriter out = response.getWriter();
		
		out.println(json);
		out.close();
	}
}
