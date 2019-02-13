package com.nts.reservation.service.detail;

import com.nts.reservation.dto.detail.DetailExtraImage;

public interface DetailExtraImageService {
	/**
	 * api/products/{diplayInfoId}/extra response에 사용
	 * @return 페이지에서 'et' 타입 이미지가 있을때 첫 번째 row를 return
	 */
	DetailExtraImage getExtraImage(int displayInfoId);
}
