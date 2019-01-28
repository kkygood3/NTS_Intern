/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.dto.TodoDto;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@WebServlet("/todoAdd")
public class TodoAddServlet extends HttpServlet {

	/**
	 * 입력받은 title, name, sequence 속성값을 갖는 TodoDto 객체를 데이터베이스에 삽입 후, main으로 이동합니다.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");

		TodoDto todoDto = new TodoDto();
		todoDto.setTitle(request.getParameter("title"));
		todoDto.setName(request.getParameter("name"));
		todoDto.setSequence(Integer.parseInt(request.getParameter("sequence")));

		TodoDao todoDao = new TodoDao();
		if (todoDao.addTodo(todoDto) != 0) {
			System.out.println("등록 성공");
			response.sendRedirect("/main");
		} else {
			System.out.println("등록 실패");
		}
	}

}
