package jp.co.webAuction.db.entity.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.Util;

public class TradeDao {

	private Connection con;

	private final String INSERT_SUSCCEFUL_DID = "INSERT INTO successful_bid (product_id , user_id , contract_price , trade_status , trade_dete) VALUES(?,?,?,?,now())";

	private void Setup() {

		System.out.println("接続開始");

		con = Util.getConnection();

		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("トランザクションエラー");
		}
	}

	public void tearDown() {
		try {
			con.rollback();
			System.out.println("開始前にロールバックします");
			System.out.println("接続終了");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void register(int productId, int userId, int price) {

		Setup();

		try (PreparedStatement stmt = con.prepareStatement(INSERT_SUSCCEFUL_DID)) {

			stmt.setInt(1, productId);
			stmt.setInt(2, userId);
			stmt.setInt(3, price);
			stmt.setInt(4, 1);

			stmt.executeUpdate();

			System.out.println("INSERTに成功しました");
			System.out.println("接続終了");

			con.commit();

		} catch (SQLException e) {
			System.out.println("INSERT失敗");
			e.printStackTrace();
			tearDown();
		}

	}

}
