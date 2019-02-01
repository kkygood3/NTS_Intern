/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todolist.common.TodoType;
import com.nts.todolist.dao.TodoDao;
import com.nts.todolist.dto.TodoDto;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {

	/**
	 * 데이터베이스에 저장된 TodoDto 객체들을 화면에 출력
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		List<TodoDto> allTypeList = TodoDao.getInstance().getTodos();
		HashMap<TodoType, List<TodoDto>> todoListMap = getTodoListMap(allTypeList);

		for (TodoType todoType : TodoType.getTodoTypeList()) {
			request.setAttribute(todoType.getTodoType().toLowerCase() + "List", todoListMap.get(todoType));
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * HashMap 타입의 객체를 생성하여 반환 (이 때 각 타입을 key로, 타입에 해당하는 TodoDto 객체의 List를 value로 가짐)
	 * @param allTypeList 타입과 상관없이 모든 TodoDto 객체를 요소로 갖는 List
	 * @return key를 타입으로, value를 타입에 해당하는 TodoDto 객체의 List로 갖는 HashMap 타입의 객체를 반환
	 */
	private HashMap<TodoType, List<TodoDto>> getTodoListMap(List<TodoDto> allTypeList) {
		List<TodoDto> todoList = new ArrayList<>();
		List<TodoDto> doingList = new ArrayList<>();
		List<TodoDto> doneList = new ArrayList<>();

		HashMap<TodoType, List<TodoDto>> todoListMap = new HashMap<TodoType, List<TodoDto>>();
		todoListMap.put(TodoType.TODO, todoList);
		todoListMap.put(TodoType.DOING, doingList);
		todoListMap.put(TodoType.DONE, doneList);

		for (TodoDto todoDto : allTypeList) {
			TodoType todoType = TodoType.valueOf(todoDto.getType());
			todoListMap.get(todoType).add(todoDto);
		}

		return todoListMap;
	}

}
