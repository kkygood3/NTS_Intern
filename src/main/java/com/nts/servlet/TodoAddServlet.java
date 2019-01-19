/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;
import com.nts.type.TodoType;

/**
 * @author 전연빈
 */
@WebServlet("/newtodo")
public class TodoAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @desc newtodo.jsp 파일로 포워딩
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/newtodo.jsp");
		rd.forward(request, response);
	}

	/**
	 * @desc 새로운 todo 등록
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		request.setCharacterEncoding("utf-8");
		
		TodoDto todoDto = new TodoDto();

		todoDto.setPersonName((String) request.getParameter("personName"));
		todoDto.setTitle((String) request.getParameter("title"));
		todoDto.setSequence(Integer.parseInt(request.getParameter("sequence")));

		TodoDao todoDao = new TodoDao();

		try {
			todoDao.insertTodo(todoDto);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// 성공할시엔 redirect 실패시엔 에러 throws
		response.sendRedirect("/main");
	}

}
