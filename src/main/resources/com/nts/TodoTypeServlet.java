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

import com.nts.dao.TodoDao;

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

		try (PrintWriter printWriter = response.getWriter()) {
			String type = request.getParameter("type");
			Long id = Long.parseLong(request.getParameter("id"));

			TodoDao todoDao = new TodoDao();

			type = moveNextType(type);

			if (todoDao.updateTodo(id, type) == -1) {
				printWriter.print("error");
			} else {
				printWriter.print("success");
			}

		} catch (NullPointerException e) {
			System.out.println("type을 Long으로 형변환 중 에러가 발생했습니다.");
		}

	}

	private String moveNextType(String type) {
		switch (type) {
			case "TODO":
				type = "DOING";
				break;
			case "DOING":
				type = "DONE";
				break;
		}
		return type;
	}

}
