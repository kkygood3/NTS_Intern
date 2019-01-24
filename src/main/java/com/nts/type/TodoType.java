/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.type;

import com.nts.dto.TodoDto;
import com.nts.dto.TodoListContainer;

/**
*
* @description : TodoDto의 각각의 Type에 대한 addList 및 다음 Type의 getString enum
* @filename : TodoType.java
* @package : com.nts.type
* @author : Seokhyeon Choi
* @method : addTodoList(TodoListContainer todoListContainer, TodoDto todoDto)
* @method : String getNextType()
*/
public enum TodoType {
	TODO{
		public void addTodoList(TodoListContainer todoListContainer, TodoDto todoDto) {
			todoListContainer.getTodoDtoList().add(todoDto);
		}
		
		public String getNextType() {
			return TODO.toString();
		}
	},
	DOING{
		public void addTodoList(TodoListContainer todoListContainer, TodoDto todoDto) {
			todoListContainer.getDoingDtoList().add(todoDto);
		}
		
		public String getNextType() {
			return DOING.toString();
		}
	},
	DONE{
		public void addTodoList(TodoListContainer todoListContainer, TodoDto todoDto) {
			todoListContainer.getDoneDtoList().add(todoDto);
		}
		
		public String getNextType() {
			return DONE.toString();
		}
	};
	
	/**
	 * @description : Type 별로 addList method를 override
	 */
	public abstract void addTodoList(TodoListContainer todoListContainer, TodoDto todoDto);
	
	/**
	 * @description : Type 별로 getString method을 override
	 */
	public abstract String getNextType();
}


