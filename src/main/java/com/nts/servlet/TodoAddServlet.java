package com.nts.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.exception.ServerErrorException;
import com.nts.model.TodoDto;
import com.nts.service.TodoService;

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
		request.getRequestDispatcher("/WEB-INF/jsp/newtodo.jsp").forward(request, response);
	}

	/* 
	 * Todo의 데이터를 DB에 저장
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// sequence parameter값이 정수형으로 변환불가능한 형일경우 400(Bad Request) 반환
		TodoDto todo = new TodoDto();
		try {
			todo.setTitle(request.getParameter("title"));
			todo.setName(request.getParameter("name"));
			todo.setSequence(Integer.parseInt(request.getParameter("sequence")));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request Parameter value is wrong");
			return;
		}

		TodoService todoService = TodoService.getInstance();
		try {
			todoService.addTodo(todo);
		} catch (ServerErrorException e) {
			response.sendError(e.getERROR_CODE(), e.getMessage());
			return;
		}

		response.sendRedirect("/main");
	}

}
