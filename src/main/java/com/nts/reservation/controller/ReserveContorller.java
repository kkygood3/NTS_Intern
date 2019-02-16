/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.dto.reserve.ReserveResponse;
import com.nts.reservation.service.reserve.ReserveResponseService;

@Controller
public class ReserveContorller {
	@Autowired
	ReserveResponseService reserveResponseService;
	/**
	 * /reserve 요청을 받아 reserve view로 넘겨주는 역할.
	 * @param id - displayInfo 테이블의 id 
	 */
	@GetMapping("/reserve")
	public String requestReserve(@RequestParam(name = "id", required = true) Integer id, ModelMap map) {
		ReserveResponse reserveResponse = reserveResponseService.getReserveResponse(id);
		map.addAttribute("reserveDisplayInfo",reserveResponse.getReserveDisplayInfo());
		map.addAttribute("reservePrice",reserveResponse.getReservePrice());
		return "reserve";
	}
}
