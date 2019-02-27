package com.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.Book;
import com.library.entity.User;
import com.library.page.PageResult;
import com.library.utils.JDBCUtils;

/**
 * 
 * @author 刘鸿伟
 *
 */
public class BookDao {

	/**
	 * 添加书籍
	 * 
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public boolean addBook(Book book) throws Exception {
		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();

		ArrayList<Object> params = new ArrayList<Object>();

		params.add(0, book.getBookid());
		params.add(1, book.getBookname());
		params.add(2, book.getType());
		params.add(3, book.getAuthor());
		params.add(4, book.getPrice());
		params.add(5, book.getState());

		String sql = "INSERT INTO t_book VALUES (NULL,?,?,?,?,?,?)";

		boolean b = utils.updateByPreparedStatement(sql, params);
		return b;

	}

	/**
	 * 删除书籍
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delectBook(Long id, Long bookid) throws Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();

		String sql = "delete from t_book where id=? AND bookid=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, id);
		params.add(1, bookid);
		boolean b = utils.updateByPreparedStatement(sql, params);
		return b;
	}

	/**
	 * 修改书籍信息
	 * 
	 * @param book
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean updateBook(Book book, Long id) throws Exception {
		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();

		String sql = "UPDATE t_book SET  bookname =?, type = ?, author = ?, price = ?, state = ? WHERE id = ? AND bookid = ?";

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, book.getBookname());
		params.add(1, book.getType());
		params.add(2, book.getAuthor());
		params.add(3, book.getPrice());
		params.add(4, book.getState());
		params.add(5, id);
		params.add(6, book.getBookid());

		boolean b = utils.updateByPreparedStatement(sql, params);
		return b;
	}

	/**
	 * 单-查询书籍
	 * 
	 * @return Book
	 * 
	 * @throws Exception
	 * @throws SQLException
	 * 
	 */
	public Book findBook(Long id, Long bookid) throws SQLException, Exception {
		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, id);
		params.add(1, bookid);

		String sql = "select * from t_book where id=? AND bookid=?";
		Book book = utils.findSimpleResult(sql, params, Book.class);
		return book;
	}

	/**
	 * 单-查询书籍
	 * 
	 * @return Book
	 * 
	 * @throws Exception
	 * @throws SQLException
	 * 
	 */
	public Book findBookid(Long bookid) throws SQLException, Exception {
		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, bookid);
		String sql = "select * from t_book where bookid=?";
		Book book = utils.findSimpleResult(sql, params, Book.class);
		return book;
	}

	/**
	 * 查询所有书籍
	 * 
	 * @return List<Book>
	 * 
	 * @throws Exception
	 * @throws SQLException
	 */
	public List<Book> findAllBook() throws SQLException, Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();
		String sql = "select * from t_book";
		List<Book> bookList = utils.findModeResult(sql, null, Book.class);
		return bookList;

	}

	/**
	 * 模糊查询
	 * 
	 * @param findmsg
	 * @return List<Book>
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<Book> findBookLike(String findmsg) throws SQLException, Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();
		String sql = "SELECT * FROM t_book WHERE bookid LIKE concat('%',?,'%') OR bookname LIKE concat('%',?,'%') OR type LIKE concat('%',?,'%') OR author LIKE concat('%',?,'%') OR price LIKE concat('%',?,'%') OR state LIKE concat('%',?,'%')";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, findmsg);
		params.add(1, findmsg);
		params.add(2, findmsg);
		params.add(3, findmsg);
		params.add(4, findmsg);
		params.add(5, findmsg);
		List<Book> bookList = utils.findModeResult(sql, params, Book.class);
		return bookList;

	}

	/**
	 * 查询-书籍-结果集:总条数
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int queryResultSet() throws SQLException {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();
		int recordTotal = 0;
		String sql = "select count(id) from t_book";
		ResultSet resultSet = utils.queryResultSet(sql, null);
		if (resultSet.next()) {
			recordTotal = resultSet.getInt(1);
		}
		return recordTotal;
	}

	/**
	 * 
	 * 分页查询:查询当前页的结果集
	 * 
	 * @param CurrentPage
	 *            当前页
	 * @return
	 * @throws Exception
	 */
	public PageResult findPageResult(int CurrentPage) throws Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();
		List<Book> list = findBookPage(CurrentPage);
		int resultSet = queryResultSet();
		PageResult pageResult = new PageResult(list, CurrentPage, 5, resultSet);
		return pageResult;

	}

	/**
	 * 分页查询:结果集
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<Book> findBookPage(int currentPage) throws SQLException, Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();
		int limitBegin = ((currentPage + 1 - 2)) * 5;
		String sql = "select * from t_book LIMIT ?, 5";// 每页5条数据
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, limitBegin);
		List<Book> bookList = utils.findModeResult(sql, params, Book.class);
		return bookList;

	}
	// -->模糊查询 开始

	/**
	 * 模糊查询_分页结果集
	 * 
	 * @param findmsg
	 * @return List<Book>
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<Book> findBookLikePage(String findmsg, int CurrentPage) throws SQLException, Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();
		String sql = "SELECT * FROM t_book WHERE bookid LIKE concat('%',?,'%') OR bookname LIKE concat('%',?,'%') OR type LIKE concat('%',?,'%') OR author LIKE concat('%',?,'%') OR price LIKE concat('%',?,'%') OR state LIKE concat('%',?,'%') LIMIT ?,5";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, findmsg);
		params.add(1, findmsg);
		params.add(2, findmsg);
		params.add(3, findmsg);
		params.add(4, findmsg);
		params.add(5, findmsg);
		int c = (CurrentPage + 1 - 2) * 5;
		params.add(6, c);
		List<Book> bookList = utils.findModeResult(sql, params, Book.class);
		return bookList;

	}

	/**
	 * 模糊查询_分页:查询当前页的结果集
	 * 
	 * @param CurrentPage
	 *            当前页
	 * @return
	 * @throws Exception
	 */
	public PageResult findBookLikePageResult(int CurrentPage, String findmsg) throws Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();
		List<Book> booklist = findBookLike(findmsg);// 查询结果集
		List<Book> list = findBookLikePage(findmsg, CurrentPage);
		int resultSet = booklist.size();// 总条数
		PageResult pageResult = new PageResult(list, CurrentPage, 5, resultSet);
		return pageResult;

	}
	// 模糊查询结束 <--

}
