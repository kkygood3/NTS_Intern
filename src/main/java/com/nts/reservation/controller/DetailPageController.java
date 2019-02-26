/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 육성렬
 */
@Controller
public class DetailPageController {

	/**
	 * @desc 상세 페이지 요청에 대한 반환
	 * @param displayInfoId
	 * @param map
	 * @return
	 */
	@GetMapping("/displayInfo/{displayInfoId}")
	public String getDisplayInfoPage(@PathVariable Long displayInfoId, ModelMap map) {
		map.addAttribute("displayInfoId", displayInfoId);
		return "detailPage";
	}
}
