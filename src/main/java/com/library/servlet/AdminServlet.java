package com.library.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.library.dao.AdminDao;
import com.library.entity.Admin;
import com.library.utils.StringUtils;

/**
 * 用户登陆
 * 
 * @author 刘鸿伟
 *
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

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
		// 获取请求参数
		String msg = req.getParameter("msg");

		if (StringUtils.hasLength(msg)) {
			if (msg.equals("login")) {
				login(req, resp);
			} else if (msg.equals("logout")) {
				logout(req, resp);
			} else if (msg.equals("changepw")) {
				try {
					changepw(req, resp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	/**
	 * admin 登录处理
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp) {

		// 创建session
		HttpSession session = req.getSession();
		// 获取登陆的请求参数 username和password
		String adminname = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(adminname + password);

		AdminDao dao = new AdminDao();
		Admin admin;
		try {
			admin = dao.findAdmin(adminname, password);

			if (admin == null) {
				// 查无用户返回登陆界面
				resp.sendRedirect("/Library/login.jsp");
			} else {
				// 登录新用户是先移除之前的首页内容
				// session.removeAttribute("findBookLike");
				// session.removeAttribute("findUserLike");
				// 共享用户进入主界面
				session.setAttribute("admin_in_session", admin);
				resp.sendRedirect("/Library/index.jsp");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * admin 退出处理
	 * 
	 * @throws IOException
	 */
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session = req.getSession();
		// 退出并清除session和返回登陆界面
		session.invalidate();
		resp.sendRedirect("/Library/login.jsp");

	}

	/**
	 * admin 修改密码
	 * 
	 * @throws Exception
	 */
	public void changepw(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		// 创建session
		HttpSession session = req.getSession();
		Object haveadmin = session.getAttribute("admin_in_session");
		if (haveadmin == null) {
			resp.sendRedirect("/Library/login.jsp");
		} else {
			// 获取登陆的请求参数 username(adminname)和password
			String adminname = req.getParameter("adminname");
			String password = req.getParameter("password");
			String newpassword = req.getParameter("password1");
			String password1 = req.getParameter("password2");

			// 判断为空立即返回
			if ((StringUtils.hasLength(adminname)) && (StringUtils.hasLength(password))
					&& (StringUtils.hasLength(newpassword)) && (StringUtils.hasLength(password1))
					&& (newpassword.equals(password1))) {
				// 匹配数据库 adminname 和 password

				AdminDao dao = new AdminDao();
				Admin admin = dao.findAdmin(adminname, password);
				if (admin != null) {
					Long id = admin.getId();
					boolean b = dao.changePassword(id, newpassword);
					if (b) {// 修改成功 返回
						System.out.println("修改成功");
						resp.sendRedirect("/Library/login.jsp");
					} else {// 修改失败 返回
						resp.sendRedirect("/Library/sysset.jsp");
					}
				} else {// 查无admin 返回
					resp.sendRedirect("/Library/sysset.jsp");
				}
			} else {// 参数有误 返回
				resp.sendRedirect("/Library/sysset.jsp");
			}
		}
	}
}