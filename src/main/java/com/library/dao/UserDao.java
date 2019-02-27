package com.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.library.entity.User;
import com.library.page.PageResult;
import com.library.utils.JDBCUtils;

/**
 * 
 * @author 刘鸿伟
 *
 */
public class UserDao {

	/**
	 * 添加用户(读者)
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean addUser(User user) throws Exception {
		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();

		ArrayList<Object> params = new ArrayList<Object>();

		params.add(0, user.getUsername());
		params.add(1, user.getSex());
		params.add(2, user.getIphone());
		params.add(3, user.getEmail());
		params.add(4, user.getIdcard());
		params.add(5, user.getNote());

		String sql = "INSERT INTO t_user VALUES (NULL,?,?,?,?,?,?)";

		boolean b = utils.updateByPreparedStatement(sql, params);
		return b;

	}

	/**
	 * 删除用户(读者)
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delectUser(Long id, Long idcard) throws Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();

		String sql = "DELETE FROM t_user WHERE id=? AND idcard=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, id);
		params.add(1, idcard);

		boolean b = utils.updateByPreparedStatement(sql, params);
		return b;
	}

	/**
	 * 修改用户(读者)
	 * 
	 * @param user
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean updateUser(User user, Long id) throws Exception {
		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();

		String sql = "UPDATE t_user SET username = ?, sex =?, iphone = ?, email = ?, idcard = ?, note = ? WHERE id = ?";

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, user.getUsername());
		params.add(1, user.getSex());
		params.add(2, user.getIphone());
		params.add(3, user.getEmail());
		params.add(4, user.getIdcard());
		params.add(5, user.getNote());
		params.add(6, id);

		boolean b = utils.updateByPreparedStatement(sql, params);
		return b;
	}

	/**
	 * 单-查询用户(读者)
	 * 
	 * @return User
	 * @throws SQLException
	 * 
	 * @throws Exception
	 * 
	 */
	public User findUser(Long id, Long idcard) throws SQLException, Exception {
		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, id);
		params.add(1, idcard);

		String sql = "select * from t_user where id = ? AND idcard = ? ";
		User user = utils.findSimpleResult(sql, params, User.class);
		return user;
	}

	/**
	 * 查询所有的用户(读者)
	 * 
	 * @return List<User>
	 * @throws SQLException
	 * 
	 * @throws Exception
	 * 
	 */
	public List<User> findAllUser() throws SQLException, Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();
		String sql = "select * from t_user";
		List<User> userList = utils.findModeResult(sql, null, User.class);
		return userList;

	}

	/**
	 * 分页查询:结果集
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<User> findUserPage(int currentPage) throws SQLException, Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();

		int limitBegin = ((currentPage + 1 - 2)) * 5;

		String sql = "select * from t_user LIMIT ?, 5";// 每页5条数据
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, limitBegin);

		List<User> userList = utils.findModeResult(sql, params, User.class);
		return userList;

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
		List<User> list = findUserPage(CurrentPage);
		int resultSet = queryResultSet();
		PageResult pageResult = new PageResult(list, CurrentPage, 5, resultSet);
		return pageResult;

	}

	/**
	 * 模糊查询
	 * 
	 * @return List<User>
	 * @throws Exception
	 * @throws SQLException
	 * 
	 */
	public List<User> findUserLike(String findmsg) throws SQLException, Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();

		String sql = "SELECT * FROM t_user WHERE username LIKE concat('%',?,'%') OR sex LIKE concat('%',?,'%') OR iphone LIKE concat('%',?,'%') OR email LIKE concat('%',?,'%') OR idcard LIKE concat('%',?,'%') OR note LIKE concat('%',?,'%') ";

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, findmsg);
		params.add(1, findmsg);
		params.add(2, findmsg);
		params.add(3, findmsg);
		params.add(4, findmsg);
		params.add(5, findmsg);
		List<User> userList = utils.findModeResult(sql, params, User.class);
		return userList;

	}

	/**
	 * 查询结果集:总条数
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int queryResultSet() throws SQLException {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();
		int recordTotal = 0;
		String sql = "select count(id) from t_user";
		ResultSet resultSet = utils.queryResultSet(sql, null);
		if (resultSet.next()) {
			recordTotal = resultSet.getInt(1);
		}
		return recordTotal;
	}

	/**
	 * 单-查询用户(读者) user.idcard 是否存在
	 * 
	 * @return User
	 * @throws SQLException
	 * 
	 * @throws Exception
	 * 
	 */
	public User findidcard(Long idcard) throws SQLException, Exception {
		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, idcard);
		String sql = "select * from t_user where idcard = ? ";
		User user = utils.findSimpleResult(sql, params, User.class);
		return user;
	}
	// -->模糊查询开始

	/**
	 * 模糊查询_分页结果集
	 * 
	 * @return List<User>
	 * @throws Exception
	 * @throws SQLException
	 * 
	 */
	public List<User> findUserLikePage(String findmsg, int CurrentPage) throws SQLException, Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();
		String sql = "SELECT * FROM t_user WHERE username LIKE concat('%',?,'%') OR sex LIKE concat('%',?,'%') OR iphone LIKE concat('%',?,'%') OR email LIKE concat('%',?,'%') OR idcard LIKE concat('%',?,'%') OR note LIKE concat('%',?,'%') LIMIT ?,5";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(0, findmsg);
		params.add(1, findmsg);
		params.add(2, findmsg);
		params.add(3, findmsg);
		params.add(4, findmsg);
		params.add(5, findmsg);
		int c = (CurrentPage + 1 - 2) * 5;
		params.add(6, c);
		List<User> userList = utils.findModeResult(sql, params, User.class);
		return userList;

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
	public PageResult findUserLikePageResult(int CurrentPage, String findmsg) throws Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();
		List<User> list = findUserLikePage(findmsg, CurrentPage);// 分页集合
		List<User> userLike = findUserLike(findmsg);// 模糊查询总list
		int resultSet = userLike.size();// 总条数
		PageResult pageResult = new PageResult(list, CurrentPage, 5, resultSet);
		return pageResult;

	}

	// 模糊查询结束<--

}
