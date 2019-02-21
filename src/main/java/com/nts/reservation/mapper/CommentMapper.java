/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.reservation.dto.CommentDto;

/**
 * 상품평 Mapper
 * @author jinwoo.bae
 */
@Mapper
public interface CommentMapper {
	/**
	 * productId에 해당하는 상품평들을 가져옵니다.
	 */
	List<CommentDto> selectComments(@Param("productId") int productId,
		@Param("start") int start, @Param("limit") int limit);

	/**
	 * productId에 해당하는 상품평들의 총 평균점수를 가져옵니다.
	 */
	double selectCommentAvgScore(int productId);

	/**
	 * productId에 해당하는 상품평들의 총 개수를 가져옵니다.
	 */
	int selectCommentCount(int productId);
}
