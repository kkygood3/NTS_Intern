/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;
import com.nts.type.TodoType;

/**
 * @desc Todo Business layer
 * @author 전연빈
 */
public class TodoService {

	/**
	 * @desc todo type 변경
	 * @param id
	 * @param type
	 * @throws SQLException
	 */
	public int updateTodo(TodoDto todoDto) throws SQLException {
		return new TodoDao().updateTodo(todoDto);
	}

	/**
	 * @desc type에 맞게끔 map에 키 밸류 형태로 넣어줌 
	 * @return todoMap (key -> todoList, doingList, doneList)
	 * @throws SQLException
	 */
	public Map<String, List<TodoDto>> getTodos() throws SQLException {

		TodoDao todoDao = new TodoDao();
		List<TodoDto> list = todoDao.getTodos();

		List<TodoDto> todoList = new ArrayList<>();
		List<TodoDto> doingList = new ArrayList<>();
		List<TodoDto> doneList = new ArrayList<>();

		for (TodoDto todoDto : list) {
			String type = todoDto.getType();

			if (TodoType.TODO.equals(type)) {
				todoList.add(todoDto);
			} else if (TodoType.DOING.equals(type)) {
				doingList.add(todoDto);
			} else if (TodoType.DONE.equals(type)) {
				doneList.add(todoDto);
			}
		}

		Map<String, List<TodoDto>> todoMap = new HashMap<>();

		todoMap.put("todoList", todoList);
		todoMap.put("doingList", doingList);
		todoMap.put("doneList", doneList);

		return todoMap;
	}

	/**
	 * @desc todo 내용 삽입
	 * @param todoDto
	 * @return result
	 * @throws SQLException
	 */
	public int addTodo(TodoDto todoDto) throws SQLException {
		return new TodoDao().addTodo(todoDto);
	}

}
