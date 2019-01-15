package com.nts;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodayServlet
 */
@WebServlet(name = "today", urlPatterns = {"/today"})
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TodayServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

		LocalDateTime time = LocalDateTime.now();

		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy/M/d HH:mm");
		String customTime = time.format(timeFormat);

		PrintWriter out = response.getWriter();
		out.print("<link rel='stylesheet' type='text/css' href='today.css'>"
			+ "<a href='index.html' id='goToMain'>메인화면</a>"
			+ "<h1> 현재시간 : " + customTime + "</h1>");
	}

}