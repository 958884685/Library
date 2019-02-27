package com.library.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.utils.JDBCUtils;

@WebServlet("/login")
public class Test extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		System.out.println("get_post");

		String usernameString = req.getParameter("username");
		String passwordString = req.getParameter("password");
		System.out.println(usernameString);
		System.out.println(passwordString);

		// 创建cookie
		Cookie cookie = new Cookie("username", usernameString);
		resp.addCookie(cookie);
		// 设置请求编码格式和网页形式
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print("<a href='/Book/cookie'> 22</a>");

	}

}
