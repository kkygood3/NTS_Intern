/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.exception;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class ServerErrorException extends Exception {

	public ServerErrorException(Exception e) {
		super(e);
	}

}
