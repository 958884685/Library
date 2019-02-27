package com.library.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dao.BookDao;
import com.library.dao.UserDao;
import com.library.entity.Book;
import com.library.entity.User;
import com.library.page.PageResult;
import com.library.utils.StringUtils;

/**
 * 
 * @author 刘鸿伟
 *
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {

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
			// 获取参数
			String findmsg = req.getParameter("findmsg");// 查找内容
			String msg = req.getParameter("msg");// 查找 范围
			System.out.println(" 查找内容" + findmsg);
			System.out.println(" 查找范围" + msg);

			if (StringUtils.hasLength(findmsg)) {
				if (StringUtils.hasLength(msg)) {
					if (msg.equals("removeEverything")) { // 移除主页所有查找内容
						session.removeAttribute("find_BU");
						resp.sendRedirect("/Library/index.jsp");
					} else if (msg.equals("findAnything")) { // 模糊查询user和book
						try {
							findAnything(req, resp);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else {
				// 无查找内容时 移除所有的搜索并返回主页
				session.removeAttribute("find_BU");
				session.setAttribute("findnothing", "没有查到你想要的信息");
				resp.sendRedirect("/Library/index.jsp");

			}

		}
	}

	public void findAnything(HttpServletRequest req, HttpServletResponse resp) throws SQLException, Exception {
		// TODO Auto-generated method stub

		HttpSession session = req.getSession();
		// 获取参数
		String findmsg = req.getParameter("findmsg");// 查找内容
		session.setAttribute("findmsg", findmsg);

		String page = req.getParameter("currentPage");
		if (page == null) {
			page = "1";
		}
		int currentPage = (int) Integer.valueOf(page);
		if (!(currentPage > 1)) {
			currentPage = 1;
		}

		ArrayList<Object> find_book_user = find_book_user(findmsg);

		// find_book_user-->list 总集合到分页集合
		// limit ?,5
		ArrayList<Object> list = new ArrayList<Object>();// 存放模糊查询分页的集合,共享页面数据的list
		int k = ((currentPage - 1) * 5);// ?=k 分页的起始位置
		for (int i = k; i < currentPage * 5; i++) {
			if (i >= find_book_user.size()) {
				break;
			}
			list.add(i - k, find_book_user.get(i));// 存放到新的集合
			System.out.println("第" + i + "个");
			System.out.println("现在的集合是" + list);
		}
		System.out.println("截取的集合是:" + list);
		PageResult find_BU = new PageResult(list, currentPage, 5, find_book_user.size());
		List<?> listData = find_BU.getListData();
		System.out.println("PageResult" + listData);

		session.setAttribute("find_BU", find_BU);
		resp.sendRedirect("/Library/index.jsp");

	}

	public ArrayList<Object> find_book_user(String findmsg) throws SQLException, Exception {
		// TODO Auto-generated method stub

		BookDao bookDao = new BookDao();
		UserDao userDao = new UserDao();
		ArrayList<Object> listsum = new ArrayList<Object>();// 存放模糊查询的总集合

		List<Book> findBookLike = bookDao.findBookLike(findmsg);
		List<User> findUserLike = userDao.findUserLike(findmsg);

		System.out.println("结果集是");
		System.out.println(findBookLike);
		System.out.println(findUserLike);
		System.out.println("---华---丽---的---分---隔---线---");

		int bookListSize = findBookLike.size();
		int userListSize = findUserLike.size();
		int recordTotal = bookListSize + userListSize;// 总条数

		System.out.println("模糊查询 所有");

		if (findBookLike != null) {
			for (int i = 0; i < bookListSize; i++) {
				listsum.add(i, findBookLike.get(i));
			}
		}

		System.out.println(listsum);
		System.out.println("book 结束");

		int c = bookListSize;
		int d = userListSize;
		if (findUserLike != null) {
			// 12345 67(12) 01234 56(01)
			// 5 < 5 + 2 =7
			for (int i = c; i < c + d; i++) {
				listsum.add(c, findUserLike.get(i - c));
			}
		}

		System.out.println(listsum);
		System.out.println("user 结束");
		System.out.println("条数是" + listsum.size());
		System.out.println("---华---丽---的---分---隔---线---");
		return listsum;

	}

}