/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
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
 *
 * @description : newtodo.jsp로 forwarding
 * @filename : TodoFormServlet.java
 * @package : com.nts.servlet
 * @author : Seokhyeon Choi
 * @method : void doGet(HttpServletRequest request, HttpServletResponse
 *         response)
 */
@WebServlet("/newtodo")
public class TodoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @description : todoForm.jsp로 forwarding.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/newtodo.jsp").forward(request, response);

	}
}
