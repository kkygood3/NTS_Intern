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
import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.dto.TodoDto;
import com.nts.todolist.exception.ServerErrorException;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@WebServlet("/todoAdd")
public class TodoAddServlet extends HttpServlet {

	static String errorMessage;

	/**
	 * 입력받은 title, name, sequence 속성값을 갖는 TodoDto 객체를 데이터베이스에 삽입 후, 메인으로 이동
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String title = request.getParameter("title");
		String name = request.getParameter("name");
		String sequenceString = request.getParameter("sequence");

		errorMessage = null;
		if (!isCorrectValue(title, name, sequenceString)) {
			AlertMessage.showMessage(response, errorMessage, "/todoForm");
			System.out.println(errorMessage);
			return;
		}

		int sequence = Integer.parseInt(sequenceString);

		TodoDto todoDto = new TodoDto();
		todoDto.setTitle(title);
		todoDto.setName(name);
		todoDto.setSequence(sequence);

		try {
			if (TodoDao.getInstance().addTodo(todoDto) == 1) {
				AlertMessage.showMessage(response, "등록 성공", "/main");
				System.out.println("TodoAddServlet.java 등록 성공");
			} else {
				AlertMessage.showMessage(response, "등록 실패", "/todoForm");
				System.out.println("TodoAddServlet.java 등록 실패");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();

			ServerErrorException serverErrorException = new ServerErrorException(e);
			response.sendError(serverErrorException.getErrorCode(), serverErrorException.getMessage());
			return;
		}
	}

	/**
	 * 제목, 이름, 우선순위의 값이 유효한지 차례로 검사
	 * @param title 입력한 제목
	 * @param name 입력한 이름
	 * @param sequenceString 입력한 우선순위
	 * @return 유효한 값이면 true를, 그렇지 않다면 false를 반환
	 */
	public static boolean isCorrectValue(String title, String name, String sequenceString)
		throws IOException {
		if (!isCorrectTitle(title)) {
			errorMessage = ("제목이 올바르지 않습니다.");
			return false;
		}
		if (!isCorrectName(name)) {
			errorMessage = ("이름이 올바르지 않습니다.");
			return false;
		}
		if (!isCorrectSequence(sequenceString)) {
			errorMessage = ("우선순위가 올바르지 않습니다.");
			return false;
		}
		return true;
	}

	public static boolean isCorrectTitle(String title) {
		if (title == null) {
			return false;
		}
		if (title.length() > 24) {
			return false;
		}
		// 공백을 포함한 영문, 한글, 숫자로 구성된 문자열
		if (Pattern.matches("^[a-zA-Z가-힣0-9\\s]*$", title)) {
			return true;
		}
		return false;
	}

	public static boolean isCorrectName(String name) {
		if (name == null) {
			return false;
		}
		if (name.length() > 17) {
			return false;
		}
		// 공백을 포함하지 않은 영문, 한글, 숫자로 구성된 문자열
		if (Pattern.matches("^[a-zA-Z가-힣0-9]*$", name)) {
			return true;
		}
		return false;
	}

	public static boolean isCorrectSequence(String sequenceString) {
		if (sequenceString == null) {
			return false;
		}
		if (Pattern.matches("^[1-3]$", sequenceString)) {
			return true;
		}
		return false;
	}
}
