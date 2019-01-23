/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

/**
 * todo_register.jsp페이지를 띄우고 데이터 등록을 수행하는 서블렛
 * @author 박우성
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * GET method 호출시 todo_register.jsp페이지로 포워딩 수행
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/todo_register.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * POST method 호출시 todo_register.jsp페이지에서 받은 패러미터로 할일 등록 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String whatdo = decodeUTF8(request.getParameter("whatdo"));
		String name = decodeUTF8(request.getParameter("wname"));
		int priority = new Integer(request.getParameter("priority"));
		
		TodoDao dao = TodoDao.getInstance();
		dao.addTodo(new TodoDto(whatdo,name,priority));
		
		response.sendRedirect("main");
	}
	
	/**
	 * form에서 Post로 값을 전송한 패러미터를 받기위해 필요한 한글 디코딩
	 */
	private String decodeUTF8(String str) throws UnsupportedEncodingException{
		return new String(str.getBytes("8859_1"),"utf-8");
	}
}
