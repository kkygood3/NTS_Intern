package com.nts.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

/**
 * Servlet implementation class TodoTypeServle
 */
@WebServlet("/todo/*")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * todo type 수정 (todo->doing or doing->done)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] pathParts = request.getPathInfo().split("/");
		for (String pp : pathParts)
			System.out.println(pp);
		if (pathParts.length != 2) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		Long id;
		try {
			id = Long.parseLong(pathParts[1]);
		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		TodoDto todo = new TodoDto();
		todo.setId(id);
		if (TodoDao.getInstance().updateTodo(todo) == 1) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
