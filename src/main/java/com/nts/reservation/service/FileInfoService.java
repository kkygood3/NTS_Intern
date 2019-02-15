package com.nts.reservation.service;

import java.util.List;

/**
 * 파일정보 관련 서비스 로직 수행하는 클래스
 * @author si yoon
 *
 */
public interface FileInfoService {
	/**
	 * 상품명과 타입에 따라 파일이름 가져온다
	 * @param productId 조회할 상품
	 * @param type 조회할 타입 ma(main), et(etc), th(thumbnail)
	 * @return 해당 파일 이름들
	 */
	public List<String> getProductFileNameByProductIdAndType(long productId, String type);
}
