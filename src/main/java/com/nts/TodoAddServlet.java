/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* @description : 전달 받은 파라미터를 DB에 Insert 후 main으로 Redirect
* @filename : TodoAddServlet.java
* @package : com.nts
* @author : Seokhyeon Choi
* @method : void doPost(HttpServletRequest request, HttpServletResponse response)
*/
@WebServlet("/todo-add")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @description : 수신된 사용자 정보를 DAO를 통해 DB에 등록 후 main으로 Redirect
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		int sequence = Integer.parseInt(request.getParameter("sequence"));
		
		TodoDto todoDto = new TodoDto(name,title,sequence);
		TodoService.getInstance().addTodo(todoDto);
		
		response.sendRedirect("/main");
	}

}
