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
 * todo type 수정
 * @author 시윤
 * Servlet implementation class TodoTypeServlet
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
		Long id = getId(request.getPathInfo());
		if (id == -1) {
			response.sendRedirect("./todos");
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

	/*
	 * 잘못된요청 - main page로 reidrect
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.sendRedirect("./todos");
	}

	/*
	 * path가 유효하면 id를 return
	 * 유효하지 않으면 -1을 return
	 */
	private Long getId(String path) {
		String[] pathParts = path.split("/");
		if (pathParts.length != 2) {
			return (long)-1;
		}
		Long id;
		try {
			id = Long.parseLong(pathParts[1]);
		} catch (NumberFormatException e) {
			return (long)-1;
		}
		return id;
	}
}
