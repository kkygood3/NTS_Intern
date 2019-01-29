/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todolist.common.StringUtils;
import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.dto.TodoDto;
import com.nts.todolist.exception.DatabaseAccessException;

/**
 * 입력받은 todo (title, name, sequence)를 Database에 추가시키는 servlet
 * @author yongjoon.Park
 *
 */
@WebServlet("/todoAdd")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 값이 유의미하면 Database에 등록시킨 후 main화면으로 redirect
	 * 입력 받은 값들중 빈 값이 있다면 alert 및 초기화
	 * @author yongjoon.Park
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		request.setCharacterEncoding(StandardCharsets.UTF_8.toString());

		String title = request.getParameter("title");
		String name = request.getParameter("name");
		String sequence = request.getParameter("sequence");
		
		// 양식과 다를 경우 Alert
		if(!StringUtils.isValid(title, name, sequence)) {
			printErrorAlertToJsp(response);
			return;
		}

		TodoDto newTodo = new TodoDto(title.trim(), name.trim(), Integer.parseInt(sequence));

		try {
			TodoDao.getInstance().addTodo(newTodo);
			response.sendRedirect("/main");
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/error.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		
		
	}

	private void printErrorAlertToJsp(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.write("<script>");
		printWriter.write("alert('알맞은 값을 입력하십시오.');");
		printWriter.write("location.href='/todoForm'");
		printWriter.write("</script>");
		printWriter.close();
	}


}
