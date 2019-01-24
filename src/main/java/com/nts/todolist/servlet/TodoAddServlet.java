/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.AddressingFeature.Responses;

import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.dto.TodoDto;

/**
 * 입력받은 todo (title, name, sequence)를 Database에 추가시키는 servlet
 * @author yongjoon.Park
 *
 */
@WebServlet("/todoAdd")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		request.setCharacterEncoding("utf-8");

		String title = (String)request.getParameter("title").trim();
		String name = (String)request.getParameter("name").trim();
		int sequence = Integer.parseInt((String)request.getParameter("sequence"));

		if (title.length() == 0 || name.length() == 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.write("<script>");
			printWriter.println("alert('알맞은 값을 입력하십시오.');");
			printWriter.write("</script>");
			printWriter.flush();
			printWriter.close();
			// XXX error
			response.sendRedirect("/todoForm");
		} else {
			TodoDto newTodo = new TodoDto(title, name, sequence);

			int result = TodoDao.getInstance().addTodo(newTodo);
			if (result == 1) {
				response.sendRedirect("/main");
			}
		}
	}
}
