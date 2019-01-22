/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

/**
 * todo_register.jsp에서 받은 새로운 정보를 DB에 등록하는 서블렛 
 * @author 박우성
 */
@WebServlet("/register_ok")
public class RegisterOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String whatdo = decodeUTF8(request.getParameter("wname"));
		String name = decodeUTF8(request.getParameter("wname"));
		int priority = new Integer(request.getParameter("priority"));
		
		TodoDao dao = new TodoDao();
		dao.addTodo(new TodoDto(whatdo,name,priority));
		
		response.sendRedirect("main");
	}

	private String decodeUTF8(String str) throws UnsupportedEncodingException{
		return new String(str.getBytes("8859_1"),"utf-8");
	}
}
