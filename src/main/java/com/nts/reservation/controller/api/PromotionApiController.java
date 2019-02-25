package com.nts.reservation.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.service.PromotionService;

/**
 * 프로모션 관련 API 클래스
 * 
 * @author si yoon
 *
 */
@RestController
@RequestMapping("/api/promotion")
public class PromotionApiController {
	@Autowired
	private PromotionService promotionService;

	/**
	 * 전체 프로모션 이미지의 파일 이름을 리턴하는 메소드
	 * 
	 * @return 전체 프로모션 이미지 파일이름 리스트
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<String> getPromotions() {
		return promotionService.getPromotions();
	}
}
