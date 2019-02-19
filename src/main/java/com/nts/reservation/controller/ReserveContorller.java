/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.dto.reserve.ReserveResponse;
import com.nts.reservation.service.ReserveService;

@Controller
public class ReserveContorller {
	@Autowired
	ReserveService reserveResponseService;

	/**
	 * /reserve 요청을 받아 reserve view로 넘겨주는 역할.
	 * @param id - displayInfo 테이블의 id 
	 */
	@GetMapping("/reserve")
	public String requestReserve(@RequestParam(name = "id", required = true) Integer id, ModelMap map,
		HttpSession session) {
		
		String sessionEmail = (String)session.getAttribute("email");
		if (sessionEmail != null) {
			map.addAttribute("email",sessionEmail);
		}
		
		//공연 정보 날짜를 오늘부터 1~5일후의 날짜로 무작위 생성.
		SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyy.MM.dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, (int)((Math.random() * 5))+1);
		
		ReserveResponse reserveResponse = reserveResponseService.getReserveResponse(id);
		map.addAttribute("reserveDisplayInfo", reserveResponse.getReserveDisplayInfo());
		map.addAttribute("reservePrice", reserveResponse.getReservePrice());
		map.addAttribute("reservationDate",dateFormat.format(calendar.getTime()));
		return "reserve";
	}
}
