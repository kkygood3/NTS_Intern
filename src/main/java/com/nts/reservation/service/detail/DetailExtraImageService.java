package com.nts.reservation.service.detail;

import com.nts.reservation.dto.detail.DetailExtraImage;

public interface DetailExtraImageService {
	/**
	 * api/products/{diplayInfoId}/extra response에 사용
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @return 해당 ID에 속하는 'et' 타입 파일이 있을때의 첫 번째
	 */
	DetailExtraImage getExtraImage(int displayInfoId);
}
