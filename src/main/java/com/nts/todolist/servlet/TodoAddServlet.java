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

import com.nts.todolist.check.ValidationCheck;
import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.dto.TodoDto;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@WebServlet("/todoAdd")
public class TodoAddServlet extends HttpServlet {

	private static String title;
	private static String name;
	private static String sequenceString;
	private static int sequence;

	/**
	 * 입력받은 title, name, sequence 속성값을 갖는 TodoDto 객체를 데이터베이스에 삽입 후, main으로 이동합니다.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		request.setCharacterEncoding("utf-8");

		title = request.getParameter("title");
		name = request.getParameter("name");
		sequenceString = request.getParameter("sequence");

		if (ValidationCheck.isCorrentValue(title, name, sequenceString)) {
			sequence = Integer.parseInt(sequenceString);

			TodoDto todoDto = new TodoDto();
			todoDto.setTitle(title);
			todoDto.setName(name);
			todoDto.setSequence(sequence);

			try {
				if (TodoDao.getInstance().addTodo(todoDto) == 1) {
					//
					System.out.println("TodoAddServlet.java 등록 성공");
					response.sendRedirect("/main");
				} else {
					//
					System.out.println("TodoAddServlet.java 등록 실패");
				}
			} catch (Exception e) {}
		} else {
			//
			System.out.println("TodoAddServlet.java 예외처리 1");
			response.sendRedirect("/todoForm");
		}
	}
}
