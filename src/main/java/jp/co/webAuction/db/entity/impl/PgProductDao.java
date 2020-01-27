package jp.co.webAuction.db.entity.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.ProductDao;

@Repository
public class PgProductDao implements ProductDao {

	private final String SELECT_CATEGORY = "SELECT * FROM category WHERE "
			+ "category_name = :category_name";
	private final String INSERT_INTO_PRODUCT = "INSERT INTO product (user_id , product_name ,product_img ,category_id , product_status , description , postage , shipping_origin , shipping_method , price , exhibition_period , should_show , Registration_dete) VALUES "
			+ "(:user_id , :product_name , :product_img , :category_id , :product_status , :description , :postage , :shipping_origin , :shipping_method ,:price , :exhibition_period ,1,now())";

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void register(ProductForm productForm, User user) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("user_id", user.getId());
		param.addValue("product_name", productForm.getProductName());
		param.addValue("product_img", productForm.getProductImg());
		param.addValue("category_id", categorySearch(productForm.getCategoryName()));
		param.addValue("product_status", productForm.getProductStatus());
		param.addValue("description", productForm.getDescription());
		param.addValue("postage", productForm.getPostage());
		param.addValue("shipping_origin", productForm.getShippingOrigin());
		param.addValue("shipping_method", productForm.getShipping_method());
		param.addValue("price", productForm.getPrice());
		param.addValue("exhibition_period", productForm.getExhibition_period());

		jdbcTemplate.update(INSERT_INTO_PRODUCT, param);

	}

	@Override
	public int categorySearch(String categoryName) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("category_name", categoryName);

		List<Category> category = new ArrayList<>();

		category = jdbcTemplate.query(
				SELECT_CATEGORY,
				param,
				new BeanPropertyRowMapper<Category>(Category.class));

		if (!(category.size() == 0)) {

			return category.get(0).getId();
		}

		return 0;

	}

}
