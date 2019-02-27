package com.library.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie")
public class Cookie extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");

		System.out.println("分隔线");
		javax.servlet.http.Cookie[] cookies = req.getCookies();
		System.out.println("---华---丽---的---分---隔---线---");
		System.out.println(cookies.length);
		System.out.println("---华---丽---的---分---隔---线---");
		for (javax.servlet.http.Cookie cookie : cookies) {
			String name = cookie.getName();

			System.out.println(name);

		}

	}

}
