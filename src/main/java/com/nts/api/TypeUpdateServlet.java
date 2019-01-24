/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;
import com.nts.dto.TodoType;

/**
 * 메인페이지에서 화살표를 눌렀을 때 비동기로 DB갱신을 위해 호출하는 서블렛
 * @author 박우성
 */
@WebServlet("/update")
public class TypeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {
			Integer targetID = new Integer(request.getParameter("id"));
			TodoType targetType = TodoType.valueOf(request.getParameter("type"));

			TodoDao dao = TodoDao.getInstance();

			TodoDto targetTodo = new TodoDto(targetID, targetType);

			if (dao.updateTodo(targetTodo))
				out.print("success");
			else
				throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error/invalid_access.jsp");
		}
	}

}
