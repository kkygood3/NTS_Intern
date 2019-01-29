/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todolist.check.ValidationCheck;
import com.nts.todolist.common.TodoType;
import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.dto.TodoDto;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@WebServlet("/todoUpdate/*")
public class TodoTypeServlet extends HttpServlet {

	/**
	 * TodoDto 객체와 관련된 데이터베이스의 type 속성값을 갱신합니다. (TODO에서 DOING으로, DOING에서 DONE으로)
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		Long id = Long.parseLong(request.getParameter("id"));
		String typeString = request.getParameter("type");

		System.out.println("id : " + id);
		System.out.println("typeString : " + typeString);
		if (ValidationCheck.isCorrentValue(id, typeString)) {
			TodoType type = TodoType.valueOf(typeString);
			TodoType nextType = type.getNextType();

			TodoDto todoDto = new TodoDto();
			todoDto.setId(id);
			todoDto.setType(nextType.getTodoType());

			TodoDao todoDao = TodoDao.getInstance();
			int typeUpdateState = 0;
			if (type != TodoType.DONE) {
				try {
					typeUpdateState = todoDao.updateTodo(todoDto);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (typeUpdateState == 1) {
				//
				System.out.println("TodoTypeServlet.java 타입 변경 성공");
			} else {
				//
				System.out.println("TodoTypeServlet.java 타입 변경 실패");
			}
		} else {
			//
			System.out.println("TodoAddServlet.java 예외처리 1");
			response.sendRedirect("/main");
		}
	}

}
