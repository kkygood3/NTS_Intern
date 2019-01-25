package com.nts.todolist.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.dto.TodoDto;

/**
 * Servlet implementation class TodoAddServlet
 */
@WebServlet("/todoAdd")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");

		TodoDto todoDto = new TodoDto();

		todoDto.setTitle(request.getParameter("title"));
		todoDto.setName(request.getParameter("name"));
		todoDto.setSequence(Integer.parseInt(request.getParameter("sequence")));

		TodoDao todoDao = new TodoDao();

		if (todoDao.addTodo(todoDto) != 0) {
			System.out.println("��� ����");
			response.sendRedirect("/main");
		} else {
			System.out.println("��� ����");
		}
	}

}
