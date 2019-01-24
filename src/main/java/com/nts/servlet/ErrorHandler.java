package com.nts.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 예외발생시 에러를 처리해주는 서블릿
 */
@WebServlet("/error")
public class ErrorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("./error.jsp");
		rd.forward(req, resp);
	}

	/**
	 * 브라우저에 alert메시지를 띄워주고 이전 페이지로 이동합니다.
	 */
	public static void alertMessage(String msg, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<script>alert('" + msg + "'); history.back();</script>");
		out.close();
	}
}
