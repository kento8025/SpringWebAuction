package tool;

import java.sql.Connection;
import java.sql.SQLException;

import util.Util;

public class DbSetUp {

	private Connection con;

	public void setup() {

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

}
