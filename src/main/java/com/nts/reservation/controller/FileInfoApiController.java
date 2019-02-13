package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.service.FileInfoService;

/**
 * 상품 아이디에 따라서 파일관련 정보 가져오는 api클래스
 * TODO: 상품이랑 상관없이 다 가져오는것도 일관성있는 path로 처리할수있을까?
 * @author si yoon
 *
 */
@RestController
@RequestMapping("product/{productId}/file_info")
public class FileInfoApiController {
	@Autowired
	private FileInfoService fileInfoService;

	/**
	 * 상품명이랑 타입에 맞는 이미지 가져온다
	 * @param productId 조회할 상품 id
	 * @param type 조회할 타입 ma(main), et(etc), th(thumbnail)
	 * @return 해당 파일 이름들
	 */
	@GetMapping("/{type}")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getProductCountAndThumbnailInfos(
		@PathVariable(name = "productId", required = true) int productId,
		@PathVariable(name = "type", required = true) String type) {
		return fileInfoService.getSaveFileName(productId, type);
	}
}
