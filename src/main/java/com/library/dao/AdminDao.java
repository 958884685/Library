package com.library.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.library.entity.Admin;
import com.library.utils.JDBCUtils;

/**
 * 
 * @author 刘鸿伟
 *
 */
public class AdminDao {

	/**
	 * 登陆 admin
	 * 
	 * @param adminname admin：用户名
	 * @param password  密码
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Admin findAdmin(String adminname, String password) throws SQLException, Exception {
		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();

		ArrayList<Object> params = new ArrayList<Object>();

		params.add(0, adminname);
		params.add(1, password);
		String sql = "select * from t_admin where adminname=? and password=?";

		Admin admin = utils.findSimpleResult(sql, params, Admin.class);
		return admin;
	}

	/**
	 * // 修改密码
	 * 
	 * @param id          admin?id
	 * @param newpassword
	 * @return
	 * @throws Exception
	 */
	public boolean changePassword(Long id, String newpassword) throws Exception {

		JDBCUtils utils = new JDBCUtils();
		utils.getConnection();

		ArrayList<Object> params = new ArrayList<Object>();

		params.add(0, newpassword);
		params.add(1, id);

		String sql = " update t_admin set password=? where id=?";
		boolean b = utils.updateByPreparedStatement(sql, params);
		if (b) {
			System.out.println("修改成功");
		}
		return b;

	}

}
