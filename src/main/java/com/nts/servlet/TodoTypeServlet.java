package com.nts.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dto.TodoDto;
import com.nts.exception.DataAccessException;
import com.nts.service.TodoService;

/**
 *
 * @description : 수신된 id의 DTO type을 update
 * @filename : TodoTypeServlet.java
 * @package : com.nts.servlet
 * @author : Seokhyeon Choi
 * @method : void doPost(HttpServletRequest request, HttpServletResponse
 *         response)
 */
@WebServlet("/todo-type")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @description : 수신된 id의 type정보를 수정
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Long id = Long.parseLong(request.getParameter("id"));
		String type = request.getParameter("type");
		TodoDto todoDto = new TodoDto(id, type);

		
		try {
			TodoService.getInstance().updateTodo(todoDto);
			out.write("success");
		} catch (DataAccessException e) {
			response.sendError(500, e.getErrorMessage());
		}
	}
}
