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

import com.nts.config.checkInputForm;
import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;
import com.nts.exception.ServerErrorException;

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
		String sequenceStringType = request.getParameter("input-sequence");

		//입력형식이 맞는지 확인 후 맞지 않다면 에러페이지 출력
		if (checkInputForm.isRightInputForm(title, name, sequenceStringType)) {
			response.sendError(500, "입력 형식이 잘못되었습니다.");
			return;
		}

		int sequence = Integer.parseInt(sequenceStringType);

		TodoDto todoDto = new TodoDto(title, name, sequence);

		try {
			TodoDao.TODODAO.addTodo(todoDto);

		} catch (ServerErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(e.getERROR_CODE(), e.getMESSAGE());
		}

		response.sendRedirect("/main");

	}

}
