package com.library.Test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.BookDao;
import com.library.dao.UserDao;
import com.library.entity.Book;
import com.library.entity.User;
import com.library.page.PageResult;

@WebServlet("/sc")
public class SearchTest extends HttpServlet {

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

		String findmsg = req.getParameter("findmsg");// 查找内容
		String searchtype = req.getParameter("searchtype");// 查找类型
		System.out.println(findmsg);
		System.out.println(searchtype);

		int CurrentPage = 2;
		UserDao dao = new UserDao();
		try {
			List<User> findUserLike = dao.findUserLike(findmsg);
			System.out.println("个数:"+findUserLike.size());
			PageResult result = dao.findUserLikePageResult(CurrentPage, findmsg);
			List<User> list = (List<User>) result.getListData();
			System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
