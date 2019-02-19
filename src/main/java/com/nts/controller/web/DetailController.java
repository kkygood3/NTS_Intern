/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 전연빈
 */
@Controller
public class DetailController {

	/**
	 * @desc detail page로 view 렌더링
	 * @param displayInfoId
	 * @param modelAndView
	 * @return modelAndView ( viewName : detail , object: displayInfoId)
	 */
	@RequestMapping("/detail/{displayInfoId}")
	public ModelAndView getDetailPage(@PathVariable int displayInfoId, ModelAndView modelAndView) {

		modelAndView.addObject("displayInfoId", displayInfoId);
		modelAndView.setViewName("detail");

		return modelAndView;
	}

}
