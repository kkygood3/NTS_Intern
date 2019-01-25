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

import com.mysql.cj.util.StringUtils;
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

	/**
	 * 값이 유의미하면 Database에 등록시킨 후 main화면으로 redirect
	 * 입력 받은 값들중 빈 값이 있다면 alert 및 초기화
	 * @author yongjoon.Park
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		request.setCharacterEncoding("utf-8");

		String title = (String)request.getParameter("title").trim();
		String name = (String)request.getParameter("name").trim();
		int sequence = Integer.parseInt((String)request.getParameter("sequence"));

		if (isEmpty(title, name)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.write("<script>");
			printWriter.write("alert('알맞은 값을 입력하십시오.');");
			printWriter.write("location.href='/todoForm'");
			printWriter.write("</script>");
			printWriter.close();
		} else {
			TodoDto newTodo = new TodoDto(title, name, sequence);

			int result = TodoDao.getInstance().addTodo(newTodo);
			if (result == 1) {
				response.sendRedirect("/main");
			}
		}
	}
	
	/**
	 * 1개 이상의 Stirng value를 빈값 혹은 null인지 확인하는 method
	 * @author yongjoon.Park
	 * @param 빈 값인지 확인하려는 String value
	 * @return String value가 null 혹은 빈값("")이라면 true를 반환  
	 */
	private boolean isEmpty(String... values) {
		for(String value : values) {
			if(StringUtils.isEmptyOrWhitespaceOnly(value)) {
				return true;
			}
		}
		return false;
	}
}
