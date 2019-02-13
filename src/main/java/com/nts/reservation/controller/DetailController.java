package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nts.reservation.dto.DatailPageInfo;
import com.nts.reservation.service.DatailPageInfoService;

@Controller
public class DetailController {
	@Autowired
	private DatailPageInfoService datailPageInfoService;

	/**
	 * 상세페이지 URL 맵핑 
	 * @return 메인페이지
	 */
	@GetMapping(path = "/detail/{displayInfoId}")
	public String detail(@PathVariable(name = "displayInfoId", required = true) long displayInfoId,
		ModelMap model) {
		DatailPageInfo datailPageInfo = datailPageInfoService.getDetailPageInfo(displayInfoId);

		model.addAttribute("displayInfoId", displayInfoId);
		model.addAttribute("datailPageInfo", datailPageInfo);

		return "detail";
	}
}
