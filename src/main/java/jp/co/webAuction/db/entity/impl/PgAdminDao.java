package jp.co.webAuction.db.entity.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.webAuction.db.entity.AdminDao;
import util.Util;

public class PgAdminDao implements AdminDao {

	private Connection con;

	private void Setup() {

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

	public void delete(int id) {

		Setup();

		try (PreparedStatement stmt = con.prepareStatement("DELETE FROM Product WHERE id = ? ;")) {

			stmt.setInt(1, id);

			stmt.executeUpdate();

			System.out.println("�폜����");

			con.commit();

		} catch (Exception e) {
			System.out.println("delete ���\�b�h");
			System.out.println("�폜���s");
			tearDown();
		}

	}

	public void hidden(int id) {

		Setup();

		try (PreparedStatement stmt = con.prepareStatement("UPDATE Product SET should_show = 2 WHERE id = ?")) {

			stmt.setInt(1, id);

			stmt.executeUpdate();

			System.out.println("��\��");

			con.commit();

		} catch (Exception e) {
			System.out.println("hidden ���\�b�h");
			System.out.println("�X�V���s");
			tearDown();
		}

	}

	public void hiddenCancel(int id) {

		Setup();

		try (PreparedStatement stmt = con.prepareStatement("UPDATE Product SET should_show = 1 WHERE id = ?")) {

			stmt.setInt(1, id);

			stmt.executeUpdate();

			System.out.println("���J");

			con.commit();

		} catch (Exception e) {
			System.out.println("hiddenCancel ���\�b�h");
			System.out.println("�X�V���s");
			tearDown();
		}

	}

}
