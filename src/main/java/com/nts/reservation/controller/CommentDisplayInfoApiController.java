package com.nts.reservation.controller;

import static com.nts.reservation.property.Const.COMMENT_DEFAULT_PAGING_SIZE;
import static com.nts.reservation.property.Const.DEFAULT_SATRT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.CommentDisplayInfo;
import com.nts.reservation.service.CommentDisplayInfoService;

/**
 * 코멘트관련 API 클래스
 * @author si yoon
 *
 */
@RestController
@RequestMapping("/product/{productId}/comment")
public class CommentDisplayInfoApiController {
	@Autowired
	private CommentDisplayInfoService commentDisplayInfoService;

	/**
	 * productId에 해당하는 리뷰를 페이징해서 가져온다
	 * @param productId 조회할 상품 id
	 * @param start 페이징 시작 인덱스
	 * @param limit 페이징 사이즈
	 * @return 코멘트 리스트
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDisplayInfo> getProductCountAndThumbnailInfos(
		@PathVariable(name = "productId", required = true) long productId,
		@RequestParam(name = "start", required = false, defaultValue = DEFAULT_SATRT) int start,
		@RequestParam(name = "limit", required = false, defaultValue = COMMENT_DEFAULT_PAGING_SIZE) int limit) {
		List<CommentDisplayInfo> commentDisplayInfos = commentDisplayInfoService.getCommentDisplayInfos(productId, start, limit);
		return commentDisplayInfos;
	}
}
