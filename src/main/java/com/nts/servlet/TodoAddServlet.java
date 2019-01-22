/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

/**
 * 입력한 일정추가 처리를 위한 서블릿
 * @author jinwoo.bae
 */
@WebServlet("/newTodo")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * todoForm.jsp 페이지화면으로 포워딩 해준다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/todoForm.jsp");
		rd.forward(req, resp);
	}

	/**
	 * todoForm.jsp 에서 입력받은 데이터를 DB Todo에 추가한후 main으로 다시 리다이렉트 해준다.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Form의 Parameter들을 지정한 문자코드로 인코딩한다.
		req.setCharacterEncoding("UTF-8");

		String title = (String)req.getParameter("title");
		String name = (String)req.getParameter("name");
		String sequence = (String)req.getParameter("sequence");

		// TODO: null입력 에외처리 구현
		if (title.isEmpty() || name.isEmpty() || sequence == null) {
			throw new NullPointerException();
		}

		TodoDto todoDto = new TodoDto();
		todoDto.setTitle(title);
		todoDto.setName(name);
		todoDto.setSequence(Integer.parseInt(sequence));

		TodoDao todoDao = new TodoDao();
		todoDao.addTodo(todoDto);

		resp.sendRedirect("./main");
	}

}
