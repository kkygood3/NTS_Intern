package com.nts.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.exception.ServerErrorException;
import com.nts.model.TodoDto;
import com.nts.service.TodoService;

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

		// 요청된 uri가 의도한 형태와 다를경우(ex : /todo/1 or /todo/1/done/dummy ...) 404(Not Found) 반환 
		if (pathParts.length != 3) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

		// id parameter값이 long형으로 변환불가능한 형일경우 400(Bad Request) 반환
		TodoDto todo = new TodoDto();
		try {
			todo.setId(Long.parseLong(pathParts[1]));
			todo.setType(pathParts[2]);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request Parameter value is wrong");
			return;
		}

		TodoService todoService = TodoService.getInstance();
		try {
			todoService.changeTypeToNext(todo);
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().append("success");
		} catch (ServerErrorException e) {
			response.sendError(e.getERROR_CODE(), e.getMessage());
			return;
		}

	}

}
