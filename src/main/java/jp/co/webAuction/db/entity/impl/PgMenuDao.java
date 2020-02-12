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

	private final String CASE = " " +
			"CASE " +
			"WHEN MAX(s.contract_price)  IS NULL THEN  MAX(p.price)  " +
			"ELSE MAX(s.contract_price)  " +
			"END AS price " +
			"FROM product as p ";

	private final String JOIN = " " +
			"LEFT JOIN successful_bid s ON p.id = s.product_id  " +
			"LEFT JOIN category c ON  c.id = p.category_id  ";

	private final String WHERE = " " +
			"WHERE 1 = 1  ";

	private String GROUP_BY = " GROUP BY  p.id , s.trade_status , u.user_name  ";

	private String SBJOIN = "" +
			") sb1 " +
			"LEFT JOIN " +
			"(SELECT count(*) as measurement , p.id  as secondid  FROM successful_bid as s " +
			"LEFT JOIN product p ON s.product_id = p.id  " +
			"WHERE 1 = 1 " +
			"GROUP BY s.product_id , p.id , p.product_name) " +
			"sb2 " +
			"ON sb1.id = sb2.secondid";

	private String SBSBJOIN = " " +
			"LEFT JOIN " +
			"(SELECT p.id , " + 
			"CASE  " +
			"WHEN MAX(s.contract_price)  IS NULL THEN  MAX(p.price) " +
			"ELSE MAX(s.contract_price)  " +
			"END AS  highestbidprice  " +
			"FROM product as p  " +
			"LEFT JOIN successful_bid s ON p.id = s.product_id  " +
			"LEFT JOIN users u ON  u.id = s.user_id  " +
			"WHERE 1 = 1  AND (trade_status IS NULL OR trade_status = 1) AND should_show = 1  \r\n" +
			"GROUP BY  p.id , s.trade_status " +
			") as sb3 " +
			"ON sb2.secondid = sb3.id";

	private String ORDER_BY = " ORDER BY p.id DESC ";

	private final String UPDETA_USERS = "UPDATE users SET user_id = :user_id, password = :password , user_name = :user_name , birthday = :birthday , man_or_woman = :man_or_woman ,  mail = :mail "
			+ "WHERE id = :id ";

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

	//
	@Override
	public List<Product> menuSearch(int userId, String menuCommand) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		String sql = SELECT + SELECT_PRODUCT + CASE + JOIN + menuJoinCommand(menuCommand) + WHERE;

		sql += menuWhereCommand(menuCommand);

		sql += GROUP_BY + ORDER_BY + SBJOIN;

		if (menuCommand.equals("successfulDid")) {
			sql += SBSBJOIN;
		}

		param.addValue("userId", userId);

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

	private String menuWhereCommand(String menuCommand) {

		if (menuCommand == null) {
			return "";
		}

		String sql = "";

		switch (menuCommand) {

		case "successfulDid":
			sql += ""
					+ " AND (trade_status IS NULL OR trade_status = 1) AND should_show = 1  AND s.user_id =:userId ";
			break;

		case "productSuccessfulDidHistory":
			sql += ""
					+ " AND p.should_show = 3 AND s.user_id = :userId AND trade_status = 3";

			break;

		case "exhibition":
			sql += ""
					+ " AND p.should_show = 1 AND p.user_id =:userId AND (trade_status IS NULL OR trade_status = 1)";

			break;

		case "exhibitionHistory":
			sql += ""
					+ " AND p.should_show = 3 AND p.user_id = :userId AND trade_status = 3";

			break;

		}

		return sql;

	}

	private String menuJoinCommand(String menuCommand) {

		if (menuCommand == null) {
			return "";
		}

		String sql = "";

		switch (menuCommand) {

		case "successfulDid":
			sql += " "
					+ " LEFT JOIN users u ON  u.id = p.user_id  ";
			break;

		case "productSuccessfulDidHistory":
			sql += ""
					+ " LEFT JOIN users u ON  u.id = p.user_id";
			break;

		case "exhibition":
			sql += ""
					+ " LEFT JOIN users u ON  u.id = p.user_id  ";
			break;

		case "exhibitionHistory":
			sql += ""
					+ " LEFT JOIN users u ON  u.id = p.user_id ";
			break;

		}

		return sql;

	}

}
