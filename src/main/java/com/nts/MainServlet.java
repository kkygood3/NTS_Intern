package com.nts;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodoServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		try {
			List<List<TodoDTO>> list_all = TodoDAO.getToDoList();
			request.setAttribute("todoList", list_all.get(0));
			request.setAttribute("doingList", list_all.get(1));
			request.setAttribute("doneList", list_all.get(2));
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
