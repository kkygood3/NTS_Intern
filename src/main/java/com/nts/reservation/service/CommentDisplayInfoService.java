package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.CommentDisplayInfo;

/**
 * 코멘트 화면 정보 관련 서비스 로직 수행하는 클래스
 * @author si yoon
 *
 */
public interface CommentDisplayInfoService {
	/**
	 * 코멘트 화면정 보 상품에따라서 페이징해서 가져온다
	 * @param productId 조회할 상품정보
	 * @param start 시작인덱스
	 * @param limit 페이징 사이즈
	 * @return 코멘트정보 리스트
	 */
	public List<CommentDisplayInfo> getCommentDisplayInfos(long productId, int start, int limit);
}
