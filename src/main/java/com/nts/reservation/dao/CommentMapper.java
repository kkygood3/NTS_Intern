package com.nts.reservation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.CommentDisplayItem;
import com.nts.reservation.dto.CommentPageInfo;
import com.nts.reservation.dto.ReservationUserComment;
import com.nts.reservation.dto.ReservationUserCommentImage;

/**
 * 코멘드 화면 관련정보 처리하는 클래스
 * 
 * @author si yoon
 *
 */
@Repository
public interface CommentMapper {
	/**
	 * 전체 코멘트 화면 정보 페이징해서 셀렉트하는 메소드
	 * 
	 * @param productId 해당 상품 id
	 * @param start     시작 인덱스
	 * @param limit     페이징 사이즈
	 * @return 쿼리 실행 결과 코멘트 리스트
	 */
	List<CommentDisplayItem> selectFromTheProductWithPageing(@Param("productId") long productId,
			@Param("startRow") int startRow, @Param("limit") int limit);

	/**
	 * 상품에 따라 리뷰페이지 정보를 셀렉트한다
	 * 
	 * @param displayInfoId 조회할 상품
	 * @return 쿼리실행결과 리뷰페이지 정보
	 */
	public CommentPageInfo selectCommentPageInfoByProductId(long ProductId);

	/**
	 * 리뷰 등록
	 * 
	 * @param reservationUserComment 리뷰 내용
	 * @return 리뷰id
	 */
	int insertReservationUserComment(ReservationUserComment reservationUserComment);

	/**
	 * 리뷰이미지정보 등록
	 * 
	 * @param reservationUserCommentImage 리뷰이미지 정보
	 * @return 리뷰이미지 id
	 */
	int insertReservationUserCommentImage(ReservationUserCommentImage reservationUserCommentImage);
}
