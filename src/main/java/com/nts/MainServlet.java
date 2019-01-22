package com.nts;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todo.dao.TodoDao;
import com.nts.todo.dto.TodoDto;
import com.nts.todo.dto.TodoDto.TodoType;

/**
 * MainServlet implementation
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		List<TodoDto> todoListAll = new ArrayList<>();

		List<TodoDto> todoList = new ArrayList<>();
		List<TodoDto> doingList = new ArrayList<>();
		List<TodoDto> doneList = new ArrayList<>();

		try {
			todoListAll = new TodoDao().getTodos();
		} catch (SQLException | ClassNotFoundException | NamingException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		for (TodoDto item : todoListAll) {
			String type = item.getType();
			if (TodoType.TODO.toString().equals(type)) {
				todoList.add(item);
			} else if (TodoType.DOING.toString().equals(type)) {
				doingList.add(item);
			} else if (TodoType.DONE.toString().equals(type)) {
				doneList.add(item);
			}
		}

		request.setAttribute("todoList", todoList);
		request.setAttribute("doingList", doingList);
		request.setAttribute("doneList", doneList);

		request.getRequestDispatcher("/main.jsp").forward(request, response);

	}
}
