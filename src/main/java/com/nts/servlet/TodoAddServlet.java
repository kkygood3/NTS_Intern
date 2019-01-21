/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 입력한 일정추가 처리를 위한 서블릿
 * @author jinwoo.bae
 */
@WebServlet("/newTodo")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// TODO: 일정 등록화면으로 포워딩 구현
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}

	// TODO: 일정을 데이터베이스에 등록한후 main.jsp로 리다이렉트 구현
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
