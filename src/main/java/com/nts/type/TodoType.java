/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.type;

import com.nts.dto.TodoDto;
import com.nts.dto.TodoDtoList;
/**
 * @desc TodoType 상수 관리
 * @author 전연빈
 */
public enum TodoType {
	
	TODO {
		@Override
		public void addList(TodoDtoList todoDtoList, TodoDto todoDto) {
			todoDtoList.getTodoList().add(todoDto);
		}
	},
	DOING {
		@Override
		public void addList(TodoDtoList todoDtoList, TodoDto todoDto) {
			todoDtoList.getDoingList().add(todoDto);
		}
	},
	DONE {
		@Override
		public void addList(TodoDtoList todoDtoList, TodoDto todoDto) {
			todoDtoList.getDoneList().add(todoDto);
		}
	};
	
	public abstract void addList(TodoDtoList todoDtoList,TodoDto todoDto);
}
