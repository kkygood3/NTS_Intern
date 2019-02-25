/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.displayinfo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DisplayInfoImage {
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private Integer displayInfoId;
	private Integer displayInfoImageId;
	private Integer fileId;
	private String fileName;
	private String modifyDate;
	private String saveFileName;
}
