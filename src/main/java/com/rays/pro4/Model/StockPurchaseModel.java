package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.StockPurchaseBean;
import com.rays.pro4.Util.JDBCDataSource;

public class StockPurchaseModel {
	public Integer nextPK() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_stockpurchase");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}

	public long add(StockPurchaseBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_stockpurchase values(?,?,?,?,?)");

		pstmt.setInt(1, pk);
		pstmt.setInt(2, bean.getQuantity());
		pstmt.setInt(3, bean.getPurchasePrice());
		pstmt.setDate(4, new java.sql.Date(bean.getPurchaseDate().getTime()));
		pstmt.setString(5, bean.getOrderType());

		int i = pstmt.executeUpdate();
		System.out.println("Data Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}

	public void delete(StockPurchaseBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from st_stockpurchase where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("Data delete successfully " + i);
		conn.commit();

		pstmt.close();
	}

	public void update(StockPurchaseBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_stockpurchase set quantity = ?, purchasePrice = ?, purchaseDate = ?, orderType = ? where id = ?");

		pstmt.setInt(1, bean.getQuantity());
		pstmt.setInt(2, bean.getPurchasePrice());
		pstmt.setDate(3, new java.sql.Date(bean.getPurchaseDate().getTime()));
		pstmt.setString(4, bean.getOrderType());
		pstmt.setLong(5, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public StockPurchaseBean findByPK(long pk) throws Exception {

		String sql = "select * from st_stockpurchase where id = ?";
		StockPurchaseBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new StockPurchaseBean();
			bean.setId(rs.getLong(1));
			bean.setQuantity(rs.getInt(2));
			bean.setPurchasePrice(rs.getInt(3));
			bean.setPurchaseDate(rs.getDate(4));
			bean.setOrderType(rs.getString(5));

		}

		rs.close();

		return bean;
	}

	public List search(StockPurchaseBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_stockpurchase where 1=1");
		if (bean != null) {

			if (bean.getQuantity() > 0) {
				sql.append(" AND quantity like '" + bean.getQuantity() + "%'");
			}

			if (bean.getPurchasePrice() > 0) {
				sql.append(" AND purchasePrice like '" + bean.getPurchasePrice() + "%'");
			}

			if (bean.getOrderType() != null && bean.getOrderType().length() > 0) {
				sql.append(" AND orderType like '" + bean.getOrderType() + "%'");
			}

			if (bean.getPurchaseDate() != null && bean.getPurchaseDate().getTime() > 0) {
				Date d = new Date(bean.getPurchaseDate().getTime());
				sql.append(" AND purchaseDate = '" + d + "'");
				System.out.println("done");
			}

			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}

		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);

		}

		System.out.println("sql query search >>= " + sql.toString());
		List list = new ArrayList();

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new StockPurchaseBean();
			bean.setId(rs.getLong(1));
			bean.setQuantity(rs.getInt(2));
			bean.setPurchasePrice(rs.getInt(3));
			bean.setPurchaseDate(rs.getDate(4));
			bean.setOrderType(rs.getString(5));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_stockpurchase");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			StockPurchaseBean bean = new StockPurchaseBean();

			bean.setId(rs.getLong(1));
			bean.setQuantity(rs.getInt(2));
			bean.setPurchasePrice(rs.getInt(3));
			bean.setPurchaseDate(rs.getDate(4));
			bean.setOrderType(rs.getString(5));

			list.add(bean);

		}

		rs.close();

		return list;
	}

}