package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {

	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/auctiondb", "axizuser", "axiz");
		} catch (Exception e) {
			// �{���͐�p�̗�O�N���X���쐬�����ق����悢
			throw new RuntimeException(e);
		}
	}


}
