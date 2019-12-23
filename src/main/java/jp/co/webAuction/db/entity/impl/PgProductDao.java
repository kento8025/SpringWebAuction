package jp.co.webAuction.db.entity.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.ProductDao;
import util.Util;

@Repository
public class PgProductDao implements ProductDao{

	private Connection con;
	private final String SELECT_CATEGORY = "SELECT * FROM category WHERE category_name = ?";
	private final String INSERT_INTO_PRODUCT = "INSERT INTO product (user_id , product_name ,product_img ,category_id , product_status , description , postage , shipping_origin , shipping_method , price , exhibition_period , should_show , Registration_dete) VALUES "
			+ "(?,?,?,?,?,?,?,?,?,?,?,1,now())";

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

	@Override
	public void register(Product product , User user) {

		Setup();

		System.out.println("register メソッド");

		try (PreparedStatement stmt = con.prepareStatement(INSERT_INTO_PRODUCT)) {

			stmt.setInt(1,user.getUserNo());
			stmt.setString(2,product.getProductName());
			stmt.setString(3,product.getProductImg());
			stmt.setInt(4,product.getCategoryId());
			stmt.setString(5,product.getProductStatus());
			stmt.setString(6,product.getDescription());
			stmt.setString(7,product.getPostage());
			stmt.setString(8,product.getShippingOrigin());
			stmt.setString(9,product.getShipping_method());
			stmt.setInt(10,product.getPrice());
			stmt.setInt(11,product.getExhibition_period());


			stmt.executeUpdate();

			System.out.println("INSERTに成功しました");
			System.out.println("接続終了");

			con.commit();

		} catch (Exception e) {
			System.out.println("INSERT失敗");
			e.printStackTrace();
			tearDown();
		}
	}


	@Override
	public int categorySearch(String category) {

		Setup();

		try (PreparedStatement stmt = con.prepareStatement(SELECT_CATEGORY)) {

			stmt.setString(1, category);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			tearDown();
		}
		return 0;

	}

}
