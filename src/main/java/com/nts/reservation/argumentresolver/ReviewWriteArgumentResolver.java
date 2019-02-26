/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nts.reservation.common.RequestValidator;
import com.nts.reservation.dto.reviewwrite.ReviewWriteRequest;

/**
 * Multipart-form data request인 ReviewWriteRequest 요청에 사용
 * @return ReviewWriteRequest
 */
public class ReviewWriteArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType() == ReviewWriteRequest.class;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		ReviewWriteRequest reviewWriteRequest = new ReviewWriteRequest();

		MultipartHttpServletRequest multipartRequest = webRequest.getNativeRequest(MultipartHttpServletRequest.class);

		reviewWriteRequest.setImageFile(multipartRequest.getFile("imageFile"));
		reviewWriteRequest.setComment(multipartRequest.getParameter("comment"));
		reviewWriteRequest.setProductId(new Integer(multipartRequest.getParameter("productId")));
		reviewWriteRequest.setScore(new Integer(multipartRequest.getParameter("score")));

		if (!RequestValidator.validateReviewWriteRequest(reviewWriteRequest)) {
			throw new RuntimeException("Comment 형식 이상");
		}

		return reviewWriteRequest;
	}

}
