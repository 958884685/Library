package com.library.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dao.UserDao;
import com.library.entity.User;
import com.library.page.PageResult;
import com.library.utils.StringUtils;

/**
 * 读者(user)的添加、更新、删除
 * 
 * 分页查询：所有用户的分页
 * 
 * @author 刘鸿伟
 *
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

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
			if (StringUtils.hasLength(msg)) {
				// 选择跳转的界面
				try {
					if (msg.equals("adduser")) {
						addUser(req, resp);
					} else if (msg.equals("updateuser")) {
						updateUser(req, resp);
					} else if (msg.equals("delectuser")) {
						deleteUser(req, resp);
					} else if (msg.equals("findUserPage")) {
						findUserPage(req, resp);
					} else if (msg.equals("findAUser")) {
						findAUser(req, resp);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	/**
	 * 添加用户
	 * 
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	protected void addUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub

		// HttpSession session = req.getSession();

		// 获取请求参数
		String usernameString = req.getParameter("username");
		String sexString = req.getParameter("sex");
		Long iphone = Long.valueOf(req.getParameter("iphone"));
		String emailString = req.getParameter("email");
		Long idcard = Long.valueOf(req.getParameter("idcard"));
		String noteString = req.getParameter("note");
		if (!(StringUtils.hasLength(noteString))) {
			noteString = "无";
		}
		System.out.println(usernameString + sexString + iphone + emailString + idcard + noteString);

		UserDao dao = new UserDao();
		User user = new User();

		User haveuser = dao.findidcard(idcard);
		if (haveuser != null) {
			System.out.println("用户已经存在");
			resp.sendRedirect("/Library/addreader.jsp");
		} else {

			user.setUsername(usernameString);
			user.setSex(sexString);
			user.setIphone(iphone);
			user.setEmail(emailString);
			user.setIdcard(idcard);
			user.setNote(noteString);
			boolean b = dao.addUser(user);

			if (b) {
				System.out.println("添加成功");
				// 返回添加界面
				resp.sendRedirect("/Library/addreader.jsp");
			} else {
				// 添加失败 返回添加界面 共享错误信息
				System.out.println("添加失败");
				resp.sendRedirect("/Library/addreader.jsp");
			}
		}
	}

	/**
	 * 更新用户
	 * 
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	protected void updateUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		// 创建session
		HttpSession session = req.getSession();
		// 获取请求参数
		User user = (User) session.getAttribute("findAUser");
		// 获取原书籍的信息
		Long id = user.getId();
		String username = user.getUsername();
		String sex = user.getSex();
		Long iphone = user.getIphone();
		String email = user.getEmail();
		Long idcard = user.getIdcard();
		String note = user.getNote();

		// 获取请求参数
		String newusername = req.getParameter("username");
		String newsex = req.getParameter("sex");
		String newiphone = req.getParameter("iphone");
		String newemail = req.getParameter("email");
		String newnote = req.getParameter("note");

		// 处理请求参数
		if (StringUtils.hasLength(newusername)) {// 新读者名称
			username = newusername;
		}
		if (StringUtils.hasLength(newsex)) {// 新性别
			sex = newsex;
		}
		if (StringUtils.hasLength(newiphone)) {// 新手机号码
			iphone = Long.valueOf(newiphone);
		}
		if (StringUtils.hasLength(newemail)) {// 新邮箱
			email = newemail;
		}
		if (StringUtils.hasLength(newnote)) {// 新备注
			note = newnote;

		}
		System.out.println("---华---丽---的---分---隔---线---");

		User newuser = new User();
		// newbook.setId(null);
		newuser.setUsername(username);
		newuser.setSex(sex);
		newuser.setIphone(iphone);
		newuser.setEmail(email);
		newuser.setIdcard(idcard);
		newuser.setNote(note);

		System.out.println("新读者" + newuser);
		//
		UserDao dao = new UserDao();
		boolean b = dao.updateUser(newuser, id);
		System.out.println(b);
		if (b) {
			System.out.println("更新成功");
			// 返回更新
			User findAUser = dao.findUser(id, idcard);
			session.setAttribute("findAUser", findAUser);
			resp.sendRedirect("/Library/updatereader.jsp");
		} else {
			System.out.println("更新失败");
			resp.sendRedirect("/Library/updatereader.jsp");
		}
	}

	/**
	 * 删除用户
	 * 
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	protected void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub

		// 获取请求参数
		Long id = Long.valueOf(req.getParameter("id"));
		Long idcard = Long.valueOf(req.getParameter("idcard"));
		int currentPage = (int) Integer.valueOf(req.getParameter("currentPage"));

		UserDao dao = new UserDao();
		boolean b = dao.delectUser(id, idcard);
		if (b) {
			System.out.println("删除成功");// 返回当前页
			// 当删除的是最后一条且单独一页时
			PageResult findPageResult = dao.findPageResult(currentPage);
			if (!(findPageResult.getListData().size() > 0)) {
				currentPage = currentPage - 1;
				if (currentPage == 0) {
					currentPage = 1;
				}
			}
			// 删除成功 返回当前页
			resp.sendRedirect("/Library/user?msg=findUserPage&currentPage=" + currentPage);
		} else {
			// 删除不成功 返回当前页
			resp.sendRedirect("/Library/user?msg=findUserPage&currentPage=" + currentPage);

		}

	}

	/**
	 * 分页处理
	 * 
	 * @param req
	 * @param resp
	 * @throws SQLException
	 * @throws Exception
	 */
	protected void findUserPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, Exception {

		// TODO Auto-generated method stub

		// 获取请求参数:当前页 并转成 int
		String page = req.getParameter("currentPage");
		if (page == null) {
			page = "1";
		}
		int currentPage = (int) Integer.valueOf(page);
		if (!(currentPage > 1)) {
			currentPage = 1;
		}

		UserDao dao = new UserDao();
		PageResult pageResult = dao.findPageResult(currentPage);

		// 创建session
		HttpSession session = req.getSession();
		session.setAttribute("userPageResult", pageResult);
		// 返回页面
		resp.sendRedirect("/Library/reader.jsp");
	}

	/**
	 * 查找user 读者 -idcard
	 * 
	 * @param req
	 * @param resp
	 * @throws SQLException
	 * @throws Exception
	 */
	public void findAUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, Exception {

		// TODO Auto-generated method stub

		UserDao dao = new UserDao();

		HttpSession session = req.getSession();

		// 获取请求参数
		long id = (long) Long.valueOf(req.getParameter("id"));
		long idcard = (long) Long.valueOf(req.getParameter("idcard"));

		User findAUser = dao.findUser(id, idcard);
		session.setAttribute("findAUser", findAUser);
		resp.sendRedirect("/Library/updatereader.jsp");

	}

}
