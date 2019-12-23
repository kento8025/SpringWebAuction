/*package jp.co.webAuction.db.entity.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.dto.PurchaseDisplay;
import jp.co.webAuction.db.dto.User;
import util.Util;

public class PgStock  {

	private Connection con;
	private final String SELECT_PRODUCT = "SELECT * FROM product WHERE 1=1";
	private String WHERE_SET = "";
	private final String SELECT_FROM_PRODUCT_AND_USERS_AND_CATEGORY = "  SELECT * FROM product as p LEFT JOIN users u ON  u.id = p.user_id LEFT JOIN category c ON  c.id = p.category_id";

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

	public List<Product> productSearch(String productName) {

		List<Product> list = new ArrayList<Product>();
		List<Object> searchList = new ArrayList<Object>();
		Product product = new Product();
		product.setProductName(productName);
		searchList = searchSet(product);

		Setup();

		try (PreparedStatement stmt = con.prepareStatement(SELECT_PRODUCT + WHERE_SET + "AND should_show = 1")) {

			System.out.println(SELECT_PRODUCT + WHERE_SET + " AND should_show = 1");

			for (int i = 0; searchList.size() > i; i++) {

				if (searchList.get(i) instanceof Integer) {
					Integer setInt = (Integer) searchList.get(i);
					stmt.setInt(i + 1, setInt);

				} else {
					String setStr = (String) searchList.get(i);
					stmt.setString(i + 1, "%" + setStr + "%");
				}

			}

			ResultSet rs = stmt.executeQuery();

			con.commit();

			while (rs.next()) {

				Product p = new Product(rs.getString("product_name"), rs.getString("product_img"),
						rs.getInt("category_id"), rs.getString("product_status"),
						rs.getString("description"), rs.getString("postage"), rs.getString("shipping_origin"),
						rs.getString("shipping_method"), rs.getInt("price"),
						rs.getInt("exhibition_period"));

				p.setProductId(rs.getInt("id"));

				list.add(p);
			}

			System.out.println("検索完了");

		} catch (Exception e) {
			System.out.println("productSearch メソッド");
			System.out.println("検索失敗");
			e.printStackTrace();
			tearDown();
		}

		return list;

	}

	public PurchaseDisplay productInformation(String productId) {

		Integer Id = Integer.parseInt(productId);

		Setup();

		try (PreparedStatement stmt = con
				.prepareStatement(SELECT_FROM_PRODUCT_AND_USERS_AND_CATEGORY + " WHERE p.id = ? ")) {

			stmt.setInt(1, Id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Product p = new Product(rs.getString("product_name"), rs.getString("product_img"),
						rs.getInt("category_id"), rs.getString("product_status"),
						rs.getString("description"), rs.getString("postage"), rs.getString("shipping_origin"),
						rs.getString("shipping_method"), rs.getInt("price"),
						rs.getInt("exhibition_period"));

				p.setProductId(rs.getInt("id"));


				User u = new User(rs.getInt("id"), rs.getString("user_name"), rs.getDate("birthday"),
						rs.getString("man_or_woman"), rs.getString("mail"), rs.getString("user_id"),
						rs.getString("password"));

				String c = rs.getString("category_name");

				PurchaseDisplay purchaseDisplay = new PurchaseDisplay(u, p, c);

				return purchaseDisplay;
			}

			con.commit();

			System.out.println("検索完了");

		} catch (Exception e) {
			System.out.println("productInformationメソッド");
			System.out.println("検索失敗");
			e.printStackTrace();
			tearDown();
		}

		return null;

	}

	private List<Object> searchSet(Product product) {

		String sql = "";

		List<Object> list = new ArrayList<Object>();

		if (!(product.getProductName() == null || product.getProductName().isEmpty())) {

			sql += " AND " + "product_name LIKE ?";
			list.add(product.getProductName());
		}

		this.WHERE_SET = sql;

		return list;

	}

}
*/