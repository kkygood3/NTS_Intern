package com.nts.reservation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.PriceInfo;
import com.nts.reservation.dto.ProductPageInfo;
import com.nts.reservation.dto.ProductDisplayItem;
import com.nts.reservation.dto.CommentWritePageInfo;

/**
 * 상품 테이블 접근하는 클래스
 * 
 * @author si yoon
 *
 */
@Repository
public interface ProductMapper {
	/**
	 * 선택된 카테고리의 상품수 리턴하는 메소드
	 * 
	 * @param categoryId 카테고리 아이디 0이면 전체 상품 수
	 * @return 해당 카테고리의 상품 수
	 */
	Integer selectProductCount(@Param("categoryId") int categoryId);

	/**
	 * 상품이랑 타입에 따라 파일이름 가져오는 메소드
	 * 
	 * @param productId 조회할 상품 id
	 * @param type      조회할 타입 ma(main), et(etc), th(thumbnail)
	 * @return 쿼리 실행결과 파일이름 리스트
	 */
	List<String> selectByDisplayInfoIdAndType(@Param("productId") long productId, @Param("type") String type);

	ProductPageInfo selectDetailPageInfo(@Param("displayInfoId") long displayInfoId);

	/**
	 * 
	 * 예약페이지에 표시할 가격정보
	 * 
	 * @param productId 상품 아이디
	 * @return 가격정보
	 */
	List<PriceInfo> selectPriceInfoByProductId(long productId);

	/**
	 * 특정 카테고리의 썸네일 정보 셀렉트
	 * 
	 * @param category 셀렉트할 카테고리
	 * @param start    시작 인텍스
	 * @param limit    셀렉트할 갯수
	 * @return 해당 카테고리의 썸네일 정보
	 */
	List<ProductDisplayItem> selectProductThumbnailByCategoryIdWithPaging(@Param("categoryId") int categoryId,
			@Param("start") int start, @Param("limit") int limit);

	/**
	 * description을 가져온다
	 * 
	 * @param productId 상품 아이디
	 * @return 상품 이름
	 */
	CommentWritePageInfo selectProductInfoByReservationInfoId(long reservationInfoId);
}
