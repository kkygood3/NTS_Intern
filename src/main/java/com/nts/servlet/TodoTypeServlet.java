package com.nts.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.model.TodoDto;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * Todo의 상태를 변환하는 Servlet
 */
@WebServlet("/todo/*")
public class TodoTypeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");

		if (pathParts.length == 3) {
			long id = Long.parseLong(pathParts[1]);
			String type = pathParts[2];

			TodoDto todo = new TodoDto();
			todo.setId(id);
			todo.setType(type);

			TodoDao todoDao = new TodoDao();
			try {
				todoDao.updateTodo(todo);
				response.setStatus(HttpServletResponse.SC_OK);
			} catch (IllegalStateException e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

	}

}
