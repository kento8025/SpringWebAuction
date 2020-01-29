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
import jp.co.webAuction.db.dto.SuccessfulDidCount;
import jp.co.webAuction.db.entity.ProductDao;
import jp.co.webAuction.db.entity.SearchDao;

@Repository
public class PgSearchDao implements SearchDao {

	@Autowired
	private ProductDao productDao;

	private final String SELECT = "SELECT * , COALESCE(measurement , 0 ) as count  FROM( ";

	private final String SELECT_PRODUCT = " " +
			"SELECT " +
			"p.product_name , " +
			"p.id , " +
			"p.user_id , " +
			"u.user_name , " +
			"p.product_name , " +
			"p.product_img , " +
			"p.category_id , " +
			"p.product_status , " +
			"p.description  , " +
			"p.postage , " +
			"p.shipping_origin , " +
			"p.shipping_method  , " +
			"p.exhibition_period , " +
			"p.should_show , " +
			"p.Registration_dete , " +
			"s.trade_status , " +
			"";

	private final String SELECT_FROM_PRODUCT_INFORMATION = "" +
			"SELECT p.id as primaryProductId , " +
			"p.user_id as primaryUserId , " +
			"MAX (s.id) as trade , " +
			"p.user_id as seller , " +
			"s.user_id as buyer , " +
			"u.user_name  , " +
			"u.mail , " +
			"p.product_name , " +
			"p.product_img , " +
			"p.category_id , " +
			"p.product_status ,  " +
			"p.description , " +
			"p.postage , " +
			"p.shipping_origin , " +
			"p.shipping_method  , " +
			"p.exhibition_period , " +
			"p.should_show , " +
			"p.Registration_dete ,  " +
			"s.trade_status  ,";

	private final String CASE = " " +
			"CASE " +
			"WHEN MAX(s.contract_price)  IS NULL THEN  MAX(p.price)  " +
			"ELSE MAX(s.contract_price)  " +
			"END AS price " +
			"FROM product as p ";

	private final String JOIN = " " +
			"LEFT JOIN successful_bid s ON p.id = s.product_id  " +
			"LEFT JOIN users u ON  u.id = p.user_id  " +
			"LEFT JOIN category c ON  c.id = p.category_id  ";

	private final String WHERE = " " +
			"WHERE 1 = 1  " +
			"AND (trade_status IS NULL OR trade_status = 1) AND should_show = 1 ";

	private final String INFORMATION_WHERE = "" +
			"WHERE p.id = :p.id  AND (trade_status IS NULL OR trade_status = 1) AND should_show = 1 ";

	private final String GROUP_BY = " GROUP BY  p.id , s.trade_status , u.user_name  ";

	private final String INFORMATION_GROUP_BY = " GROUP BY  p.id , s.trade_status , u.user_name , u.user_name , " +
			"s.user_id , u.mail ";

	private final String ORDER_BY = " ORDER BY p.id DESC ";

	private final String INFORMATION_GROUP_ORDER_BY = " ORDER BY price ASC ";

	private final String SBJOIN = "" +
			") sb1 " +
			"LEFT JOIN " +
			"(SELECT count(*) as measurement , p.id  as secondid  FROM successful_bid as s " +
			"LEFT JOIN product p ON s.product_id = p.id  " +
			"WHERE 1 = 1 AND p.should_show = 1  " +
			"GROUP BY s.product_id , p.id , p.product_name) " +
			"sb2 ";

	private final String ON = " ON sb1.id = sb2.secondid ";

	private final String INFORMATION_ON = " ON sb1.seller = sb2.secondid ";

	private int lowerPrice;
	private int highPrice;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/*検索フォームからの商品情報*/
	@Override
	public List<Product> productSearch(String productName, String category, String priceBetweenCommand,
			String productStatus) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		String sql = SELECT +
				SELECT_PRODUCT +
				CASE +
				JOIN +
				WHERE;

		sql += whereSet(productName, category, priceBetweenCommand, productStatus);

		sql += GROUP_BY +
				ORDER_BY +
				SBJOIN +
				ON;

		param.addValue("product_name", "%" + productName + "%");
		param.addValue("categoryId", productDao.categorySearch(category));
		param.addValue("lowerPrice", lowerPrice);
		param.addValue("highPrice", highPrice);
		param.addValue("productStatus", productStatus);

		return jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<Product>(Product.class));

	}

	/*商品画像クリックの情報検索*/
	@Override
	public PurchaseDisplay productInformation(int productId) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		String sql = SELECT +
				SELECT_FROM_PRODUCT_INFORMATION +
				CASE +
				JOIN +
				INFORMATION_WHERE +
				INFORMATION_GROUP_BY +
				INFORMATION_GROUP_ORDER_BY +
				SBJOIN +
				INFORMATION_ON;

		param.addValue("p.id", productId);

		List<PurchaseDisplay> purchaseDisplay = new ArrayList<>();

		purchaseDisplay = jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<PurchaseDisplay>(PurchaseDisplay.class));

		return purchaseDisplay.get(purchaseDisplay.size() - 1);

	}

	/*入札回数カウント*/
	public List<SuccessfulDidCount> successfulDidCountSearch(String productName, String category,
			String priceBetweenCommand,
			String productStatus) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		String sql = "" +
				"SELECT count(*) , s.product_id  FROM successful_bid as s  " +
				"LEFT JOIN product p ON s.product_id = p.id  " +
				"LEFT JOIN category c ON  c.id = p.category_id " +
				"WHERE 1 = 1 AND p.should_show != 3  ";

		sql += whereSet(productName, category, priceBetweenCommand, productStatus);

		param.addValue("product_name", "%" + productName + "%");
		param.addValue("categoryId", productDao.categorySearch(category));
		param.addValue("lowerPrice", lowerPrice);
		param.addValue("highPrice", highPrice);
		param.addValue("productStatus", productStatus);

		sql += " GROUP BY  s.product_id , p.id " + ORDER_BY;

		return jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<SuccessfulDidCount>(SuccessfulDidCount.class));

	}

	/*値段を指定*/
	private void priceBetween(String priceBetweenCommand) {

		if (priceBetweenCommand == null) {
			return;
		}

		switch (priceBetweenCommand) {
		case "1":
			lowerPrice = 0;
			highPrice = 1000;
			break;

		case "2":
			lowerPrice = 1000;
			highPrice = 5000;
			break;

		case "3":
			lowerPrice = 5000;
			highPrice = 10000;
			break;

		case "4":
			lowerPrice = 10000;
			highPrice = 99999999;
			break;
		}

	}

	/*WHERE文作成*/
	private String whereSet(String productName, String category, String priceBetweenCommand, String productStatus) {

		String sql = "";

		priceBetween(priceBetweenCommand);

		if (!(productName == null) && !(productName.isEmpty())) {

			sql += " AND p.product_name LIKE :product_name ";

		}

		if (!(category == null) && !(category.isEmpty())) {

			sql += " AND category_id = :categoryId ";

		}

		if (!(priceBetweenCommand == null) && !(priceBetweenCommand.isEmpty())) {

			sql += " AND p.price BETWEEN :lowerPrice AND :highPrice ";
		}

		if (!(productStatus == null) && !(productStatus.isEmpty())) {

			sql += " AND p.product_status = :productStatus ";

		}

		return sql;

	}

}
