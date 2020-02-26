package jp.co.webAuction.db.entity.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.UserDao;

@Repository
public class PgUserDao implements UserDao {

	private User user;

	private final String INSERT_INTO_USERS = "INSERT INTO users (user_id,password,user_name,birthday,man_or_woman,mail, user_rank ,registration_date) "
			+ "VALUES (:user_id, :password , :user_name , :birthday , :man_or_woman , :mail ,2,now())";
	private final String SELECT_USER = "SELECT * FROM users WHERE user_id = :user_id AND password = :password ";
	private final String SELECT_USER_ID = "SELECT * FROM users WHERE user_id = :user_id ";

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public boolean userIdCheck(String userId) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("user_id", userId);

		List<User> user = new ArrayList<User>();

		user = jdbcTemplate.query(
				SELECT_USER_ID,
				param,
				new BeanPropertyRowMapper<User>(User.class));

		if (user.size() == 0) {

			return true;
		}

		return false;
	}

	@Override
	public void register(UserForm user) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("user_id", user.getUserId());
		param.addValue("password", user.getPassWord());
		param.addValue("user_name", user.getUserName());
		param.addValue("birthday", user.getBirthday());
		param.addValue("man_or_woman", user.getManOrWoman());
		param.addValue("mail", user.getMail());

		jdbcTemplate.update(INSERT_INTO_USERS, param);

	}

	@Override
	public boolean loginCheck(String userId, String password) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("user_id", userId);
		param.addValue("password", password);

		System.out.println(userId + "  " + password);

		List<User> user = new ArrayList<User>();

		user = jdbcTemplate.query(
				SELECT_USER,
				param,
				new BeanPropertyRowMapper<User>(User.class));

		System.out.println(user.size());

		if (!(user.size() == 0)) {

			this.user = user.get(0);

			return true;
		}

		return false;

	}

	public User getUser() {
		return user;
	}

}
