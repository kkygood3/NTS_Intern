package com.nts.reservation.dao;

import static com.nts.reservation.property.Const.PRODUCT_ID;
import static com.nts.reservation.property.Const.TYPE;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ProductPageInfo;

/**
 * 상품 테이블 접근하는 클래스
 * @author si yoon
 *
 */
@Repository
public interface ProductMapper {
	/**
	 * 선택된 카테고리의 상품수 리턴하는 메소드 
	 * @param categoryId 카테고리 아이디 0이면 전체 상품 수
	 * @return 해당 카테고리의 상품 수
	 */
	Integer selectProductCount(@Param("categoryId")int categoryId);

	/**
	 * 상품이랑 타입에 따라 파일이름 가져오는 메소드
	 * @param productId 조회할 상품 id
	 * @param type 조회할 타입  ma(main), et(etc), th(thumbnail)
	 * @return 쿼리 실행결과 파일이름 리스트
	 */
	List<String> selectByDisplayInfoIdAndType(@Param(PRODUCT_ID)long productId, @Param(TYPE)String type);
	
	ProductPageInfo selectDetailPageInfo(@Param("displayInfoId")long displayInfoId);
}
