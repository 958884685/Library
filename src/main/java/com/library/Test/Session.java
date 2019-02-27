package com.library.Test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session")
public class Session extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");

		String usernameString = req.getParameter("username");

		// 获取session
		Object object = req.getSession().getAttribute("USER_IN_SESSION");
		Object object1 = req.getSession().getAttribute("PASSWORD_IN_SESSION");
		System.out.println("session的name是" + object);
		System.out.println("session的password是" + object1);

		// 请求转发
		// req.getRequestDispatcher("/Book/index.jsp").forward(req, resp);
		// url重定向
		// resp.sendRedirect("https://www.baidu.com");
	}

}
