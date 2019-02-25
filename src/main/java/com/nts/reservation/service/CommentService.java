package com.nts.reservation.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.CommentDisplayItem;
import com.nts.reservation.dto.CommentPageInfo;
import com.nts.reservation.dto.ReservationUserComment;

/**
 * 코멘트 화면 정보 관련 서비스 로직 수행하는 클래스
 * 
 * @author si yoon
 *
 */
public interface CommentService {
	/**
	 * 코멘트 화면정 보 상품에따라서 페이징해서 가져온다
	 * 
	 * @param productId 조회할 상품정보
	 * @param start     시작인덱스
	 * @param limit     페이징 사이즈
	 * @return 코멘트정보 리스트
	 */
	List<CommentDisplayItem> getCommentsByProductIdWithPaging(long productId, int start, int limit);

	/**
	 * 상품에 맞는 리뷰페이지 정보를 가져온다
	 * 
	 * @param displayInfoId 조회할 상품
	 * @return 리뷰페이지 정보
	 */
	CommentPageInfo getCommentPageInfoByProductId(long productId);

	/**
	 * 사용자 리뷰를 등록한다
	 * 
	 * @param reservationUserComment      사용자 리뷰
	 * @param reservationUserCommentImage 리뷰 이미지 정보
	 * @param fileInfo                    리뷰이미지 파일정보
	 * @return 사용자 리뷰 id
	 */
	ReservationUserComment addReservationUserComment(ReservationUserComment comment, MultipartFile image);
}
