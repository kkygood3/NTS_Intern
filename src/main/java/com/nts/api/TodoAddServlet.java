package com.nts.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.Const;
import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

/**
 * todo 등록
 * @author 시윤
 * Servlet implementation class TodoAddServlet
 */
@WebServlet("/todo")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * todo 등록
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter(Const.TITLE);
		String name = request.getParameter(Const.NAME);
		int sequence = Integer.parseInt(request.getParameter(Const.SQQUENCE));
		
		
		
		if (isValidInput(title, sequence)) {
			TodoDao.getInstance().addTodo(new TodoDto(name, sequence, title));
		}
		response.sendRedirect("./todos");
	}
	
	/*
	 * 입력값이 유효한지 검사
	 */
	private boolean isValidInput(String title, int sequence) {
		if (title.length() > 24) return false;
		if (sequence > 3 || sequence < 1) return false;
		return true;
	}
}
