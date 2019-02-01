/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todolist.common.AlertMessage;
import com.nts.todolist.common.TodoType;
import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.dto.TodoDto;
import com.nts.todolist.exception.ServerErrorException;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@WebServlet("/todoUpdate/*")
public class TodoTypeServlet extends HttpServlet {

	static String errorMessage;

	/**
	 * TodoDto 객체와 관련된 데이터베이스의 type 속성값을 갱신 (TODO에서 DOING으로, DOING에서 DONE으로)
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		Long id = Long.parseLong(request.getParameter("id"));
		TodoType type = TodoType.valueOf(request.getParameter("type"));

		errorMessage = null;
		if (!isCorrectValue(id, type)) {
			AlertMessage.showMessage(response, errorMessage, "/main");
			System.out.println(errorMessage);
			return;
		}

		TodoDto todoDto = new TodoDto();
		TodoType nextType = type.getNextType();
		todoDto.setId(id);
		todoDto.setType(nextType.getTodoType());

		int typeUpdateState = 0;
		try {
			typeUpdateState = TodoDao.getInstance().updateTodo(todoDto);
		} catch (RuntimeException e) {
			e.printStackTrace();

			ServerErrorException serverErrorException = new ServerErrorException(e);
			response.sendError(serverErrorException.getErrorCode(), serverErrorException.getMessage());
			return;
		}
		if (typeUpdateState == 1) {
			System.out.println("TodoTypeServlet.java 타입 변경 성공");
		} else {
			AlertMessage.showMessage(response, "타입 변경 실패", "/main");
			System.out.println("TodoTypeServlet.java 타입 변경 실패");
		}
	}

	/**
	 * 아이디와 타입의 값이 유효한지 차례로 검사
	 * @param id 버튼을 클릭한 할 일의 아이디
	 * @param type 버튼을 클릭한 할 일의 타입
	 * @return 유효한 값이면 true를, 그렇지 않다면 false를 반환
	 */
	public static boolean isCorrectValue(Long id, TodoType type) {
		if (!isCorrectId(id)) {
			errorMessage = ("아이디가 올바르지 않습니다.");
			System.out.println("TodoAddServlet.java 아이디가 올바르지 않습니다.");
		}
		if (!isCorrectType(type)) {
			errorMessage = ("타입이 올바르지 않습니다.");
			System.out.println("TodoAddServlet.java 타입이 올바르지 않습니다.");
		}
		return true;
	}

	public static boolean isCorrectId(Long id) {
		if (id == 0) {
			return false;
		}
		if (Pattern.matches("^[0-9]*$", Long.toString(id))) {
			return true;
		}
		return false;
	}

	public static boolean isCorrectType(TodoType type) {
		if (type == null) {
			return false;
		}
		for (TodoType todoType : TodoType.getTodoTypeList()) {
			if (type == todoType) {
				return true;
			}
		}
		return false;
	}
}
