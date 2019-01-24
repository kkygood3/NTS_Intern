package com.nts.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.exception.ServerErrorException;
import com.nts.model.TodosDto;
import com.nts.service.TodoService;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * Todo 목록을 응답하는 Servlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		try {
			TodoService todoService = TodoService.getInstance();
			TodosDto todos = todoService.getTodos();

			request.setAttribute("todos", todos);
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		} catch (ServerErrorException e) {
			response.sendError(e.getERROR_CODE(), e.getMessage());
		}

	}

}
