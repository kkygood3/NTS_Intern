/**
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @desc : 현재 시간을 알려주는 Servlet Class
 * @file : TodayServlet.java
 * @package : main.java.com.nts
 * @author : Seokhyeon Choi
 * @date : 2019. 1. 16.
 */

package main.java.com.nts;
import java.io.PrintWriter;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/aboutme/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
	private String htmlSendFrame;
	private DateTimeFormatter dtFormatter;
	
    public void init(ServletConfig config)throws ServletException{
    	htmlSendFrame = new String();
    	htmlSendFrame += "<a href=\"/2019_1st_intern/aboutme/index.html\"><h2>메인화면</h2></a>\n";
    	htmlSendFrame += "<h1 style=\"text-align:center; margin-top:150px;\">";
    	htmlSendFrame += "현재시간 : ";
		
    	dtFormatter = DateTimeFormatter.ofPattern("y/M/d H:m");
    }
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8;");
		
		String htmlSend = new String(htmlSendFrame);
		htmlSend += LocalDateTime.now().format(dtFormatter);
		htmlSend += "</h1>\n";
		
		PrintWriter out = response.getWriter();
		out.print(htmlSend);
		out.close();
	}
}
