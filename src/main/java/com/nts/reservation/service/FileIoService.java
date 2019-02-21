/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.FileInfo;

/**
* @author  : 이승수
*/
public interface FileIoService {
	public FileInfo getFileInfoByProductId(Integer productId);
}
