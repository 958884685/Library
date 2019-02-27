package com.library.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.utils.StringUtils;

/**
 * 
 * 主界面导航栏跳转 cmd? msg 选择跳转
 * 
 * @author 刘鸿伟
 *
 */
@WebServlet("/cmd")
public class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 设置编码格式
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		HttpSession session = req.getSession();
		Object haveadmin = session.getAttribute("admin_in_session");
		if (haveadmin == null) {
			resp.sendRedirect("/Library/login.jsp");
		} else {
			String msg = req.getParameter("msg");
			if (StringUtils.hasLength(msg)) {// 选择跳转的界面
				if (msg.equals("index")) {
					resp.sendRedirect("/Library/index.jsp");
				} else if (msg.equals("reader")) {
					resp.sendRedirect("/Library/user?msg=findUserPage");
				} else if (msg.equals("books")) {
					resp.sendRedirect("/Library/book?msg=findBookPage");
				} else if (msg.equals("sysset")) {
					resp.sendRedirect("/Library/sysset.jsp");
				} else if (msg.equals("about")) {
					resp.sendRedirect("/Library/about.jsp");
				} else if (msg.equals("logout")) {
					resp.sendRedirect("/Library/admin?msg=logout");
				} else if (msg.equals("addbook")) {
					resp.sendRedirect("/Library/addbook.jsp");
				} else if (msg.equals("addreader")) {
					resp.sendRedirect("/Library/addreader.jsp");
				}
			}
		}

	}

}
