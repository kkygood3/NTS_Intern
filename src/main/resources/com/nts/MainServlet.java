/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;
import com.nts.dto.TodoType;
import com.nts.exception.ServerErrorException;

/**
 * 메인화면으로 넘어가는 MainServlet 클래스입니다.
 * @author kkygood3
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 전체 List를 가지고 와서 divList를 사용해 각 list를 forward방식으로 main.jsp에 넘기는 메소드입니다.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		try {
			Map<TodoType, List<TodoDto>> classifyMap = getClassifyMap();

			for (TodoType type : TodoType.getTodoTypes()) {
				request.setAttribute(type.getTodoType().toLowerCase() + "List", classifyMap.get(type));
			}
		} catch (ServerErrorException e) {
			e.printStackTrace();
			response.sendError(e.getERROR_CODE(), e.getMESSAGE());
			return;
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * totalList를 각 리스트로 분류하는 메소드입니다.
	 * @throws ServerErrorException
	 */
	private Map<TodoType, List<TodoDto>> getClassifyMap() throws ServerErrorException {

		Map<TodoType, List<TodoDto>> classifyMap = new HashMap<TodoType, List<TodoDto>>();

		List<TodoDto> totalList = TodoDao.TODODAO.getTodos();

		for (TodoType type : TodoType.getTodoTypes()) {
			classifyMap.put(type, new ArrayList<TodoDto>());
		}

		for (TodoDto todoDto : totalList) {
			classifyMap.get(todoDto.getType()).add(todoDto);
		}

		return classifyMap;

	}

}