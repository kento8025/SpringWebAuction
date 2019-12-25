package jp.co.webAuction.db.entity.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.dto.PurchaseDisplay;
import jp.co.webAuction.db.entity.SearchDao;

@Repository
public class PgSearchDao implements SearchDao {

	private final String SELECT_PRODUCT = "SELECT * FROM product WHERE 1=1";
	private String where_set = "";
	private final String SELECT_FROM_PRODUCT_AND_USERS_AND_CATEGORY = "  SELECT  p.id as primaryProductId , u.id as primaryUserId , *  FROM product as p LEFT JOIN users u ON  u.id = p.user_id LEFT JOIN category c ON  c.id = p.category_id";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Product> productSearch(String productName) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		Product product = new Product();
		product.setProductName(productName);
		searchSet(productName);

		param.addValue("product_name", product.getProductName());

		String sql = SELECT_PRODUCT + where_set + " AND should_show = 1";

		System.out.println(sql);

		System.out.println("åüçıäÆóπ");

		return jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<Product>(Product.class));

	}

	@Override
	public PurchaseDisplay productInformation(int productId) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		String sql = SELECT_FROM_PRODUCT_AND_USERS_AND_CATEGORY + " WHERE p.id = :p.id  ";

		param.addValue("p.id", productId);

		System.out.println(sql);

		List<PurchaseDisplay> purchaseDisplay =  new ArrayList<>();


		purchaseDisplay = jdbcTemplate.query(
		sql,
		param,
		new BeanPropertyRowMapper<PurchaseDisplay>(PurchaseDisplay.class));

		return purchaseDisplay.get(0);


	}



	private void searchSet(String productName) {

		String sql = "";

		if (!(productName == null || productName.isEmpty())) {
			//sql += " AND " + "product_name LIKE = :" + "%" + "product_name+"+ "%";
			sql += " AND product_name  = :product_name ";

		}

		this.where_set = sql;

	}

}
