package com.nts.todolist.exception;


/**
 * Database에 접근이 불가능할 경우 발생하는 Exception
 * @author yongjoon.Park
 */
public class DatabaseAccessException extends Exception {
	private static final long serialVersionUID = 1L;

	public DatabaseAccessException(String message, Throwable cause) {
		super(message, cause);
	}
}
