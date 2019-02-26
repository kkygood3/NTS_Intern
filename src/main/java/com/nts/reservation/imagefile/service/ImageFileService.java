/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.imagefile.service;

import java.util.List;

import com.nts.reservation.imagefile.dto.ImageFile;

public interface ImageFileService {

	List<ImageFile> getImageFile(int fileId);


}
