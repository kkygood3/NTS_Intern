/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.imagefile.dao;

import java.util.List;

import com.nts.reservation.imagefile.dto.ImageFile;

public interface ImageFileDao {

	List<ImageFile> selectImageFile(int fileId);

}
