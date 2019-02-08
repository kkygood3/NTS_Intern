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
public class ReivewController {

	/**
	 * @desc review page 렌더링 및 productId front로 값 넘겨주기
	 * @param productId
	 * @param modelAndView
	 * @return modelAndView
	 */
	@RequestMapping("/review/{productId}")
	public ModelAndView getReivewPage(@PathVariable int productId, ModelAndView modelAndView) {

		modelAndView.addObject("productId", productId);
		modelAndView.setViewName("review");
		return modelAndView;
	}
}
