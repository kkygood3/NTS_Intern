/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
* @author  : 이승수
*/
@Data
public class FileInfo {
	private int id;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private boolean deleteFlag;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date createDate;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date modifyDate;
}
