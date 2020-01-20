package jp.co.webAuction.db.entity.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.webAuction.controller.form.TradeForm;
import jp.co.webAuction.db.dto.SuccessfulDid;
import jp.co.webAuction.db.entity.TradeDao;

@Repository
public class PgTradeDao implements TradeDao {

	private final String INSERT_SUSCCEFUL_DID = "INSERT INTO successful_bid (product_id , user_id , contract_price , trade_status , trade_dete) "
			+ "VALUES(:product_id , :user_id , :contract_price , :trade_status ,now())";

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void register(TradeForm tradeForm) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("product_id", tradeForm.getProductId());
		param.addValue("user_id", tradeForm.getUserId());
		param.addValue("contract_price", tradeForm.getContractPrice());
		param.addValue("trade_status", 1);

		jdbcTemplate.update(INSERT_SUSCCEFUL_DID, param);

	}

	@Override
	public void productHidden(int productId) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		String sql = "UPDATE Product SET should_show = 2 WHERE id = :id";

		param.addValue("id", productId);

		jdbcTemplate.update(sql, param);

	}

	@Override
	public void tradeCancel(int id) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		String sql = "UPDATE successful_bid SET trade_status = 2 WHERE id = :id";

		param.addValue("id", id);

		jdbcTemplate.update(sql, param);


	}

	@Override
	public void promptDecision(Integer productId) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		String sql = "UPDATE Product SET should_show = 3  WHERE id = :productId  ";
		param.addValue("productId", productId);
		jdbcTemplate.update(sql, param);


		sql = "SELECT *  FROM successful_bid  WHERE trade_status = 1 AND product_id = :productId  GROUP BY id ORDER BY contract_price DESC";
		param.addValue("productId", productId);

		List<SuccessfulDid> successfulDid =  new ArrayList<>();

		successfulDid = jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<SuccessfulDid>(SuccessfulDid.class));

		sql = "UPDATE successful_bid SET trade_status = 3 WHERE product_id = :productId AND id = :tradeId";

		param.addValue("productId", productId);
		param.addValue("tradeId", successfulDid.get(0).getId());

		jdbcTemplate.update(sql, param);

	}

}
