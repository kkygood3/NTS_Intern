package com.nts.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

/**
 * todo_register.jsp에서 받은 새로운 정보를 DB에 등록하는 서블렛 
 * @author 박우성
 */
@WebServlet("/register_ok")
public class RegisterOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터 등록
		String whatdo = request.getParameter("whatdo");
		String name = request.getParameter("wname");
		int priority = new Integer(request.getParameter("priority"));
		
		TodoDao dao = new TodoDao();
		dao.addTodo(new TodoDto(whatdo,name,priority));
		
		//main.jsp로 리다이렉트
		response.sendRedirect("main");
		
		
		
	}
}
