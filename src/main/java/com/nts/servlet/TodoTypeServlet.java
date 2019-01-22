/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;
import com.nts.dto.TodoDto.Type;

/**
 * Todo type 변경처리를 위한 서블릿
 * @author jinwoo.bae
 */
@WebServlet("/updateTodo")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * id와 type값을 받아 해당 todo 데이터의 type값을 다음상태값으로 변경
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = (String)req.getParameter("id");
		String type = (String)req.getParameter("type");

		if (id == null || type.isEmpty()) {
			throw new NullPointerException();
		}

		TodoDto todoDto = new TodoDto();
		todoDto.setId(Long.parseLong(id));
		todoDto.setType(Type.valueOf(type).getNext());

		TodoDao todoDao = new TodoDao();
		int updateCount = todoDao.updateTodo(todoDto);

		PrintWriter out = resp.getWriter();
		out.println(updateCount);
		out.close();
	}
}
