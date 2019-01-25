/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;
import com.nts.dto.TodoSequence;

/**
 * post방식으로 요청을 가져와 DB에 Insert시키는 서블릿입니다.
 * @author kkygood3
 */
@WebServlet("/add")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");

		String title = request.getParameter("input-title");
		String name = request.getParameter("input-name");
		Integer sequence = 0;

		sequence = divSequence(request, sequence);

		TodoDto todoDto = new TodoDto(title, name, sequence);
		TodoDao todoDao = new TodoDao();

		if (todoDao.addTodo(todoDto) == 1) {
			System.out.println("Insert complete");
		} else {
			System.out.println("Insert fail");
		}

		response.sendRedirect("/main");

	}

	private Integer divSequence(HttpServletRequest request, Integer sequence) {
		TodoSequence todoSequence = TodoSequence.valueOf(request.getParameter("input-sequence"));

		switch (todoSequence) {
			case FIRST:
				sequence = 1;
				break;
			case SECOND:
				sequence = 2;
				break;
			case THIRD:
				sequence = 3;
				break;
		}
		return sequence;
	}

}
