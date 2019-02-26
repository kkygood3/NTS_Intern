/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.imagefile.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ImageFile {
	private int id;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private boolean deleteFlag;
	private String createDate;
	private String modifyDate;
}
