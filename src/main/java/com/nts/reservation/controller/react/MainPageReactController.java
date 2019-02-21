package com.nts.reservation.controller.react;

import java.net.ConnectException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.nts.reservation.dto.CategoryDto;
import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.dto.request.react.MainPageRequestDto;
import com.nts.reservation.dto.response.CategoryResponseDto;
import com.nts.reservation.dto.response.ProductResponseDto;
import com.nts.reservation.dto.response.PromotionResponseDto;
import com.nts.reservation.exception.UnauthorizedRequestException;
import com.nts.reservation.service.CategoryService;
import com.nts.reservation.service.DisplayInfoService;
import com.nts.reservation.service.ProductService;
import com.nts.reservation.service.RequestHtmlService;

public class MainPageReactController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private DisplayInfoService displayInfoService;

	@Autowired
	private RequestHtmlService requestHtmlService;

	/**
	 * React 서버에 접속하여 서버사이드렌더링된 html 을 받아와서 클라이언트에 그려줌.
	 * 
	 * @param userEmail
	 * @param session
	 * @return
	 * @throws ConnectException, HttpClientErrorException
	 */
	@GetMapping(path = "/react", produces = "text/html; charset=utf8")
	public @ResponseBody String myReservationPageTest(HttpSession session, HttpServletRequest request)
			throws UnauthorizedRequestException, ConnectException, HttpClientErrorException {

		String userEmail = (String) session.getAttribute("userEmail");

		List<ProductDto> products = productService.getProductList(0);
		int productCount = productService.getCount();
		ProductResponseDto productResponse = new ProductResponseDto(products, productCount);

		List<ProductDto> promotions = productService.getPromotionProductList();
		PromotionResponseDto promotionResponse = new PromotionResponseDto(promotions, promotions.size());

		List<CategoryDto> categories = categoryService.getCategoryList();
		CategoryResponseDto categoryResponse = new CategoryResponseDto(categories);

		MainPageRequestDto requestDto = new MainPageRequestDto(userEmail, productResponse, promotionResponse,
				categoryResponse);
		String html = "";
		try {
			html = requestHtmlService.requestToReactHtml("/main", requestDto);
		} catch (HttpClientErrorException | ConnectException exception) {
			if (exception instanceof HttpClientErrorException) {
				throw (HttpClientErrorException) exception;
			} else {
				throw (ConnectException) exception;
			}
		}
		return html;
	}
}
