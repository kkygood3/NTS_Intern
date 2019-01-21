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
 * Todo type 변경처리를 위한 서블릿
 * @author jinwoo.bae
 */
@WebServlet("/updateTodo")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// TODO: id값을 받아 해당 todo의 type을 변경하는것을 구현
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}
}
