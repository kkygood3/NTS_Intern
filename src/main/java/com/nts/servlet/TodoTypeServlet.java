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

import com.mysql.jdbc.StringUtils;
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
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idString = req.getParameter("id");
		String type = req.getParameter("type");

		if (StringUtils.isNullOrEmpty(idString) || StringUtils.isNullOrEmpty(type)) {
			ErrorHandler.alertMessage("값을 제대로 입력해주세요.", resp);
			return;
		}
		long id = Long.parseLong(idString);
		TodoDao todoDao = TodoDao.getInstance();
		TodoDto todoDto = todoDao.getTodo(id);

		/**
		 * 입력한 type이 DB에있는 type과 다른지 검사
		 */
		if (todoDto == null || !type.equals(todoDto.getType().name())) {
			ErrorHandler.alertMessage("잘못된 타입을 입력하셨습니다.", resp);
			return;
		}
		todoDto.setType(Type.valueOf(type).getNext());
		int updateCount = todoDao.updateTodo(todoDto);

		PrintWriter out = resp.getWriter();
		out.println(updateCount);
		out.close();
	}
}
