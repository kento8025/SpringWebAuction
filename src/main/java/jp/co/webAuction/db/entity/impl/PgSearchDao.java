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


	private final String SELECT_PRODUCT =  "" +

			"SELECT p.product_name , p.id ,  p.user_id ,p.product_name ,p.product_img ,p.category_id , p.product_status , " +
			"p.description , p.postage , p.shipping_origin , p.shipping_method  , p.exhibition_period ,p.should_show , p.Registration_dete , s.trade_status , " +
			"CASE " +
			"WHEN MAX(s.contract_price)  IS NULL THEN  MAX(p.price)  " +
			"ELSE MAX(s.contract_price)  " +
			"END AS price " +
			"FROM product as p " +
			"LEFT JOIN successful_bid s ON p.id = s.product_id  " +
			"LEFT JOIN users u ON  u.id = s.user_id  " +
			"LEFT JOIN category c ON  c.id = p.category_id  " +
			"WHERE (trade_status IS NULL OR trade_status = 1) AND should_show = 1 "+
			"GROUP BY  p.id , s.trade_status ";

			/*"WHERE 1 = 1 AND p.product_name = :product_name  "+*/

	private final String SELECT_FROM_PRODUCT_AND_USERS_AND_CATEGORY = ""+
			"SELECT p.id as primaryProductId , p.user_id as primaryUserId , MAX (s.id) as trade ,  p.user_id as seller , s.user_id as buyer ,p.product_name  , p.product_name ,p.product_img ,p.category_id , p.product_status ,  " +
			"p.description , p.postage , p.shipping_origin , p.shipping_method  , p.exhibition_period ,p.should_show , p.Registration_dete ,  s.trade_status  ," +
			"CASE " +
			"WHEN MAX(s.contract_price)  IS NULL THEN  MAX(p.price) " +
			"ELSE MAX(s.contract_price) " +
			"END AS price " +
			"FROM product as p " +
			"LEFT JOIN successful_bid s ON p.id = s.product_id " +
			"LEFT JOIN users u ON  u.id = s.user_id " +
			"LEFT JOIN category c ON  c.id = p.category_id  "+
			"WHERE p.id = :p.id  AND (trade_status IS NULL OR trade_status = 1) AND should_show = 1 "  +
			"GROUP BY p.id , s.trade_status , s.id "+
			"ORDER BY price ASC  ";


	//private  String WHERE_SET =  " WHERE should_show = 1  ";


	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Product> productSearch(String productName) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		Product product = new Product();
		product.setProductName(productName);
		searchSet(productName);

		String sql = SELECT_PRODUCT  ;

		param.addValue("product_name", product.getProductName());

		return jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<Product>(Product.class));

	}

	@Override
	public PurchaseDisplay productInformation(int productId) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		String sql = SELECT_FROM_PRODUCT_AND_USERS_AND_CATEGORY ;

		param.addValue("p.id", productId);

		List<PurchaseDisplay> purchaseDisplay =  new ArrayList<>();

		purchaseDisplay = jdbcTemplate.query(
		sql,
		param,
		new BeanPropertyRowMapper<PurchaseDisplay>(PurchaseDisplay.class));

		return purchaseDisplay.get(purchaseDisplay.size()-1);


	}


	private void searchSet(String productName) {

		String sql = "";

		if (!(productName == null || productName.isEmpty())) {
			//sql += " AND " + "product_name LIKE = :" + "%" + "product_name+"+ "%";
			sql += " AND product_name  = :product_name ";

		}

		//this.where_set = sql;

	}

}
