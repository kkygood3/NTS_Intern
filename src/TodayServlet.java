
/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 현재 시간을 출력해주는 Class
 * 
 * @author yongjoon.park
 */

@WebServlet(name = "today", urlPatterns = {"/today"})
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DateTimeFormatter dateTimeFormatter;

	/**
	 * 재사용을 고려하여 처음 servlet 호출 시에만 formatter를 작성
	 */
	@Override
	public void init() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	}

	/**
	 * 현재 시간과 그에 해당하는 간단한 페이지 출력
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		// 기본 설정
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();
		writer.print("<a href='view/index.html'>메인화면</a>");
		writer.print("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");

		// 시간 설정 및 출력
		LocalDateTime now = LocalDateTime.now();
		writer.print("<h1 style='text-align: center;'>현재시간 : " + getNow(now) + "</h1>");
	}

	/**
	 * 지정된 시간을 String으로 반환
	 * 
	 * @param	LocalDateTime 
	*/
	private String getNow(LocalDateTime localDateTime) {
		return localDateTime.format(dateTimeFormatter);
	}

}
