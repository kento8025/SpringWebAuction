package jp.co.webAuction.db.entity.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.entity.MenuDao;

@Repository
public class PgMenuDao implements MenuDao {

	private final String UPDETA_USERS = "UPDATE users SET user_id = :user_id, password = :password , user_name = :user_name , birthday = :birthday , man_or_woman = :man_or_woman ,  mail = :mail "
			+ "WHERE id = :id ";

	private final String SELECT_PRODUCT_SUCCESSFUL_DID = "" +
			"SELECT p.product_name , p.id ,  p.user_id ,p.product_name ,p.product_img ,p.category_id , p.product_status , "
			+
			"p.description , p.postage , p.shipping_origin , p.shipping_method  , p.exhibition_period ,p.should_show , p.Registration_dete ,"
			+
			"CASE " +
			"WHEN MAX(s.contract_price)  IS NULL THEN  MAX(p.price) " +
			"ELSE MAX(s.contract_price) " +
			"END  AS  price  " +
			"FROM  product  as  p " +
			"LEFT JOIN successful_bid s ON p.id = s.product_id " +
			"LEFT JOIN users u ON  u.id = s.user_id " +
			"WHERE p.should_show = 1 AND s.user_id =:id AND trade_status = 1 ";

	private final String SELECT_PRODUCT_SUCCESSFUL_DID_HISTORY = "" +
			"SELECT p.product_name , p.id ,  p.user_id ,p.product_name ,p.product_img ,p.category_id , p.product_status , "
			+
			"p.description , p.postage , p.shipping_origin , p.shipping_method  , p.exhibition_period ,p.should_show , p.Registration_dete , "
			+
			"CASE " +
			"WHEN MAX(s.contract_price)  IS NULL THEN  MAX(p.price) " +
			"ELSE MAX(s.contract_price)  " +
			"END  AS  price   " +
			"FROM  product  as  p  " +
			"LEFT JOIN successful_bid s ON p.id = s.product_id  " +
			"LEFT JOIN users u ON  u.id = s.user_id  " +
			"WHERE p.should_show = 3 AND s.user_id = :id AND trade_status = 3 ";

	private final String SELECT_PRODUCT_EXHIBITION = "" +
			"SELECT * " +
			"FROM ( " +
			"SELECT p.product_name , p.id ,  p.user_id ,p.product_name ,p.product_img ,p.category_id , p.product_status , "
			+
			"p.description , p.postage , p.shipping_origin , p.shipping_method  , p.exhibition_period ,p.should_show , p.Registration_dete , s.trade_status , "
			+
			"CASE " +
			"WHEN MAX(s.contract_price)  IS NULL THEN  MAX(p.price)  " +
			"ELSE MAX(s.contract_price)  " +
			"END AS price " +
			"FROM product as p " +
			"LEFT JOIN successful_bid s ON p.id = s.product_id  " +
			"LEFT JOIN users u ON  u.id = s.user_id  " +
			"LEFT JOIN category c ON  c.id = p.category_id  " +
			"GROUP BY p.product_name , p.id ,  p.user_id ,p.product_name ,p.product_img ,p.category_id , p.product_status , "
			+
			"p.description , p.postage , p.shipping_origin , p.shipping_method  , p.exhibition_period ,p.should_show , p.Registration_dete , s.trade_status "
			+
			" ) as åãçá " +
			" WHERE should_show = 1 AND user_id =:id AND (trade_status IS NULL OR trade_status = 1) ";

	private final String SELECT_PRODUCT_EXHIBITION_HISTORY = "" +
			"SELECT p.product_name , p.id ,  p.user_id ,p.product_name ,p.product_img ,p.category_id , p.product_status ,  "
			+
			"p.description , p.postage , p.shipping_origin , p.shipping_method  , p.exhibition_period ,p.should_show , p.Registration_dete , "
			+
			"CASE  " +
			"WHEN MAX(s.contract_price)  IS NULL THEN  MAX(p.price)  " +
			"ELSE MAX(s.contract_price)  " +
			"END  AS  price   " +
			"FROM  product  as  p  " +
			"LEFT JOIN successful_bid s ON p.id = s.product_id  " +
			"LEFT JOIN users u ON  u.id = s.user_id  " +
			"WHERE p.should_show = 3 AND p.user_id = :id AND trade_status = 3  ";

	private final String SELECT_PRODUCT_GROUP_BY = "GROUP BY p.product_name , p.id ,  p.user_id ,p.product_name ,p.product_img ,p.category_id , p.product_status , "
			+
			"p.description , p.postage , p.shipping_origin , p.shipping_method  , p.exhibition_period ,p.should_show , p.Registration_dete ";

	private final String SELECT_CATEGORY = "SELECT * FROM category";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void userUpdate(UserForm user, int id) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("user_id", user.getUserId());
		param.addValue("password", user.getPassWord());
		param.addValue("user_name", user.getUserName());
		param.addValue("birthday", user.getBirthday());
		param.addValue("man_or_woman", user.getManOrWoman());
		param.addValue("mail", user.getMail());
		param.addValue("id", id);

		jdbcTemplate.update(UPDETA_USERS, param);

	}

	//óééD
	@Override
	public List<Product> productSuccessfulDid(int userId) {

		System.out.println(userId);

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("id", userId);

		String sql = SELECT_PRODUCT_SUCCESSFUL_DID + SELECT_PRODUCT_GROUP_BY;

		return jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<Product>(Product.class));
	}

	//óééDóöó

	@Override
	public List<Product> productSuccessfulDidHistory(int userId) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("id", userId);

		String sql = SELECT_PRODUCT_SUCCESSFUL_DID_HISTORY + SELECT_PRODUCT_GROUP_BY;

		return jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<Product>(Product.class));

	}

	//èoïi
	@Override
	public List<Product> productExhibition(int userId) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("id", userId);

		String sql = SELECT_PRODUCT_EXHIBITION;

		return jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<Product>(Product.class));

	}

	//èoïióöó
	@Override
	public List<Product> productExhibitionHistory(int userId) {

		System.out.println(userId);

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("id", userId);

		String sql = SELECT_PRODUCT_EXHIBITION_HISTORY + SELECT_PRODUCT_GROUP_BY;

		return jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<Product>(Product.class));
	}

	//ÉJÉeÉSÉäåüçı
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
