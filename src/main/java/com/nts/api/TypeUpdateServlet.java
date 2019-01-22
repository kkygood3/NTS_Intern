package com.nts.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

/**
 * Servlet implementation class TypeUpdateServlet
 */
@WebServlet("/update/*")
public class TypeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer targetID = new Integer(request.getParameter("id"));
		String targetType = request.getParameter("type");
		TodoDao dao = new TodoDao();
		dao.updateTodo(new TodoDto(targetID,targetType));
	}

}
