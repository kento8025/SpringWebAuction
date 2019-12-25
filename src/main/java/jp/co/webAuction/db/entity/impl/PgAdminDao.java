package jp.co.webAuction.db.entity.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.webAuction.db.entity.AdminDao;
import util.Util;

public class PgAdminDao implements AdminDao {

	private Connection con;

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

	public void delete(int id) {

		Setup();

		try (PreparedStatement stmt = con.prepareStatement("DELETE FROM Product WHERE id = ? ;")) {

			stmt.setInt(1, id);

			stmt.executeUpdate();

			System.out.println("削除完了");

			con.commit();

		} catch (Exception e) {
			System.out.println("delete メソッド");
			System.out.println("削除失敗");
			tearDown();
		}

	}

	public void hidden(int id) {

		Setup();

		try (PreparedStatement stmt = con.prepareStatement("UPDATE Product SET should_show = 2 WHERE id = ?")) {

			stmt.setInt(1, id);

			stmt.executeUpdate();

			System.out.println("非表示");

			con.commit();

		} catch (Exception e) {
			System.out.println("hidden メソッド");
			System.out.println("更新失敗");
			tearDown();
		}

	}

	public void hiddenCancel(int id) {

		Setup();

		try (PreparedStatement stmt = con.prepareStatement("UPDATE Product SET should_show = 1 WHERE id = ?")) {

			stmt.setInt(1, id);

			stmt.executeUpdate();

			System.out.println("公開");

			con.commit();

		} catch (Exception e) {
			System.out.println("hiddenCancel メソッド");
			System.out.println("更新失敗");
			tearDown();
		}

	}

}
