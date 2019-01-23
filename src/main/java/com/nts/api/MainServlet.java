package com.nts.api;

import java.io.IOException;
import java.util.List;

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
 * todo 등록, 수정, 조회
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("utf-8");
		List<TodoDto> todos = TodoDao.getInstance().getTodos();

		request.setAttribute("todos", todos);
		
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
	}

}
