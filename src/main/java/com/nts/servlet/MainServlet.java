/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		
		System.out.println("메인페이지");

		// 데이터 가져오기
		
		// 데이터 request에 setting
		
		// 메인 페이지로 이동
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/main.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// todo 내용 셋팅
		
		// todo update
		
		// update 결과 값을 가지고 request담기
		
		// 결과값 전달
	}
}
