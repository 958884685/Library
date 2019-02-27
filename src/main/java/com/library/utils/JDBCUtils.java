package com.library.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mysql数据库工具类
 * 
 * 
 * @author 刘鸿伟
 *
 */
public class JDBCUtils {

	// 数据库用户名
	private static final String USERNAME = "root";
	// 数据库密码
	private static final String PASSWORD = "root";
	// 驱动信息
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	// 数据库地址
	private static final String URL = "jdbc:mysql://localhost:3306/library?useUnicode=true&characterEncoding=UTF-8";

	private Connection connection;// 连接对象
	private PreparedStatement pstmt;// 预编译对象
	private ResultSet resultSet;// 结果集

	public JDBCUtils() {
		try {
			Class.forName(DRIVER);

			System.out.println("数据库连接成功");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;

	}

	/**
	 * 释放资源
	 */
	public void releaseConn() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 增加,删除,修改
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean updateByPreparedStatement(String sql, List<Object> params) throws Exception {
		boolean flag = false;
		int result = -1;
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		result = pstmt.executeUpdate();
		flag = result > 0 ? true : false;
		return flag;
	}

	/**
	 * 
	 * 查询单条记录
	 * 
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 * @throws Exception
	 */
	public <T> T findSimpleResult(String sql, List<Object> params, Class<T> cls)
			throws SQLException, Exception, Exception {
		T resultObject = null;
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			resultObject = cls.newInstance();

			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);

				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true);
				field.set(resultObject, cols_value);

			}

		}
		return resultObject;
	}

	/**
	 * 多条记录查询
	 * 
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 * @throws Exception
	 */
	public <T> List<T> findModeResult(String sql, List<Object> params, Class<T> cls)
			throws SQLException, Exception, Exception {
		ArrayList<T> list = new ArrayList<T>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			T resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true);
				field.set(resultObject, cols_value);

			}
			list.add(resultObject);
		}
		return list;
	}

	/**
	 * 查询结果
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 * @throws SQLException
	 */
	public ResultSet queryResultSet(String sql, List<Object> params) throws SQLException {

		PreparedStatement prepareStatement = connection.prepareStatement(sql);

		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}

		ResultSet resultSet = prepareStatement.executeQuery();
		return resultSet;
	}

}
