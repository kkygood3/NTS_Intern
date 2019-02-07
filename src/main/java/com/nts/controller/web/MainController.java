/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 전연빈
 */
@Controller
public class MainController {

	@GetMapping("/main")
	public String showMainPage() {
		return "mainpage";
	}
}
