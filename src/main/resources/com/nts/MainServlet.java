/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;
import com.nts.dto.TodoType;

/**
 * 메인화면으로 넘어가는 MainServlet 클래스입니다.
 * @author kkygood3
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final TodoDao TODODAO = new TodoDao();

	/**
	 * 전체 List를 가지고 와서 divList를 사용해 각 list를 forward방식으로 main.jsp에 넘기는 메소드입니다.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		List<TodoDto> totalList = TODODAO.getTodos();
		List<TodoDto> todoList = new ArrayList<TodoDto>();
		List<TodoDto> doingList = new ArrayList<TodoDto>();
		List<TodoDto> doneList = new ArrayList<TodoDto>();

		divList(totalList, todoList, doingList, doneList);

		request.setAttribute("todoList", todoList);
		request.setAttribute("doingList", doingList);
		request.setAttribute("doneList", doneList);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * totalList를 각 리스트로 분류하는 메소드입니다.
	 */
	private void divList(List<TodoDto> totalList, List<TodoDto> todoList, List<TodoDto> doingList,
		List<TodoDto> doneList) {
		for (TodoDto todoDto : totalList) {

			if (todoDto.getType() == TodoType.TODO) {
				todoList.add(todoDto);
			}
			if (todoDto.getType() == TodoType.DOING) {
				doingList.add(todoDto);
			}
			if (todoDto.getType() == TodoType.DONE) {
				doneList.add(todoDto);
			}

		}
	}

}