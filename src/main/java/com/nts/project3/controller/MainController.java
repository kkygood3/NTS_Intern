/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * /mainpage을 요청하면 mainpage.jsp로 리다이렉트하는 Controller
 */
@Controller
public class MainController {
	@GetMapping("/mainpage")
	public String visitMainPage() {
		return "mainpage";
	}
}
