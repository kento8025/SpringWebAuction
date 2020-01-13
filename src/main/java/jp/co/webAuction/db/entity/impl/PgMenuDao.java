package jp.co.webAuction.db.entity.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.entity.MenuDao;

@Repository
public class PgMenuDao implements MenuDao {

	private final String SELECT_PRODUCT_SUCCESSFUL_DID = "SELECT p.id ,p.user_id ,p.product_name ,p.product_img ,p.category_id , p.product_status,"
			+ "p.description , p.postage , p.shipping_origin , p.shipping_method , p.price , p.exhibition_period ,p.should_show , p.Registration_dete "
			+ " FROM successful_bid as s LEFT JOIN users u ON  u.id = s.user_id LEFT JOIN product p ON  p.id = s.product_id WHERE u.id = :u.id AND s.trade_status=1 GROUP BY p.id ,u.id";


	private final String SELECT_PRODUCT_EXHIBITION = "SELECT p.id ,p.user_id ,p.product_name ,p.product_img ,p.category_id , p.product_status,"
			+ "p.description , p.postage , p.shipping_origin , p.shipping_method , p.price , p.exhibition_period ,p.should_show , p.Registration_dete "
			+ " FROM successful_bid as s LEFT JOIN users u ON  u.id = s.user_id LEFT JOIN product p ON  p.id = s.product_id WHERE u.id = :u.id GROUP BY p.id ,u.id";


	private final String SELECT_CATEGORY = "SELECT * FROM category";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Product> productSuccessfulDid(int userId) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("u.id", userId);

		String sql = SELECT_PRODUCT_SUCCESSFUL_DID;

		return jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<Product>(Product.class));
	}

	@Override
	public List<Product> productExhibition(int userId) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("u.id", userId);

		String sql = SELECT_PRODUCT_EXHIBITION;

		return jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<Product>(Product.class));

	}

	@Override
	public List<Category> categorySearch() {

		MapSqlParameterSource param = new MapSqlParameterSource();


		String sql = SELECT_CATEGORY;

		return jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<Category>(Category.class));
	}








}
