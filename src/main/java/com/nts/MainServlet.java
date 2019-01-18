package com.nts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class TodoServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		JSONArray todoAll_array = new JSONArray();

		try {
			todoAll_array = TodoDAO.getList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray todo_todoAll = new JSONArray();
		JSONArray doing_todoAll = new JSONArray();
		JSONArray done_todoAll = new JSONArray();

		for (int i = 0; i < todoAll_array.length(); i++) {
			JSONObject order = todoAll_array.getJSONObject(i);
			if (order.get("type").equals("TODO")) {
				todo_todoAll.put(order);
			} else if (order.get("type").equals("DOING")) {
				doing_todoAll.put(order);
			} else if (order.get("type").equals("DONE")) {
				done_todoAll.put(order);
			}
		}

		request.setAttribute("todoList", todo_todoAll);
		request.setAttribute("doingList", doing_todoAll);
		request.setAttribute("doneList", done_todoAll);

		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
}
