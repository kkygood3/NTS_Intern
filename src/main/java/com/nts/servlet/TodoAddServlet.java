package com.nts.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.model.TodoDto;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * 새로운 할일을 등록하는 Servlet
 */
@WebServlet("/newtodo")
public class TodoAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/* 
	 * Todo의 내용을 입력할 수 있는 form을 가지는 jsp 페이지로 forward
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/newtodo.jsp");
		requestDispatcher.forward(request, response);
	}

	/* 
	 * Todo의 데이터를 DB에 저장
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String title = request.getParameter("title");
		String name = request.getParameter("name");
		int sequence = Integer.parseInt(request.getParameter("sequence"));

		TodoDto todo = new TodoDto();
		todo.setTitle(title);
		todo.setName(name);
		todo.setSequence(sequence);

		TodoDao todoDao = new TodoDao();
		try {
			todoDao.addTodo(todo);
		} catch (IllegalStateException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "server error, sorry");
		}

		response.sendRedirect("/main");
	}

}
