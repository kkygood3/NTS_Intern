/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package main.java.com.nts;

import java.io.PrintWriter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @description : 현재 시간을 알려주는 Servlet Class
 * @filename : TodayServlet.java
 * @package : main.java.com.nts
 * @author : Seokhyeon Choi
 * @method : void doGet(HttpServletRequest request, HttpServletResponse response)
 */
@WebServlet("/aboutme/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
	private static final DateTimeFormatter YMDHM = DateTimeFormatter.ofPattern("y/M/d H:m");
	
    private static final String HTML_CURRENT_TIME_PREFIX = 
    		 	"<html>"
    		+	"<head><title>Today</title></head>"
    		+	"<body>"
    		+		"<div style = \"height:10%\">"
    		+			"<a href=\"/aboutme/index.html\">"
    		+				"<h2>메인화면</h2>"
    		+			"</a>"
    		+		"</div>"
    		+		"<div style = \"display:table; height:80%; width:100%;\">"
    		+			"<p style = \"font-weight: bold; font-size: 3em; display:table-cell; text-align: center; vertical-align:middle; \">"
    		+				"현재시간 : ";
    private static final String HTML_CURRENT_TIME_SUFFIX = 
    					"</p>"
    		+		"</div>"
    		+	"</body>"
    		+	"</html>";
    
	/**
	 * @description : HttpServletResponse에 현재 시간을 HTML로 출력
	 * @param : request
	 * @param : response
	 * @throws : ServletException
	 * @throws : IOException
	 * @return : void
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8;");
		
		PrintWriter out = response.getWriter();
		out.println(HTML_CURRENT_TIME_PREFIX);
		out.println(LocalDateTime.now().format(YMDHM));
		out.println(HTML_CURRENT_TIME_SUFFIX);
	}
}
