package jp.co.webAuction.db.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.webAuction.db.entity.AdminDao;

@Repository
public class PgAdminDao implements AdminDao {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void delete(int id) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		String sql = "DELETE FROM Product WHERE id = :id" ;

		param.addValue("id", id);

		jdbcTemplate.update(sql, param);
	}

	@Override
	public void hidden(int id) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		String sql = "UPDATE Product SET should_show = 4 WHERE id = :id";

		param.addValue("id", id);

		jdbcTemplate.update(sql, param);

	}

	@Override
	public void hiddenCancel(int id) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		String sql = "UPDATE Product SET should_show = 1 WHERE id = :id ";

		param.addValue("id", id);

		jdbcTemplate.update(sql, param);

	}

}
