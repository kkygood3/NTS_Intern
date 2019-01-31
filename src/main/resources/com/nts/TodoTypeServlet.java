/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.config.InputFormValidator;
import com.nts.dao.TodoDao;
import com.nts.dto.TodoType;
import com.nts.exception.ServerErrorException;

/**
 * typeup.js로부터 id와 type을 가져와 해당 id의 type을 한단계 올려주는 Servlet입니다.
 * @author kkygood3
 */
@WebServlet("/update")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		PrintWriter printWriter = response.getWriter();

		String typeStringType = request.getParameter("type");
		String idStringType = request.getParameter("id");

		//입력형식이 맞는지 확인 후 맞지 않다면 에러페이지 출력
		if (InputFormValidator.isNotVaild(idStringType, typeStringType)) {
			response.sendRedirect("/main");
			return;
		}

		TodoType type = TodoType.valueOf(typeStringType);
		Long id = Long.parseLong(idStringType);

		try {
			if (TodoDao.TODODAO.updateTodo(id, type) == 1) {
				printWriter.print("success");
			} else {
				printWriter.print("error");
				System.out.println("db업데이트에 실패했습니다");
			}
		} catch (ServerErrorException e) {
			e.printStackTrace();
			response.sendError(e.getERROR_CODE(), e.getMESSAGE());
		}

	}

}
