/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller;

import java.net.ConnectException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.nts.reservation.dto.CategoryDto;
import com.nts.reservation.dto.CommentDto;
import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.DisplayInfoImageDto;
import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceDto;
import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.dto.request.MyReservationPageRequestDto;
import com.nts.reservation.dto.request.react.DetailPageRequestDto;
import com.nts.reservation.dto.request.react.MainPageRequestDto;
import com.nts.reservation.dto.response.CategoryResponseDto;
import com.nts.reservation.dto.response.ProductResponseDto;
import com.nts.reservation.dto.response.PromotionResponseDto;
import com.nts.reservation.exception.UnauthorizedRequestException;
import com.nts.reservation.service.CategoryService;
import com.nts.reservation.service.DisplayInfoService;
import com.nts.reservation.service.ProductService;
import com.nts.reservation.service.RequestHtmlService;
import com.nts.reservation.service.ReservationService;

/**
 * @author 육성렬
 */

@Controller
@RequestMapping(path = "/react")
public class ViewReactController {

	private final CategoryService categoryService;

	private final ProductService productService;

	private final DisplayInfoService displayInfoService;

	private final RequestHtmlService requestHtmlService;

	private final ReservationService reservationService;

	@Autowired
	public ViewReactController(CategoryService categoryService, ProductService productService,
		DisplayInfoService displayInfoService, RequestHtmlService requestHtmlService,
		ReservationService reservationService) {

		this.categoryService = categoryService;
		this.productService = productService;
		this.displayInfoService = displayInfoService;
		this.requestHtmlService = requestHtmlService;
		this.reservationService = reservationService;
	}

	/**
	 * React 서버에 접속하여 서버사이드렌더링된 html 을 받아와서 클라이언트에 그려줌.
	 * 
	 * @param userEmail
	 * @param session
	 * @return
	 * @throws ConnectException, HttpClientErrorException
	 */
	@GetMapping(produces = "text/html; charset=utf8")
	public @ResponseBody String mainPage(HttpSession session, HttpServletRequest request)
		throws ConnectException, HttpClientErrorException {

		String userEmail = (String)session.getAttribute("userEmail");

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
				throw (HttpClientErrorException)exception;
			} else {
				throw (ConnectException)exception;
			}
		}
		return html;
	}

	@GetMapping(path = "/displayInfo/{displayInfoId}", produces = "text/html; charset=utf8")
	public @ResponseBody String detailPage(@PathVariable Long displayInfoId, HttpSession session,
		HttpServletRequest request) throws ConnectException, HttpClientErrorException {
		String userEmail = (String)session.getAttribute("userEmail");

		DisplayInfoDto displayInfo = displayInfoService.getDisplayInfo(displayInfoId);
		DisplayInfoImageDto displayInfoImage = displayInfoService.getDisplayInfoImage(displayInfoId);
		List<ProductImageDto> productImages = displayInfoService.getProductImageList(displayInfo.getProductId());
		List<ProductPriceDto> productPrices = displayInfoService.getProductPriceList(displayInfo.getProductId());
		List<CommentDto> comments = displayInfoService.getCommentList(displayInfoId);
		float averageScore = displayInfoService.getCommentAvgScore(displayInfoId);

		DetailPageRequestDto requestDto = new DetailPageRequestDto(userEmail, averageScore, comments, displayInfo,
			displayInfoImage, productImages, productPrices);
		String html = "";
		try {
			html = requestHtmlService.requestToReactHtml("/detail", requestDto);
		} catch (HttpClientErrorException | ConnectException exception) {
			if (exception instanceof HttpClientErrorException) {
				throw (HttpClientErrorException)exception;
			} else {
				throw (ConnectException)exception;
			}
		}
		return html;
	}

	@GetMapping(path = "/myReservation", produces = "text/html; charset=utf8")
	public @ResponseBody String myReservationPageTest(
		HttpSession session, HttpServletRequest request)
		throws UnauthorizedRequestException, ConnectException, HttpClientErrorException {

		String userEmail = (String)session.getAttribute("userEmail");
		if (userEmail == null) {
			String ipAddress = request.getRemoteAddr();
			throw new UnauthorizedRequestException(ipAddress, "/myReservationWithReact");
		}

		List<ReservationInfoDto> list = reservationService.getReservationList(userEmail);
		int count = reservationService.getReservationCount(userEmail);

		MyReservationPageRequestDto requestDto = new MyReservationPageRequestDto(userEmail, list, count);
		String html = "";
		try {
			html = requestHtmlService.requestToReactHtml("/myReservation", requestDto);
		} catch (HttpClientErrorException | ConnectException exception) {
			if (exception instanceof HttpClientErrorException) {
				throw (HttpClientErrorException)exception;
			} else {
				throw (ConnectException)exception;
			}
		}
		return html;
	}
}
