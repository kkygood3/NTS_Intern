package com.nts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.JDBC.TodoDao;
import com.nts.JDBC.TodoDto;

/**
 * MainServlet implementation
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String todo = "TODO";
	private static final String doing = "DOING";
	private static final String done = "DONE";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		List<TodoDto> todoList = new ArrayList<>();
		List<TodoDto> doingList = new ArrayList<>();
		List<TodoDto> doneList = new ArrayList<>();

		List<TodoDto> todoListAll = null;

		try {
			todoListAll = new TodoDao().getTodos();
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		for (TodoDto item : todoListAll) {
			if (todo.toString().equals(item.getType())) {
				todoList.add(item);
			} else if (doing.toString().equals(item.getType())) {
				doingList.add(item);
			} else if (done.toString().equals(item.getType())) {
				doneList.add(item);
			}
		}

		request.setAttribute("todoList", todoList);
		request.setAttribute("doingList", doingList);
		request.setAttribute("doneList", doneList);

		request.getRequestDispatcher("/main.jsp").forward(request, response);

	}
}
