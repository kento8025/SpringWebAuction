package tool;

import java.sql.Connection;
import java.sql.SQLException;

import util.Util;

public class DbSetUp {

	private Connection con;

	public void setup() {

		System.out.println("�ڑ��J�n");

		con = Util.getConnection();
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�g�����U�N�V�����G���[");
		}
	}

	public void tearDown() {
		try {
			con.rollback();
			System.out.println("�J�n�O�Ƀ��[���o�b�N���܂�");
			System.out.println("�ڑ��I��");
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

}
