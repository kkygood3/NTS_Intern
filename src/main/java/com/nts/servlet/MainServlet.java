package com.nts.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Todo 목록을 응답하는 Servlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		TodoDao todoDao = new TodoDao();
		List<TodoDto> todoList = todoDao.getTodos();
		Map<String, List<TodoDto>> todos = todoListToMap(todoList);

		request.setAttribute("todos", todos);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * Todo list를 Todo의 type을 기준으로 분류한 리스트를 map으로 반환
	 */
	private Map<String, List<TodoDto>> todoListToMap(List<TodoDto> todoList) {
		Map<String, List<TodoDto>> todoMap = new HashMap<>();

		for (TodoDto todo : todoList) {
			if (!todoMap.containsKey(todo.getType())) {
				todoMap.put(todo.getType(), new ArrayList<>());
			}
			todoMap.get(todo.getType()).add(todo);
		}
		return todoMap;
	}
}
