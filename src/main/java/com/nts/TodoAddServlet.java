package com.nts;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todo.dao.TodoDao;
import com.nts.todo.dto.TodoDto;

/**
 * TodoAddServlet implementation
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@WebServlet("/todoadd")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws IOException 
	 * @doPost(request,response)
	 * add todo에 대한 request를 받아서 처리함.
	 * 실패시에 alert 창 팝업.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		try {
			TodoDto dto = new TodoDto();
			dto.setTitle(request.getParameter("title"));
			dto.setName(request.getParameter("name"));
			dto.setSequence(Integer.valueOf(request.getParameter("sequence")));

			if (new TodoDao().addTodo(dto) > 0) {
				response.sendRedirect("/jaewonlee/main");
			}

		} catch (SQLException | ClassNotFoundException | NamingException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

}
