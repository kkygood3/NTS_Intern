/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.error;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;

import javax.naming.NoPermissionException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nts.dto.error.ErrorMessage;
import com.nts.exception.DisplayInfoNullException;
import com.nts.exception.InvalidFormatException;
import com.nts.exception.NoMatchReservationException;
import com.nts.exception.ProductParamException;

/**
 * @author 전연빈
 */
@RestControllerAdvice(annotations = RestController.class)
public class RestControllerExceptionAdvice {
	
	/**
	 * @desc Product Parameter가 정상값이 아닐경우
	 * @param e
	 * @return errorMessage
	 */
	@ExceptionHandler(ProductParamException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage productParamExceptionHandling(ProductParamException e) {
		return new ErrorMessage(e.getMessage());
	}

	/**
	 * @desc display정보가 없을때 에러 전송
	 * @param e
	 * @return errorMessage
	 */
	@ExceptionHandler(DisplayInfoNullException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage displayNotFound(DisplayInfoNullException e) {
		return new ErrorMessage(e.getMessage());
	}

	/**
	 * @desc 잘못된 format을 요청시
	 * @param e
	 * @return errorMessage
	 */
	@ExceptionHandler(InvalidFormatException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage invalidFormat(InvalidFormatException e) {
		return new ErrorMessage(e.getMessage());
	}

	/**
	 * @desc 유효하지않은 파라미터 요청시
	 * @param e
	 * @return errorMessage
	 */
	@ExceptionHandler(InvalidParameterException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage invalidParameter(InvalidParameterException e) {
		return new ErrorMessage(e.getMessage());
	}

	/**
	 * @desc 권한이 없는 사용자가 요청시
	 * @param e
	 * @return errorMessage
	 */
	@ExceptionHandler(NoPermissionException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ErrorMessage noPermission(NoPermissionException e) {
		return new ErrorMessage(e.getMessage());
	}

	/**
	 * @desc reservation 정보가 맞지 않을 경우
	 * @param e
	 * @return	errorMessage
	 */
	@ExceptionHandler(NoMatchReservationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage noMatchReservationInformation(NoMatchReservationException e) {
		return new ErrorMessage(e.getMessage());
	}

	/**
	 * @desc 파일 IO Exception 발생시
	 * @param e
	 * @return errorMessage
	 */
	@ExceptionHandler(IOException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage ioExceptionHandling(IOException e) {
		return new ErrorMessage(e.getMessage());
	}

	/**
	 * @desc file을 찾을수 없을시
	 * @param e
	 * @return errorMessage
	 */
	@ExceptionHandler(FileNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage fileNotFoundExceptionHandling(FileNotFoundException e) {
		return new ErrorMessage(e.getMessage());
	}
	
	/**
	 * @desc UUID 생성시에  없는 Hash알고리즘 사용시
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NoSuchAlgorithmException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage noSuchAlgorithmExceptionHandling(NoSuchAlgorithmException e) {
		return new ErrorMessage(e.getMessage());
	}
}
