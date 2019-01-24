package com.nts.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;
import com.nts.Const;
import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

/**
 * todo type 수정
 * @author 시윤
 * Servlet implementation class TodoTypeServlet
 */
@WebServlet("/todo/update")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * todo type 수정 (todo->doing or doing->done)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		Long id = getId(request.getParameter(Const.ID));
		String type = (String)request.getParameter(Const.TYPE);

		if (id > -1 && (Const.TODO.equals(type) || Const.DOING.equals(type))) {
			TodoDto todo = new TodoDto();
			todo.setId(id);
			todo.setType(type);
			if (TodoDao.getInstance().updateTodo(todo) == 1) {
				return;
			}
		}
		response.sendRedirect("./error");
	}

	/*
	 * id가 유효하면 id를 return
	 * 유효하지 않으면 -1을 return
	 */
	private Long getId(String id) {
		Long longId;
		try {
			longId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			longId = (long)-1;
		}
		return longId;
	}
}
