package com.nts.reservation.controller;

import static com.nts.reservation.property.Const.COMMENT_DEFAULT_PAGING_SIZE;
import static com.nts.reservation.property.Const.DEFAULT_SATRT;
import static com.nts.reservation.property.Const.SELECT_ALL;
import static com.nts.reservation.property.Const.THUMBNAIL_DEFAULT_PAGING_SIZE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.CommentDisplayInfo;
import com.nts.reservation.dto.ErrorInfo;
import com.nts.reservation.dto.PriceInfo;
import com.nts.reservation.dto.ProductThumbnail;
import com.nts.reservation.dto.UserReservationInput;
import com.nts.reservation.service.CommentService;
import com.nts.reservation.service.ProductService;
import com.nts.reservation.service.ReservationService;

/**
 * 상품 관련 API 클래스
 * @author USER
 *
 */
@RestController
@RequestMapping(path = "/product")
public class ProductApiController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ReservationService reservationService;

	/**
	 * 썸네일 정보 start부터 limit개 리턴
	 * 
	 * @param start 시작 인덱스, 지정하지않으면 0
	 * @param limit SELECT할 썸네일 갯수, 지정하지않으면 4
	 * @param categoryId SELECT할 카테고리, 지정하지않으면 전체 카테고리
	 * @return 해당카테고리의 전체 상품 갯수와 limit개의 썸네일 정보
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> getProductCountAndThumbnailInfos(
		@RequestParam(name = "start", required = false, defaultValue = DEFAULT_SATRT) int start,
		@RequestParam(name = "limit", required = false, defaultValue = THUMBNAIL_DEFAULT_PAGING_SIZE) int limit,
		@RequestParam(name = "category_id", required = false, defaultValue = SELECT_ALL) int categoryId) {
		int productCount = productService.getProductCountByCategoryId(categoryId);
		List<ProductThumbnail> thumbnailInfoList = null;

		if (productCount > 0) {
			thumbnailInfoList = productService.getProductThumbnailsByCategoryIdWithPaging(categoryId, start, limit);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("productCount", productCount);
		map.put("thumbnailInfoList", thumbnailInfoList);
		return map;
	}

	/**
	 * 상품명이랑 타입에 맞는 이미지 가져온다
	 * @param productId 조회할 상품 id
	 * @param type 조회할 타입 ma(main), et(etc), th(thumbnail)
	 * @return 해당 파일 이름들
	 */
	@GetMapping("/{productId}/file_info/{type}")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getProductCountAndThumbnailInfos(
		@PathVariable(name = "productId", required = true) int productId,
		@PathVariable(name = "type", required = true) String type) {
		return productService.getProductFileNameByProductIdAndType(productId, type);
	}

	/**
	 * productId에 해당하는 리뷰를 페이징해서 가져온다
	 * @param productId 조회할 상품 id
	 * @param start 페이징 시작 인덱스
	 * @param limit 페이징 사이즈
	 * @return 코멘트 리스트
	 */
	@GetMapping("/{productId}/comment")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDisplayInfo> getProductCountAndThumbnailInfos(
		@PathVariable(name = "productId", required = true) long productId,
		@RequestParam(name = "start", required = false, defaultValue = DEFAULT_SATRT) int start,
		@RequestParam(name = "limit", required = false, defaultValue = COMMENT_DEFAULT_PAGING_SIZE) int limit) {
		List<CommentDisplayInfo> commentDisplayInfos = commentService.getCommentsByProductIdWithPaging(productId, start, limit);
		return commentDisplayInfos;
	}

	/**
	 * productId에 해당하는 가격을 가져온다
	 * @param productId 조회할 상품 id
	 * @return
	 */
	@GetMapping("/{productId}/price")
	@ResponseStatus(HttpStatus.OK)
	public List<PriceInfo> getProductPrice(@PathVariable(name = "productId", required = true) long productId) {
		return productService.getPriceInfoByProductId(productId);
	}
	
	/**
	 * 예약정보 받아서 서버로 넘긴다
	 * @param displayInfoId 예약할 상품 id
	 * @param userReservationInputString 예약정보 
	 * @param model 에러정보
	 * @return 뷰이름 리턴
	 */
	@PostMapping(path = "/{displayInfoId}/reservation")
	public boolean postReservation(@PathVariable(name = "displayInfoId", required = true) long displayInfoId,
		@RequestBody UserReservationInput userReservationInput,
		ModelMap model) {
		System.out.println(userReservationInput);
		if (reservationService.addReservation(userReservationInput, displayInfoId) == null) {
			model.addAttribute("errorInfo", new ErrorInfo(400, "Bad Request", "잘못된 입력입니다."));
			return false;
		}
		return true;
	}
}
