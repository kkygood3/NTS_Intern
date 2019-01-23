package com.nts.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.Const;
import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

/**
 * todo전체 조회
 * @author 시윤
 * Servlet implementation class TodoServlet
 */
@WebServlet("/todos")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * todo 전체 조회
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		List<TodoDto> todos = TodoDao.getInstance().getTodos();

		Map<String, List<TodoDto>> groupedTodos = todos.stream().collect(Collectors.groupingBy(TodoDto::getType));
		request.setAttribute("todo", groupedTodos.get(Const.TODO));
		request.setAttribute("doing", groupedTodos.get(Const.DOING));
		request.setAttribute("done", groupedTodos.get(Const.DONE));

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
		requestDispatcher.forward(request, response);
	}
}
