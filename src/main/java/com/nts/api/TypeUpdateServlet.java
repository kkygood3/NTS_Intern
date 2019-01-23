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

/**
 * 메인페이지에서 화살표를 눌렀을 때 비동기로 DB갱신을 위해 호출하는 서블렛
 * @author 박우성
 */
@WebServlet("/update/*")
public class TypeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			Integer targetID = new Integer(request.getParameter("id"));
			String targetType = request.getParameter("type");

			boolean invalidTagName = !("TODO".equals(targetType) || "DOING".equals(targetType) || "DONE".equals(targetType));
			if (invalidTagName)
				throw new Exception();

			TodoDao dao = TodoDao.getInstance();

			if (dao.updateTodo(new TodoDto(targetID, targetType)))
				out.print("success");
			else
				throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error/invalid_access.jsp");
		} finally {
			out.close();
		}
	}

}
