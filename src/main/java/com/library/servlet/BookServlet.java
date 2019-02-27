package com.library.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dao.BookDao;
import com.library.entity.Book;
import com.library.page.PageResult;
import com.library.utils.Errormsg;
import com.library.utils.StringUtils;

/**
 * 书籍的添加、更新、删除
 * 
 * 分页查询
 * 
 * @author 刘鸿伟
 *
 */
@WebServlet("/book")
public class BookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// resp.sendRedirect("/Library/index.jsp");
	// req.getRequestDispatcher("/WEB-INF/addbook.jsp").forward(req,resp);

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
					if (msg.equals("addbook")) {
						addbook(req, resp);
					} else if (msg.equals("updatebook")) {
						updatebook(req, resp);
					} else if (msg.equals("delectbook")) {
						delectbook(req, resp);
					} else if (msg.equals("findBookPage")) {
						findBookPage(req, resp);
					} else if (msg.equals("findABook")) {
						findABook(req, resp);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	// 添加书籍
	protected void addbook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub

		// 获取请求参数
		Long bookid = Long.valueOf(req.getParameter("bookid"));// 唯一识别码
		String booknameString = req.getParameter("bookname");// 书名
		String typeString = req.getParameter("type");// 类型
		String authorString = req.getParameter("author");// 作者
		Double price = Double.valueOf(req.getParameter("price"));// 价格
		String stateString = req.getParameter("state");// 备注信息

		HttpSession session = req.getSession();
		Errormsg errormsg = new Errormsg();
		if (!(StringUtils.hasLength(stateString))) {
			stateString = "无";
		}

		System.out.println(bookid + booknameString + typeString + authorString + price + stateString);

		BookDao dao = new BookDao();
		Book haveBook = dao.findBookid(bookid);
		if (haveBook != null) {
			System.out.println("书籍存在,无法添加");
			session.setAttribute("bookErrorMsg", errormsg.Bookhad);
			resp.sendRedirect("/Lirary/addbook.jsp");
			session.removeAttribute("bookErrorMsg");
		} else {
			// 书籍-不存在
			Book book = new Book();
			book.setBookid(bookid);
			book.setBookname(booknameString);
			book.setType(typeString);
			book.setAuthor(authorString);
			book.setPrice(price);
			book.setState(stateString);
			boolean b = dao.addBook(book);
			if (b) {
				System.out.println("添加成功");
				session.setAttribute("bookErrorMsg", errormsg.BookTrue);

				resp.sendRedirect("/Lirary/addbook.jsp");
				session.removeAttribute("bookErrorMsg");
			} else {
				System.out.println("添加失败");
				session.setAttribute("bookErrorMsg", errormsg.BookFalse);
				resp.sendRedirect("/Lirary/addbook.jsp");
				session.removeAttribute("bookErrorMsg");
			}

		}

	}

	// 更新书籍
	protected void updatebook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		// 获取请求参数
		Book book = (Book) session.getAttribute("findABook");
		// 获取原书籍的信息
		Long id = book.getId();
		Long bookid = book.getBookid();
		String bookname = book.getBookname();
		String type = book.getType();
		String author = book.getAuthor();
		Double price = book.getPrice();
		String state = book.getState();

		// 获取请求参数
		String newbookname = req.getParameter("bookname");
		String newtype = req.getParameter("type");
		String newauthor = req.getParameter("author");
		String newstate = req.getParameter("state");
		String newprice = req.getParameter("price");
		System.out.println("参数价格是" + newprice);
		// 处理请求参数
		if (StringUtils.hasLength(newbookname)) {// 新书名
			bookname = newbookname;
		}
		if (StringUtils.hasLength(newtype)) {// 新类型
			type = newtype;
		}
		if (StringUtils.hasLength(newauthor)) {// 新作者
			author = newauthor;
		}
		if (StringUtils.hasLength(newstate)) {// 备注
			state = newstate;
		}
		if (StringUtils.hasLength(newprice)) {// 新价格
			System.out.println(newprice + "xinjiage");
			price = Double.valueOf(newprice);
			System.out.println("新价格是" + price);
		}
		System.out.println("---华---丽---的---分---隔---线---");
		System.out.println("价格是" + price);
		Book newbook = new Book();
		// newbook.setId(null);
		newbook.setBookid(bookid);
		newbook.setBookname(bookname);
		newbook.setType(type);
		newbook.setAuthor(author);
		newbook.setPrice(price);
		newbook.setState(state);
		System.out.println("新书" + newbook);
		//
		BookDao dao = new BookDao();
		boolean b = dao.updateBook(newbook, id);
		System.out.println(b);
		if (b) {
			System.out.println("更新成功");
			// 返回更新
			Book findABook = dao.findBook(id, bookid);
			session.setAttribute("findABook", findABook);
			resp.sendRedirect("/Library/updatebook.jsp");
		} else {
			System.out.println("更新失败");
			resp.sendRedirect("/Library/updatebook.jsp");
		}

	}

	// 删除书籍
	protected void delectbook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub

		// 获取请求参数
		Long id = Long.valueOf(req.getParameter("id"));
		Long bookid = Long.valueOf(req.getParameter("bookid"));
		int currentPage = (int) Integer.valueOf(req.getParameter("currentPage"));

		BookDao dao = new BookDao();
		boolean b = dao.delectBook(id, bookid);
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
			resp.sendRedirect("/Library/book?msg=findBookPage&currentPage=" + currentPage);
		} else {
			resp.sendRedirect("/Library/book?msg=findBookPage&currentPage=" + currentPage);

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
	protected void findBookPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, Exception {

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

		BookDao dao = new BookDao();
		PageResult pageResult = dao.findPageResult(currentPage);

		// 创建session
		HttpSession session = req.getSession();
		session.setAttribute("bookPageResult", pageResult);
		// 返回页面
		resp.sendRedirect("/Library/books.jsp");
	}

	/**
	 * 查询即将要修改的书籍 并 返回修改页面
	 * 
	 * @param req
	 * @param resp
	 * @throws SQLException
	 * @throws Exception
	 */
	public void findABook(HttpServletRequest req, HttpServletResponse resp) throws SQLException, Exception {
		// TODO Auto-generated method stub

		BookDao dao = new BookDao();

		HttpSession session = req.getSession();

		// 获取请求参数
		long id = (long) Long.valueOf(req.getParameter("id"));
		long bookid = (long) Long.valueOf(req.getParameter("bookid"));

		Book findABook = dao.findBook(id, bookid);
		session.setAttribute("findABook", findABook);
		resp.sendRedirect("/Library/updatebook.jsp");

	}

}
