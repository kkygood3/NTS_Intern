/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidParameterException extends RuntimeException {
	
	private String errCode;
    private String errMsg;
 
    public InvalidParameterException(String errMsg) {
    	super(errMsg);
    }
 
    public InvalidParameterException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
 
    public String getErrCode() {
        return errCode;
    }
 
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
 
    public String getErrMsg() {
        return errMsg;
    }
 
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }   

    

}