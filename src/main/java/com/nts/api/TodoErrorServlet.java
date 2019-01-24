package com.nts.api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 에러페이지
 * @author 시윤
 * Servlet implementation class TodoErrorServlet
 */
@WebServlet("/error")
public class TodoErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * error.html로 forwarding
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./error.html");
		requestDispatcher.forward(request, response);
	}

}
